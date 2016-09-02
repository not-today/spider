package com.heetian.spider.process.shanghai;

import java.util.Iterator;
import java.util.List;

import com.heetian.spider.component.EnterUrls;
import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.ShangHaiProcessHandlePrepare;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
/**
 * 
 * @author tst
 *
 */
public class CompanyURLProcess extends ShangHaiProcessHandlePrepare{
	public CompanyURLProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri(EnterUrls.SHCom);
		setProcessName(processName_list);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		/*String nextPage_hebei = (String) page.getRequest().getExtra("nextPage_hebei");
		if(!"nextPage_hebei".equals(nextPage_hebei)){
			String maxNumber = page.getHtml().regex("(\\s*page\\s*:\\s*\")(\\d+)(\"\\s*,\\s*)",2).replace("\\s", "").get();
			if(maxNumber!=null&&maxNumber.matches("\\d*")){
				int max = Integer.parseInt(maxNumber);
				NameValuePair[] nvps = (NameValuePair[]) page.getRequest().getExtra(NAMEVALUEPAIR);
				int currentPage = Integer.parseInt(nvps[0].getValue());
				if(currentPage<max){
					for(int x=currentPage+1;x<=max;x++){
						NameValuePair[] nvpsTmp = new NameValuePair[nvps.length];
						for(int y=0;y<nvps.length;y++){
							if(nvps[y].getName().equals("condition.pageNo")){
								nvpsTmp[y] = new BasicNameValuePair("condition.pageNo", String.valueOf(x));
							}else{
								nvpsTmp[y] = new BasicNameValuePair(nvps[y].getName(), nvps[y].getValue());
							}
						}
						Request request = builderRequest("https://www.sgs.gov.cn/notice/search/ent_info_list?"+urlTail(), Method.POST, null, null, nvpsTmp);
						request.putExtra("nextPage_hebei", "nextPage_hebei");
						page.addTargetRequest(request);
					}
				}
			}
		}*/
		List<String> hrefs = page.getHtml().xpath("//div[@class='list-info']/div[@class='list-item']/div[@class='link']/a/@href").all();
		if(hrefs==null||hrefs.size()<=0){
			return;
		}
		Iterator<String> iter = hrefs.iterator();
		while(iter.hasNext()){
			Request request = builderRequestGet(iter.next());
			//request.putExtra(NAMEVALUEPAIR, page.getRequest().getExtra(NAMEVALUEPAIR));
			page.addTargetRequest(request);
		}
		((TSTPageProcessor)task).setSeedSdP(((TSTPageProcessor)task).getSeedSdP()+hrefs.size());
	}
}
