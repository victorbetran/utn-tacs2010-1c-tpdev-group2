package org.utn.tacs.tp.group2.pieza;

public class CategoriaPieza {

	//********************************************
	//** CLASS VARIABLES
	//********************************************
	private static final String STANDARD = "STANDARD";
	private static final String CLASSIC = "CLASSIC";
	private static final String GOLD = "GOLD";
	
	
	//********************************************
	//** ATRIBUTTES
	//********************************************
	/**
	 * Categoria a la cual pertenece <i>this</i> Categoria.
	 */
	@SuppressWarnings("unused")
	private String tipoCategoria;	
	
	
	//********************************************
	//** CONSTRUCTOR
	//********************************************
	/**
	 * Constructor protegido, para no permitir su instanciacion por fuera de la clase.
	 */
	protected CategoriaPieza(String tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}


	//********************************************
	//** PUBLIC CLASS METHODS
	//********************************************
	/**
	 * Retorna una categoria <b>STANDARD</b>.
	 */
	public static CategoriaPieza getCategoriaStandard() {
		return new CategoriaPieza(STANDARD);
	}
	
	/**
	 * Retorna una categoria <b>CLASSIC</b>.
	 */
	public static CategoriaPieza getCategoriaClassic() {
		return new CategoriaPieza(CLASSIC);
	}
	
	/**
	 * Retorna una categoria <b>GOLD</b>.
	 */
	public static CategoriaPieza getCategoriaGold() {
		return new CategoriaPieza(GOLD);
	}
}
