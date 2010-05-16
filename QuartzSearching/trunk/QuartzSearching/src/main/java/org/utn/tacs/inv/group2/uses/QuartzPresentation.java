package org.utn.tacs.inv.group2.uses;

import java.text.ParseException;
import java.util.GregorianCalendar;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.utn.tacs.inv.group2.job.CloseSchedulerJob;
import org.utn.tacs.inv.group2.job.LogJob;

public class QuartzPresentation {

	public static void main(String[] args) {
		
		try {
			
			//CREACION + CONFIGURACION DEL SCHEDULER
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
          
            
            //CREACION DE JOBS
            
            //----PRIMER JOB-----//
            JobDetail jobLog = new JobDetail("jobLog", "group2", LogJob.class);

            //La expresion dice: q se dispare cada 10 segundos, en el minuto 19, de la hora 20, todos los dias.
            Trigger triggerLog = new CronTrigger("triggerLog", "group2", "0/10 19 20 * * ?");
            
            //----SEGUNDO JOB-----//
            JobDetail jobCloseSch = new JobDetail("jobCloseSch", "group2", CloseSchedulerJob.class);
            
            //Se dispara el 16/05/2010 (recuerden que los meses son uno menos, malditos!) al minuto 20:20 hs)
            Trigger triggerCloseSch = new SimpleTrigger("triggerCloseSch", "group2", new GregorianCalendar(2010, 4, 16, 20, 20).getTime());
            
            
            //CARGA DE LOS JOBS & TRIGGERS
            scheduler.scheduleJob(jobLog, triggerLog);
            
            scheduler.scheduleJob(jobCloseSch, triggerCloseSch);
            
            
            //INICIO DEL SCHEDULER
            scheduler.start();

//            scheduler.shutdown();
            
        } 
		catch (SchedulerException se) {
            se.printStackTrace();
        } 
		catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
