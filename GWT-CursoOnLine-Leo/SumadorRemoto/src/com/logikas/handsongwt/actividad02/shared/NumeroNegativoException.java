package com.logikas.handsongwt.actividad02.shared;

public class NumeroNegativoException extends RuntimeException {

	private static final long serialVersionUID = 1062386207143884928L;
	
	public NumeroNegativoException() {}
	
	public NumeroNegativoException(String message) {
		super(message);
	}
	
	public NumeroNegativoException(Throwable caught) {
		super(caught);
	}
	
	public NumeroNegativoException(String message, Throwable caught) {
		super(message, caught);
	}
}
