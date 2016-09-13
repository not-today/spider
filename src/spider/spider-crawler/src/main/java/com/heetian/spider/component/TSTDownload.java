package com.heetian.spider.component;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.selector.PlainText;

import com.heetian.spider.enumeration.SeedStatus;
import com.heetian.spider.ocr.exception.ImageConverte;
import com.heetian.spider.peking.strategy.IsSucess;
import com.heetian.spider.peking.strategy.Recognized;
import com.heetian.spider.peking.strategy.RecognizedContext;
import com.heetian.spider.process.abstractclass.ProcessHandlePrepare;

public class TSTDownload extends HttpClientDownloader {
	private static Logger logger = LoggerFactory.getLogger(TSTDownload.class);
	private static final RecognizedContext recognized = RecognizedContext.newInstance();
	private static final int codeisNullofNumber = 5;
	private static final String  requestOFReNumber_KEY = "code_over";
	@Override
	protected Page handleResponse(Request request, String charset,HttpResponse httpResponse, Task task) throws IOException{
		String province = ((TSTPageProcessor)((Spider) task).getPageProcessor()).getProvince();
		ProcessHandlePrepare handleResponse = (ProcessHandlePrepare) ProcessHandlesManager.getProcessHandle(province);
		boolean flag = handleResponse.isDownloadCodeProcess()&&request.getUrl().contains(handleResponse.getUniqueWebUri());
		while(!flag){
			handleResponse = (ProcessHandlePrepare) handleResponse.getNextHandle();
			if(handleResponse==null)
				break;
			flag = handleResponse.isDownloadCodeProcess()&&request.getUrl().contains(handleResponse.getUniqueWebUri());
		}
		if(flag)
			return downloadCodeProcess(request,httpResponse,task,handleResponse);
		return super.handleResponse(request, charset, httpResponse, task);
	}
	
	private Page downloadCodeProcess(Request request,HttpResponse httpResponse,Task task,ProcessHandlePrepare handleResponse){
		Page page = new Page();
		String results[] = null;
		File parent = new File(Recognized.SAVE_DIR);
		if(!parent.exists()){
			parent.mkdirs();
		}
		try{
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			page.setStatusCode(statusCode);
			request.putExtra(Request.STATUS_CODE, statusCode);
			BufferedImage image = ImageIO.read(httpResponse.getEntity().getContent());
			if(image==null){
				throw new ImageConverte("从"+request.getUrl()+"获取的验证码转换成BufferedImage对象时，该对象转换出错，为null");
			}
			results = recognized.recognized(handleResponse.getCodeRecognized(),image, "png");
			page.setRawText(Arrays.toString(results));
		}catch(Exception e){
			page.setRawText(e.getMessage());
			logger.error(e.getMessage());
		}finally{
			if(results==null||results.length!=3)
				results = new String[]{null,"-1","-1"};
			if(results[0]==null||"".equals(results[0].replaceAll("\\s", ""))){
				Integer requestOFReNumber = request.getExtra(requestOFReNumber_KEY)==null?0:(Integer) request.getExtra(requestOFReNumber_KEY);
				if(requestOFReNumber<codeisNullofNumber){//此处用来防止代理ip或者本地ip出现错误，而导致验证码错误，而进入死循环
					Request reDownRequest = handleResponse.reloadCode(request,task.getSite());
					if(reDownRequest!=null){
						reDownRequest.putExtra(requestOFReNumber_KEY,requestOFReNumber+1);
						page.addTargetRequest(reDownRequest);
					}
				}else{
					((TSTPageProcessor)((Spider)task).getPageProcessor()).setStatus(SeedStatus.FAIL);//当验证码为null的次数超过一定值时，回收此种子
				}
				RecognizedContext.newInstance().updateCurrentError(IsSucess.FAIL, Integer.parseInt(results[1]), Long.parseLong(results[2]));
				request.putExtra(ProcessHandlePrepare.isProcessPageProcess, ProcessHandlePrepare.isProcessPageProcess);
			}else{
				results[0] = results[0].replaceAll("\\s", "");
			}
			//------------------------
			request.putExtra(ProcessHandlePrepare.pictureContent, results);
			page.setUrl(new PlainText(request.getUrl()));
			page.setRequest(request);
			processCookie(httpResponse, task, page);
			System.out.println("code result:"+results[0]);
		}
		return page;
	}
}
