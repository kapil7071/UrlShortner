package com.example.demo.service.model;

public class UrlDto {
public UrlDto() {
		
	}
private String url;
private String data;
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getData() {
	return data;
}
public void setData(String data) {
	this.data = data;
}
public UrlDto(String url, String data) {
	super();
	this.url = url;
	this.data = data;
}
@Override
public String toString() {
	return "UrlDto [url=" + url + ", data=" + data + "]";
}


}
