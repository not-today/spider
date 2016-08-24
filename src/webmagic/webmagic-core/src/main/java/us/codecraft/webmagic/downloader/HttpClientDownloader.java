package us.codecraft.webmagic.downloader;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.annotation.ThreadSafe;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.selector.PlainText;
import us.codecraft.webmagic.utils.HttpConstant;
import us.codecraft.webmagic.utils.UrlUtils;

import com.google.common.collect.Sets;


/**
 * The http downloader based on HttpClient.
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 */
@ThreadSafe
public class HttpClientDownloader extends AbstractDownloader {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final Map<String, CloseableHttpClient> httpClients = new HashMap<String, CloseableHttpClient>();

    private HttpClientGenerator httpClientGenerator = new HttpClientGenerator();

    private CloseableHttpClient getHttpClient(Site site) {
        if (site == null) {
            return httpClientGenerator.getClient(null);
        }
        String domain = site.getDomain();
        CloseableHttpClient httpClient = httpClients.get(domain);
        if (httpClient == null) {
            synchronized (this) {
                httpClient = httpClients.get(domain);
                if (httpClient == null) {
                    httpClient = httpClientGenerator.getClient(site);
                    httpClients.put(domain, httpClient);
                }
            }
        }
        return httpClient;
    }

    @Override
    public Page download(Request request, Task task) {
        Site site = null;
        if (task != null) {
            site = task.getSite();
        }
        Set<Integer> acceptStatCode;
        String charset = null;
        Map<String, String> headers = null;
        if (site != null) {
            acceptStatCode = site.getAcceptStatCode();
            charset = request.getExtra(Request.private_charset)==null?site.getCharset():(String)request.getExtra(Request.private_charset);
            //charset = site.getCharset();
            headers = site.getHeaders();
        } else {
            acceptStatCode = Sets.newHashSet(200);
        }
        //logger.info("downloading page {}", request.getUrl());
        CloseableHttpResponse httpResponse = null;
        int statusCode=0;
        Page page = null;
        try {
        	//logger.info(Thread.currentThread().getName()+"----------------------------------开始http请求----------------------------------------------");
            HttpUriRequest httpUriRequest = getHttpUriRequest(request, site, headers);
            httpResponse = getHttpClient(site).execute(httpUriRequest);
            statusCode = httpResponse.getStatusLine().getStatusCode();
            request.putExtra(Request.STATUS_CODE, statusCode);
            if (statusAccept(acceptStatCode, statusCode)) {
                page = handleResponse(request, charset, httpResponse, task);
                onSuccess(request);
            } else {
                //logger.warn("code error " + statusCode + "\t" + request.getUrl());
            	page = createPage("statusCode["+statusCode+"] not equals 200;", statusCode,
            			"code["+statusCode+"] error:" + request.getUrl(), request);
            }
            Header[] allHeader = httpResponse.getAllHeaders();
            request.putExtra("allHead", allHeader);
        } catch (Exception e) {
        	//logger.error("download page " + request.getUrl() + " error:",e);
        	 request.putExtra(Request.STATUS_CODE, -1);
            if (site.getCycleRetryTimes() > 0) {
                return addToCycleRetry(request, site);
            }
            onError(request);
            page = createPage("http response appear exception:"+e.getMessage(), -1, e.toString(), request);
        } finally {
        	//request.putExtra(Request.STATUS_CODE, statusCode);
            try {
                if (httpResponse != null) {
                    //ensure the connection is released back to pool
                    EntityUtils.consume(httpResponse.getEntity());
                }
            } catch (IOException e) {
                logger.warn("close response fail", e);
            }
            //logger.info(Thread.currentThread().getName()+"-----------------------------------结束http请求---------------------------------------------");
        }
        return page;
    }
    private Page createPage(String rawText,int status,String error,Request request){
    	 Page page = new Page();
         page.setRawText(rawText);
         page.setStatusCode(status);
         page.setError(error);
         //page.setUrl(new PlainText(request.getUrl()));
         page.setRequest(request);
         return page;
    }
    @Override
    public void setThread(int thread) {
        httpClientGenerator.setPoolSize(thread);
    }

    protected boolean statusAccept(Set<Integer> acceptStatCode, int statusCode) {
        return acceptStatCode.contains(statusCode);
    }

    protected HttpUriRequest getHttpUriRequest(Request request, Site site, Map<String, String> headers) throws UnsupportedEncodingException {
        RequestBuilder requestBuilder = selectRequestMethod(request,site).setUri(request.getUrl());
        if (headers != null) {
            for (Map.Entry<String, String> headerEntry : headers.entrySet()) {
            	//logger.info(Thread.currentThread().getName()+"为请求设置http请求头：["+headerEntry.getKey()+"]:["+headerEntry.getValue()+"]");
                requestBuilder.addHeader(headerEntry.getKey(), headerEntry.getValue());
            }
        }
        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom()
                .setConnectionRequestTimeout(site.getTimeOut())
                .setSocketTimeout(site.getTimeOut())
                .setConnectTimeout(site.getTimeOut())
                .setCookieSpec(CookieSpecs.BEST_MATCH);
        if (site.getHttpProxyPool() != null && site.getHttpProxyPool().isEnable()) {
            HttpHost host = site.getHttpProxyFromPool();
			requestConfigBuilder.setProxy(host);
			request.putExtra(Request.PROXY, host);
			//-------------------修改--------------------
			site.setHttpProxy(host);
			//-------------------修改--------------------
		}else if(site.getHttpProxy()!= null){
            HttpHost host = site.getHttpProxy();
			requestConfigBuilder.setProxy(host);
			request.putExtra(Request.PROXY, host);	
		}
        requestBuilder.setConfig(requestConfigBuilder.build());
        return requestBuilder.build();
    }


