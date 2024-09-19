package com.example.demo.service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.demo.service.Repo.UrlReposotory;
import com.example.demo.service.model.Url;
import com.example.demo.service.model.UrlDto;
import com.google.common.hash.Hashing;


@Component
public class UrlServiceImp implements UrlService{
	 private static final int BASE = 62;
	@Autowired
	private UrlReposotory service;
	@Override
	public Url generateLink(UrlDto urlDto) {
		
		if (StringUtils.isNotEmpty(urlDto.getUrl())) {
			String encodeUrl = encodeUrl(urlDto.getUrl());
			Url urlToPersist = new Url();
			urlToPersist.setCreate(LocalDateTime.now());
			urlToPersist.setOrUrl(urlDto.getUrl());
			urlToPersist.setShortLink(encodeUrl);
			urlToPersist.setExpire(getExpireDate(urlDto.getData(), urlToPersist.getCreate()));			
			Url urlToRet = persistShortLink(urlToPersist);
			if (urlToRet!=null)return urlToRet;
		return null;
		}
		return null;
	}

	
	private LocalDateTime getExpireDate(String date, LocalDateTime create) {
		if (StringUtils.isBlank(date))
			return create.plusSeconds(120);
		LocalDateTime expireDate = LocalDateTime.parse(date);
		return expireDate;
	}


	private String encodeUrl(String url) {
		String encodeUrl ="";
		LocalDateTime time = LocalDateTime.now();
		encodeUrl = Hashing.murmur3_32()
				.hashString(url.concat(time.toString()), StandardCharsets.UTF_8)
				.toString();
		// long id = generateRandomLong(9);
		 //encodeUrl = encode(id, url);
	        //System.out.println("Base62 Encoded String: " + base62Encoded);
		return encodeUrl;
	}

	@Override
	public Url persistShortLink(Url url) {
		Url urltoRet = service.save(url);
		return urltoRet;
	}

	@Override
	public Url getEncodedUrl(String url) {
		Url urltoRep = service.findByShortLink(url);
		return urltoRep;
	}

	@Override
	public void deleteShortLink(Url url) {
		// TODO Auto-generated method stub
		service.delete(url);
	}
	
//	public static String encode(long num, String url ) {
//        StringBuilder result = new StringBuilder();
//        while (num > 0) {
//            int remainder = (int) (num % BASE);
//            result.append(url.charAt(remainder));
//            num /= BASE;
//        }
//        return result.reverse().toString();
//    }

//    public static long generateRandomLong(int digits) {
//        long lowerBound = (long) Math.pow(10, digits - 1); 
//        long upperBound = (long) Math.pow(10, digits) - 1;  
//
//        return ThreadLocalRandom.current().nextLong(lowerBound, upperBound + 1);
//    }

}
