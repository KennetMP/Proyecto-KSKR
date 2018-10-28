/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arboles;

import Algoritmos_de_ordenamiento.Molde;
import java.util.ArrayList;

/**
 * @author Serprogramadores
 * tomado de: https://serprogramador.es/programar-arboles-binarios-parte-1-introduccionclasesagregar-nodo/
 */
public class ArbolBi {
    
    private double aux = 4;
    private static int constante = 86;
    private int num = 0;
    
    private int x = 900;
    /* Atributos */
    private NodoA raiz;
    private ArrayList<Molde> lista= new ArrayList<>();
    /* Contructories */

    public void setLista(ArrayList<Molde> lista) {
        this.lista = lista;
    }

    public ArrayList<Molde> getLista() {
        return lista;
    }

    public ArbolBi( NodoA raiz ) {
        this.raiz = raiz;
    }
    public ArbolBi() {
        System.out.println("Crear arbolB");
    }
    public ArbolBi( Molde valor ) {
        this.raiz = new NodoA( valor );
    }


    /* Setters y Getters */
    public NodoA getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoA raiz) {
        this.raiz = raiz;
    }


    private void addNodo( NodoA nodo, NodoA raiz ) {
        /* 2.- Partiendo de la raíz preguntamos: Nodo == null ( o no existe ) ? */
        if ( raiz == null ) {
            /*
             * 3.- En caso afirmativo X pasa a ocupar el lugar del nodo y ya
             * hemos ingresado nuestro primer dato.
             * ==== EDITO =====
             * Muchas gracias a @Espectro por la corrección de esta línea
             */
            this.setRaiz(nodo);
            
            //nodo.setPadre(raiz);
            
            //this.raiz.getValor().setY(315);
            //this.raiz.getValor().setX(x);
        }
        else {
            /* 4.- En caso negativo preguntamos: X < Nodo */
            if ( nodo.getValor().getEdad() <= raiz.getValor().getEdad() ) {
                /* 
                 * 5.- En caso de ser menor pasamos al Nodo de la IZQUIERDA del
                 * que acabamos de preguntar y repetimos desde el paso 2 
                 * partiendo del Nodo al que acabamos de visitar 
                 */
                if (raiz.getHojaIzquierda() == null) {
                    raiz.getValor().setHi(nodo.getValor());
                    nodo.getValor().setPadre(raiz.getValor());
                    raiz.setHojaIzquierda(nodo);
                    
                }
                else {
                    addNodo( nodo , raiz.getHojaIzquierda() );
                }
            }
            else {
                /* 
                 * 6.- En caso de ser mayor pasamos al Nodo de la DERECHA y tal
                 * cual hicimos con el caso anterior repetimos desde el paso 2
                 * partiendo de este nuevo Nodo.
                 */
                if (raiz.getHojaDerecha() == null) {
                    raiz.setHojaDerecha(nodo);
                    raiz.getValor().setHd(nodo.getValor());
                    nodo.getValor().setPadre(raiz.getValor());
                }
                else {
                    addNodo( nodo, raiz.getHojaDerecha() );
                }
            }
        }
    }

    public void addNodo( NodoA nodo ) {
        this.addNodo( nodo , this.raiz );
    }


    public boolean removeNodo( NodoA nodo ) {

        /* Creamos variables para saber si tiene hijos izquierdo y derecho */
        boolean tieneNodoDerecha = nodo.getHojaDerecha() != null ? true : false;
        boolean tieneNodoIzquierda = nodo.getHojaIzquierda() != null ? true : false;

        /* Verificamos los 3 casos diferentes y llamamos a la función correspondiente */

        /* Caso 1: No tiene hijos */
        if (!tieneNodoDerecha && !tieneNodoIzquierda) {
            return removeNodoCaso1( nodo );
        }

        /* Caso 2: Tiene un hijo y el otro no */
        if ( tieneNodoDerecha && !tieneNodoIzquierda ) {
            return removeNodoCaso2( nodo );
        }

        /* Caso 2: Tiene un hijo y el otro no */
        if ( !tieneNodoDerecha && tieneNodoIzquierda ) {
            return removeNodoCaso2( nodo );
        }

        /* Caso 3: Tiene ambos hijos */
        if ( tieneNodoDerecha && tieneNodoIzquierda ) {
            return removeNodoCaso3( nodo );
        }

        return false;
    }
    private boolean removeNodoCaso1( NodoA nodo ) {
        /* lo único que hay que hacer es borrar el nodo y establecer el apuntador de su padre a nulo */

        /*
         * Guardemos los hijos del padre temporalmente para saber cuál de sus hijos hay que
         * eliminar
         */
        NodoA hijoIzquierdo = nodo.getPadre().getHojaIzquierda();
        NodoA hijoDerecho = nodo.getPadre().getHojaDerecha();

        if ( hijoIzquierdo == nodo ) {
            nodo.getPadre().setHojaIzquierda( null );
            return true;
        }

        if ( hijoDerecho == nodo) {
            nodo.getPadre().setHojaDerecha( null );
            return true;
        }

        return false;
    }
    private boolean removeNodoCaso2( NodoA nodo) {
        /* Borrar el Nodo y el subárbol que tenía pasa a ocupar su lugar */

        /*
         * Guardemos los hijos del padre temporalmente para saber cuál de sus hijos hay que
         * eliminar
         */
        NodoA hijoIzquierdo = nodo.getPadre().getHojaIzquierda();
        NodoA hijoDerecho = nodo.getPadre().getHojaDerecha();

        /*
         * Buscamos el hijo existente del nodo que queremos eliminar
         */
        NodoA hijoActual = nodo.getHojaIzquierda() != null ?
                nodo.getHojaIzquierda() : nodo.getHojaDerecha();

        if ( hijoIzquierdo == nodo ) {
            nodo.getPadre().setHojaIzquierda( hijoActual );
            nodo.getPadre().getValor().setHi(hijoActual.getValor());

            /* Eliminando todas las referencias hacia el nodo */
            hijoActual.setPadre(nodo.getPadre());
            hijoActual.getValor().setPadre(nodo.getPadre().getValor());
            nodo.getValor().setHd(null);
            nodo.getValor().setHi(null);
            nodo.setHojaDerecha(null);
            nodo.setHojaIzquierda(null);

            return true;
        }

        if ( hijoDerecho == nodo) {
            nodo.getPadre().setHojaDerecha( hijoActual );
            nodo.getPadre().getValor().setHd(hijoActual.getValor());
            /* Eliminando todas las referencias hacia el nodo */
            hijoActual.setPadre(nodo.getPadre());
            hijoActual.getValor().setPadre(nodo.getPadre().getValor());
            nodo.getValor().setHd(null);
            nodo.getValor().setHi(null);
            nodo.setHojaDerecha(null);
            nodo.setHojaIzquierda(null);

            return true;
        }

        return false;
    }
    private boolean removeNodoCaso3( NodoA nodo ) {
        /* Tomar el hijo derecho del Nodo que queremos eliminar */
        NodoA nodoMasALaIzquierda = recorrerIzquierda( nodo.getHojaDerecha() );
        if ( nodoMasALaIzquierda != null ) {
            /*
             * Reemplazamos el valor del nodo que queremos eliminar por el nodo que encontramos
             */
            nodo.setValor( nodoMasALaIzquierda.getValor() );
            nodo.getValor().setPadre(nodo.getPadre().getValor());
            /*
             * Eliminar este nodo de las formas que conocemos ( caso 1, caso 2 )
             */
            removeNodo( nodoMasALaIzquierda );
            return true;
        }
        return false;
    }

    /* Recorrer de forma recursiva hasta encontrar el nodo más a la izquierda */
    private NodoA recorrerIzquierda(NodoA nodo) {
        if (nodo.getHojaIzquierda() != null) {
            return recorrerIzquierda( nodo.getHojaIzquierda() );
        }
        return nodo;
    }
public ArrayList<Molde> recorrer(){
    recorrer(this.raiz);
    return lista;
}
private void recorrer(NodoA a){      
        if(a!= null)
        {   
            recorrer(a.getHojaIzquierda());
            recorrer(a.getHojaDerecha());
            this.lista.add(a.getValor());
        
        }
    }
	
	/** 
	 * Profundidad/Altura del arbol
	 * @return 
	 */
	public int getDeep() {
		
		if (raiz == null)//No tiene ningun elemento
			return -1;
		else 
			return getDeepAux(raiz, 0);
		
	}
	
	private int getDeepAux(NodoA node, int deep) {
		
		if (node.getHojaIzquierda() == null && node.getHojaDerecha()== null)//Nodo es una hoja
			return deep;
		else if (node.getHojaIzquierda() == null)//No tiene hijo izquierdo
			return getDeepAux(node, deep + 1);
		else if (node.getHojaDerecha() == null)//No tiene hijo derecho
			return getDeepAux(node, deep + 1);
		else //Tiene ambos hijos, debe saber cual tiene mayor profundidad
			return Math.max(getDeepAux(node.getHojaIzquierda(), deep + 1), getDeepAux(node.getHojaDerecha(), deep + 1));
	}
}
