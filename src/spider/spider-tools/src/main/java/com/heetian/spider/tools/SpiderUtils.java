package com.heetian.spider.tools;

import java.util.List;

public class SpiderUtils {
	public static boolean checkArguForStr(String arg)throws IllegalArgumentException{
		if(arg==null||"".equals(arg.trim()))
			return false;
		return true;
	}
	public static boolean checkArguForObj(Object arg)throws IllegalArgumentException{
		if(arg==null)
			return false;
		return true;
	}
	public static <T> boolean checkArguForList(List<T> arg)throws IllegalArgumentException{
		if(arg==null||arg.size()<=0)
			return false;
		return true;
	}
}
