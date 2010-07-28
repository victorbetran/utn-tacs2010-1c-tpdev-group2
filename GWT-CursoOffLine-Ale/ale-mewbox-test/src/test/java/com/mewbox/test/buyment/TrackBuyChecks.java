package com.mewbox.test.buyment;

import org.junit.Before;
import org.junit.Test;

import com.mewbox.domain.Track;
import com.mewbox.test.common.BussinesTest;

public class TrackBuyChecks extends BussinesTest{

	private Track track;
	
	@Before
	public void setUp(){
		this.track = new Track("name","12"); 
	}
	
	// *******************************************************************************
	// *** TESTS
	// *******************************************************************************
	
	@Test
	public void buyWithoutUser() {
		
		
	}

	@Test
	public void buyWithOutCreditCard() {
		
	}
	
	@Test
	public void buyWithOutSdCard() {
		
	}
	
	
	
}
