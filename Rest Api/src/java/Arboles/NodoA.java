/*
 * Estructura del arbol Binario para almacenar datos
 ** @author Serprogramadores
 * tomado de: https://serprogramador.es/programar-arboles-binarios-parte-1-introduccionclasesagregar-nodo/
 */
package Arboles;


import Algoritmos_de_ordenamiento.Molde;

public class NodoA {

    /* Declaraciones de variables */
    private Molde valor;
    private NodoA padre;
    private NodoA hojaIzquierda;
    private NodoA hojaDerecha;

    /* Constructor */
    public NodoA(Molde valor) {
        this.valor = valor;
    }

    /* Setters y Getters */
    /**
     * Asigna el dato al nodo
     * @param valor el valor que se le asigna
     */
    public void setValor(Molde valor) {
        this.valor = valor;
    }
    /**
     * get del valor
     * @return valor del nodo
     */
    public Molde getValor() {
        return valor;
    }
    /**
     * get del padre
     * @return el padre del nodo
     */
    public NodoA getPadre() {
        return padre;
    }
    /**
     * Asignar el padre
     * @param padre el nodo padre que se le asigna
     */
    public void setPadre(NodoA padre) {
        this.padre = padre;
    }
    /**
     * para saber el hijo izquierdo del nodo
     * @return el hijo izquierdo del nodo
     */
    public NodoA getHojaIzquierda() {
        return hojaIzquierda;
    }
    /**
     * asigna el hijo izquierdo al nodo
     * @param hojaIzquierda un nodo
     */
    public void setHojaIzquierda(NodoA hojaIzquierda) {
        this.hojaIzquierda = hojaIzquierda;
    }
    /**
     * para saber el hijo derecho del nodo
     * @return el hijo derecho del nodo
     */
    public NodoA getHojaDerecha() {
        return hojaDerecha;
    }
    /**
     * para asignar el hijo derecho al nodo
     * @param hojaDerecha un nodo
     */
    public void setHojaDerecha(NodoA hojaDerecha) {
        this.hojaDerecha = hojaDerecha;
    }

}

