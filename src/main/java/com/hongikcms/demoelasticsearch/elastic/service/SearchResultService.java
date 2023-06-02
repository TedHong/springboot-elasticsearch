package com.hongikcms.demoelasticsearch.elastic.service;

import com.hongikcms.demoelasticsearch.elastic.document.SearchResultDocument;
import com.hongikcms.demoelasticsearch.elastic.repository.SearchResultRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * null.java
 *
 * @author swhong
 * @date 2023-05-30 오후 1:02
 **/
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchResultService {

    private final SearchResultRepo searchResultRepo;
    private final ElasticsearchOperations elasticsearchOperations;

    public Page<SearchResultDocument> findAll(){
        return (Page<SearchResultDocument>) searchResultRepo.findAll();
    }

    public SearchResultDocument saveResult(SearchResultDocument doc){
        return searchResultRepo.save(doc);
    }
    public Optional<SearchResultDocument> getResultById(int id){
        return searchResultRepo.findById(id);
    }

    public void deleteResult(int id){
        searchResultRepo.deleteById(id);
    }

    public void deleteResult(SearchResultDocument doc){
        searchResultRepo.delete(doc);
    }

    public List<SearchResultDocument> searchResult(String keyword) {
        IndexCoordinates indexCoordinates = IndexCoordinates.of("search_result");
        Criteria criteria = new Criteria("keywords").contains(keyword)
                .or(new Criteria("resultData").contains(keyword));
        CriteriaQuery query = new CriteriaQuery(criteria);
        SearchHits<SearchResultDocument> searchHits = elasticsearchOperations.search(query, SearchResultDocument.class, indexCoordinates);
        return searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
    }

}
