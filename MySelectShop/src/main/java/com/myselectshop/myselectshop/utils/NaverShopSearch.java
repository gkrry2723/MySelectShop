package com.myselectshop.myselectshop.utils;

import com.myselectshop.myselectshop.config.ApiKey;
import com.myselectshop.myselectshop.models.ItemDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class NaverShopSearch {

    private final ApiKey apiKey = new ApiKey();
    private final String key = apiKey.getKey();

    public String search(String query) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "HZomfRFw61ZKAtDbREoB");
        headers.add("X-Naver-Client-Secret", this.key);
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?query="+query, HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        System.out.println(response);

        return response;
    }

    public List<ItemDto> fromJSONtoItems(String result){
        // 검색 결과를 JSON으로 가져오기
        JSONObject rjson = new JSONObject(result);
        // JSONObject에서 키값이 items인 배열 꺼내기
        JSONArray items = rjson.getJSONArray("items");

        List<ItemDto> itemDtoList = new ArrayList<>();

        // JSON Array에서 그안에 또 들어있는 JSON 꺼내기
        for (int i=0; i<items.length(); i++) {
            JSONObject itemJson =items.getJSONObject(i);
            ItemDto itemDto = new ItemDto(itemJson);
            itemDtoList.add(itemDto);
        }
        return itemDtoList;
    }

    public static void main(String[] args) {
        NaverShopSearch naverShopSearch = new NaverShopSearch();
    }

}