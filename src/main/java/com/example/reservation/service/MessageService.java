package com.example.reservation.service;

import com.example.reservation.dto.MemberDTO;
import com.example.reservation.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class MessageService {

    final DefaultMessageService nurigoMessageService;

//    public final MemberDTO memberDTO;


    public MessageService() {
        // 반드시 계정 내 등록된 유효한 API 키, API Secret Key를 입력해주셔야 합니다!
        this.nurigoMessageService = NurigoApp.INSTANCE.initialize("NCSGYUYPMCBURYWS", "PMLISVFX12TRF8GE2AREJ0OXHVECY7QR", "https://api.coolsms.co.kr");
    }


    //예약 성공시 발송 문자
    public SingleMessageSentResponse sendOneReservationComplete(String memberMobile) {
        Message message = new Message();
        String setTo = memberMobile;
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom("01088657126");
        message.setTo(setTo);
        message.setText("이거는 입에서 나는 소리가 아니여");

        SingleMessageSentResponse response = this.nurigoMessageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);

        return response;

    }

    //예약 취소시 발송 문자
    public SingleMessageSentResponse sendOneReservationCancle() {
        Message message = new Message();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom("01088657126");
        message.setTo("01088657126");
        message.setText("이거는 입에서 나는 소리가 아니여");

        SingleMessageSentResponse response = this.nurigoMessageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);

        return response;
    }

    //예약대기 발송문자
    public SingleMessageSentResponse sendOneReservationWait() {
        Message message = new Message();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom("01088657126");
        message.setTo("01088657126");
        message.setText("이거는 입에서 나는 소리가 아니여");

        SingleMessageSentResponse response = this.nurigoMessageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);

        return response;
    }

}
