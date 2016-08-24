package com.heetian.spider.ocr.exception;

public class ImageConverte extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ImageConverte(){}
	public ImageConverte(Throwable exception){
		super(exception);
	}
	public ImageConverte(String msg){
		super(msg);
	}
	
}
