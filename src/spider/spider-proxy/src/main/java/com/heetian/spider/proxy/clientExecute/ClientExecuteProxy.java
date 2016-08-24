package com.heetian.spider.proxy.clientExecute;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author tst
 *
 */
public class ClientExecuteProxy implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(ClientExecuteProxy.class);
	private static Map<String, ProxyProcessInterface> pros = new HashMap<String, ProxyProcessInterface>();
	private static final Executor threadpool = Executors.newFixedThreadPool(2);
	private static final int  sleeptimes = 60000;//一分钟的秒数
	static{
		InputStream in = ClientExecuteProxy.class.getClassLoader().getResourceAsStream("proxy.properties");
		Properties p = new Properties();
		try {
			p.load(in);
			Set<Object> keys = p.keySet();
			Iterator<Object> iter = keys.iterator();
			while(iter.hasNext()){
				Object key = iter.next();
				Object value = p.get(key);
				@SuppressWarnings("rawtypes")
				Class clazz = Class.forName(key.toString());
				ProxyProcessInterface tmp = (ProxyProcessInterface) clazz.newInstance();
				pros.put(value.toString(), tmp);
			}
		} catch (Exception e) {
			logger.error("加载代理线程资源出错",e);
			System.exit(0);
		}
	}
	@Override
	public void run() {
		while (true) {
			Set<String> keys = pros.keySet();
			Iterator<String> iter = keys.iterator();
			while(iter.hasNext()){
				final String url = iter.next();
				final ProxyProcessInterface pro = pros.get(url);
				HttpGet httpGet = new HttpGet(url);
				RequestConfig config = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();//设置请求和传输超时时间
				httpGet.setConfig(config);
				CloseableHttpClient client = HttpClients.createDefault();
				try {
					final String content = getPageByURL(httpGet, client);
					if(content==null||"".equals(content.trim()))
						continue;
					threadpool.execute(new Runnable() {
						@Override
						public void run() {
							pro.process(content);
						}
					});
				}catch (Exception e) {
					logger.error("代理线程抛出异常：", e);
				}finally {
					try {
						client.close();
					} catch (IOException e) {
						logger.error("关闭client链接抛出异常" ,e);
					}
				}
			}
			sleepTime(sleeptimes);
		}
	}
	public boolean testProxy(String ip, int port) {
		if ("".equals(ip) || ip == null) {
			logger.info("没有使用代理，无须测试。");
			return false;
		}
		try {
			URL url = new URL("http://www.baidu.com");
			InetSocketAddress addr = new InetSocketAddress(ip, port);
			java.net.Proxy proxy = new java.net.Proxy(java.net.Proxy.Type.HTTP, addr); 
			URLConnection conn = url.openConnection(proxy);
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			InputStream in = conn.getInputStream();
			String s = IOUtils.toString(in);
			if (s.indexOf("www.hao123.com") != -1 && s.indexOf("home.baidu.com") != -1 && s.indexOf("京ICP证030173号") != -1 && s.indexOf("www.baidu.com/duty/") != -1 && s.indexOf("百度一下，你就知道") != -1) {
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}
	public void sleepTime(long time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取网页内容
	 * @param request
	 * @param httpclient
	 * @return
	 * @throws IOException
	 */
	private String getPageByURL(HttpGet request, CloseableHttpClient httpclient) throws IOException {
		String str = null;
		CloseableHttpResponse response = httpclient.execute(request);
		try {
			// EntityUtils.consume(response.getEntity());
			str = EntityUtils.toString(response.getEntity());
			// EntityUtils.consume(response.getEntity());
			EntityUtils.consume(response.getEntity());
		} finally {
			response.close();
		}
		return str;
	}
	public static void main(String[] args) {
		new Thread(new ClientExecuteProxy()).start();// 启动代理线程
		logger.info("开启代理线程........");
	}
}
