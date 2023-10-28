package com.example.reservation.controller;// KakaoPayController.java

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/kakaoPay")
public class KakaoPayController {

    private static final String HOST = "https://kapi.kakao.com";
//    private static final String HOST = "https://kapi.kakao.com/v1/payment/ready";

    private static final String KAKAO_APP_KEY = "2aef84c3b1c918d234a6f1c6401f4820";

    @PostMapping("/ready")
    public ResponseEntity<?> ready() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + KAKAO_APP_KEY);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", "YOUR_CID");
        parameters.add("partner_order_id", "YOUR_ORDER_ID");
        parameters.add("partner_user_id", "YOUR_USER_ID");
        parameters.add("item_name", "ITEM_NAME");
        parameters.add("quantity", "1");
        parameters.add("total_amount", "TOTAL_AMOUNT");
        parameters.add("tax_free_amount", "TAX_FREE_AMOUNT");
        parameters.add("approval_url", "YOUR_SUCCESS_URL");
        parameters.add("cancel_url", "YOUR_CANCEL_URL");
        parameters.add("fail_url", "YOUR_FAIL_URL");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(parameters, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(HOST + "/v1/payment/ready", request, String.class);

        return ResponseEntity.ok(response.getBody());
    }

// KakaoPayController.java에 추가

    @PostMapping("/approve")
    public ResponseEntity<?> approve(@RequestBody Map<String, String> payload) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + KAKAO_APP_KEY);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", payload.get("cid"));
        parameters.add("tid", payload.get("tid"));
        parameters.add("partner_order_id", payload.get("partner_order_id"));
        parameters.add("partner_user_id", payload.get("partner_user_id"));
        parameters.add("pg_token", payload.get("pg_token"));

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(parameters, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(HOST + "/v1/payment/approve", request, String.class);

        return ResponseEntity.ok(response.getBody());
    }


}
