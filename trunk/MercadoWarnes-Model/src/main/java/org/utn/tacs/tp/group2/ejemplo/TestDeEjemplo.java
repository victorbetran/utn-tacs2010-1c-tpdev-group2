package org.utn.tacs.tp.group2.ejemplo;

import org.junit.Before;
import org.junit.Test;

public class TestDeEjemplo {

	@Before
	private void levantarEscenario() {
		//Este método se ejecuta antes que cualquier Test. Si hay mas de un test se va a ejecutar mas de una vez
		//entonces.
	}
	
	@Test
	private void soyUnTest() {
		//Este es un test donde se va a hacer Asserts y cosas cosmozónicas.
	}

	@Test(expected=RuntimeException.class)
	private void soyUnTestQueSoloDaVerdeSiLanzoUnaExcepcion() {
		//Sólo da verde si la excepción que se declaró en el annotation salta en la ejecución
		//del código que va acá.
	}
	
}
