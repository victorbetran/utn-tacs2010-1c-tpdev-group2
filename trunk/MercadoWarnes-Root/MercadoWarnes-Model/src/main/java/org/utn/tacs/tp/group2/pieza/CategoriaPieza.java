package org.utn.tacs.tp.group2.pieza;

public class CategoriaPieza {

	//********************************************
	//** CLASS VARIABLES
	//********************************************
	private static final String STANDARD = "STANDARD";
	private static final String CLASSIC = "CLASSIC";
	private static final String GOLD = "GOLD";
	private static final String PREMIUM = "PREMIUM";
	
	
	//********************************************
	//** ATRIBUTTES
	//********************************************
	/**
	 * Categoria a la cual pertenece <i>this</i> Categoria.
	 */
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
	public static CategoriaPieza getStandar() {
		return new CategoriaPieza(STANDARD);
	}
	
	/**
	 * Retorna una categoria <b>CLASSIC</b>.
	 */
	public static CategoriaPieza getClassic() {
		return new CategoriaPieza(CLASSIC);
	}
	
	/**
	 * Retorna una categoria <b>GOLD</b>.
	 */
	public static CategoriaPieza getGold() {
		return new CategoriaPieza(GOLD);
	}
	
	/**
	 * Retorna una categoria <b>PREMIUM</b>.
	 */
	public static CategoriaPieza getPremium() {
		return new CategoriaPieza(PREMIUM);
	}
	
	
	//********************************************
	//** OVERWRITTEN METHODS
	//********************************************
	@Override public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}
		if(obj == this){
			return true;
		}
		if(!(obj instanceof CategoriaPieza)){
			return false;
		}
		CategoriaPieza categoriaPieza = (CategoriaPieza) obj;
		return this.tipoCategoria.equals(categoriaPieza.tipoCategoria);
	}
}
