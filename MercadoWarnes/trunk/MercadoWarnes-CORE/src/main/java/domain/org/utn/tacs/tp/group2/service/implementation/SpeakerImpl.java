package org.utn.tacs.tp.group2.service.implementation;

import javax.jws.WebService;

import org.utn.tacs.tp.group2.service.definition.Speaker;

@WebService(endpointInterface="org.utn.tacs.tp.group2.service.definition.Speaker")
public class SpeakerImpl implements Speaker{

	// http://127.0.0.1:8080/mercadowarnes/speaker/talkAsMaradona
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
