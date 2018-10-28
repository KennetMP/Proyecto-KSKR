/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    public void setValor(Molde valor) {
        this.valor = valor;
    }

    public Molde getValor() {
        return valor;
    }

    public NodoA getPadre() {
        return padre;
    }

    public void setPadre(NodoA padre) {
        this.padre = padre;
    }

    public NodoA getHojaIzquierda() {
        return hojaIzquierda;
    }

    public void setHojaIzquierda(NodoA hojaIzquierda) {
        this.hojaIzquierda = hojaIzquierda;
    }

    public NodoA getHojaDerecha() {
        return hojaDerecha;
    }

    public void setHojaDerecha(NodoA hojaDerecha) {
        this.hojaDerecha = hojaDerecha;
    }

}

