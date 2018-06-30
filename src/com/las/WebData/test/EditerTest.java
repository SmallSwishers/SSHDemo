package com.las.WebData.test;

import java.io.IOException;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.las.WebData.tool.HttpClientHelper;

public class EditerTest {

	public static void editer() {
		try {
			HttpClientHelper httpClientHelper=new HttpClientHelper();
			String url = "https://dl.acm.org/griddata.cfc?" + 
					"method=getNames" + 
					"&returnFormat=json" + 
					"&argumentCollection="+URLEncoder.encode("{\"page\":1,\"pageSize\":20,\"gridsortcolumn\":\"\",\"gridsortdir\":\"\",\"useid\":\"J204\",\"myfilter\":\"\"}")+""
					+ "&_cf_nodebug=true&_cf_nocache=true"
					+ "&_cf_clientid=20432D0328D81A157F7F2CAD34BAA9F4"
					+ "&_cf_rc=2";
//			String json = Jsoup.connect(url).get().text();
			String json = httpClientHelper.get(url);
			System.out.println(json);
		
		
		
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		editer();
	}

}
