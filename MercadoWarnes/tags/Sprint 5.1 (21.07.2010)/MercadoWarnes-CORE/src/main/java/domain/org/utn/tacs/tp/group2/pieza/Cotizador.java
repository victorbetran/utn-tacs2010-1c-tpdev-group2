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
	 * Abstracción que permite administrar las cotizaciones.
	 */
	private Properties cotizaciones;
	
	/**
	 * Última fecha de actualización de las cotizaciones. 
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
	 * Obtiene la cotización entre dos monedas. El valor devuelto actúa a modo de
	 * multiplicador.
	 */
	public double getCotizacionBetween(Moneda monedaOrigen, Moneda monedaDestino) {
		String key = this.createKey(monedaOrigen, monedaDestino);
		return this.getCotizacion(key);
	}
	
	/**
	 * Almacena una cotización para el par (monedaOrigen, monedaDestino)
	 */
	public void loadCotizacionBetween(Moneda monedaOrigen, Moneda monedaDestino, double cotizacion) {
		String key = this.createKey(monedaOrigen, monedaDestino);
		this.saveCotizacion(key, cotizacion);
	}
	
	
	//****************************************
	//** PRIVATE METHODS
	//****************************************
	/**
	 * Carga las cotizaciones y actualiza la fecha de última motificacion.
	 */
	private void loadCotizaciones() {
		try {
			File file = new File(pathCotizacionesFile);
			this.lastModified = file.lastModified();
			this.cotizaciones.load(new FileInputStream(file));
		} 
		catch (FileNotFoundException e) {
			throw new CotizadorException("No se encontró el archivo de cotizaciones.");
		} 
		catch (IOException e) {
			TheLogger.getConsoleLogger().error("Error al cargar las cotizaciones.");
		}
	}

	/**
	 * Construye la key para acceder al archivo properties. La misma está formada por
	 * el nombre de la moneda origen, un guión, y el nombre de la moneda destino.
	 * Ejemplo: para obtener la cotizacion de la moneda PESO respecto de la moneda DOLAR
	 * la key sería: DOLAR-PESO
	 */
	private String createKey(Moneda monedaOrigen, Moneda monedaDestino) {
		return new StringBuffer()
					.append(monedaOrigen.name())
					.append("-")
					.append(monedaDestino.name())
					.toString();
	}

	/**
	 * Obtiene el valor numerico de la cotización a partir de una key.
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
	 * Realiza el guardado físico de la cotización en el archivo properties.
	 */
	private void saveCotizacion(String key, double cotizacion) {
		try {
			this.cotizaciones.setProperty(key, String.valueOf(cotizacion));
			this.cotizaciones.store(new FileOutputStream(new File(pathCotizacionesFile)), commnents);
		} 
		catch (FileNotFoundException e) {
			throw new CotizadorException("No se encontró el archivo de cotizaciones.");
		} 
		catch (IOException e) {
			TheLogger.getConsoleLogger().error("Error al guardar las cotizaciones.");
		}
	}




}
