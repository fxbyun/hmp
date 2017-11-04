package com.qiaobei.hmp.modules.repository;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.ImageWall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import javax.print.Doc;
import java.util.List;

/**
 * Created by Administrator on 2016/9/29 0029.
 */
public interface ImageWallDao extends JpaRepository<ImageWall, Long>, JpaSpecificationExecutor<ImageWall> {

    @Query(value = "select * from image_wall image where image.doctor_id=?1 ", nativeQuery = true)
    public List<ImageWall> findByDoctorId(Long doctorId);

    @Query(value = "select image from ImageWall image where image.doctorId=?1 and image.level=?2 ")
    public ImageWall findByDoctorAndLevel(Long doctorId, ImageWall.ImageLevel level);

    @Query(value = "select image from ImageWall image where image.doctorId=?1 and image.level<>0 order by image.id asc ")
    public List<ImageWall> findImageOrderById(Long doctorId);

}
