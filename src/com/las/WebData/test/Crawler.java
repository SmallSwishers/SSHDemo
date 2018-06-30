package com.las.WebData.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {

	private String url = "http://dl.acm.org/pub.cfm?id=J204&CFID=814203019&CFTOKEN=71790322";

	private String label = "title";

	private String labelValue = "ACM Computing Surveys (CSUR)";

	public Crawler() {
		super();
	}

	public Crawler(String url, String label, String labelValue) {
		super();
		this.url = url;
		this.label = label;
		this.labelValue = labelValue;
	}

	
	public Crawler(String url, String label) {
		super();
		this.url = url;
		this.label = label;
	}

	public String crawler() {
		try {
			Document document = Jsoup.connect(url)
					.userAgent(
							"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
					.post();
			Elements arr = document.getElementsByAttributeValue(label, labelValue);
			int count = 0;
			for (Element element : arr) {
				if (element.text() != null && element.text().length() != 0) {
					count++;
					if (count == 1) {
						return element.text();
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<String> crawlerURL() {
		try {
			Document document = Jsoup.connect(url)
					.userAgent(
							"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
					.post();
			Elements arr = document.getElementsByAttribute(label);
			List<String> list = new ArrayList<String>();
			for (Element element : arr) {
				list.add(element.id());
			}
			return list;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
