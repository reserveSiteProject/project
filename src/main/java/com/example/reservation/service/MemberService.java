package com.example.reservation.service;

import com.example.reservation.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    public final MemberRepository memberRepository;
}
