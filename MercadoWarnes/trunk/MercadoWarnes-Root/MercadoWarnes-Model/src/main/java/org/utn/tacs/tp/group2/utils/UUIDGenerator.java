package org.utn.tacs.tp.group2.utils;

import com.eaio.uuid.UUID;

public class UUIDGenerator {

	private static UUIDGenerator INSTANCE;
//	private long seed;
	
	private UUIDGenerator() {
//		this.seed = 0;
	}

	public static UUIDGenerator getInstance() {
		if(INSTANCE == null)
			INSTANCE = new UUIDGenerator();
		return INSTANCE;
	}

	public Long getId() {
//		return Long.valueOf(this.seed ++);
		return new Long(new UUID().getClockSeqAndNode());
	}
	
}
