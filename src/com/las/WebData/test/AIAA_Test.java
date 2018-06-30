package com.las.WebData.test;

import javax.print.Doc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.las.WebData.tool.HttpClientHelper;

public class AIAA_Test {
	private static HttpClientHelper httpClientHelper = new HttpClientHelper();

	public static void aiaa() {
		try {
			System.out.println("开始");
			String url = "https://arc.aiaa.org/action/showPublications?pubType=journal&_ga=2.122679308.641043027.1506065202-1228769585.1506065202& ";
			String data = Jsoup.connect(url).get().toString();
			Document document = Jsoup.parse(data);
			Element subjectElement = document.getElementsByClass("subjectTitleListing pubSubjectTitleListing").first();

			Elements subjectAElement = subjectElement.select("a");
			for (Element element : subjectAElement) {
				// 期刊信息链接
				String aiaaproductrUrl = "";
				// 期刊名称
				String aiaaproductName = "";
				// 期刊图片地址
				String aiaaproductPicUrl = "";
				// 期刊图片文件名
				String aiaaproductPicName = "";
				// 期刊图片
				String aiaaproductPic = "";
				// 期刊描述
				String aiaaproductDescription = "";
				// 期刊介绍
				String aiaaproductIntroduction = "";
				// 期刊ISSN
				String ISSN = "";
				// 期刊EISSN
				String EISSN = "";
				// 期刊类型
				String aiaaproductType = "Journals ";

				aiaaproductrUrl = "https://arc.aiaa.org" + element.attr("href");
				aiaaproductName = element.text();

				System.out.println("进入二级界面");
				String secondData = Jsoup.connect(aiaaproductrUrl).get().toString();
				Document secondDocument = Jsoup.parse(secondData);

				Element imgDivElement = secondDocument.getElementById("smallIssueCover");
				Element imgElement = imgDivElement.select("img").first();
				aiaaproductPicUrl = "https://arc.aiaa.org" + imgElement.attr("src");
				aiaaproductPicName = imgElement.attr("src").substring(56, imgElement.attr("src").length());
				try {
					aiaaproductPic = httpClientHelper.get(aiaaproductPicUrl);
				} catch (Exception e) {
					aiaaproductPic = null;
				}
				Element introductionDiv = secondDocument.getElementById("widget-7660");
				aiaaproductDescription = introductionDiv.text();
				Element EISSNDiv = secondDocument.getElementById("widget-7664");
				Element EISSNP = null;
				try {
					EISSNP = EISSNDiv.getElementsByClass("box-inner").first();
					aiaaproductIntroduction = EISSNDiv.getElementsByClass("box-inner").text().substring(17);
					int index1 = EISSNP.text().indexOf("(");
					int index2 = EISSNP.text().indexOf(")");
					if (index1 != -1 && index2 != -1) {
						String info = EISSNP.text().substring(index1, index2);
						if (info.indexOf("ISSN") != (-1)) {
							ISSN = info.substring(7, 16);
						}
					}
					EISSN = EISSNP.text().substring(7, 16);
				} catch (Exception e) {
					EISSNP = null;
					EISSN = null;
					aiaaproductIntroduction = null;
				}
				System.out.println(aiaaproductIntroduction);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		aiaa();
	}

}
