package com.example.reservation.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewDTO {
    private Long id;
    private String reviewTitle;
    private String reviewContents;
    private String reviewWriter;
    private double reviewStar;
    private int hits;

    private List<MultipartFile> reviewFile = new ArrayList<>();
    private int fileAttached;
    private List<String> originalFileName = new ArrayList<>();
    private List<String> storedFileName = new ArrayList<>();
}


