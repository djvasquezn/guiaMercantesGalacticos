/**
 * 
 */
package com.fisa.prueba.tecnica.util;

/**
 * @author dvasq
 *
 */
public final class Utilitario {

	public static final String FIN_DE_CONVERSACION = "enviar";
	public static final String ESPACIO_EN_BLANCO = "\\s";
	public static final String INDICE_PREGUNTA = "?";
	public static final String INDICE_IS = "is";
	public static final String MENSAJE_ERROR_PROCESO = "I have no idea what you are talking about";
	
	private Utilitario() {}
	
	/**
	 * <b>Obtiene el indice de una cadena dentro de un array de cadenas</b>
	 * dvasq
	 * @param arrCadenas - Array de cadenas
	 * @param cadena - Cadena a encontrar el indice
	 * @return - El indice de la posicion de la cadena en el array
	 */
	public static int obtenerIndice(String[] arrCadenas, String cadena) {
		int indexIs = 0;
		for (int i = 0 ; i < arrCadenas.length ; i ++) {
			if (arrCadenas[i].equals(cadena)) {
				indexIs = i;
				break;
			}
		}
		return indexIs;
	} 
}
