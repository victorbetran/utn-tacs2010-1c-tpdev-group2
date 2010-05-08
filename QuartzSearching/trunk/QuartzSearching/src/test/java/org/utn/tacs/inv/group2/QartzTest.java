package org.utn.tacs.inv.group2;

import org.junit.After;
import org.junit.Before;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public abstract class QartzTest {

	protected Scheduler scheduler;
	
	@Before
	public void setUp(){
		this.scheduler = this.createScheduler();
		try {
			this.scheduler.start();
		} catch (SchedulerException e) {
			throw new RuntimeException("Imposible inicializar el Scheduler.");
		}
	}

	@After
	public void tearDown(){
		try {
			this.scheduler.shutdown();
		} catch (SchedulerException e) {
			throw new RuntimeException("Imposible detener el Scheduler.");
		}
	}

	// *************************************************************
	// * PROTECTED METHODS
	// *************************************************************
	
	/**
	 * Genera un SchedulerDefault pero permite que el Test subclase genere el que necesite.
	 */
	private Scheduler createScheduler() {
		try {
			return StdSchedulerFactory.getDefaultScheduler();
		} catch (SchedulerException e) {
			throw new RuntimeException("Imposible crear Scheduler.");
		}
	}
	
}
