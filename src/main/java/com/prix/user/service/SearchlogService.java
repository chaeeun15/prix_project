package com.prix.user.service;

import com.prix.user.DTO.SearchlogDTO;
import com.prix.user.Entity.SearchlogEntity;

import java.time.LocalDate;
import java.util.List;

public interface SearchlogService {

    int saveSearchlog(SearchlogEntity searchlogEntity);

    SearchlogEntity findById(Integer id);

    List<SearchlogDTO> getSearchlogList();

    List<SearchlogDTO> findByUserID(Integer userId);

    Integer getUserIDByResult(String result);

    LocalDate getDateByResult(String result);

    String getTitleByResult(String result);
}
