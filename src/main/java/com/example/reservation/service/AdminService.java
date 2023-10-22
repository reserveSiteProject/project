package com.example.reservation.service;

import com.example.reservation.dto.MemberDTO;
import com.example.reservation.entity.MemberEntity;
import com.example.reservation.repository.MemberRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class AdminService {
    private final MemberRepository memberRepository;

}