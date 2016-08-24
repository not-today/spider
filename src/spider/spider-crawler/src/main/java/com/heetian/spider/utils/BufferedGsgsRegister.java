package com.heetian.spider.utils;

import com.heetian.spider.dbcp.bean.GsgsRegister;
import com.heetian.spider.dbcp.bean.ProxyStatus;

public class BufferedGsgsRegister {
	private ProxyStatus status = ProxyStatus.YSE;
	private GsgsRegister gsgsRegister;

	public GsgsRegister getGsgsRegister() {
		return gsgsRegister;
	}

	public void setGsgsRegister(GsgsRegister gsgsRegister) {
		this.gsgsRegister = gsgsRegister;
	}
	public void fail(){
		this.status = ProxyStatus.NO;
	}

	public ProxyStatus getStatus() {
		return status;
	}
}
