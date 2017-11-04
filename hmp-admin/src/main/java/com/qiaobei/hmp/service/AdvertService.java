package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Advert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdvertService {

    Page<Advert> findPage(Pageable page);

    void save(Advert advert, MultipartFile file);

    Advert findById(Long id);

    Advert getAdvertByPosition(Advert.Position position);

    void deleteAdvert(Advert advert);

}
