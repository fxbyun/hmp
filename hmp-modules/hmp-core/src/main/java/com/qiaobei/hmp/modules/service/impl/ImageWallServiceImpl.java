package com.qiaobei.hmp.modules.service.impl;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.ImageWall;
import com.qiaobei.hmp.modules.repository.ImageWallDao;
import com.qiaobei.hmp.modules.service.ImageWallService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("imageWallService")
@Transactional
public class ImageWallServiceImpl implements ImageWallService {

    @Resource
    private ImageWallDao imageWallDao;

    @Autowired
    private HttpServletRequest request;

    @Override
    public void save(List<ImageWall> imageWallList) {
        imageWallDao.save(imageWallList);
    }

    @Override
    public List<ImageWall> findByDoctorId(Doctor doctor) {

        return imageWallDao.findByDoctorId(doctor.getId());
    }

    @Override
    public ImageWall findFirstImage(Doctor doctor, ImageWall.ImageLevel level) {
        return imageWallDao.findByDoctorAndLevel(doctor.getId(), level);
    }

    @Override
    public List<String> getImageUrlList(List<ImageWall> imageWallList) {
        List<String> imageUrlList = new ArrayList<>();
        if (imageWallList != null && !imageWallList.isEmpty()) {
            for (ImageWall image : imageWallList) {
                if (image != null) {

                    String fileName = image.getFileName();
                    ServletContext sc = ContextLoader.getCurrentWebApplicationContext().getServletContext();
                    String dir = sc.getRealPath("/temp");
                    try {
                        File f = new File(dir + "/" + fileName);
                        imageUrlList.add(fileName);
                        if (f.exists()) {
                            continue;
                            //f.delete();
                        }
                        byte[] content = image.getContent();
                        FileUtils.writeByteArrayToFile(f, content);
                    } catch (IOException e) {
                        System.out.println("形象墙图片写出失败！");
                    }
                }
            }
        }
        return imageUrlList;
    }

    @Override
    public ImageWall findById(Long imageId) {
        return imageWallDao.getOne(imageId);
    }

    @Override
    public boolean deleteImageWall(Long imageId) {
        ImageWall imageWall = imageWallDao.getOne(imageId);
        if(imageWall==null){

            return false;
        }
        imageWallDao.delete(imageWall);
        return true;
    }

    @Override
    public void save(ImageWall imageWall) {
        imageWallDao.save(imageWall);
    }

    @Override
    public ImageWall findOrderImageId(Long doctorId) {
        List<ImageWall> imageWallList = imageWallDao.findImageOrderById(doctorId);
        if(CollectionUtils.isEmpty(imageWallList)){
            return null;
        }
        return imageWallList.get(0);
    }
}
