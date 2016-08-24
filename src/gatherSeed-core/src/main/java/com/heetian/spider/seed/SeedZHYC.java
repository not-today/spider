package com.heetian.spider.seed;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.selector.Html;
/**
 * ""http://www.chinahr.com/changsha/jobs/", "UTF-8", 5,"1" 参数模版
 * @author tst
 *
 */
public class SeedZHYC extends SeedPageProcess {
	public static void main(String[] args) {
		new SeedZHYC("http://www.chinahr.com/changsha/jobs/", "UTF-8", 5,"1").run();
	}
	public SeedZHYC(String url, String character, int threadNumer,String step) {
		super(url,character,threadNumer,step);
	}
	public SeedZHYC(String url, String character) {
		super(url,character,0,null);
	}
	public SeedZHYC(String url, int threadNumer) {
		super(url,null,threadNumer,null);
	}
	public SeedZHYC(String url) {
		super(url,null,0,null);
	}
	@Override
	public void process(Page page) {
		String key = (String) page.getRequest().getExtra("key");
		if(page.getStatusCode()!=200){
			logger.warn("status["+page.getStatusCode()+"] !200 error:"+page.getRequest().getUrl());
			page.addTargetRequest(httpGET(page.getRequest().getUrl(), key));
			return;
		}
		if ("1".equals(key)) {
			List<String> urls = page.getHtml().xpath("//div[@class='main']/div[@class='class-job']//a/@href").replace("\\s", "").all();
			if (urls != null && urls.size() > 0){
				for (String url : urls){
					page.getTargetRequests().add(httpGET(url, "2"));
				}
			}
		}else if ("2".equals(key)) {
			List<String> urls = page.getHtml().xpath("//div[@class='pageList']//a").all();
			if(urls!=null&&urls.size()>0){
				for(String href:urls){
					Html html = new Html(href);
					String content = html.xpath("//a/text()").get();
					if(content!=null&&content.contains("下一页")){
						String url = html.xpath("//a/@href").get();
						if(url!=null&&!"".equals(url)){
							Request request = httpGET(url, "2");
							page.addTargetRequest(request);
						}
					}
				}
			}else{
				logger.warn("no next urls of entName.................................."+page.getRequest().getUrl());
			}
			List<String> lis = page.getHtml().xpath("//section[@id='searchList']/div[@class='resultList']/div/ul/li[1]").all();
			if(lis==null||lis.size()<=0){
				lis = page.getHtml().xpath("//div[@id='searchList']/div[@class='resultList']/div/ul/li[1]").all();
			}
			if(lis!=null&&lis.size()>0){
				Set<String> entNames = new TreeSet<String>();
				Set<String> positions = new TreeSet<String>();
				for(String li:lis){
					Html html = new Html(li);
					String position = html.xpath("//li/span[1]/a/allText()").replace("\\s", "").replace(reg, rep).get();
					String entName = html.xpath("//li/span[3]/a/allText()").replace("\\s", "").get();
					if(position!=null&&!"".equals(position)){
						if(position.contains(rep)){
							String tmps[] = position.split(rep);
							for(String tmp:tmps){
								if(!"".equals(tmp.trim()))
									positions.add(tmp);
							}
						}else{
							positions.add(position);
						}
					}
					if(entName!=null&&!"".equals(entName))
						entNames.add(entName);
				}
				saveSeedToDB(new ArrayList<String>(positions),2);
				saveSeedToDB(new ArrayList<String>(entNames),1);
			}else{
				logger.warn("no data of entName.................................."+page.getRequest().getUrl());
			}
		}
	}
}