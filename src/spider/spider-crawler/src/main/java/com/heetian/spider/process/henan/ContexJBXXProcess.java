package com.heetian.spider.process.henan;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsRegister;
import com.heetian.spider.process.abstractclass.HeNanProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForTable;
import com.heetian.spider.utils.TSTUtils;
/**
 * 
 * @author tst
 *
 */
public class ContexJBXXProcess extends HeNanProcessHandlePrepare {
	public ContexJBXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/businessPublicity.jspx");
		setProcessName(processName_containJbxx);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		TSTPageProcessor tst = ((TSTPageProcessor) task);
		String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
		String entName = (String) page.getRequest().getExtra(ENTNAME);
		List<String> jbTrs = page.getHtml().xpath("//div[@id='jibenxinxi']/table[1]//tr").all();
		GsgsRegister jbxxBean = AnalysisForTable.jbxxHTMLProcessToJAVAObject(regNumber, entName, jbTrs,tst);
		if(jbxxBean==null){
			return;
		}
		jbxxBean.setUrl(TSTUtils.bufferedURL(page.getRequest().getUrl(), httpMethodParam_get, null));
		
		Selectable dj = page.getHtml().xpath("//div[@id='jibenxinxi']");
		//股东信息
		String check = dj.xpath("//table[@id='touziren']").get();
		List<String> gdTrs = dj.xpath("//div[@id='invDiv']/table[@class='detailsList']//tr").all();
		if(check==null||!check.contains("合伙人信息")){
			jbxxBean.setShareholders(AnalysisForTable.gdxxHTMLToJObjSC(this,gdTrs, regNumber, entName, task.getSite(), page));
		}else{
			jbxxBean.setShareholders(AnalysisForTable.gdxxHTMLToJObj(this,gdTrs, regNumber, entName, task.getSite(), page));
		}
		//变更
		List<String> bgTrs = dj.xpath("//div[@id='altDiv']/table[@id='altTab']//tr").all();
		jbxxBean.setChanges(AnalysisForTable.bgxxHTMLProcessToJAVAObject(bgTrs, regNumber, page));
		Selectable ba = page.getHtml().xpath("//div[@id='beian']");
		//主要人员
		List<String> zyryTrs = ba.xpath("//div[@id='memDiv']/table[@class='detailsList']//tr").all();
		jbxxBean.setKeyPersons(AnalysisForTable.zyryxxHTMLProcessToJAVAObject(zyryTrs, regNumber, page));
		//分支机构
		List<String> fzjgTrs = ba.xpath("//div[@id='childDiv']/table[@class='detailsList']//tr").all();
		jbxxBean.setBranchs(AnalysisForTable.fzjgxxHTMLProcessToJAVAObject(fzjgTrs, regNumber, page));
		
		tst.setDataJB(jbxxBean,regNumber);
	}
	public void nextPage(Selectable total,Page page,Site site) {
		String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
		String entName = (String) page.getRequest().getExtra(ENTNAME);
		//对于分页处理//   goPage\\d
		List<String> aTags = total.xpath("//a/@href").regex("(goPage3\\s*\\(\\s*)(\"\\s*.+\\s*\"\\s*,\\s*\\d\\s*)(\\s*\\))",2).replace("\"", "").all();
		if(aTags!=null&aTags.size()>0){
			for(String aTag:aTags){
				String[] tmps = aTag.split(",");
				String param1 = tmps[0];
				String param2 = tmps[1];
				if(!"1".equals(param2)){
					Request tmp = page.getRequest();
					String url = goPage(param1,param2,(String)tmp.getExtra("mainID"));//上一个url的后面参数
					Request request = builderRequest(builderURL(url,site), Method.GET, regNumber, entName, null);
					request.putExtra("pageNumberFlag", aTags.toString());
					//还未完成
					
				}
			}
		}
	}
	private String goPage(String flag, String pageNumber,String mainId) {
        if ("mem".equals(flag)) {
            return "/QueryMemList.jspx?pno=" + pageNumber + "&mainId="+mainId/*, refreshMemList)*/;
        } else if ("child".equals(flag)) {
            return "/QueryChildList.jspx?pno=" + pageNumber + "&mainId="+mainId/*, refreshChildList)*/;
        }else if ("alt".equals(flag)) {
            return "/QueryAltList.jspx?pno=" + pageNumber + "&mainId="+mainId/*, refreshAltList)*/;
        }else if ("serill".equals(flag)) {
            return "/QuerySerillList.jspx?pno=" + pageNumber + "&mainId="+mainId/*, refreshSerillList)*/;
        }else if ("spotCheck".equals(flag)) {
            return "/QuerySpotCheckList.jspx?pno=" + pageNumber + "&mainId="+mainId/*, refreshSpotCheckList)*/;
        }else {
        	return "/QueryInvList.jspx?pno=" + pageNumber + "&mainId="+mainId/*, refreshInvList)*/;
        }
    }
}

