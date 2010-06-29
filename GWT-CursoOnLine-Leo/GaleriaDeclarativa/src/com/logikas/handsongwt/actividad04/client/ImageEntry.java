package com.logikas.handsongwt.actividad04.client;

/**
 * Abstracción que representa una imagen con su nombre.
 */
public class ImageEntry {

	//************************************
	//** ATRIBUTTES
	//************************************
	/**
	 * Nombre de la imagen representada.
	 */
	private String name;
	
	/**
	 * Url de la imagen representada.
	 */
	private String url;

	
	//************************************
	//** CONSTRUCTOR
	//************************************
	public ImageEntry(String name, String url) {
		this.setName(name);
		this.setUrl(url);
	}

	
	//************************************
	//** GETTERS & SETTERS
	//************************************
	public String getName() { return this.name; }
	public String getUrl() { return this.url; }
	public void setUrl(String url) { this.url = url; }
	
	/**
	 * Setea el nombre de la imagen.
	 * Si el mismo contiene algun espacio en blanco, se lanza una IllegalArgumentException.
	 */
	public void setName(String name) { 
		if(name.contains(" ")) 
			throw new IllegalArgumentException("El nombre no puede tener espacios en blanco.");
		this.name = name; 
	}
	
	
	//************************************
	//** PUBLIC METHODS
	//************************************
	@Override public boolean equals(Object obj) {
		if(obj == null) return false;
		if(!(obj instanceof ImageEntry)) return false;
		ImageEntry iEntry = (ImageEntry) obj;
		return this.name.toLowerCase().equals(iEntry.name.toLowerCase());
	}
}
