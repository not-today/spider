package com.heetian.spider.seed;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.selector.Html;
/**
 * "http://www.58.com/changecity.aspx", "UTF-8", 5 参数模版
 * @author tst
 *
 */
public class SeedTC58 extends SeedPageProcess {
	public static void main(String[] args) {
		new SeedTC58("http://www.58.com/changecity.aspx", "UTF-8", 5,"1").run();
	}
	public SeedTC58(String url, String character, int threadNumer,String step) {
		super(url,character,threadNumer,step);
	}
	public SeedTC58(String url, String character) {
		super(url,character,0,null);
	}
	public SeedTC58(String url, int threadNumer) {
		super(url,null,threadNumer,null);
	}
	public SeedTC58(String url) {
		super(url,null,0,null);
	}
	@Override
	public void process(Page page) {
		String key = (String) page.getRequest().getExtra("key");
		if ("1".equals(key)) {
			List<String> citys = page.getHtml().xpath("//dl[@id='clist']/dd/a//@href").replace("\\s", "").all();
			if (citys != null && citys.size() > 0)
				for (String city : citys)
					page.getTargetRequests().add(httpGET(city + "/job.shtml", "2"));
		} else if ("2".equals(key)) {
			List<String> urlseeds = new ArrayList<String>();
			List<String> contentUrls = page.getHtml().xpath("//div[@id='posExp']/dl//a/@href").all();
			if (contentUrls != null && contentUrls.size() > 0) {
				for (String contentUrl : contentUrls) {
					if (contentUrl.lastIndexOf('/') == -1)
						contentUrl = contentUrl + "/pn1/";
					else
						contentUrl = contentUrl + "pn1/";
					if (!contentUrl.contains("http"))
						contentUrl = page.getRequest().getUrl() + contentUrl;
					Request request = httpGET(contentUrl, "4");
					request.putExtra("number", 1);
					// page.getTargetRequests().add(request);
					urlseeds.add(contentUrl);
				}
			}
			saveSeedToDB(urlseeds, 3);
		} else if ("4".equals(key)) {
			List<String> contents = page.getHtml().xpath("//div[@id='infolist']/dl").all();
			if (contents != null && contents.size() > 0) {
				String url = page.getRequest().getUrl();
				int number = (int) page.getRequest().getExtra("number");
				url = url.substring(0, url.indexOf("/pn" + number) + 3) + (number + 1) + "/";
				Request request = httpGET(url, "4");
				request.putExtra("number", number + 1);
				page.getTargetRequests().add(request);
				Set<String> positions = new HashSet<String>();
				Set<String> entNames = new HashSet<String>();
				for (String content : contents) {
					Html html = new Html(content);
					String position = html.xpath("//dt/a[1]/allText()").replace("\\s", "").replace("[（）\\(\\)、\\-——，,【】！!\\+]", "/").get();
					if (position != null && !"".equals(position)) {
						if (position.contains("/")) {
							String tmps[] = position.split("/");
							for (String tmp : tmps) {
								if (!"".equals(tmp.trim()))
									positions.add(tmp);
							}
						} else {
							positions.add(position);
						}
					}
					String entName = html.xpath("//dd[@class='w271']/a/@title").replace("\\s", "").get();
					if (entName == null || "".equals(entName))
						entName = html.xpath("//dd[@class='w271']/a/allText()").replace("\\s", "").get();
					if (entName != null && !"".equals(entName))
						entNames.add(entName);
				}
				saveSeedToDB(new ArrayList<String>(positions), 2);
				saveSeedToDB(new ArrayList<String>(entNames), 1);
			}
		}
	}
}