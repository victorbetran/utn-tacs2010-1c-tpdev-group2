package com.logikas.handsongwt.actividad04.client;

import java.util.ArrayList;
import java.util.List;

public class GaleriaDeclarativaModel {
	
	//************************************
	//** ATRIBUTTES
	//************************************
	/**
	 * Lista de objetos ImageEntry
	 */
	private List<ImageEntry> images;
	
	
	//************************************
	//** CONSTRUCTOR
	//************************************
	public GaleriaDeclarativaModel() {
		this.images = new ArrayList<ImageEntry>();
	}
	
	/**
	 * Crea un nuevo objeto ImageEntry
	 */
	public ImageEntry newImageEntry(String name, String url){
		return new ImageEntry(name, url);
	}
	
	//************************************
	//** PUBLIC METHODS
	//************************************
	/**
	 * Agrega un objeto ImageEntry al listado. Si ya existe 
	 * actualiza la URL.
	 */
	public void addImageEntry(ImageEntry iEntry){
		if(!isRepeat(iEntry))
			this.images.add(iEntry);
		else
			this.updateUrl(iEntry);
	}
	
	/**
	 * Retorna el listado de objetos ImageEntry.
	 */
	public List<ImageEntry> getImageEntries() {
		return this.images;
	}

	/**
	 * Busca por el nombre y devuelve un objeto ImageEntry.
	 */
	public ImageEntry getImageByName(String iEntryName) {
		for(ImageEntry iEntry : this.images){
			if(iEntry.getName().equals(iEntryName));
				return iEntry;
		}
		return null;
	}
	
	/**
	 * Devuelve el objeto ImageEntry ubicado en la posicion position.
	 */
	public ImageEntry getImageByPosition(int position) {
		return this.images.get(position);
	}
	

	//************************************
	//** PRIVATE METHODS
	//************************************
	/**
	 * Busca la objeto ImageEntry y actualiza la URL.
	 */
	private void updateUrl(ImageEntry iEntry) {
		for(ImageEntry image : this.images){
			if(image.equals(iEntry))
				image.setUrl(iEntry.getUrl());
		}
	}

	/**
	 * Determina si el objeto ImageEntry ya se encuentra en la lista.
	 */
	private boolean isRepeat(ImageEntry iEntry) {
		return this.images.contains(iEntry);
	}

}
