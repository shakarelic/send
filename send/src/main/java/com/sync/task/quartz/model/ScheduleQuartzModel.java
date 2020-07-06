package com.sync.task.quartz.model;

import java.util.Date;

/**
 * @author:
 * @date 2016/9/30.
 */
public class ScheduleQuartzModel extends ScheduleModel {

    private String className;

    private String cronExpression;

    private String triggerType;

    private String status;

    private String data;
    
    private Date updateTime;

    @Override
    public String toString() {
        return "ScheduleQuartzModel{" +
                "className='" + className + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", triggerType='" + triggerType + '\'' +
                ", status='" + status + '\'' +
                ", data='" + data + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }

    public static final class QuartzScheduleModelBuilder {
        private String className;
        private String cronExpression;
        private String triggerType;
        private String status;
        private String data;
        private Date updateTime;

        public static QuartzScheduleModelBuilder newBuilder() {
            return new QuartzScheduleModelBuilder();
        }

        public QuartzScheduleModelBuilder withClassName(String className) {
            this.className = className;
            return this;
        }

        public QuartzScheduleModelBuilder withCronExpression(String cronExpression) {
            this.cronExpression = cronExpression;
            return this;
        }

        public QuartzScheduleModelBuilder withTriggerType(String triggerType) {
            this.triggerType = triggerType;
            return this;
        }

        public QuartzScheduleModelBuilder withStatus(String status) {
            this.status = status;
            return this;
        }

        public QuartzScheduleModelBuilder withData(String data) {
            this.data = data;
            return this;
        }

        public QuartzScheduleModelBuilder withUpdateTime(Date updateTime) {
        	this.updateTime = updateTime;
        	return this;
        }

        public ScheduleQuartzModel build() {
            ScheduleQuartzModel scheduleQuartzModel = new ScheduleQuartzModel();
            scheduleQuartzModel.setClassName(this.className);
            scheduleQuartzModel.setStatus(this.status);
            scheduleQuartzModel.setCronExpression(this.cronExpression);
            scheduleQuartzModel.setTriggerType(this.triggerType);
            scheduleQuartzModel.setData(this.data);
            scheduleQuartzModel.setUpdateTime(this.updateTime);
            return scheduleQuartzModel;
        }
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
