package com.zh.entity.mongo;

import lombok.Data;

import java.io.Serializable;

@Data
public class NoteAddDTO implements Serializable {

    private String content;

    private Boolean important;
}
