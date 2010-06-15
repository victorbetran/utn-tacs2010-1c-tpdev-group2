package com.logikas.handsongwt.actividad02.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Suma implements Serializable{
	
	private static final long serialVersionUID = 5112491711584522465L;
	
	private List<Integer> sumandos;
	
	public Suma() {
		this.sumandos = new ArrayList<Integer>();
	}
	
	public Suma addSumando(int sumando){
		this.sumandos.add(Integer.valueOf(sumando));
		return this;
	}
	
	public Integer execute(){
		Integer result = Integer.valueOf(0);
		for(Integer sumando : this.sumandos){
			result = result.intValue() + sumando.intValue();
		}
		return result;
	}

	public void validate() {
		if(this.containsNegativeNumbers())
			throw new NumeroNegativoException();
	}

	private boolean containsNegativeNumbers() {
		for(Integer sumando : this.sumandos){
			if(sumando.compareTo(Integer.valueOf(0)) < 0)
					return true;
		}
		return false;
	}
}