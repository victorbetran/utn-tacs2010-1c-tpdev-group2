package org.utn.tacs.inv.group2.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Este Job loguea en la consola cuando es ejecutado
 */
public class LogJob implements Job{

	private static Log log = LogFactory.getLog(LogJob.class);
	
	public LogJob() {}
	
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		log.info("Corrio MyJob :D");
	}

}
