package com.heetian.spider.peking.pageProcessor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.http.HttpResponse;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.HttpClientDownloader;

import com.heetian.spider.ocr.exception.ImageConverte;
import com.heetian.spider.peking.strategy.Recognized;

public class DowaLoadCode extends HttpClientDownloader {
	public static final String formate_png = "png";
	@Override
	protected Page handleResponse(Request request, String charset,HttpResponse httpResponse, Task task) throws IOException{
		Page page = new Page();
		page.setRequest(request);
		File parent = new File(Recognized.SAVE_DIR);
		if(!parent.exists()){
			parent.mkdirs();
		}
		try{
			BufferedImage image = ImageIO.read(httpResponse.getEntity().getContent());
			if(image==null){
				throw new ImageConverte("从"+request.getUrl()+"获取的验证码转换成BufferedImage对象时，该对象转换出错，为null");
			} 
			String fileName = "";
			synchronized (DowaLoadCode.class) {
				fileName = CodePageProcess.x+"."+ formate_png;
				CodePageProcess.x++;
			}
			page.setRawText(fileName);
			if(!CodePageProcess.dest.exists())
				CodePageProcess.dest.mkdirs();
			ImageIO.write(image, formate_png, new File(CodePageProcess.dest,fileName));
		}catch(Exception e){
			page.setRawText("失败:"+e.getMessage());
		}
		return page;
	}
}
