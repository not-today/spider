package com.heetian.spider.peking.pageProcessor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class TestHttpClientMain {
	private static HttpClientContext context = HttpClientContext.create();
	public static void main(String[] args) throws Exception {
		System.out.println((1*60*1000)%(1000*60*60*24));
	}
	public static void test() throws UnsupportedEncodingException, IOException, ClientProtocolException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			// 模拟表单
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("username", "**"));
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
			HttpPost httpPost = new HttpPost( "http://localhost:8080/testHttpClient/test");
			httpPost.setEntity(entity);
			// 将HttpClientContext传入execute()中
			CloseableHttpResponse response = httpClient.execute(httpPost, context);
			try {
				HttpEntity responseEntity = response.getEntity();
				System.out.println(EntityUtils.toString(responseEntity));
			} finally {
				response.close();
			}
		} finally {
			httpClient.close();
		}
		CloseableHttpClient httpClient2 = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet( "http://localhost:8080/testHttpClient/test");
			// 设置相同的HttpClientContext
			CloseableHttpResponse response = httpClient2.execute(httpGet, context);
			try {
				HttpEntity entity = response.getEntity();
				System.out.println(EntityUtils.toString(entity));
			} finally {
				response.close();
			}
		} finally {
			httpClient2.close();
		}
	}
}
