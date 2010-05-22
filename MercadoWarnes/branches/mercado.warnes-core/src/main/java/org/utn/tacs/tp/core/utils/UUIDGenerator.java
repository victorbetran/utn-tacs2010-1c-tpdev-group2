package org.utn.tacs.tp.core.utils;

import java.util.UUID;

public class UUIDGenerator {

	private static UUIDGenerator INSTANCE;
	
	public static UUIDGenerator getInstance() {
		if(INSTANCE == null)
			INSTANCE = new UUIDGenerator();
		return INSTANCE;
	}

	public Long getId() {
		return new Long(UUID.randomUUID().hashCode());
	}
	
}
