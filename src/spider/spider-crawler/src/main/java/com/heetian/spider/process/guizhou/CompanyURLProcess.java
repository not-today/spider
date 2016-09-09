package com.heetian.spider.process.guizhou;

import java.util.Iterator;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.component.EnterUrls;
import com.heetian.spider.peking.strategy.IsSucess;
import com.heetian.spider.peking.strategy.RecognizedContext;
import com.heetian.spider.process.abstractclass.GuiZhouProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForJson;
import com.heetian.spider.utils.TSTUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;
/**
 * 
 * @author tst
 *
 */
public class CompanyURLProcess extends GuiZhouProcessHandlePrepare{
	public CompanyURLProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri(EnterUrls.GZComs);
		setProcessName(processName_list);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String resultsImage[] = (String[]) page.getRequest().getExtra(RecognizedContext.saveName);
		String resultJson = page.getRawText();
		CompanyBeanJson companyBeanJsons = AnalysisForJson.jsonToObject(resultJson,new TypeToken<CompanyBeanJson>() {});
		if(companyBeanJsons==null){
			logger.warn("贵州获取企业列表失败:json转成的bean为null");
			RecognizedContext.newInstance().updateCurrentError(IsSucess.FAIL, Integer.parseInt(resultsImage[1]), Long.parseLong(resultsImage[2]));
			validateCodeFail(page, task, resultsImage);
			return;
		}
		String successed = companyBeanJsons.getSuccessed();
		if(successed==null||"".equals(successed)||"false".equals(successed)){
			logger.warn("贵州获取企业列表失败:"+companyBeanJsons.getMessage());
			RecognizedContext.newInstance().updateCurrentError(IsSucess.FAIL, Integer.parseInt(resultsImage[1]), Long.parseLong(resultsImage[2]));
			validateCodeFail(page, task, resultsImage);
			return;
		}
		RecognizedContext.newInstance().updateCurrentError(IsSucess.SUCCESS, Integer.parseInt(resultsImage[1]), Long.parseLong(resultsImage[2]));
		List<DataBean> beans = companyBeanJsons.getData(); 
		if(beans==null||beans.size()<=0){
			//此关键字没有数据，删除种子
			return;
		}
		Iterator<DataBean> iter = beans.iterator();
		while(iter.hasNext()){
			DataBean bean = iter.next();
			if(bean==null)
				continue;
			String regNumber = bean.getZch().trim();
			if(regNumber==null)
				continue;
			String tmps[] = null;
			if(regNumber.contains("/"))
				tmps = regNumber.split("/");
			if(!tmps[1].contains("无")){
				regNumber = tmps[1];
			}else{
				regNumber = tmps[0];
			}
				
			String entName = bean.getQymc().trim();
			String nbxh = bean.getNbxh();//
			if(!TSTUtils.checkIsExitForEnter(task, regNumber, entName)){
				iter.remove();
				continue;
			}
			for(int x=0;x<5;x++){
				NameValuePair[] nvps = new NameValuePair[4];
				nvps[0] = new BasicNameValuePair("nbxh", nbxh);
				if(x==0){//基本
					nvps[1] = new BasicNameValuePair("c", "0");
					nvps[2] = new BasicNameValuePair("t", "5");
					nvps[3] = new BasicNameValuePair("type", "jb");
				}else if(x==1){//股东
					nvps[1] = new BasicNameValuePair("c", "2");
					nvps[2] = new BasicNameValuePair("t", "3");
					nvps[3] = new BasicNameValuePair("type", "gd");
				}else if(x==2){//变更
					nvps[1] = new BasicNameValuePair("c", "1");
					nvps[2] = new BasicNameValuePair("t", "2");
					nvps[3] = new BasicNameValuePair("type", "bg");
				}else if(x==3){//主要人员
					nvps[1] = new BasicNameValuePair("c", "0");
					nvps[2] = new BasicNameValuePair("t", "8");
					nvps[3] = new BasicNameValuePair("type", "zy");
				}else if(x==4){//分支机构
					nvps[1] = new BasicNameValuePair("c", "0");
					nvps[2] = new BasicNameValuePair("t", "9");
					nvps[3] = new BasicNameValuePair("type", "fz");
				}
				
				Request request = builderRequest(builderURL(EnterUrls.GZAll,task.getSite())+"?"+urlTail()+"&"+Math.random(),Method.POST, regNumber,entName, nvps);
				page.addTargetRequest(request);
			}
		}
	}
	public String getScztPath(String ztlx, String qylx){
	    String path = null;
	    if ("1".equals(ztlx)){
	        if (qylx.indexOf("12") == 0){
	            path = "gfgs";
	        }else if (qylx.indexOf("1") == 0){
	            path = "nzgs";
	        }else if (qylx.indexOf("2") == 0){
	            path = "nzgsfgs";
	        }else if (qylx.indexOf("3") == 0){
	            path = "nzqyfr";
	        }else if ("4310".equals(qylx)){
	            path = "nzyyfz";
	        }else if (("4000".equals(qylx)) || (qylx.indexOf("41") == 0)
	                || (qylx.indexOf("42") == 0) || (qylx.indexOf("43") == 0)
	                || (qylx.indexOf("44") == 0) || (qylx.indexOf("46") == 0)
	                || (qylx.indexOf("47") == 0)){
	            path = "nzyy";
	        }else if (qylx.indexOf("453") == 0){
	            path = "nzhh";
	        }else if ("4540".equals(qylx)){
	            path = "grdzgs";
	        }else if (qylx.indexOf("455") == 0){
	            path = "nzhhfz";
	        }else if ("4560".equals(qylx)){
	            path = "grdzfzjg";
	        }else if ((qylx.indexOf("50") == 0) || (qylx.indexOf("51") == 0)
	                || (qylx.indexOf("52") == 0) || (qylx.indexOf("53") == 0)
	                || (qylx.indexOf("60") == 0) || (qylx.indexOf("61") == 0)
	                || (qylx.indexOf("62") == 0) || (qylx.indexOf("63") == 0)){
	            path = "wstz";
	        }else if (((qylx.indexOf("58") == 0) || (qylx.indexOf("68") == 0)
	                || (qylx.indexOf("70") == 0) || (qylx.indexOf("71") == 0)
	                || ("7310".equals(qylx)) || ("7390".equals(qylx)))
	                && (!"5840".equals(qylx)) && (!"6840".equals(qylx))){
	            path = "wstzfz";
	        }else if ((qylx.indexOf("54") == 0) || (qylx.indexOf("64") == 0)){
	            path = "wzhh";
	        }else if ((qylx.indexOf("5840") == 0) || (qylx.indexOf("6840") == 0)){
	            path = "wzhhfz";
	        }else if ("7200".equals(qylx)){
	            path = "czdbjg";
	        }else if ("7300".equals(qylx)){
	            path = "wgqycsjyhd";
	        }else if ("9100".equals(qylx)){
	            path = "nmzyhzs";
	        }else if ("9200".equals(qylx)){
	            path = "nmzyhzsfz";
	        }
	    }else if ("2".equals(ztlx)){
	        path = "gtgsh";
	    }
	    return "/"+path+"/index.jsp";
	}
	private void validateCodeFail(Page page, PageProcessor task,String[] resultsImage) {
		page.addTargetRequest(builderRequestGet(builderURL(EnterUrls.GZDwcode+"&"+urlTail(),task.getSite())));
	}
}
