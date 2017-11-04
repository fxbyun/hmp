package com.qiaobei.hmp.modules.service;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.ImageWall;

import java.util.List;

/**
 * Created by Administrator on 2016/9/29 0029.
 */
public interface ImageWallService {

    public void save(List<ImageWall> imageWallList);

    public void save(ImageWall imageWall);

    public List<ImageWall> findByDoctorId(Doctor doctor);

    public ImageWall findFirstImage(Doctor doctor, ImageWall.ImageLevel level);

    public List<String> getImageUrlList(List<ImageWall> imageWallList);

    public boolean deleteImageWall(Long imageId);

    public ImageWall findById(Long imageId);

    public ImageWall findOrderImageId(Long doctorId);
}
