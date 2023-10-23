package com.example.reservation.controller;

import com.example.reservation.dto.CouponDTO;
import com.example.reservation.dto.MemberDTO;
import com.example.reservation.dto.ReviewDTO;
import com.example.reservation.service.MyPagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/myPages")
public class    MyPagesController {
    private final MyPagesService myPagesService;
    // 마이페이지 리뷰목록 출력
//    @GetMapping("/review")
//    public String review(HttpSession session, Model model){
//        Object memberDTO1 = session.getAttribute("memberDTO");
//        MemberDTO memberDTO = (MemberDTO) memberDTO1;
//        List<ReviewDTO> reviewList = myPagesService.findAll(memberDTO);
//        System.out.println("reviewList = " + reviewList);
//        model.addAttribute("reviewList", reviewList);
//        return "MyPages/review";
//    }
    // 마이페이지 리뷰 상세페이지 화면 출력
    @GetMapping("/review/{id}")
    public String reviewDetail(@PathVariable("id") Long id, Model model){
        ReviewDTO reviewDTO = myPagesService.findById(id);
        model.addAttribute("review",reviewDTO);
        return "MyPages/reviewDetail";
    }
    // 마이페이지 리뷰 수정화면 출력
    @GetMapping("/review/update/{id}")
    public String reviewUpdate(@PathVariable("id") Long id, Model model){
        System.out.println("id = " + id);
        ReviewDTO reviewDTO = myPagesService.findById(id);
        System.out.println("reviewDTO = " + reviewDTO);
        model.addAttribute("review", reviewDTO);
        return "MyPages/reviewUpdate";
    }
    // 마이페이지 리뷰 수정 처리
    @PutMapping("/review")
    public ResponseEntity reviewUpdate(@ModelAttribute ReviewDTO reviewDTO){
        System.out.println("reviewDTO = " + reviewDTO);
        boolean result = myPagesService.save(reviewDTO);
        if(result){
            return new ResponseEntity<>("수정 완료", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("수정 실패", HttpStatus.CONFLICT);
        }
    }
    // 마이페이지 리뷰 삭제 처리
    @DeleteMapping("/review/{id}")
    public ResponseEntity reviewDelete(@PathVariable("id") Long id){
        myPagesService.deleteById(id);
        return new ResponseEntity<>("삭제완료",HttpStatus.OK);
    }
    // 마이페이지 내 정보 화면 출력
    @GetMapping
    public String myInfo(HttpSession session, Model model){
        Object memberDTO = session.getAttribute("memberDTO");
        MemberDTO memberDTO1 = myPagesService.findMember((MemberDTO) memberDTO);
        model.addAttribute("member", memberDTO1);
        return "MyPages/myInfo";
    }
    // 마이페이지 내 정보 수정 처리 axios로 보낼 주소 처리
    @PostMapping
    public ResponseEntity myInfoUpdate(@ModelAttribute MemberDTO memberDTO){
        boolean result = myPagesService.saveMember(memberDTO);
        if(result){
            return new ResponseEntity<>("정보수정완료", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("정보수정실패", HttpStatus.CONFLICT);
        }
    }
    // 마이페이지 비밀번호 변경 페이지 출력
    @GetMapping("/change/{id}")
    public String passChange(@PathVariable("id") Long id, Model model){
        MemberDTO memberDTO = myPagesService.findByMemberId(id);
        model.addAttribute("member", memberDTO);
        return "MyPages/passChange";
    }
    // 마이페이지 비밀번호 변경 처리
    @PostMapping("/change")
    public ResponseEntity passChange(@ModelAttribute MemberDTO memberDTO){
        boolean result = myPagesService.saveMember(memberDTO);
        return new ResponseEntity("수정완료", HttpStatus.OK);
    }
    @GetMapping("/coupon")
    public String coupon(HttpSession session, Model model){
        Object memberDTO = session.getAttribute("memberDTO");
        List<CouponDTO> couponList = myPagesService.coupon((MemberDTO) memberDTO);
        model.addAttribute("coupon", couponList);
        return "MyPages/coupon";
    }
}