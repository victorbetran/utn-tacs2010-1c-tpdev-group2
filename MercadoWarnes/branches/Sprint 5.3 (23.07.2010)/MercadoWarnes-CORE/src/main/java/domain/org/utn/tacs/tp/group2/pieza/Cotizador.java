package org.utn.tacs.tp.group2.pieza;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.utn.tacs.tp.group2.exceptions.pieza.CotizadorException;
import org.utn.tacs.tp.group2.log.TheLogger;

/**
 * Clase encargada de obtener y mantener las cotizaciones entre las diversas monedas.
 */
public class Cotizador {

	//****************************************
	//** ATRIBUTTES
	//****************************************
	/**
	 * Path donde se encuentra ubicado el archivo con las cotizaciones entre las monedas.
	 */
	private static final String pathCotizacionesFile = "src/main/resources/cotizaciones.properties";

	/**
	 * Cabecera del archivo
	 */
	private static final String commnents = "### COTIZACIONES - FORMATO: MonedaOrigen-MonedaDestino=Multiplicador";
	
	/**
	 * Unique Instance
	 */
	private static Cotizador INSTANCE;
	
	/**
	 * Abstracci�n que permite administrar las cotizaciones.
	 */
	private Properties cotizaciones;
	
	/**
	 * �ltima fecha de actualizaci�n de las cotizaciones. 
	 */
	private long lastModified;

	
	//****************************************
	//** CONSTRUCTORS
	//****************************************
	/**
	 * Static Constructor
	 */
	public static Cotizador getInstance() {
		if(INSTANCE == null)
			INSTANCE = new Cotizador();
		return INSTANCE;
	}
	
	private Cotizador() {
		this.cotizaciones = new Properties();
		this.loadCotizaciones();
	}
	
	
	//****************************************
	//** PUBLIC METHODS
	//****************************************
	/**
	 * Obtiene la cotizaci�n entre dos monedas. El valor devuelto act�a a modo de
	 * multiplicador.
	 */
	public double getCotizacionBetween(Moneda monedaOrigen, Moneda monedaDestino) {
		String key = this.createKey(monedaOrigen, monedaDestino);
		return this.getCotizacion(key);
	}
	
	/**
	 * Almacena una cotizaci�n para el par (monedaOrigen, monedaDestino)
	 */
	public void loadCotizacionBetween(Moneda monedaOrigen, Moneda monedaDestino, double cotizacion) {
		String key = this.createKey(monedaOrigen, monedaDestino);
		this.saveCotizacion(key, cotizacion);
	}
	
	
	//****************************************
	//** PRIVATE METHODS
	//****************************************
	/**
	 * Carga las cotizaciones y actualiza la fecha de �ltima motificacion.
	 */
	private void loadCotizaciones() {
		try {
			File file = new File(pathCotizacionesFile);
			this.lastModified = file.lastModified();
			this.cotizaciones.load(new FileInputStream(file));
		} 
		catch (FileNotFoundException e) {
			throw new CotizadorException("No se encontr� el archivo de cotizaciones.");
		} 
		catch (IOException e) {
			TheLogger.getConsoleLogger().error("Error al cargar las cotizaciones.");
		}
	}

	/**
	 * Construye la key para acceder al archivo properties. La misma est� formada por
	 * el nombre de la moneda origen, un gui�n, y el nombre de la moneda destino.
	 * Ejemplo: para obtener la cotizacion de la moneda PESO respecto de la moneda DOLAR
	 * la key ser�a: DOLAR-PESO
	 */
	private String createKey(Moneda monedaOrigen, Moneda monedaDestino) {
		return new StringBuffer()
					.append(monedaOrigen.name())
					.append("-")
					.append(monedaDestino.name())
					.toString();
	}

	/**
	 * Obtiene el valor numerico de la cotizaci�n a partir de una key.
	 */
	private double getCotizacion(String key) {
		this.updateCotizaciones();
		String cotizacion = this.cotizaciones.getProperty(key);
		return Double.valueOf(cotizacion).doubleValue();
	}

	/**
	 * Checkea si es necesario actualizar el archivo de cotizaciones.
	 */
	private void updateCotizaciones() {
		File file = new File(pathCotizacionesFile);
		if(this.lastModified != file.lastModified())
			this.loadCotizaciones();
	}
	
	/**
	 * Realiza el guardado f�sico de la cotizaci�n en el archivo properties.
	 */
	private void saveCotizacion(String key, double cotizacion) {
		try {
			this.cotizaciones.setProperty(key, String.valueOf(cotizacion));
			this.cotizaciones.store(new FileOutputStream(new File(pathCotizacionesFile)), commnents);
		} 
		catch (FileNotFoundException e) {
			throw new CotizadorException("No se encontr� el archivo de cotizaciones.");
		} 
		catch (IOException e) {
			TheLogger.getConsoleLogger().error("Error al guardar las cotizaciones.");
		}
	}




}
