package com.qiaobei.hmp.service;


import com.qiaobei.hmp.modules.entity.Advert;
import com.qiaobei.hmp.modules.entity.StatusEntity;

import java.util.List;

public interface AdvertService {

    List<Advert> getAdvertByPositionAndStatus(Advert.Position position, StatusEntity.Status status);

}
