package org.utn.tacs.tp.group2.utils;

import java.util.UUID;


public class UUIDGenerator {

	private static UUIDGenerator INSTANCE;
	
	
	public static UUIDGenerator getInstance() {
		if(INSTANCE == null)
			INSTANCE = new UUIDGenerator();
		return INSTANCE;
	}

	public Long getId() {
//		long clockSeqAndNode = new UUID().getClockSeqAndNode();
//		if(clockSeqAndNode < 0)
//			clockSeqAndNode = clockSeqAndNode * -1;
//		
//		return Long.valueOf(clockSeqAndNode);
		return new Long(UUID.randomUUID().hashCode());
	}
	
}
