package org.utn.tacs.tp.group2.utils;

import com.eaio.uuid.UUID;

public class UUIDGenerator {

	private static UUIDGenerator INSTANCE;
	
	private UUIDGenerator() {}
	
	public static UUIDGenerator getInstance() {
		if(INSTANCE == null)
			INSTANCE = new UUIDGenerator();
		return INSTANCE;
	}

	public Long getId() {
		return Long.valueOf(new UUID().getClockSeqAndNode());
	}
	
}
