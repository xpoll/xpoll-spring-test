package cn.blmdz.httpclient;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HCTest2 {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		List<Item2> items = new ArrayList<>();
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String prefix = "http://www.dy2018.com/html/tv/oumeitv/index";
		String sufix = ".html";
		for (int i = 1; i < 34; i++) {
			String url = "";
			
			if (i == 1) {
				url = prefix + sufix;
			} else {
				url = prefix + "_" + i + sufix;
			}
			HttpGet httpget = new HttpGet(url);
			CloseableHttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String nnn = EntityUtils.toString(entity, Charset.forName("gbk"));
				String[] arr = nnn.split("<div class=\"x\">")[1].split("<td width=\"5%\" height=\"26\" align=\"center\"><img src=\"/templets/img/item.gif\" width=\"18\" height=\"17\"></td>");
				for (int j = 1; j < arr.length; j++) {
					System.out.println(arr.length);
					Item2 item = new Item2();
					item.setUrl("http://www.dy2018.com" + arr[j].split("<a href=\"")[1].split("\"")[0].trim());
					item.setName(arr[j].split("class=\"ulink\" title=\"")[1].split("\"")[0].trim());
					item.setTime(arr[j].split("<font color=\"#8F8C89\">")[1].split("点击")[0].trim());
					item.setTimes(arr[j].split("点击：")[1].split("</font>")[0].trim());
					item.setDesc(arr[j].split("片　　名")[1].split("</td>")[0].trim());
					items.add(item);
				}
			}
			response.close();
		}
		for (Item2 item : items) {
			System.out.println(item);
		}
	}
}
