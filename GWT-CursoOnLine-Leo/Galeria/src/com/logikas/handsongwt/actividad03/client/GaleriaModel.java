package com.logikas.handsongwt.actividad03.client;

import java.util.ArrayList;
import java.util.List;

public class GaleriaModel {

	private List<ImageEntry> images;
	
	public GaleriaModel() {
		this.images = new ArrayList<ImageEntry>();
	}
	
	public void addImageEntry(ImageEntry image){
		if(!isRepeat(image))
			this.images.add(image);
		else
			updateImage(image);
	}

	private void updateImage(ImageEntry entry) {
		for(ImageEntry image : this.images){
			if(image.equals(entry))
				image.setURL(entry.getURL());
		}
	}

	private boolean isRepeat(ImageEntry entry) {
		return this.images.contains(entry);
	}

	public List<ImageEntry> getImageEntries() {
		return this.images;
	}

	public ImageEntry getImageByName(String imageName) {
		for(ImageEntry image : this.images){
			if(image.getName().equals(imageName));
				return image;
		}
		return null;
	}
}
