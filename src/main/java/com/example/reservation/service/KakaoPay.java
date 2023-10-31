package com.example.reservation.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;

import com.example.reservation.dto.KakaoPayApprovalVO;
import com.example.reservation.dto.KakaoPayCancelVO;
import com.example.reservation.dto.KakaoPayReadyVO;
import com.example.reservation.dto.ReserveDTO;
import com.example.reservation.entity.MemberEntity;
import com.example.reservation.entity.ReserveEntity;
import com.example.reservation.entity.RoomEntity;
import com.example.reservation.repository.MemberRepository;
import com.example.reservation.repository.ReserveRepository;
import com.example.reservation.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.java.Log;

@Service
@Log
@RequiredArgsConstructor
public class KakaoPay {

    private static final String HOST = "https://kapi.kakao.com";

    private KakaoPayReadyVO kakaoPayReadyVO;
    private KakaoPayApprovalVO kakaoPayApprovalVO;
    private KakaoPayCancelVO kakaoPayCancelVO;
    private final ReserveRepository reserveRepository;
    private final MemberRepository memberRepository;
    private final RoomRepository roomRepository;

    public String kakaoPayReady(ReserveDTO reserveDTO) {
        System.out.println("reserveDTO = " + reserveDTO);
        Long memberId = (Long) reserveDTO.getMemberId();
        Long roomId = (Long) reserveDTO.getRoomId();
        RoomEntity roomEntity = roomRepository.findById(roomId).orElseThrow(() -> new NoSuchElementException());
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException());
        System.out.println(roomEntity.getId());
        System.out.println(memberEntity.getId());
        ReserveEntity reserveEntity = ReserveEntity.toSaveEntity(reserveDTO, memberEntity, roomEntity);
        System.out.println("reserveEntity = " + reserveEntity);
        ReserveEntity save = reserveRepository.save(reserveEntity);
        RestTemplate restTemplate = new RestTemplate();
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "8195b09f32e4faf23a4af02d67c6fd58");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", "gorany");
        params.add("item_name", save.getRoomEntity().getRoomName());
        params.add("quantity", "1");
        params.add("total_amount", "20000");
        params.add("tax_free_amount", "100");
        params.add("approval_url", "http://localhost:8084/kakaoPaySuccess");
        params.add("cancel_url", "http://localhost:8084/kakaoPayCancel");
        params.add("fail_url", "http://localhost:8084/kakaoPaySuccessFail");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);

            log.info("" + kakaoPayReadyVO);

            return kakaoPayReadyVO.getNext_redirect_pc_url();

        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "/pay";

    }

    public Object kakaoPayInfo(String pg_token) {

        log.info("KakaoPayInfoVO............................................");
        log.info("-----------------------------");

        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "8195b09f32e4faf23a4af02d67c6fd58");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayReadyVO.getTid());
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", "gorany");
        params.add("pg_token", pg_token);
        params.add("total_amount", "2100");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalVO.class);
            log.info("" + kakaoPayApprovalVO);

            return kakaoPayApprovalVO;

        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
    public KakaoPayCancelVO KakaoCancle(){
        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "8195b09f32e4faf23a4af02d67c6fd58");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayReadyVO.getTid());
        params.add("cancel_amount", "1000");
        params.add("cancel_tax_free_amount", "0");
        params.add("cancel_vat_amount", "0");
        params.add("cancel_available_amount", "2100");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayCancelVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/cancel"), body, KakaoPayCancelVO.class);
            log.info("" + kakaoPayCancelVO);

            return kakaoPayCancelVO;

        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}
