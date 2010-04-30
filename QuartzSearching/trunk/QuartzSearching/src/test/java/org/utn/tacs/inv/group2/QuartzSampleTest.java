package org.utn.tacs.inv.group2;

import java.util.Date;

import org.junit.Test;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;


public class QuartzSampleTest {

	@Test
	public void simpleTest(){
		try {
            // Grab the Scheduler instance from the Factory 
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            //NO HACE FALTA LA CONFIGURACION DESDE LA VERSION 1.8.0
            //reset log4j config for FileAppender
//            PropertyConfigurator.configure("src/main/resources/log4j.properties");
            
            // and start it off
            scheduler.start();

            // Define job instance
            JobDetail job = new JobDetail("job1", "group1", MyJobClass.class);
            	
            // Define a Trigger that will fire "now"
            Trigger trigger = new SimpleTrigger("trigger1", "group1", new Date());
            	
            // Schedule the job with the trigger
            scheduler.scheduleJob(job, trigger);
            
            scheduler.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
	}
}