    protected RequestBuilder selectRequestMethod(Request request,Site site) throws UnsupportedEncodingException {
        String method = request.getMethod();
        if (method == null || method.equalsIgnoreCase(HttpConstant.Method.GET)) {
        	//logger.info(Thread.currentThread().getName()+"链接："+request.getUrl()+"的请求方式为Get,无参数设置");
            return RequestBuilder.get();
        } else if (method.equalsIgnoreCase(HttpConstant.Method.POST)) {
        	//logger.info(Thread.currentThread().getName()+"链接："+request.getUrl()+"的请求方式为POST，有参数设置");
            RequestBuilder requestBuilder = RequestBuilder.post();
            NameValuePair[] nameValuePair = (NameValuePair[]) request.getExtra("nameValuePair");
            if (nameValuePair != null && nameValuePair.length > 0) {
            	List<NameValuePair> npvs = new ArrayList<NameValuePair>();
            	for(NameValuePair tmp :nameValuePair){
            		if(tmp!=null)
            			npvs.add(tmp);
            	}
        		//logger.info(Thread.currentThread().getName()+"为POST请求设置请求参数："+Arrays.toString(nameValuePair));
                //由于RequestBuilder在对post参数编码时默认使用ISO-8859-1，会导致中文乱码，所以在此修正
                //requestBuilder.setEntity(new UrlEncodedFormEntity(Arrays.asList(nameValuePair),site.getCharset()));
            	if(npvs.size()>0)
            		requestBuilder.setEntity(new UrlEncodedFormEntity(npvs,site.getCharset()));
            }
            return requestBuilder;
        } else if (method.equalsIgnoreCase(HttpConstant.Method.HEAD)) {
            return RequestBuilder.head();
        } else if (method.equalsIgnoreCase(HttpConstant.Method.PUT)) {
            return RequestBuilder.put();
        } else if (method.equalsIgnoreCase(HttpConstant.Method.DELETE)) {
            return RequestBuilder.delete();
        } else if (method.equalsIgnoreCase(HttpConstant.Method.TRACE)) {
            return RequestBuilder.trace();
        }
        throw new IllegalArgumentException("Illegal HTTP Method " + method);
    }

    protected Page handleResponse(Request request, String charset, HttpResponse httpResponse, Task task) throws IOException {
        String content = getContent(charset, httpResponse);
        Page page = new Page();
        page.setRawText(content);
        page.setUrl(new PlainText(request.getUrl()));
        page.setRequest(request);
        page.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        processCookie(httpResponse, task, page);
        return page;
    }

    protected void processCookie(HttpResponse httpResponse, Task task, Page page) {
		Header[] cookies = httpResponse.getHeaders("Set-Cookie");
        if(cookies!=null&&cookies.length>0){
        	HeaderElementIterator it = new BasicHeaderElementIterator(httpResponse.headerIterator("Set-Cookie"));
    		Site site = task.getSite();
        	while (it.hasNext()) {
    		    HeaderElement elem = it.nextElement(); 
    		    site.addCookie(elem.getName(), elem.getValue());
    		}
        	 page.getRequest().putExtra("Set-Cookie", cookies);
        	 //logger.info("获取的cookie："+ task.getSite().getCookies());
        }
	}

    protected String getContent(String charset, HttpResponse httpResponse) throws IOException {
        if (charset == null) {
            byte[] contentBytes = IOUtils.toByteArray(httpResponse.getEntity().getContent());
            String htmlCharset = getHtmlCharset(httpResponse, contentBytes);
            if (htmlCharset != null) {
                return new String(contentBytes, htmlCharset);
            } else {
                logger.warn("Charset autodetect failed, use {} as charset. Please specify charset in Site.setCharset()", Charset.defaultCharset());
                return new String(contentBytes);
            }
        } else {
            return IOUtils.toString(httpResponse.getEntity().getContent(), charset);
        }
    }

    protected String getHtmlCharset(HttpResponse httpResponse, byte[] contentBytes) throws IOException {
        String charset;
        // charset
        // 1、encoding in http header Content-Type
        String value = httpResponse.getEntity().getContentType().getValue();
        charset = UrlUtils.getCharset(value);
        if (StringUtils.isNotBlank(charset)) {
            logger.debug("Auto get charset: {}", charset);
            return charset;
        }
        // use default charset to decode first time
        Charset defaultCharset = Charset.defaultCharset();
        String content = new String(contentBytes, defaultCharset.name());
        // 2、charset in meta
        if (StringUtils.isNotEmpty(content)) {
            Document document = Jsoup.parse(content);
            Elements links = document.select("meta");
            for (Element link : links) {
                // 2.1、html4.01 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
                String metaContent = link.attr("content");
                String metaCharset = link.attr("charset");
                if (metaContent.indexOf("charset") != -1) {
                    metaContent = metaContent.substring(metaContent.indexOf("charset"), metaContent.length());
                    charset = metaContent.split("=")[1];
                    break;
                }
                // 2.2、html5 <meta charset="UTF-8" />
                else if (StringUtils.isNotEmpty(metaCharset)) {
                    charset = metaCharset;
                    break;
                }
            }
        }
        logger.debug("Auto get charset: {}", charset);
        // 3、todo use tools as cpdetector for content decode
        return charset;
    }
}
