package org.utn.tacs.tp.group2.service.implementation;

import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;

import org.utn.tacs.tp.group2.service.definition.Speaker;

@WebService(endpointInterface="org.utn.tacs.tp.group2.service.definition.Speaker")
public class SpeakerImpl implements Speaker{

	public String talkAsMaradona() {
		return "sigan mamando, es bueno para los huesos";
	}

	@RequestWrapper(targetNamespace="http://demo.iona.com/types",
            className="java.lang.String")
	public Integer mundiales(String pais) {
		if(pais.equals("argentina"))
			return 2;
		if(pais.equals("brazil"))
			return 5;
		return 0;
	}

}
