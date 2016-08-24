package com.heetian.spider.process.jilin;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.JiLinProcessHandlePrepare;
import com.heetian.spider.utils.TSTUtils;
/**
 *220681000000939
 *苏宁环球股份有限公司
 *
 * @author tst
 */
public class HomeProcess extends JiLinProcessHandlePrepare{
	public HomeProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/aiccips/?xxx");
		setProcessName(processName_home);
	}
	@Override
	protected void analyticalProcessJiLin(Page page, PageProcessor task) {
		String homeSecondFlag = (String) page.getRequest().getExtra(homeFlag);
		if(homeSecondFlag!=null&&homeSecondFlag.equals(homeFlag)){
			if(page.getRawText().contains("<body onload=\"challenge();\">")){
				page.getRequest().putExtra(homeFlag, null);
				String url = "http://211.141.74.198:8081/aiccips/?xxx&"+urlTail();
				page.addTargetRequest(builderRequest(url, Method.GET, null,null, null));
				return;
			}
			String _csrf = page.getHtml().xpath("//form[@id='searchform']/input[@name='_csrf']/@value").get();
			NameValuePair[] nvps = new BasicNameValuePair[3];
			nvps[0] = new BasicNameValuePair("kw", ((TSTPageProcessor)task).getSeedNm());
			nvps[1] = new BasicNameValuePair("_csrf", _csrf);
			//nvps[2] = new BasicNameValuePair("secode", null);
			String url = builderURL("/aiccips/securitycode?"+urlTail(),task.getSite());
			Request request = builderRequest(url,Method.GET, null,null, nvps);
			request.putExtra(dwNum, "1");
			page.addTargetRequest(request);
		}else{
			String js = "function test(){return "+page.getHtml().regex("(.+?<script>\\s*?eval\\s*?\\()(.+)(\\)\\s*</script>.+?)",2).get()+"}";
			try {
				js = TSTUtils.scriptEval(js, "test");
				Matcher m= Pattern.compile("window\\s*?\\.location\\s*?\\.reload\\(\\)").matcher(js);
				while(m.find()){
					js = m.replaceAll("");
				}
				m= Pattern.compile("document\\s*?\\.\\s*?cookie\\s*?=\\s*?").matcher(js);
				while(m.find()){
					js = m.replaceAll("return ");
				}
				String function = page.getHtml().xpath("//body/@onload").replace("[^\\w]", "").get();
				js = TSTUtils.scriptEval(js, function).split("; ")[0];
				js = js.replaceAll("\\s","");
				if(js.contains("=")){
					//String cookcomplexy[] =  js.split("=");
					//task.getSite().addCookie(cookcomplexy[0], cookcomplexy[1]);
					task.getSite().addHeader("Cookie", js);
					String url = builderURL("/aiccips/?xxx&"+urlTail(),task.getSite());
					Request request = builderRequest(url,Method.GET, null,null, null);
					request.putExtra(homeFlag,homeFlag);
					page.addTargetRequest(request);
				}
			} catch (Exception e) {
				String url = "http://211.141.74.198:8081/aiccips/?xxx&"+urlTail();
				page.addTargetRequest(builderRequest(url, Method.GET, null, null,null));
				e.printStackTrace();
				return ;
			}
		}
	}
	public String old(Page page, String function) {
		Selectable li = page.getHtml().regex("\\<script\\>.*\\</script\\>").regex("(eval\\()(.+)(\\))",2);
		String parameter = li.regex("(function\\(.+\\)\\{.+\\}\\()(.+)(\\))",2).replace("'", "").replace("\\.split\\(\\|\\)", "").get();
		String parameters[] = parameter.split(",");
		String k = parameters[3];
		String p = parameters[0];
		String a = parameters[1];
		String c = parameters[2];
		Map<String,String> r_map = new TreeMap<String, String>();
		int c_int = Integer.parseInt(c);
		int a_int = Integer.parseInt(a);
		String[] k_arr = k.split("\\|");
		while(--c_int>=0){
			try {
				r_map.put(Long.toString(c_int, a_int),k_arr[c_int]==null?Long.toString(c_int,a_int):k_arr[c_int]);
			} catch (Exception e2) {
				r_map.put(Long.toString(c_int,a_int),Long.toString(c_int,a_int));
			}
		}
		Set<Map.Entry<String, String>> r_map_en = r_map.entrySet();
		for(Map.Entry<String, String> map:r_map_en){
			String key = map.getKey();
			String value = map.getValue();
			Pattern pattern = Pattern.compile("(\\b"+key+"\\b)");
			Matcher m = pattern.matcher(p);
			if(m.find()){
				p = m.replaceAll(value);
			}
		}
		//System.out.println(/*function+*/":"+p);
		String f_result = "(.*)("+function+"\\(\\)\\{document.cookie=\\\\)(ROBOTCOOKIEID\\s*=\\w+)(;.*)";
		Matcher m= Pattern.compile(f_result).matcher(p);
		String cookieStr = null;
		while(m.find()){
			cookieStr = m.group(3);
		}
		return cookieStr;
	}
}
