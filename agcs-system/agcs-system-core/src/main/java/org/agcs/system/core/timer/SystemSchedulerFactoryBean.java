package org.agcs.system.core.timer;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

public class SystemSchedulerFactoryBean extends SchedulerFactoryBean{
	private static final Logger log = Logger.getLogger(SystemSchedulerFactoryBean.class);

	@Override
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();
		String[] trigerrNames = this.getScheduler().getTriggerNames(Scheduler.DEFAULT_GROUP);
		for (String trigerrName : trigerrNames) {
			//存在bug，停止任务无效
			log.info("============任务调度触发器名称："+trigerrName+"==============");
			this.getScheduler().pauseTrigger(trigerrName,Scheduler.DEFAULT_GROUP);
		}
	}
	
}
