/**
 * 
 */
package com.fisa.prueba.tecnica.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

import com.fisa.prueba.tecnica.enums.EnumCasosProcesos;
import com.fisa.prueba.tecnica.util.Utilitario;

/**
 * @author dvasq
 *
 */
public class ProcesarConversacion {
	
	private Scanner scan;
	private String line;
	/**
	 * Mapa que se utilizara para guardar las asignaciones clave - valor de los numeros encontrados en las lineas ingressadas
	 */
	private HashMap<String, Object> mapAsignaciones;
	
	/**
	 * Coleccion que guarda las respuestas luego del procesamiento
	 */
	private Collection<String> respuestas;
	
	/**
	 * <b> Lee las lineas introducidas por consola</b>
	 * dvasq
	 * @return
	 */
	public Collection<String> leerMensajes() {
		// Instanciamos el procesador de entrada por consola
		scan = new Scanner(System.in);
		
		// Instanciamos la coleccion de mensajes de respuesta procesados
		respuestas = new ArrayList<>();
		
		// Instanciamos el mapa de asignaciones de valores
		mapAsignaciones =  new HashMap<>();
		
		// Leemos la entrada por consola
		while (scan.hasNextLine() && !(line = scan.nextLine()).contains(Utilitario.FIN_DE_CONVERSACION) ) {
			// Procesamos la linea de entrada
			processLinea(line);
		}
		return respuestas;
	}
	
	/**
	 * <b>Procesa las lineas leidas por consola</b>
	 * dvasq
	 * @param line - Linea introducida por consola
	 */
	private void processLinea(String line) {
		try {
			
			// Eliminamos los espacios en blanco
			line = line.trim();
			
			// Dividimos la linea de entrada en cadenas separadas por un espacio en blanco
			String[] cadenas = line.split(Utilitario.ESPACIO_EN_BLANCO);
			
			// Evaluamos los casos dependiendo de la linea ingresada por consola
			if (line.matches(EnumCasosProcesos.ASIGNACION.getCaso())) { // Para el caso de asignacion simple
				procesarAsignacion(cadenas);
			} else if (line.matches(EnumCasosProcesos.ASIGNACION_CREDITOS.getCaso())) { // Para el caso de asignacion de creditos 
				procesarAsignacionCreditos(cadenas);
			} else if (line.matches(EnumCasosProcesos.SUMATORIA_SIMPLE.getCaso())) { // Para el caso de sumatoria de valores
				procesarSumatoriaSimple(cadenas);
			} else if (line.matches(EnumCasosProcesos.CALCULO_CREDITOS.getCaso())) { // Para el caso de calculo de creditos
				procesarCalculoCreditos(cadenas);
			} else { // Si la expresion ingresada no cumple con el patron para poder evaluar
				respuestas.add(Utilitario.MENSAJE_ERROR_PROCESO);
			}
			
		} catch (Exception e) {
			respuestas.add(Utilitario.MENSAJE_ERROR_PROCESO);
		}

    }

	/**
	 * <b>Procesa la asignacion simple</b>
	 * dvasq
	 * @param line
	 */
	private void procesarAsignacion(String[] cadenas) {
		// Guardamos la clave con el valor en romano
		mapAsignaciones.put(cadenas[0], cadenas[2]);
	}
	
	/**
	 * <b>Procesa la asignacion de creditos</b>
	 * dvasq
	 * @param line
	 */
	private void procesarAsignacionCreditos(String[] cadenas) {
		// Obtenemos el indice dentro de la cadena para buscar el identificador IS
		int indexIs = Utilitario.obtenerIndice(cadenas, Utilitario.INDICE_IS);
		
		// Obtenemos la clave para guardar en el mapa de clave valor
		String key = indexIs > 0 ? cadenas[indexIs -1] : "";
		
		String numeroRomano="";
		
		// Armamos la cadena con el numero romano para transformar a arabigo 
		for(int i = 0 ; i <= indexIs - 2 ; i++) {
			numeroRomano += mapAsignaciones.get(cadenas[i]).toString();
		}
		
		// Convertimos el numero romano en arabigo
		int romanNumber = numeroRomano.length() > 0 ?  ConversorRomanoArabigo.convertir(numeroRomano) : 1;
		
		// Calculamos el valor de los creditos
		float credit = (float)(Float.parseFloat(cadenas[indexIs + 1])/romanNumber);
		
		// Guardamos la clave con el valor en arabigo
		mapAsignaciones.put(key, credit);
	}
	
	/**
	 * <b>Procesa el caso de sumatoria de valores romanos</b>
	 * dvasq
	 * @param line
	 */
	private void procesarSumatoriaSimple(String[] cadenas) {
		// Obtenemos el indice dentro de la cadena para buscar el identificador IS
		int indexIs = Utilitario.obtenerIndice(cadenas, Utilitario.INDICE_IS);
		
		// Obtenemos el indice dentro de la cadena para buscar el identificador de pregunta ?
		int indexQ = Utilitario.obtenerIndice(cadenas, Utilitario.INDICE_PREGUNTA);
		
		String numeroRomano="";
		
		StringBuilder mensajeSalida = new StringBuilder();
		
		// Armamos el numero en romano y el mensaje de salida
		for (int i = indexIs + 1 ; i < indexQ ; i ++) {
			numeroRomano += mapAsignaciones.get(cadenas[i]).toString();
			mensajeSalida.append(" ").append(cadenas[i]);
		}
		
		// Guardamos el mensaje de salida en la coleccion a retornar
		respuestas.add(String.format("%s is %d", mensajeSalida.toString().trim(), ConversorRomanoArabigo.convertir(numeroRomano)));
	}
	
	/**
	 * <b>Procesa el caso de calculo de creditos</b>
	 * dvasq
	 * @param line - linea de proceso
	 */
	private void procesarCalculoCreditos(String[] cadenas) {
		// Obtenemos el indice dentro de la cadena para buscar el identificador IS
		int indexIs = Utilitario.obtenerIndice(cadenas, Utilitario.INDICE_IS);
		
		// Obtenemos el indice dentro de la cadena para buscar el identificador de pregunta ?
		int indexQ = Utilitario.obtenerIndice(cadenas, Utilitario.INDICE_PREGUNTA);
		
		String numeroRomano="";
		
		StringBuilder mensajeSalida = new StringBuilder();
		
		// Variable que nos sirve para guardar el valor del calculo del proceso de creditos
		float creditosMultiplicar = 1f; 
		
		// Calculamos los creditos y armamos el mensaje de salida
		for (int i = indexIs + 1 ; i < indexQ ; i ++) {
			if (mapAsignaciones.get(cadenas[i]) instanceof String) {
				numeroRomano += mapAsignaciones.get(cadenas[i]).toString();
			} else {
				creditosMultiplicar *= Float.parseFloat(mapAsignaciones.get(cadenas[i]).toString());
			}
			mensajeSalida.append(" ").append(cadenas[i]);
		}
		
		// Calculamos el total creditos de numeros romanos
		float valorRomanos = numeroRomano.length() > 0 ? 
				ConversorRomanoArabigo.convertir(numeroRomano) : 1f;
		// Calculamos el total de creditos a presentar	
		float creditos = creditosMultiplicar * valorRomanos;
		
		// Guardamos el mensaje de salida en la coleccion a retornar
		respuestas.add(String.format("%s is %s Credits", mensajeSalida.toString().trim(), (int) creditos));
	}
}
