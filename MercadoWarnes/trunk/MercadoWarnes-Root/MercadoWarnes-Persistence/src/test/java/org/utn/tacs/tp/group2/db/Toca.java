package org.utn.tacs.tp.group2.db;

import com.eaio.uuid.UUID;

public class Toca {

	public static void main(String[] args) {
		UUID uuid = new UUID();
		System.out.println(uuid.getTime());
		System.out.println(uuid.getClockSeqAndNode());
	}
	
}
