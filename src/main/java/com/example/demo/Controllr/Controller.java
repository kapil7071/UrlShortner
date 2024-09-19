package com.example.demo.Controllr;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.UrlService;
import com.example.demo.service.model.Url;
import com.example.demo.service.model.UrlDto;
import com.example.demo.service.model.UrlErrorResponse;
import com.example.demo.service.model.UrlRespone;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class Controller {
	@Autowired
	private UrlService urlService;
	@PostMapping("/generate")
	public ResponseEntity<?> generateShortLink(@RequestBody UrlDto urlDto){
		//@RequestBody UrlDto urlDto
		//UrlDto urlDto = new UrlDto();
		//urlDto.setUrl("https://github.com/kapil7071/CropDoctor_Clinic");
		Url urlToRet = urlService.generateLink(urlDto);
		if (urlToRet!=null) {
			UrlRespone urlResponse = new UrlRespone();
			urlResponse.setOriginalUrl(urlToRet.getOrUrl());
			urlResponse.setExpirationDate(urlToRet.getExpire());
			urlResponse.setShortUrl(urlToRet.getShortLink());
			return new ResponseEntity<UrlRespone>(urlResponse, HttpStatus.OK);
		}
		
		UrlErrorResponse urlError = new UrlErrorResponse();
		urlError.setStatus("404");
		urlError.setError("There was an error processing you request. Please Try Again  "+urlToRet );
		return new ResponseEntity<UrlErrorResponse>(urlError , HttpStatus.OK);
		
	}
	@GetMapping("/{shortLink}")
	public ResponseEntity<?>redircetTopage(@PathVariable String shortLink ,HttpServletResponse response )throws IOException{
		if (StringUtils.isEmpty(shortLink)) {
			UrlErrorResponse urlError = new UrlErrorResponse();
			urlError.setStatus("400");
			urlError.setError("Invaild Link");
			return new ResponseEntity<UrlErrorResponse>(urlError , HttpStatus.OK);
		}
		Url urlToRet = urlService.getEncodedUrl(shortLink);
		if (urlToRet==null) {
			UrlErrorResponse urlError = new UrlErrorResponse();
			urlError.setStatus("400");
			urlError.setError("Invaild Link");
			return new ResponseEntity<UrlErrorResponse>(urlError , HttpStatus.OK);
		}
		response.sendRedirect(urlToRet.getOrUrl());
		
		return null;
	}
}



