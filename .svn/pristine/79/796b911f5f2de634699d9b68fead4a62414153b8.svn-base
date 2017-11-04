package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Adverting;
import com.qiaobei.hmp.modules.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface AdvertingService {

    Page<Adverting> findPage(Pageable page);

    void save(Adverting advert, MultipartFile file);

    Adverting findById(Long id);

    Adverting getAdvertByPosition(Adverting.Position position);

    void deleteAdvert(Adverting advert);

    Page<Adverting> findPage(Pageable pageable, User user);
}
