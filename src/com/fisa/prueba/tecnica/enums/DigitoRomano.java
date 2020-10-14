/**
 * 
 */
package com.fisa.prueba.tecnica.enums;

/**
 * @author dvasq
 *
 */
public enum DigitoRomano {

	 I(1, true), 
	 V(5, false), 
	 X(10, true), 
	 L(50, false), 
	 C(100, true), 
	 D(500, false), 
	 M(1000, true);
	 
    private int valor;
    private boolean repetible;

    DigitoRomano (int value, boolean repetible){
        this.valor = value;
        this.repetible = repetible;
    }

    /**
     * Valida si un digito romano se puede restar de otro
     * @param digito - digito en notacion romana
     * @return
     */
    public boolean sePruedeRestar(DigitoRomano digito) {
        //Validamos si el digito existe y no es repetible.
        if (digito == null || !this.isRepetible()) {
            return false;
        }

        // Obtenemos el orden del digito atual
        int oridinal = this.ordinal();
        
        // Obtenemos el orden del digito a validar
        int oridinalDigito = digito.ordinal();

        // Validamos si el ordinal es igual al ordinal anterior del digito a comprobar
        return oridinal == oridinalDigito - 1 || oridinal == oridinalDigito - 2;
    }
    
    public int getValor() {
        return valor;
    }

    public boolean isRepetible() {
        return repetible;
    }
}
