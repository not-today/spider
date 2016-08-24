package com.heetian.spider.seed;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.selector.Selectable;
/**
 * 获取智联，赶集，58，51job页面信息关键字程序
 * @author tst
 *
 */
public class KeyWordGather {
	public static void main(String[] args) {
		zhiliao();
		ganji();
		tongcheng58();
		ganjizhuye();
		job51Key();
		System.out.println("hello");
	}
	public static void job51Key() {
		Spider spider = Spider.create(new SeedPageProcess(){
			@Override
			public void process(Page page) {
				Selectable div = page.getHtml().xpath("//div[@id='typeSearchTbl']");
				List<String> position = div.xpath("//table[@id='typeSearchTbl0']//tr//td//text()").replace("[（）、()，]","/").replace("[\\s]", "").all();
				List<String> hangye = div.xpath("//table[@id='typeSearchTbl1']//tr//td//a//text()").replace("[（）、()，]","/").replace("[\\s]", "").all();
				List<String> city = div.xpath("//table[@id='typeSearchTbl2']//tr//td//a//text()").replace("[（）、()，]","/").replace("[\\s]", "").all();
				position.addAll(hangye);
				position.addAll(city);
				toData(position);				
			}
			
		}).addUrl("http://search.51job.com/jobsearch/advance_search.php?stype=2").thread(1);
		spider.getSite().setCharset("gb2312");
		spider.start();
	}
	public static void zhiliao() {
		Spider spider = Spider.create(new SeedPageProcess(){
			@Override
			public void process(Page page) {
				Selectable div = page.getHtml().xpath("//div[@id='search_bottom_content_demo']");
				List<String> position = div.xpath("//p/a/text()").replace("[（）、()，]","/").replace("[\\s]", "").all();
				List<String> hangye = div.xpath("//h1/a//text()").replace("[（）、()，]","/").replace("[\\s]", "").all();
				position.addAll(hangye);
				toData(position);				
			}
			
		}).addUrl("http://sou.zhaopin.com/").thread(1);
		spider.getSite().setCharset("UTF-8");
		spider.start();
	}
	public static void ganji() {
		Spider spider = Spider.create(new SeedPageProcess(){
			@Override
			public void process(Page page) {
				Selectable div = page.getHtml().xpath("//div[@class='f-all-news']");
				List<String> position = div.xpath("//dl/dt/a/text()").replace("[（）、()，]","/").replace("[\\s]", "").all();
				List<String> hangye = div.xpath("//dl/dd/i/a//text()").replace("[（）、()，]","/").replace("[\\s]", "").all();
				position.addAll(hangye);
				toData(position);				
			}
			
		}).addUrl("http://sh.ganji.com/zhaopin/").thread(1);
		spider.getSite().setCharset("UTF-8");
		spider.start();
	}
	public static void ganjizhuye() {
		Spider spider = Spider.create(new SeedPageProcess(){
			@Override
			public void process(Page page) {
				Selectable div1 = page.getHtml().xpath("//div[@class='category']");
				Selectable div2 = page.getHtml().xpath("//div[@class='bbs-eara']");
				List<String> position = div1.xpath("//a/text()").replace("[（）、()，]","/").replace("[\\s]", "").all();
				List<String> hangye = div2.xpath("//a//text()").replace("[（）、()，]","/").replace("[\\s]", "").all();
				position.addAll(hangye);
				toData(position);
			}
		}).addUrl("http://sh.ganji.com/").thread(1);
		spider.getSite().setCharset("UTF-8");
		spider.start();
	}
	public static void tongcheng58() {
		Spider spider = Spider.create(new SeedPageProcess(){

			@Override
			public void process(Page page) {
				Selectable div = page.getHtml().xpath("//div[@id='posExp']");
				List<String> position = div.xpath("//dl/dt/a/text()").replace("[（）、()，]","/").replace("[\\s]", "").all();
				List<String> hangye = div.xpath("//dl/dd/a//text()").replace("[（）、()，]","/").replace("[\\s]", "").all();
				position.addAll(hangye);
				toData(position);
			}
			
		}).addUrl("http://cs.58.com/job.shtml").thread(1);
		spider.getSite().setCharset("UTF-8");
		spider.start();
	}
}