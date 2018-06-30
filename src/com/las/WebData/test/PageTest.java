package com.las.WebData.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.las.WebData.entity.Acmproduct;
import com.las.WebData.tool.HttpClientHelper;

public class PageTest {
	private static HttpClientHelper httpClientHelper = new HttpClientHelper();

	public static void testAll() {
		try {

			String url = "http://dl.acm.org/pubs.cfm?CFID=812189940&CFTOKEN=55351807 ";
			System.out.println(url);
			String data = Jsoup.connect(url).get().toString();

			Document document = Jsoup.parse(data);

			Element tables = document.getElementsByClass("text12").first();
			Elements trs = tables.select("tr");
			int count = 0;
			for (Element tr : trs) {
				Thread.sleep(1000);
				count++;
				System.out.println("需要处理的数据剩余 ： " + (trs.size() - count));
				// 期刊名称
				String acmPublicationTitle = tr.select("strong").text();
				// 编者汇总
				String acmedition = "";
				// 期刊图片地址
				String acmPublicationCoverPicUrl = "https:" + tr.select("img").attr("src");
				// 期刊类型
				String acmjournalProductType = "";
				// 期刊图片名
				String acmPublicationCoverPicFileName = "";
				// 期刊简介
				String description = "";
				// 期刊具体地址
				String acmproductrUrl = "";
				// 期刊ISSN
				String ISSN = "";
				// 期刊EISSN
				String EISSN = "";
				// 期刊图片
				String acmPublicationCoverPic = null;
				try {
					acmPublicationCoverPic = httpClientHelper.get(acmPublicationCoverPicUrl);
				} catch (Exception e) {
					acmPublicationCoverPic = null;
				}
				Element tdTop = tr.select("td[valign]").first();
				String name = tdTop.select("strong").first().text();
				acmproductrUrl = ("https://"+name + ".acm.org").toLowerCase();
				String desUrl = "https://dl.acm.org/"+tdTop.select("a").first().attr("href");
				String secondUrl = tdTop.select("a").first().attr("href").split("&")[0];
				String thirdIdUrl = secondUrl.substring(8, secondUrl.length());
				String Introduction = tdTop.select("a").first().text();

//				// 期刊简介
//				String desData = Jsoup.connect(acmproductrUrl).get().toString();
//				Document desDocument = Jsoup.parse(desData);
//				Element divDesDivElement = desDocument.getElementsByClass("section journal-feature").first();
//				description = divDesDivElement.text();
				
				//进入编辑页面
				String editerUrl = (acmproductrUrl+"/editorial.cfm").toLowerCase();
				System.out.println(editerUrl);
				String editerData = Jsoup.connect(editerUrl).get().toString();
				Document editerDocument = Jsoup.parse(editerData);
				Element editerAllDiv = editerDocument.getElementsByClass("col-md-9  col-sm-8 col-xs-12 content pad20").first();
				Elements editerDiv = editerAllDiv.getElementsByClass("col-xs-6 col-md-6");
				for (Element element : editerDiv) {
					Element editerName = element.select("a").first();
					try {
						acmedition += editerName.text();
					} catch (Exception e) {
						continue;
					}
				}
//				System.out.println("acmedition : " + acmedition);
//				
				// 开始向二级界面获取信息
				System.out.println("进入二级界面");
				
//				String secondData = Jsoup.connect("https://dl.acm.org/pub_series.cfm?" + thirdIdUrl
//						+ "&_cf_containerId=pubs&_cf_nodebug=true&_cf_nocache=true&_cf_clientid=40D227EB7ABA2B5B2CFF74E0C1F3C04F&_cf_rc=1")
//						.get().toString();
//				Document secondDocument = Jsoup.parse(secondData);

				
				System.out.println(desUrl);
				String desData = Jsoup.connect(desUrl).get().toString();
				Document desDoc = Jsoup.parse(desData);
				
				Element desDivElement = desDoc.getElementsByClass("text12").last();
				System.out.println(desDivElement.text());
				
				
//				String thirdUrl = "https://dl.acm.org/" + secondDocument.select("a").first().attr("href");
//				String fourthAboutId = secondDocument.select("a").first().attr("href").split("&")[0];
//				String fourthId = fourthAboutId.substring(13, fourthAboutId.length());
				// 开始向第三界面获取信息
//				System.out.println("进入三级界面");
//				String thirdData = Jsoup.connect(thirdUrl).get().toString();
//				Document thirdDocument = Jsoup.parse(thirdData);
//				Element divMain = thirdDocument.getElementById("divmain");
//				acmjournalProductType = divMain.getElementsByAttributeValue("style", "padding-bottom:0px").text()
//						.substring(1);
//				String fourthUrl = "https://dl.acm.org/tab_source.cfm?" + fourthId
//						+ "&usebody=tabbody&type=article&sellOnline=0&purchaseable=yes&cfid=814203019&cftoken=71790322&_cf_containerId=source&_cf_nodebug=true&_cf_nocache=true&_cf_clientid=40D227EB7ABA2B5B2CFF74E0C1F3C04F&_cf_rc=1";
//				System.out.println(fourthUrl);
//				// 开始向第四界面获取信息
//				System.out.println("进入第四界面");
//				String fourthData = Jsoup.connect(fourthUrl).get().toString();
//				Document fourthDocument = Jsoup.parse(fourthData);
//				Element fourthTr = fourthDocument.select("tr").last();
//				String fourthIdea = fourthTr.text();
//				if (fourthIdea.startsWith("ISSN") && fourthIdea.indexOf("EISSN") != -1) {
//					ISSN = fourthIdea.substring(6, 15);
//					EISSN = fourthIdea.substring(23, fourthIdea.length());
//				} else if (fourthIdea.startsWith("ISSN") && fourthIdea.indexOf("EISSN") == -1) {
//					ISSN = fourthIdea.substring(7, 15);
//				} else if (!fourthIdea.startsWith("ISSN") && fourthIdea.indexOf("EISSN") != -1) {
//					EISSN = fourthIdea.substring(7, 16);
//				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		testAll();
	}

}
