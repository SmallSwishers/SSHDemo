package com.las.WebData.tool;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultRedirectHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

/**
 * @author chen zijun
 *
 */
public class HttpClientHelper {
	
	/**
	 *  通过GET方法收取http响应数据 
	 * @param url
	 * @return
	 */
	public String get(String url) {
		return this.get(url, "utf-8");
	}
	
	public String post(String url,List <NameValuePair> params) {
		return this.post(url,params, "utf-8");
	}
	
	
	/**
	 *  通过GET方法收取http响应数据
	 * @param url
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String get(String url,String enoding) {

		HttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept-Language"," en-us,en;q=0.5");
		httpGet.setHeader("Keep-Alive","timeout=20, max=9999");
		boolean hasException = false;
		String html = "";
		try {
			
			//setSocketTimeout 数据请求超时设置
			//setConnectTimeout 链接超时设置
			//setConnectionRequestTimeout 设置从connectionManager获取connection的超时时间，httpclient支持连接池
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000).setConnectionRequestTimeout(50000).setRedirectsEnabled(true).build();
			httpGet.setConfig(requestConfig);
			
			HttpResponse httpResponse = httpClient.execute(httpGet);
	

			
			int returnCode = httpResponse.getStatusLine().getStatusCode();
			if (returnCode == 200) {
				html = EntityUtils.toString(httpResponse.getEntity(), enoding);
			} else {
				System.out.println("error code: " + returnCode);
			}
		}catch(SocketTimeoutException ste )
		{
			System.out.println("SocketTimeoutException");
			//重试
//			html = this.get(url);
		} catch (Exception e) {
			e.printStackTrace();
			hasException = true;
		} finally {
			if (hasException) {
				System.out.println("exception");
				html = "";
			}
		}
		return html;
	}
	
	public String post(String url,List <NameValuePair> params,String enoding) {

		HttpClient httpClient = HttpClients.createDefault();
		
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept-Language"," en-us,en;q=0.5");
		httpPost.setHeader("Keep-Alive","timeout=20, max=9999");
		boolean hasException = false;
		String html = "";

		try {
			
			//setSocketTimeout 数据请求超时设置
			//setConnectTimeout 链接超时设置
			//setConnectionRequestTimeout 设置从connectionManager获取connection的超时时间，httpclient支持连接池
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000).setConnectionRequestTimeout(50000).setRedirectsEnabled(false).build();
			httpPost.setConfig(requestConfig);
			httpPost.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8)); 
			 
			HttpResponse httpResponse = httpClient.execute(httpPost);
			int returnCode = httpResponse.getStatusLine().getStatusCode();
			if (returnCode == 200) {
				html = EntityUtils.toString(httpResponse.getEntity(), enoding);
			}
			else 	if (returnCode == 302) {
//				for(Header header:httpResponse.getAllHeaders())
//				{
//					System.out.println(header.getName()+":"+header.getValue());
//				}
				
				String location302=httpResponse.getLastHeader("Location").getValue();
				String newurl="https://projectreporter.nih.gov/"+location302;
				System.out.println("newurl:"+newurl);
				html =this.post (newurl,params);
			}
			else {
				System.out.println("HTTP Code: " + returnCode);
			}
		}catch(SocketTimeoutException ste )
		{
			System.out.println("SocketTimeoutException");
			//重试
//			html = this.post(url);
		} catch (Exception e) {
			e.printStackTrace();
			hasException = true;
		} finally {
			if (hasException) {
				System.out.println("exception");
				html = "";
			}
		}
		return html;
	}
	

}
