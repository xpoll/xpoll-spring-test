package cn.blmdz.httpclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class HCTest3 {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String url = "http://api2.jiakaobaodian.com/api/open/exercise/sequence.htm";
		List<Integer> list = null;
		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("_r", "19508631542894706093"));
		params.add(new BasicNameValuePair("cityCode", "310000"));
		params.add(new BasicNameValuePair("page", "1"));
		params.add(new BasicNameValuePair("limit", "25"));
		params.add(new BasicNameValuePair("course", "kemu1"));
		params.add(new BasicNameValuePair("carType", "car"));
		HttpGet httpget = new HttpGet(url + "?" + EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8)));
		CloseableHttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			JSONObject jsonObj = JSONObject.parseObject(EntityUtils.toString(entity, Consts.UTF_8));
			JSONArray jsonArr = jsonObj.getJSONArray("data");
			list = jsonArr.toJavaList(Integer.class);
		}
		response.close();
		System.err.println("question success.");
		System.err.println(list.size());
		
		
		url = "http://api2.jiakaobaodian.com/api/open/question/question-list.htm";
		StringBuffer sb = new StringBuffer();
		List<Item3> list3 = new ArrayList<>();
		boolean run = true;
		int page = 1;
		int num = 100;
		while (run) {
			int s = (page - 1) * num;
			int e = page * num;
			page ++;
			if ((e - 1) >= list.size()) {
				run = false;
				e = list.size() - 1;
			}
			sb.setLength(0);
			for (Integer item : list.subList(s, e)) {
				if (sb.length() != 0) {
					sb.append(",");
				}
				sb.append(item);
			}
			params = new ArrayList<>();
			params.add(new BasicNameValuePair("_r", "16948567935326295105"));
			params.add(new BasicNameValuePair("page", "1"));
			params.add(new BasicNameValuePair("limit", "25"));
			params.add(new BasicNameValuePair("questionIds", sb.toString()));
			httpget = new HttpGet(url + "?" + EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8)));
			response = httpclient.execute(httpget);
			entity = response.getEntity();
			if (entity != null) {
				JSONObject jsonObj = JSONObject.parseObject(EntityUtils.toString(entity, Consts.UTF_8));
				JSONArray jsonArr = jsonObj.getJSONArray("data");
				list3.addAll(jsonArr.toJavaList(Item3.class));
			}
		}
		System.err.println(list3.size());
		for (Item3 item : list3) {
			System.out.println(item.getMediaType());
		}
	}
}
