/**
 * 
 */
package com.fisa.prueba.tecnica.main;

import java.util.Collection;

/**
 * @author dvasq
 *
 */
public class Conversacion {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Ingrese la conversacion separada en renglones, digite 'enviar' para terminar la conversacion");
		// Instanciamos el procesador de la conversacion intergalactica
		ProcesarConversacion conversacion = new ProcesarConversacion();
		
		// Procesamos la conversacion
		Collection<String> respuestas = conversacion.leerMensajes();
		
		// Imprimimos en consola el resultado de la conversacion
		respuestas.forEach(System.out::println);
	}

}
