package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Adverting;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdvertingService {

    Page<Adverting> findPage(Pageable page);

    void save(Adverting advert, MultipartFile file);

    Adverting findById(Long id);

    Adverting getAdvertByPosition(Adverting.Position position);

    void deleteAdvert(Adverting advert);

    Page<Adverting> findPage(Pageable pageable, User user);

    List<Adverting> findAllByDoctorAndSystem(Doctor doctor, User user);

    List<Adverting> findAllByCreateUserIdAndType(Long id, Adverting.Position positionType);

    Long findAdvertingPicAndTextCount(Doctor doctor, User user);
}
