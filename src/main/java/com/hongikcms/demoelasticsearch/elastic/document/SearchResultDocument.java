package com.hongikcms.demoelasticsearch.elastic.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * null.java
 *
 * @author swhong
 * @date 2023-05-30 오후 12:57
 **/
@Getter
@Builder
@AllArgsConstructor
@Document(indexName = "search_result")
public class SearchResultDocument {
    @Id
    @Field(type = FieldType.Integer)
    private int id;
    @Field(type = FieldType.Text)
    private String keywords;
    @Field(type = FieldType.Text)
    private String resultData;

    //    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    @Field(type = FieldType.Date, format = {DateFormat.date_hour_minute_second_millis, DateFormat.epoch_millis})
    private Date createDate;

}