package org.utn.tacs.inv.group2.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

/**
 * Este JOB se encarga de hacer el SHUTDOWN al scheduler.
 */
public class CloseSchedulerJob implements Job{

	private static Log log = LogFactory.getLog(CloseSchedulerJob.class);
	
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		
		try {
			Scheduler scheduler =  context.getScheduler();
			scheduler.shutdown();
		} 
		catch (SchedulerException exception) {
			log.error(exception.getMessage());
		}
		
	}

}
