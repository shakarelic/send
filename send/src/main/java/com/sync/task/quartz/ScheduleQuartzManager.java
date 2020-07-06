package com.sync.task.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务管理器
 *
 * @author:
 */
@Component("scheduleQuartzManager")
public class ScheduleQuartzManager {

	private static final Logger logger = LoggerFactory.getLogger(ScheduleQuartzManager.class);

	@Scheduled(cron = "0/1 0/1 * * * ?")
	public void reSchedule() {
		System.out.println(123);
	}

}
