package com.heetian.spider.tools;

public class TstUtils {
	public static boolean checkSTR(String str){
		if(str==null)
			return false;
		if("".equals(str.replaceAll("\\s*", ""))){
			return false;
		}
		return true;
	}
	public static void main(String[] args) {
		System.out.println(checkSTR("   "));
	}
}
