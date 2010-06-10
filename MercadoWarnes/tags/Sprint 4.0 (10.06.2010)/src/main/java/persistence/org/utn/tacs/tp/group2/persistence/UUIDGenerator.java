package org.utn.tacs.tp.group2.persistence;

import java.util.UUID;

public class UUIDGenerator {

	private static UUIDGenerator INSTANCE;
	
	public static UUIDGenerator getInstance() {
		if(INSTANCE == null)
			INSTANCE = new UUIDGenerator();
		return INSTANCE;
	}

	public Long getId() {
		return new Long(Math.abs(UUID.randomUUID().hashCode()));
	}
	
}
