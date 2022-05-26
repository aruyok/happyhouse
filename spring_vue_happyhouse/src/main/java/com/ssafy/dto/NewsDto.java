package com.ssafy.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NewsDto {
    String imageUrl;
    String title;
    String content;
    String link;
}