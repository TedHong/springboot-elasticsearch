package com.hongikcms.demoelasticsearch.elastic.repository;

import com.hongikcms.demoelasticsearch.elastic.document.SearchResultDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * null.java
 *
 * @author swhong
 * @date 2023-05-30 오후 1:00
 **/
public interface SearchResultRepo extends ElasticsearchRepository<SearchResultDocument, Integer> {
}
