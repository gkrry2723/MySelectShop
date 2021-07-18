package com.myselectshop.myselectshop.utils;

import com.myselectshop.myselectshop.config.ApiKey;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class NaverShopSearch {

    private final ApiKey apiKey = new ApiKey();
    private final String key = apiKey.getKey();

    public String search() {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "HZomfRFw61ZKAtDbREoB");
        headers.add("X-Naver-Client-Secret", this.key);
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?query=iphone", HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        System.out.println(response);

        return response;
    }

    public static void main(String[] args) {
        NaverShopSearch naverShopSearch = new NaverShopSearch();
        naverShopSearch.search();
    }
}