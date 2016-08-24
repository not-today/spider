package com.heetian.spider.component;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Spider;

public class SpiderBuffer {
	private String pvn;
	private List<Spider> container = new ArrayList<Spider>();
	
	public SpiderBuffer(String pvn) {
		super();
		this.pvn = pvn;
	}
	public String getPvn() {
		return pvn;
	}
	public void setPvn(String pvn) {
		this.pvn = pvn;
	}
	public void add(Spider spider){
		this.container.add(spider);
	}
}
