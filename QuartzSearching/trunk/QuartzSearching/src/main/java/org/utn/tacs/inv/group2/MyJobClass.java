package org.utn.tacs.inv.group2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJobClass implements Job{

	private static Log log = LogFactory.getLog(MyJobClass.class);
	
	public MyJobClass() {}
	
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		log.info("SE INICIO EL TRABAJO!!!");
	}

}
