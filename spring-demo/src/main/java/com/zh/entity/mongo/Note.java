package com.zh.entity.mongo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;

@Data
@Document
public class Note implements Serializable {

    @MongoId
    private String id;

    private String content;

    private Boolean important;

}
