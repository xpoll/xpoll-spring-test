package cn.blmdz.httpclient;

import lombok.Data;

@Data
public class Item {
	private String id;
	private String name;
	private String price;
	private Integer stock;
	private String img;
	private String url;
}
