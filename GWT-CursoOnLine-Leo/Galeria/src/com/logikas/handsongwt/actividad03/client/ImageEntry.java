package com.logikas.handsongwt.actividad03.client;


public class ImageEntry {

	private String name;
	private String URL;
	
	public ImageEntry(String name, String URL) {
		this.setName(name);
		this.setURL(URL);
	}

	public void setURL(String URL) {
		this.URL = URL;
	}

	private void setName(String name) {
		if(name.contains(" ")) 
			throw new IllegalArgumentException("El nombre no puede tener espacios en blanco.");
		this.name = name.toUpperCase();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(!(obj instanceof ImageEntry)) return false;
		ImageEntry entry = (ImageEntry) obj;
		return this.name.equals(entry.name);
	}

	public String getName() {
		return this.name;
	}

	public String getURL() {
		return this.URL;
	}
	
}