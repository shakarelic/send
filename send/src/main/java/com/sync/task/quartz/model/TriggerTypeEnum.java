package com.sync.task.quartz.model;

import java.util.Objects;

/**
 * quatz对应的trigger任务类型
 *
 * @author:
 * @date 2016/9/30.
 */
public enum TriggerTypeEnum {
    SIMPLE_TRIGGER("simple_trigger"),
    CRON_TRIGGER("cron_trigger");

    private final String name;

    TriggerTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static TriggerTypeEnum getTriggerTypeEnum(String name) {
        for (TriggerTypeEnum triggerTypeEnum : values()) {
            if (Objects.equals(triggerTypeEnum.getName(), name)) {
                return triggerTypeEnum;
            }
        }
        throw new IllegalArgumentException("triggerTypeEnum name " + name + " is not legal.");
    }
}
