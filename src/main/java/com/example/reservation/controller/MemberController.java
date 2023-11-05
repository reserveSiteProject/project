package com.example.reservation.controller;

import com.example.reservation.dto.MemberDTO;
import com.example.reservation.service.EmailService;
import com.example.reservation.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final EmailService emailService;

    @GetMapping("/save")
    public String save(){
        return "memberPages/memberSave";
    }

    @GetMapping("/findPass")
    public String findPass(){
        return "memberPages/findPass";
    }

    @PostMapping("/findPass")
    public ResponseEntity findPass(@RequestParam("memberEmail") String memberEmail) throws Exception{
        MemberDTO memberDTO = memberService.findByMemberEmail(memberEmail);
        if(memberDTO!=null && memberDTO.getKakao()==0){ // 카카오이메일이 아닌경우에만 findPass실행
            String confirm = emailService.sendPassMessage(memberEmail);
            memberDTO.setMemberPassword(confirm);
            System.out.println("confirm = " + confirm);
            memberService.update(memberDTO);
            System.out.println(memberDTO);
            return new ResponseEntity<>(memberDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);
        return "memberPages/memberLogin";
    }

    @GetMapping("/kakao/save")
    public String save(Model model, @RequestParam("memberEmail") String memberEmail, @RequestParam("nickName") String nickName) {
        String email[] = memberEmail.split("@");
        model.addAttribute("kakao", 1);
        model.addAttribute("memberEmail1", email[0]);
        model.addAttribute("memberEmail2", email[1]);
        model.addAttribute("nickName", nickName);
        return "memberPages/memberSave";
    }

    @GetMapping("/login")
    public String login(){
        return "memberPages/memberLogin";
    }

    @GetMapping("/emailCheck")
    public ResponseEntity duplicateCheck(@RequestParam("memberEmail") String memberEmail) {
        System.out.println("memberEmail = " + memberEmail);
        MemberDTO memberDTO = memberService.duplicateCheck(memberEmail);
        if(memberDTO!=null){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/nicknameCheck")
    public ResponseEntity nicknameCheck(@RequestParam("nickName") String nickName) {
        System.out.println("nickName = " + nickName);
        MemberDTO memberDTO = memberService.nicknameCheck(nickName);
        if(memberDTO!=null){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/emailConfirm")
    public ResponseEntity emailConfirm(@RequestParam("memberEmail") String memberEmail) throws Exception {
        String confirm = emailService.sendSimpleMessage(memberEmail);
        return new ResponseEntity<>(confirm, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(@ModelAttribute MemberDTO memberDTO, @RequestParam("keep") boolean keep, HttpSession session, HttpServletResponse response){
        MemberDTO login = memberService.login(memberDTO);
        if(login!=null){
            session.setAttribute("memberDTO", login);
            System.out.println("login = " + login);
            session.setAttribute("loginEmail", memberDTO.getMemberEmail());
            System.out.println("loginEmail = " + memberDTO.getMemberEmail());
            if(keep){
                Cookie cookie=new Cookie("memberEmail", memberDTO.getMemberEmail());
                cookie.setMaxAge(60*60*24*7);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            return new ResponseEntity<>(memberDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //카카오용
    @PostMapping("/kakao/login")
    public ResponseEntity login(@RequestParam("memberEmail") String memberEmail, HttpSession session){
        System.out.println("memberEmail = " + memberEmail);
        MemberDTO memberDTO = memberService.findByMemberEmail(memberEmail);
        MemberDTO login = memberService.login(memberDTO);
        if(login!=null){
            session.setAttribute("loginEmail", memberDTO.getMemberEmail());
            session.setAttribute("memberDTO", memberDTO);
            System.out.println("login = " + login);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/kakao")
    public ResponseEntity findByEmail(@RequestParam("memberEmail") String memberEmail) {
        System.out.println("memberEmail = " + memberEmail);
        MemberDTO memberDTO = memberService.findByMemberEmail(memberEmail);
        if(memberDTO!=null){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response){
        session.removeAttribute("memberDTO");
        session.removeAttribute("loginEmail");
        Cookie cookie=new Cookie("memberEmail", "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/member/login";
    }
}
