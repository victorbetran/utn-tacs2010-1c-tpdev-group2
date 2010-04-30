package org.utn.tacs.inv.group2;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyJobClass implements Job{

	private static Logger log = LoggerFactory.getLogger(MyJobClass.class);
	
	public MyJobClass() {}
	
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		log.info("SE INICIO EL TRABAJO!!!");
	}

}
