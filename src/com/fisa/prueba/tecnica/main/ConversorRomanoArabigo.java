/**
 * 
 */
package com.fisa.prueba.tecnica.main;

import com.fisa.prueba.tecnica.enums.DigitoRomano;

/**
 * @author dvasq
 *
 */
public class ConversorRomanoArabigo {

	/**
	 * <b>Converte un numero romano a arabigo</b>
	 * dvasq
	 * @param numeroRomano
	 * @return
	 */
	public static int convertir(String numeroRomano) {
        DigitoRomano digitoPrevio = null;
        int contadorCaracterRepetido = 1;
        int total = 0;
 
        for(int i = 0; i < numeroRomano.length(); i++){
            DigitoRomano digitoRomanoActual = DigitoRomano.valueOf(String.valueOf(numeroRomano.charAt(i)));
            int valorDigitoRomanoActual = digitoRomanoActual.getValor();
 
            if (digitoRomanoActual.equals(digitoPrevio)) {
                contadorCaracterRepetido++;
 
                if (contadorCaracterRepetido > 3) {
                    throw new IllegalArgumentException("No se puede repetir el digito mas de tres veces " + digitoRomanoActual); 
                }
                if (digitoRomanoActual.isRepetible()) {
                    total += valorDigitoRomanoActual;
                } else {
                    throw new IllegalArgumentException("El digito no se puede repetir " + digitoRomanoActual); 
                }
            } else if (digitoPrevio != null && digitoPrevio.compareTo(digitoRomanoActual) < 0) {
                if (contadorCaracterRepetido > 1) {
                    throw new IllegalArgumentException("El digito repetido esta antes del digito de mayor precedencia " +digitoRomanoActual); 
                }
                if (digitoPrevio.sePruedeRestar(digitoRomanoActual)) {
                    contadorCaracterRepetido = 1;
                    total += valorDigitoRomanoActual - (2 * digitoPrevio.getValor()); 
                } else {
                    throw new IllegalArgumentException("El digito no puede ser restado " + digitoRomanoActual); 
                }
            } else {
                contadorCaracterRepetido = 1;
                total += valorDigitoRomanoActual;
            }
 
            digitoPrevio = digitoRomanoActual;
        }
        return total;
    }
	
}
