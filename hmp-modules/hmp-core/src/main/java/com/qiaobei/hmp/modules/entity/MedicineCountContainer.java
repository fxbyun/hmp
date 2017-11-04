package com.qiaobei.hmp.modules.entity;

/**
 * Created by Administrator on 2017/2/14 0014.
 */
public class MedicineCountContainer {
    private Long medicinePrivateId;
    private Long medicineId;
    private Long countSize;
    private Long doctorId;

    public MedicineCountContainer(Long medicinePrivateId, Long medicineId, Long countSize, Long doctorId) {
        this.medicinePrivateId = medicinePrivateId;
        this.medicineId = medicineId;
        this.countSize = countSize;
        this.doctorId = doctorId;
    }

    public Long getMedicinePrivateId() {
        return medicinePrivateId;
    }

    public void setMedicinePrivateId(Long medicinePrivateId) {
        this.medicinePrivateId = medicinePrivateId;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public Long getCountSize() {
        return countSize;
    }

    public void setCountSize(Long countSize) {
        this.countSize = countSize;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
}
