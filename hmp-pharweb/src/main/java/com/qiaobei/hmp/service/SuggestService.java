package com.qiaobei.hmp.service;


import com.qiaobei.hmp.modules.entity.Suggest;

import java.util.List;

public interface SuggestService {

    List<Suggest> findByTagName(String tagName);

}
