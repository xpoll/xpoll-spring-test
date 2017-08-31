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

public class HCTest {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		List<Item> items = new ArrayList<>();
		boolean run = true;
		int start = 0;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String url = "http://gwh5.api.weidian.com/wd/cate/getEmptyCateItems";
		
		while (run) {
			List<NameValuePair> params = new ArrayList<>();
			params.add(new BasicNameValuePair("param", "{\"userID\":\"981798090\",\"f_seller_id\":\"\",\"cate_id\":\"103742004\",\"limitStart\":" + start + ",\"limitNum\":10,\"is_top\":1,\"anotherGetLimit\":1,\"include_fx\":1,\"fx_offset\":0}"));
			HttpGet httpget = new HttpGet(url + "?" + EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8)));
			CloseableHttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				JSONObject jsonObj = JSONObject.parseObject(EntityUtils.toString(entity, Consts.UTF_8));
				JSONArray jsonArr = jsonObj.getJSONObject("result").getJSONArray("items");
				if (jsonArr.size() == 0) {
					run = false;
					break;
				}
				start += 10;
				for (int i=0; i<jsonArr.size(); i++) {
					Item item = new Item();
					item.setId(jsonArr.getJSONObject(i).getString("itemID"));
					item.setName(jsonArr.getJSONObject(i).getString("itemName"));
					item.setImg(jsonArr.getJSONObject(i).getString("img"));
					item.setUrl(jsonArr.getJSONObject(i).getString("h5url"));
					item.setPrice(jsonArr.getJSONObject(i).getString("price"));
					item.setStock(jsonArr.getJSONObject(i).getInteger("stock"));
					items.add(item);
				}
			}
			response.close();
		}
		for (Item item : items) {
			System.out.println(item);
		}	
	}
}
