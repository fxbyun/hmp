package com.qiaobei.hmp.modules.entity;


import javax.persistence.*;


@Entity
@Table(name = "doctor_medicine")
public class DoctorMedicine extends IdEntity {

    private Doctor doctor;
    private Medicine medicine;

    public DoctorMedicine() {
    }

    public DoctorMedicine(Long id) {
        super(id);
    }

    public DoctorMedicine(Doctor doctor, Medicine medicine) {
        this.doctor = doctor;
        this.medicine = medicine;
    }


    @ManyToOne(cascade = CascadeType.REFRESH, optional = true)
    @JoinColumn(name = "doctor_id")
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "medicine_id")
    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }


}
