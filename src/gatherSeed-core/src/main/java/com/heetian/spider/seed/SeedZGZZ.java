package com.heetian.spider.seed;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.selector.Html;
/**
 * "http://cn.made-in-china.com/prod/catlist/", "GBK", 5 参数模版
 * @author tst
 *
 */
public class SeedZGZZ extends SeedPageProcess {
	public static void main(String[] args) {
		new SeedZGZZ("http://cn.made-in-china.com/prod/catlist/", "GBK", 10,"1").run();
	}
	public SeedZGZZ(String url, String character, int threadNumer,String step) {
		super(url,character,threadNumer,step);
	}
	public SeedZGZZ(String url, String character) {
		super(url,character,0,null);
	}
	public SeedZGZZ(String url, int threadNumer) {
		super(url,null,threadNumer,null);
	}
	public SeedZGZZ(String url) {
		super(url,null,0,null);
	}
	@Override
	public void process(Page page) {
		String key = (String) page.getRequest().getExtra("key");
		if ("1".equals(key)) {
			List<String> urls = page.getHtml().xpath("//div[@class='main-flex-bd']/div/dl/dt/a/@href").replace("\\s", "").all();
			if (urls != null && urls.size() > 0){
				for (String url : urls){
					//write(url, "file.copy");
					page.getTargetRequests().add(httpGET(url, "2"));
				}
			}
			//write("####################"+page.getRequest().getUrl()+"####################", "file.copy");
		}else if ("2".equals(key)) {
			//System.out.println(page.getRawText());
			List<String> urls = page.getHtml().xpath("//div[@id='catlist']/ul/li//a/@href").all();
			if(urls==null||urls.size()<=0){
				urls = page.getHtml().xpath("//div[@id='main']/div/ul/li/div[@class='item-cont']/div[@class='item-detail']/dl//a/@href").replace("\\s", "").all();
			}
			if(urls!=null&&urls.size()>0){
				for(String url:urls){
					//write(url, "file.copy");
					page.getTargetRequests().add(httpGET(url, "3"));
				}
				//write("----------------------------"+page.getRequest().getUrl()+"----------------------------", "file.copy");
			}else{
				logger.warn(page.getRequest().getUrl()+":"+urls);
			}
		} else if ("3".equals(key)) {
			List<String> urls = page.getHtml().xpath("//div[@id='pager']/div/a").all();
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
			List<String> contents = page.getHtml().xpath("//form[@id='inquiryForm']/li/div[2]/div/a").all();
			if(contents==null||contents.size()<=0){
				contents = page.getHtml().xpath("//form[@id='inquiryForm']/li/div[3]/div[2]/div[1]/a").all();
			}
			if (contents != null && contents.size() > 0) {
				Set<String> companies = new HashSet<String>();
				for(String content:contents){
					Html html = new Html(content);
					String entName = html.xpath("//a/@title").replace("\\s", "").get();
					if(entName==null||"".equals(entName))
						entName = html.xpath("//a/allText()").replace("\\s", "").get();
					if(entName!=null&&!"".equals(entName))
						companies.add(entName);
				}
				saveSeedToDB(new ArrayList<String>(companies), 1);
			}else{
				System.out.println(page.getError());
				logger.warn("no data of entName.................................."+page.getRequest().getUrl());
			}
		}
	}
}