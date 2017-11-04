package com.qiaobei.hmp.modules.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;


@Entity
@Table(name = "adverting")
@DynamicInsert(true)
@DynamicUpdate(true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Adverting extends StatusEntity {

    private String name;
    private String content;
    private Position position;
    private String createBy;
    private Long createById;
    private Date createOn;
    private String url;
    private Integer indate;
    private Long orderNo;
    private Type type = Type.Text;
    private int remainDay = 0;
    private DataFile file;
    public Adverting() {
    }
    public Adverting(Long id) {
        super(id);
    }

    @Column(name = "order_no")
    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    @Column(name = "name", length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "content", length = 2000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "position", nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Column(name = "create_by", length = 20)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Column(name = "create_by_id")
    public Long getCreateById() {
        return createById;
    }

    public void setCreateById(Long createById) {
        this.createById = createById;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", length = 6)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Column(name = "url", length = 100)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "indate")
    public Integer getIndate() {
        return indate;
    }

    public void setIndate(Integer indate) {
        this.indate = indate;
    }

    @Column(name = "type")
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Transient
    public int getRemainDay() {
        if (this.indate != null && this.indate > 0) {
            LocalDate createOn = new LocalDate(this.createOn).plusDays(indate);
            LocalDate currDate = new LocalDate(new Date());
            return Days.daysBetween(currDate, createOn).getDays();
        }
        return remainDay;
    }

    public void setRemainDay(int remainDay) {
        this.remainDay = remainDay;
    }

    /**
     * 判断是为 顶部跑马灯信息
     *
     * @return
     */
    @Transient
    public boolean isTopText() {
        //System.out.println(this.position == Position.DoctorTopText || this.position == Position.SystemTopText);
        return this.position == Position.DoctorTopText || this.position == Position.SystemTopText;
    }

    /**
     * 判断是否为中间部分的  系统图片
     *
     * @return
     */
    @Transient
    public boolean isCenterImg() {
        //System.out.println(this.position == Position.SystemCenterImg |);
        return this.position == Position.SystemCenterImg;
    }

    @Transient
    public long getValidityDay() {
        if (indate == null || indate == 0 || createOn == null) {
            return 0;
        }
        Date dayDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createOn);
        calendar.add(Calendar.DATE, indate);
        long si = (calendar.getTimeInMillis() - dayDate.getTime()) / (1000 * 3600 * 24);
        return si;
    }

    @Transient
    public DataFile getFile() {
        return file;
    }

    public void setFile(DataFile file) {
        this.file = file;
    }

    //广告位置
    public enum Position {
        //头部跑马灯 公告
        DoctorTopText,
        //底部广告图片
        DoctorFooterImg,
        //底部广告视频
        DoctorFooterVideo,
        SystemTopText,
        //中间 系统图片
        SystemCenterImg,
        SystemFooterImg,
        SystemFooterVideo
    }

    public enum Type {
        Text,//文字
        Pic, //图片
        video//视频
    }


}