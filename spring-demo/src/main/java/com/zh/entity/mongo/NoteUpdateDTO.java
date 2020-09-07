package com.zh.entity.mongo;

import lombok.Data;

@Data
public class NoteUpdateDTO {

    private String id;

    private String content;

    private Boolean important;
}
