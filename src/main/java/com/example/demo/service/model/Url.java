package com.example.demo.service.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
@Entity
public class Url {
	@Lob
	private String OrUrl;

	private LocalDateTime create;
	private LocalDateTime expire;
	
	public Url() {
	}
	@Id
	@GeneratedValue
private long Id;
	//@Column(name = "short_link")
	private String shortLink;

	
public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getOrUrl() {
		return OrUrl;
	}
	public void setOrUrl(String orUrl) {
		OrUrl = orUrl;
	}
	public String getShortLink() {
		return shortLink;
	}
	public void setShortLink(String shortLink) {
		this.shortLink = shortLink;
	}
	public LocalDateTime getCreate() {
		return create;
	}
	public void setCreate(LocalDateTime create) {
		this.create = create;
	}
	public LocalDateTime getExpire() {
		return expire;
	}
	public void setExpire(LocalDateTime expire) {
		this.expire = expire;
	}
public Url(long id, String orUrl, String shortLink, LocalDateTime create, LocalDateTime expire) {
		super();
		Id = id;
		OrUrl = orUrl;
		this.shortLink = shortLink;
		this.create = create;
		this.expire = expire;
	}
@Override
public String toString() {
	return "Url [Id=" + Id + ", shortLink=" + shortLink + ", OrUrl=" + OrUrl + ", create=" + create + ", expire="
			+ expire + "]";
}


}
