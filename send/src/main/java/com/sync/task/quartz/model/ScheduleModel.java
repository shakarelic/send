package com.sync.task.quartz.model;

import java.util.Date;

/**
 * @author:
 * @date 2016/9/30.
 */
public class ScheduleModel {

    private long id;

    private Date createTime = new Date();

    private Date updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
