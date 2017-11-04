package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Suggest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SuggestService {

    Page<Suggest> findPage(Pageable page, String content, String tagName);

    void save(Suggest suggest);

    Suggest findById(Long id);

    void deleteSuggest(Suggest suggest);

}
