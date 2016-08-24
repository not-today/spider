package com.heetian.spider.proxy.utils;

import com.heetian.spider.proxy.dao.Manager;

public class CleanDataUtils {
	public static void main(String[] args) {
		Manager.dao.clearProxyAll();
	}
}
