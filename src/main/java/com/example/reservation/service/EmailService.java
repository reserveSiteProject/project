package com.example.reservation.service;

public interface EmailService {
    String sendSimpleMessage(String to)throws Exception;

    String sendPassMessage(String memberEmail)throws Exception;
}
