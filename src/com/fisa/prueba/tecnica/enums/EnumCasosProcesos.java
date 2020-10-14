/**
 * 
 */
package com.fisa.prueba.tecnica.enums;

/**
 * @author dvasq
 *
 */
public enum EnumCasosProcesos {

	ASIGNACION("^([A-Za-z]+) is ([I|V|X|L|C|D|M])$"),
	ASIGNACION_CREDITOS("^([A-Za-z]+)([A-Za-z\\s]*) is ([0-9]+) ([c|C]redits)$"),
	SUMATORIA_SIMPLE("^how much is (([A-Za-z\\s])+)\\?$"),
	CALCULO_CREDITOS("^how many [c|C]redits is (([A-Za-z\\s])+)\\?$");
	
	private String patron;
	
	EnumCasosProcesos (String patron) {
		this.patron = patron;
	}
	
	public String getCaso() {
		return patron;
	}
}
