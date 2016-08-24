package com.heetian.spider.peking.strategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.heetian.spider.ocr.exception.ImageConverte;

public class CallUtil {
	public static final String laguage_chi = "chi_sim";
	public static final String laguage_eng = "eng";
	/**
	 * 是否保存处理后的验证码图片,true是保存
	 */
	private static final boolean isSaveFile = false;
	private static final boolean isSaveResult = false;
	public static String callTesseract(String picName,String laguage) throws ImageConverte {
		File file = new File(picName);
		if(!file.exists()){
			throw new ImageConverte("验证码图片["+picName+"]不存在");
		}
		ProcessBuilder pb = new ProcessBuilder(new String[]{"tesseract",picName,picName,"-l",laguage,"-psm","7"});
		//pb.directory(new File(workDir));
		pb.redirectErrorStream(true);
		BufferedReader reader = null;
		Process p = null;
		int result = 0;
		File saveDirFile = new File(picName+".txt");
		try {
			p = pb.start();
			result = p.waitFor();
			if(result==0){
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(saveDirFile),"utf-8"));
				String len = null;
				StringBuffer sb = new StringBuffer();
				while((len=reader.readLine())!=null){
					sb.append(len);
				}
				return sb.toString();
			}
			throw new ImageConverte("调用tesseract状态码非0");
		}catch (Exception e) {
			throw new ImageConverte("执行tesseract出错:"+e.getMessage());
		}finally{
			if(p!=null)
				p.destroy();
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(!isSaveFile)
				if(file!=null&&file.exists())
					file.delete();
			if(!isSaveResult)
				if(saveDirFile!=null&&saveDirFile.exists())
					saveDirFile.delete();
		}
	}
}
