package com.hongikcms.demoelasticsearch.elastic.controller;

import com.hongikcms.demoelasticsearch.elastic.document.SearchResultDocument;
import com.hongikcms.demoelasticsearch.elastic.service.SearchResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * null.java
 *
 * @author swhong
 * @date 2023-05-30 오후 3:14
 **/

@RequiredArgsConstructor
@RequestMapping("/")
@RestController
public class SearchResultController {
    private final SearchResultService searchResultService;

    @PostMapping("/save")
    public ResponseEntity<SearchResultDocument> createSearchResult(
            @RequestParam("keywords") String keywords,
            @RequestParam("resultData") String resultData
    ) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        SearchResultDocument searchResult = null;
        try {
            searchResult = SearchResultDocument.builder()
                    .keywords(keywords)
                    .resultData(resultData)
                    .createDate(format.parse(format.format(new Date())))
                    .build();
        } catch (ParseException e) {
            System.out.println(e.toString());
        }

        SearchResultDocument createdSearchResult = searchResultService.saveResult(searchResult);
        return new ResponseEntity<>(createdSearchResult, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SearchResultDocument> getSearchResultById(@PathVariable("id") int id) {
        return searchResultService.getResultById(id)
                .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/findall")
    public ResponseEntity<Page<SearchResultDocument>> getAllSearchResults() {
        Page<SearchResultDocument> searchResults = searchResultService.findAll();
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSearchResult(@PathVariable("id") int id) {
        searchResultService.deleteResult(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SearchResultDocument>> searchByKeyword(@RequestParam("keyword") String keyword) {
        List<SearchResultDocument> searchResults = searchResultService.searchResult(keyword);
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }


}
