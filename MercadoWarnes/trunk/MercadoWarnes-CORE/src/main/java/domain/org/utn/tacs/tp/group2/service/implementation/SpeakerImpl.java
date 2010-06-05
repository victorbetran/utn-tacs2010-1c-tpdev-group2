package org.utn.tacs.tp.group2.service.implementation;

import org.utn.tacs.tp.group2.service.definition.Speaker;

public class SpeakerImpl implements Speaker{

	public String talkAsMaradona() {
		return "sigan mamando, es bueno para los huesos";
	}

	public Integer mundiales(String pais) {
		if(pais.equals("argentina"))
			return 2;
		if(pais.equals("brazil"))
			return 5;
		return 0;
	}

}
