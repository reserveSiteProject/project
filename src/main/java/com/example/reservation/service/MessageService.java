package com.example.reservation.service;
import com.example.reservation.dto.MemberDTO;
import com.example.reservation.dto.ReserveDTO;
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

    @Autowired
    private MemberService memberService;

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private RoomService roomService;

    public MessageService() {
        // 반드시 계정 내 등록된 유효한 API 키, API Secret Key를 입력해주셔야 합니다!
        this.nurigoMessageService = NurigoApp.INSTANCE.initialize("NCSGYUYPMCBURYWS", "PMLISVFX12TRF8GE2AREJ0OXHVECY7QR", "https://api.coolsms.co.kr");
    }
    //예약 성공시 발송 문자
    public SingleMessageSentResponse sendOneReservationComplete(String memberMobile, Long reserveId) {
        Message message = new Message();

        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        //발신자 번호 : 태훈 고정
        message.setFrom("01088657126");
        //수신자 번호는 변수 설정
        String setTo = memberMobile;
        message.setTo(setTo);
        //고객 이름 가져오기
        String memberName = memberService.findByMemberMobile(memberMobile).getMemberName();
        // 예약 정보 가져오기 (날짜)
        String checkInDate  = reserveService.findById(reserveId).getCheckInDate();
        String checkOutDate = reserveService.findById(reserveId).getCheckOutDate();

        //예약 정보 가져오기 (roomType)
        Long roomId = reserveService.findById(reserveId).getRoomId();
        String roomType = roomService.findById(roomId).getRoomType();

        message.setText("[혼저옵서예] 예약 완료 확인문자입니다. 감사합니다!"
                        + memberName + "고객님, " + roomType + "(" + checkInDate + " ~ " + checkOutDate + ")" +
                "예약 완료 되셨습니다! ");

        SingleMessageSentResponse response = this.nurigoMessageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);

        return response;

    }

    //예약 취소시 발송 문자
    public SingleMessageSentResponse sendOneReservationCancel(Long memberId, Long reserveId) {
        Message message = new Message();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        String memberMobile = memberService.findById(memberId).getMemberMobile();
        message.setFrom("01088657126");
        String setTo = memberMobile;
        message.setTo(setTo);

        //고객 이름 가져오기
        String memberName = memberService.findByMemberMobile(memberMobile).getMemberName();
        // 예약 정보 가져오기 (날짜)
        String checkInDate  = reserveService.findById(reserveId).getCheckInDate();
        String checkOutDate = reserveService.findById(reserveId).getCheckOutDate();

        //예약 정보 가져오기 (roomType)
        Long roomId = reserveService.findById(reserveId).getRoomId();
        String roomType = roomService.findById(roomId).getRoomType();


        message.setText("[혼저옵서예] 예약 취소 확인문자입니다!"
         + memberName + "고객님, " + roomType + "(" + checkInDate + " ~ " + checkOutDate + ")" +
                "예약 취소 되셨습니다! ");

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
