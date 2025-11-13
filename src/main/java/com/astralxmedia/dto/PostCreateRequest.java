package com.astralxmedia.dto;

import lombok.Data;

@Data
public class PostCreateRequest {
    private String title;
    private String content;
}
