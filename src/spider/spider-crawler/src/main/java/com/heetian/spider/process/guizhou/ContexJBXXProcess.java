package com.heetian.spider.process.guizhou;

import org.apache.http.NameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.component.EnterUrls;
import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsRegister;
import com.heetian.spider.process.abstractclass.GuiZhouProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForJson;
import com.heetian.spider.utils.TSTUtils;
/**
 * 
 * @author tst
 *
 */
public class ContexJBXXProcess extends GuiZhouProcessHandlePrepare {
	public ContexJBXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri(EnterUrls.GZAll);
		setProcessName(processName_containJbxx);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
		NameValuePair[] tmp =(NameValuePair[]) page.getRequest().getExtra(NAMEVALUEPAIR);
		String type = tmp[3].getValue();
		String result = page.getRawText();
		TSTPageProcessor tst = ((TSTPageProcessor) task);
		if("jb".equals(type)){
			JBBean bjbean = AnalysisForJson.jsonToObject(result,new TypeToken<JBBean>() {});
			NameValuePair nvps[] = (NameValuePair[]) page.getRequest().getExtra(NAMEVALUEPAIR);
			GsgsRegister jbxx = bjbean.getJb();
			jbxx.setUrl(TSTUtils.bufferedURL(page.getRequest().getUrl(), httpMethodParam_post, nvps));
			jbxx.setPvn(tst.getcode());
			tst.setDataJB(jbxx,regNumber);
		}else if("gd".equals(type)){
			GDBean gdbean = AnalysisForJson.jsonToObject(result,new TypeToken<GDBean>() {});
			String nbxh = tmp[0].getValue();
			tst.setDataGD(gdbean.getgd(nbxh,page,task.getSite(),ENTNAME,REGNUMBER,NAMEVALUEPAIR),regNumber);
		}else if("bg".equals(type)){
			BGBean bgbean = AnalysisForJson.jsonToObject(result,new TypeToken<BGBean>() {});
			tst.setDataBG(bgbean.getbg(regNumber),regNumber);
		}else if("zy".equals(type)){
			ZYRYBean zyrybean = AnalysisForJson.jsonToObject(result,new TypeToken<ZYRYBean>() {});
			tst.setDataZYRY(zyrybean.getzy(regNumber),regNumber);
		}else if("fz".equals(type)){
			FZJGBean fzjgbean = AnalysisForJson.jsonToObject(result,new TypeToken<FZJGBean>() {});
			tst.setDataFZJG(fzjgbean.getfz(regNumber),regNumber);
		}else{
			logger.error("此url没有对应处理标识符");
		}
	}
}

