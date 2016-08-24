package com.heetian.spider.seed;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.selector.Html;
/**
 * "http://cn.made-in-china.com/prod/catlist/", "GBK", 5 参数模版
 * @author tst
 *
 */
public class SeedHY88 extends SeedPageProcess {
	public static void main(String[] args) {
		new SeedHY88("http://www.huangye88.com/", "UTF-8", 1,"1").run();
	}
	public SeedHY88(String url, String character, int threadNumer,String step) {
		super(url,character,threadNumer,step);
	}
	public SeedHY88(String url, String character) {
		super(url,character,0,null);
	}
	public SeedHY88(String url, int threadNumer) {
		super(url,null,threadNumer,null);
	}
	public SeedHY88(String url) {
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
			List<String> urls = page.getHtml().xpath("//div[@class='main_L']/div[@class='main_box main_box1']/div[@class='dl_list clearfix']/dl/dt/a/@href").replace("\\s", "").all();
			if (urls != null && urls.size() > 0){
				
				int i = 0;
				
				for (String url : urls){
					if(i==0)
						page.getTargetRequests().add(httpGET(url, "2"));
					i=2;
				}
			}
		}else if ("2".equals(key)) {
			List<String> urls = page.getHtml().xpath("//div[@class='mach_list clearfix']/dl/dd/a/@href").replace("\\s", "").all();
			if(urls!=null&&urls.size()>0){
				
				int i = 0;
				
				for(String url:urls){
					if(i==0)
						page.getTargetRequests().add(httpGET(url, "3"));
					i=2;
				}
			}else{
				logger.warn(page.getRequest().getUrl()+":"+urls);
			}
		} else if ("3".equals(key)) {
			List<String> urls = page.getHtml().xpath("//div[@class='pages']//a").all();
			if(urls!=null&&urls.size()>0){
				for(String href:urls){
					Html html = new Html(href);
					String content = html.xpath("//a/text()").get();
					if(content!=null&&content.contains("下一页")){
						String url = html.xpath("//a/@href").get();
						if(url!=null&&!"".equals(url)){
							Request request = httpGET(url, "3");
							page.addTargetRequest(request);
						}
					}
				}
			}
			List<String> ps = page.getHtml().xpath("//form[@id='jubao']/ul/li/div[@class='n_r']/p[@class='gs_n gs_name']").all();
			if(ps!=null&&ps.size()>0){
				Set<String> entNames = new TreeSet<String>();
				Set<String> positions = new TreeSet<String>();
				for(String p:ps){
					Html html = new Html(p);
					String entName = html.xpath("//p/span[@class='names']/a/@title").replace("\\s", "").get();
					if(entName==null||"".equals(entName))
						entName = html.xpath("//p/span[@class='names']/a/allText()").replace("\\s", "").get();
					if(entName!=null&&!"".equals(entName))
						entNames.add(entName);
					String position = html.xpath("//p/span[@class='gjci']/allText()").replace("关键词：", "").replace("\\s", "").replace(reg, rep).get();
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
				}
				saveSeedToDB(new ArrayList<String>(entNames),1);
				saveSeedToDB(new ArrayList<String>(positions),2);
			}else{
				logger.warn("no data of entName.................................."+page.getRequest().getUrl());
			}
		}
	}
}