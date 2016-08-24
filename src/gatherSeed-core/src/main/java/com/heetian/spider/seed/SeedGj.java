package com.heetian.spider.seed;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.selector.Html;
/**
 * "http://www.ganji.com/index.htm","UTF-8",2  参数模版
 * @author tst
 *
 */
public class SeedGj extends SeedPageProcess{
	public static void main(String[] args) {
		new SeedGj("http://www.ganji.com/index.htm","UTF-8",2,"1").run();
	}
	public SeedGj(String url, String character, int threadNumer,String step) {
		super(url,character,threadNumer,step);
	}
	public SeedGj(String url, String character) {
		super(url,character,0,null);
	}
	public SeedGj(String url, int threadNumer) {
		super(url,null,threadNumer,null);
	}
	public SeedGj(String url) {
		super(url,null,0,null);
	}
	@Override
	public void process(Page page) {
		String key = (String) page.getRequest().getExtra("key");
		if("1".equals(key)){
			List<String> citys = page.getHtml().xpath("//div[@class='all-city']/dl/dd/a//@href").all();
			if(citys!=null&&citys.size()>0){
				for(String city:citys){
					page.getTargetRequests().add(httpGET(city+"zhaopin/", "2"));
				}
			}
		}else if("2".equals(key)){
			List<String> urls = page.getHtml().xpath("//div[@class='f-all-news']/dl//a/@href").all();
			if(urls!=null&&urls.size()>0){
				for(String url:urls){
					if(url.lastIndexOf('/')==-1)
						url = url+"/o1/";
					else
						url = url+"o1/";
					if(!url.contains("http"))
						url = page.getRequest().getUrl()+url;
					Request request = httpGET(url, "3");
					request.putExtra("number", 1);
					page.getTargetRequests().add(request);
				}
			}
		}else if("3".equals(key)){
			List<String> contents = page.getHtml().xpath("//div[@id='list-job-id']/div/dl").all();
			if(contents!=null&&contents.size()>0){
				String url = page.getRequest().getUrl();
				int number = (int) page.getRequest().getExtra("number");
				Request request = httpGET(url.substring(0, url.indexOf("/o"+number)+2)+(number+1)+"/", "3");
				request.putExtra("number", number+1);
				page.getTargetRequests().add(request);
				
				Set<String> positions = new HashSet<String>();
				Set<String> entNames = new HashSet<String>();
				for(String content:contents){
					Html html = new Html(content);
					String position = html.xpath("//dt/a[1]/allText()").replace("\\s", "").replace(reg, rep).get();
					if(position!=null&&!"".equals(position)){
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
					}
					String entName = html.xpath("//dd[@class='company']/a/@title").replace("\\s", "").get();
					if(entName==null||"".equals(entName))
						entName = html.xpath("//dd[@class='company']/a/allText()").replace("\\s", "").get();
					if(entName!=null&&!"".equals(entName))
						entNames.add(entName);
				}
				saveSeedToDB(new ArrayList<String>(positions),2);
				saveSeedToDB(new ArrayList<String>(entNames),1);
			}
		}
	}
}