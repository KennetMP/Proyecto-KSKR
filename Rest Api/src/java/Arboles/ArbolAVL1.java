/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arboles;
import Algoritmos_de_ordenamiento.Molde;

import java.util.*;

/**
 * Implementa metodos de AbstractCollection y AbstractSet de forma espec�fica para
 * una estructura de arbol Arboles, mejorando en ciertos casos la velocidad de ejecucion.
 *
 * @author Alvaro Trigo Lopez
 *https://github.com/alvarotrigo/Java/blob/master/AVL%20Tree%20class/fuentes/es/ubu/lsi/util/ArbolAVL.java
 * @version 5.00 2009/6/14
 */
public class ArbolAVL1 {
    int xraiz = 500;
    int yraiz =100;
    
    /**
     * Nodo raiz del arbol.
     */
    private Nodo raiz;  //cambiar t por T

    /**
     * Comparador.
     */
    Comparator comparador;
    
    /**
     * Constructor por defecto.
     */
    public ArbolAVL1(){	
    }
    /**
     * Constructor para recibir el comparador que se utilizara para comparar los elementos. Si
     * no se construye el arbol con este metodo los objetos se compararan en funcion del orden
     * natural (comparable).
     * @param cmp comparador
     */
    public ArbolAVL1(Comparator cmp){
    	this.comparador = cmp;
    }

    /// ===============================================/// 
    ///             Abstract collection                ///
    ///                                                ///
    /// ===============================================///

    /**
     *Metodo para agregar un elemento al arbol 
     * @param e - elemento que se va a agregar al arbol (Molde)
     * @return true si el elemento es agregado, false en caso contrario
     * @throws ClassCastException - if the class of the specified element prevents it from being added to this collection 
     * @throws NullPointerException - if the specified element is null and this collection does not permit null elements 
     * @throws IllegalStateException - if the element cannot be added at this time due to insertion restrictions
     */
    public boolean add(Molde e) throws ClassCastException, NullPointerException, IllegalStateException{
    	Nodo nodo = new Nodo(e);
    	boolean salir = false;
    	boolean der = false;
    	Nodo raizTmp = this.getRaiz();

    	int altIzq, altDer;

    	//no existia arbol
    	if(raizTmp == null){
    		this.raiz = nodo;
    		return true;
    	}else
    	
    	//estaba ya en el arbol?
    	if(this.contains(nodo.getDato())){
    		return false;
    	}
    	
    	//no estaba antes en el arbol
    	else{    	
    		while(!salir){

    			//es mayor el nodo a insertar que la raiz?    				
		    	if(this.compararDato(nodo.getDato().getEdad(), raizTmp.getDato().getEdad())>0){
		    		if(raizTmp.getDerecha()!=null){
		    			raizTmp = raizTmp.getDerecha();
		    		}else{
		    			salir = true;
		    			der = true;
		    		}
		    			    		
		    	}
		    	//el nodo es menor que la raiz
		    	else{
		    		if(raizTmp.getIzquierda()!=null){
		    			raizTmp = raizTmp.getIzquierda();
		    		}else{
		    			salir = true;
		    		}
		    	}
    		}
    		
    		//tengo que insertarlo a la derecha?
    		if(der){
    			raizTmp.setDerecha(nodo);
                //nodo.getDato().setX(raizTmp.getDato().getX()+20);
                //nodo.getDato().setY(raizTmp.getDato().getY()-20);
    		}
    		
    		//lo inserto a la izquierda
    		else{
    			raizTmp.setIzquierda(nodo);
                //nodo.getDato().setX(raizTmp.getDato().getX()+20);
                //nodo.getDato().setY(raizTmp.getDato().getY()+20);
    		}
	
    		//mientras no este equilibrado el arbol	miramos donde reestructurar
    		while(equilibrado(this.getRaiz())<0){
				raizTmp = padre(raizTmp);
    		
    			if(raizTmp.getDerecha()==null){
	    			altDer = 0;
	    		}else{
	    			altDer = raizTmp.getDerecha().getAltura();
	    		}
	    		
	    		if(raizTmp.getIzquierda()==null){
	    			altIzq = 0;
	    		}else{
	    			altIzq = raizTmp.getIzquierda().getAltura();
	    		}
	    		
    			Nodo cambiar = estructurar(raizTmp, altIzq, altDer);
    			Nodo superior = padre(raizTmp);
	
    			//si los nodos modificados tenian un padre anteriormente
    			if(compararDato(superior.getDato().getEdad(), raizTmp.getDato().getEdad())!=0){
    				if(superior.getIzquierda()!=null && compararDato(superior.getIzquierda().getDato().getEdad(), raizTmp.getDato().getEdad())==0){
	    				superior.setIzquierda(cambiar);		
		    		}
		    		else if(superior.getDerecha()!=null && compararDato(superior.getDerecha().getDato().getEdad(), raizTmp.getDato().getEdad())==0){
	    				superior.setDerecha(cambiar);
	    			}
    			}else{
    				this.raiz = cambiar;
    			}
    		}
    		return true;
    	}
    }
    
    /**
     * Estructura el arbol haciendo las rotaciones necesarias.
     * Rotacion simple derecha, rotacion compuesta derecha izquierda, rotacion simple
     * izquierda y rotacion compuesta izquierda derecha.
     * @param nodo nodo con factor de equilibro 2.
     * @param altIzq altura izquierda del nodo pasado por parametro.
     * @param altDer altura derecha del nodo pasado por parametro.
     *
     * @return nodo nodo que pasa a ser la raiz del subarbol estructurado.
     */
    private Nodo estructurar(Nodo nodo, int altIzq, int altDer){
		if(extraeFactorE(nodo)==2){
			if( extraeFactorE(nodo.getDerecha())==1  || extraeFactorE(nodo.getDerecha()) == 0){
				nodo = rotacionSimpleIzquierda(nodo);
			}
			
			else if(extraeFactorE(nodo.getDerecha() )==-1){
				nodo = rotacionCompuestaDerecha(nodo);
			}
		}
		else if(extraeFactorE(nodo)==-2){
			if(extraeFactorE(nodo.getIzquierda() )==-1 || extraeFactorE(nodo.getDerecha())==0){
				nodo = rotacionSimpleDerecha(nodo);
			}
			
			else if(extraeFactorE(nodo.getIzquierda())==1){
				nodo = rotacionCompuestaIzquierda(nodo);
			}
		}

		return nodo;	
    }
    
    /**
     * Extrae el factor de equilibrio del nodo pasado por parametro.
     * @param nodo nodo del que se desea extraer el factor de equilibrio.
     * 
     * @return factor de equilibrio.
     */
    public int extraeFactorE(Nodo nodo){
    	if(nodo!=null){
    		return nodo.getFactorE();
    	}else{
    		return 0;
    	}
    }

	/**
	 * Realiza la operacion de rotacion simple izquierda en el subarbol 
	 * que tiene como raiz el nodo pasado por parametro.
	 * @param nodo raiz del subarbol a rotar.
	 *
	 * @return nodo nodo que pasa a ser la raiz del subarbol estructurado.
	 */
    public Nodo rotacionSimpleIzquierda(Nodo nodo){
		Nodo nodoTmp = nodo;
		
    	nodo = nodoTmp.getDerecha(); //clone??
		nodoTmp.setDerecha(nodo.getIzquierda());

		nodo.setIzquierda(nodoTmp);

		return nodo;
    }

	/**
	 * Realiza la operacion de rotacion simple derecha en el subarbol .
	 * que tiene como raiz el nodo pasado por parametro.
	 * @param nodo raiz del subarbol a rotar.
	 *
	 * @return nodo nodo que pasa a ser la raiz del subarbol estructurado.
	 */
    public Nodo rotacionSimpleDerecha(Nodo nodo){
    	Nodo nodoTmp = nodo;
    	nodo = nodoTmp.getIzquierda();

		nodoTmp.setIzquierda(nodo.getDerecha());
		nodo.setDerecha(nodoTmp);

		return nodo;
    }

	/**
	 * Realiza la operacion de rotacion compeusta izquierda-derecha en el subarbol .
	 * que tiene como raiz el nodo pasado por parametro.
	 * @param nodo raiz del subarbol a rotar.
	 *
	 * @return nodo nodo que pasa a ser la raiz del subarbol estructurado.
	 */
    public Nodo rotacionCompuestaIzquierda(Nodo nodo){
    	Nodo nodoTmp = nodo; //57

        nodoTmp = rotacionSimpleIzquierda(nodoTmp.getIzquierda()); //param 42 | sale: 54
        
		nodo.setIzquierda(nodoTmp); //param 54

		nodoTmp = rotacionSimpleDerecha(nodo); //param 54  | sale: 54
		
		return nodoTmp;
    }

	/**
	 * Realiza la operacion de rotacion compuesta derecha-izquierda en el subarbol 
	 * que tiene como raiz el nodo pasado por parametro.
	 * @param nodo raiz del subarbol a rotar.
	 *
	 * @return nodo nodo que pasa a ser la raiz del subarbol estructurado.
	 */
    public Nodo rotacionCompuestaDerecha(Nodo nodo){
    	Nodo nodoTmp = nodo;
    	
        nodoTmp = rotacionSimpleDerecha(nodoTmp.getDerecha());
	
		nodo.setDerecha(nodoTmp);

		nodoTmp= rotacionSimpleIzquierda(nodo);

		return nodoTmp;
    }

	/**
	 * Indica si el arbol o subarbol cuya raiz es es el nodo pasado por parametro
	 * es un arbol equilibrado o no.
	 * @param n raiz del subarbol o arbol .
	 *
	 * @return -1 si no esta equilibrado. La altura en caso contrario.
	 */
	public int equilibrado(Nodo n){
		int hIzq = 0;
		int hDer = 0;
		
		if(n==null){
    		return 0;
    	}
    	
    	hIzq = equilibrado(n.getIzquierda());
    	
    	if(hIzq < 0){
    		return hIzq;
    	}
    	
    	hDer = equilibrado(n.getDerecha());
    	
    	if(hDer <0){
    		return hDer;
    	}
    	
    	//si no es equilibrado
    	if(Math.abs(hIzq - hDer)>1){
    		return -1;
    	}
    	
    	//si el trozo de arbol es Arboles devolvemos la altura
    	return Math.max(hIzq, hDer) + 1;
	}
	
	/**
	 * Obtiene el nodo padre del nodo pasado por parametro. 
	 * En caso de que no tenga padre, devuelve el mismo nodo pasado por parametro.
	 *
	 * @param nodo nodo del que se quiere obtener su nodo padre.
	 * @return nodo padre.
	 */
	public Nodo padre(Nodo nodo){
		Nodo raizTmp = this.getRaiz();
		Stack<Nodo> pila = new Stack<Nodo>();
    	pila.push(raizTmp);	
    	while(raizTmp.getDerecha()!=null || raizTmp.getIzquierda()!=null){
	    	if(this.compararDato(nodo.getDato().getEdad(), raizTmp.getDato().getEdad())>0){
	    		if(raizTmp.getDerecha()!=null){   	
	    			raizTmp = raizTmp.getDerecha();
	    		}
	    	}
	    	else if(this.compararDato(nodo.getDato().getEdad(), raizTmp.getDato().getEdad())<0){
	    		if(raizTmp.getIzquierda()!=null){   
		    		raizTmp = raizTmp.getIzquierda();
	    		}
	    	}
	    	if(this.compararDato(nodo.getDato().getEdad(), raizTmp.getDato().getEdad())==0){
	    		return pila.pop();
	    	}

	    	pila.push(raizTmp);	
    	}
    	return pila.pop();
	}

    
    /**
     * Metodo para saber si es un elemento está contenido en el arbol
     * @param o - el elemento (molde) que se está buscando
     * @return true si el arbol contiene el elemento, false en caso contrario
     * @throws  ClassCastException - if the type of the specified element is incompatible with this collection (optional).
     * @throws NullPointerException - if the specified element is null and this collection does not permit null elements (optional).
     */
    public boolean contains(Molde o) throws ClassCastException, NullPointerException{
    	Nodo raizTmp = this.getRaiz();
    	if(this.isEmpty()){
    		return false;
    	}
    	
    	//si es la raiz el buscado
    	if(this.compararDato(o.getEdad(), raizTmp.getDato().getEdad())==0){
	    	return true;
	    }
	    
    	while(raizTmp.getDerecha()!=null || raizTmp.getIzquierda()!=null){

	    	if(this.compararDato(o.getEdad(), raizTmp.getDato().getEdad())>0){
	    		if(raizTmp.getDerecha()!=null){   		
	    			raizTmp = raizTmp.getDerecha();
	    		}else{
	    			return false;
	    		}
	    	}else if(this.compararDato(o.getEdad(), raizTmp.getDato().getEdad())<0){
	    		if(raizTmp.getIzquierda()!=null){   
		    		raizTmp = raizTmp.getIzquierda();
	    		}else{
	    			return false;
	    		}
	    	}
	    	
	    	if(this.compararDato(o.getEdad(), raizTmp.getDato().getEdad())==0){
	    		return true;
	    	}
    	}
    	return false;
    }

    /**
     *Metodo para saber si el arbol esta vacio  *
     * @return true si está vacio, false en caso contrario
     */ 
    public boolean isEmpty(){
    	return this.size()==0;
    	//?? tal vez this.getRaiz()==null?
    }
    
    /** 
     * Returns an iterator over the elements contained in this collection. 
     * @return an iterator over the elements contained in this collection.
     */
    public Iterator<Molde> iterator(){
    	List<Molde> lista= this.inOrden();
    	Iterator<Molde> iter = lista.iterator();
    	
    	return iter;
    }
    
    /**
     * Removes a single instance of the specified element from this collection, if it is present (optional operation). More formally, removes an element e such that (o==null ? e==null : o.equals(e)), if this collection contains one or more such elements. Returns true if this collection contained the specified element (or equivalently, if this collection changed as a result of the call).
     * This implementation iterates over the collection looking for the specified element. If it finds the element, it removes the element from the collection using the iterator's remove method.
     * Note that this implementation throws an UnsupportedOperationException if the iterator returned by this collection's iterator method does not implement the remove method and this collection contains the specified object. 
     *
     * @param o - element to be removed from this collection, if present.
     * @return true if an element was removed as a result of this call.
     * @throws ClassCastException - if the type of the specified element is incompatible with this collection (optional) .
     * @throws NullPointerException - if the specified element is null and this collection does not permit null elements (optional).
     */
    public boolean remove(Molde o) throws ClassCastException, NullPointerException{
    	Nodo borrar=null,mirar=null,cambiar=null, nPadre = null;
    	Nodo raizTmp = this.getRaiz();
    	Molde c_aux;
        Molde d_aux;
    	boolean salir = false;
    	int altDer = 0;
    	int altIzq = 0;
    	int a = 0;
    	
    	if(this.isEmpty()){
    		return false;
    	}

    	//el nodo a borrar es la raiz?
    	if(this.compararDato(o.getEdad(), raizTmp.getDato().getEdad())==0){
	    	salir = true;
	    	borrar = raizTmp;
	    }
    	
    	//si no es la raiz, lo buscamos
    	while(!salir && (raizTmp.getDerecha()!=null || raizTmp.getIzquierda()!=null)){

	    	if(this.compararDato(o.getEdad(), raizTmp.getDato().getEdad())>0){
	    		if(raizTmp.getDerecha()!=null){   		
	    			raizTmp = raizTmp.getDerecha();
	    		}else{
	    			return false;
	    		}
	    	}else if(this.compararDato(o.getEdad(), raizTmp.getDato().getEdad())<0){
	    	
	    		if(raizTmp.getIzquierda()!=null){   
		    		raizTmp = raizTmp.getIzquierda();
	    		}else{
	    			return false;
	    		}
	    	}
	    	
	    	if(this.compararDato(o.getEdad(), raizTmp.getDato().getEdad())==0){
	    		salir = true;
	    		borrar = raizTmp;
	    	}
    	}
    

    	//existe el nodo a borrar?
    	if(salir){
    		mirar = borrar;

	    	//es una hoja?
	    	if(borrar.getIzquierda()==null && borrar.getDerecha()==null){
	    		mirar= padre(borrar);
	    		nPadre = padre(borrar);
	    		
	    		//es un arbol raiz con solo un nodo raiz?
	    		if(this.size()==1){
	    			this.raiz = null;
	    		}
	    		
	    		if(nPadre.getIzquierda()!=null && compararDato(nPadre.getIzquierda().getDato().getEdad(), borrar.getDato().getEdad())==0){
	    			nPadre.setIzquierda(null);
	    		}else if(nPadre.getDerecha()!=null && compararDato(nPadre.getDerecha().getDato().getEdad(), borrar.getDato().getEdad())==0){
	    			nPadre.setDerecha(null);
	    		}
	    		//nos lo cargamos
	    		borrar.setDato(null);
	    	}
	    	
	    	//solo tiene un hijo? (o 2 pero en la misma altura) entonces la altura de ese subarbol ser� 1 o 2 (altura raiz = 1)
	    	else if(borrar.getAltura()<=2){

	    		if(borrar.getIzquierda()!=null){
	    			borrar.setDato(borrar.getIzquierda().getDato());
	    			borrar.setIzquierda(null);
	    		}
	    		
	    		else if(borrar.getDerecha()!=null){
	    			borrar.setDato(borrar.getDerecha().getDato());
	    			borrar.setDerecha(null);
	    		}
	    	}
	    	
	    	//cuando no es ni un hoja ni su padre. Es decir, est� por medio del arbol.
	    	else{

	    		//buscamos el mayor de la izquierda
		    	if(borrar.getIzquierda()!=null){
		    		cambiar = borrar.getIzquierda();
		    		
		    		while(cambiar.getDerecha()!=null){
		    			cambiar = cambiar.getDerecha();
		    		}
		    	}
		    		
		    	//buscamos el menor de la derecha
		    	else if(borrar.getDerecha()!=null){
		    		cambiar = cambiar.getDerecha();
		    	
		    		while(cambiar.getIzquierda()!=null){
		    			cambiar = cambiar.getIzquierda();
		    		}
		    	}
	    	
		    	c_aux = cambiar.getDato();
		    	Nodo papa = padre(cambiar);
		    	
		    	//si el nodo que hemos cambiado se ha quedado con alg�n hijo...
		    	if(cambiar.getIzquierda()!=null || cambiar.getDerecha()!=null){
			    	if(cambiar.getIzquierda()!=null){
			    		cambiar.setDato(cambiar.getIzquierda().getDato());
			    		cambiar.setIzquierda(null);
			    	}else if(cambiar.getDerecha()!=null){
			    		cambiar.setDato(cambiar.getDerecha().getDato());
			    		cambiar.setDerecha(null);
			    	}
		    	}
		    	//si no tiene hijos ya, lo eliminamos sin m�s
		    	else{		    	
			    	if(papa.getIzquierda()!=null && compararDato(papa.getIzquierda().getDato().getEdad(), cambiar.getDato().getEdad())==0){
			    		papa.setIzquierda(null);
			    	}else{
			    		papa.setDerecha(null);
			    	}
			    	cambiar.setDato(borrar.getDato());
			    	borrar.setDato(c_aux);
		    	}		    
	    	}
	    	
	    	while(equilibrado(this.getRaiz())<0){
    			if(mirar.getDerecha()==null){
	    			altDer = 0;
	    		}else{
	    			altDer = mirar.getDerecha().getAltura();
	    		}
	    		
	    		if(mirar.getIzquierda()==null){
	    			altIzq = 0;
	    		}else{
	    			altIzq = mirar.getIzquierda().getAltura();
	    		}
	    		
    			Nodo cambiar2 = estructurar(mirar, altIzq, altDer);
    			Nodo superior = padre(mirar);
    			
    			//si los nodos modificados tenian un padre anteriormente
    			if(compararDato(superior.getDato().getEdad(), mirar.getDato().getEdad())!=0){
    				if(superior.getIzquierda()!=null && compararDato(superior.getIzquierda().getDato().getEdad(), mirar.getDato().getEdad())==0){
	    				superior.setIzquierda(cambiar2);		
		    		}
		    		else if(superior.getDerecha()!=null && compararDato(superior.getDerecha().getDato().getEdad(), mirar.getDato().getEdad())==0){
	    				superior.setDerecha(cambiar2);
	    			}
    			}else{
    				this.raiz = cambiar2;
    			}
    			mirar = padre(mirar);
    		}
    		return true;	    	
    	}	
    	return false;
    }
    
 
    /**
     * Metodo para saber el tamaño del arbol
     * @return numero de elementos contenidos en el arbol
     */
    public int size(){
    	return this.preOrden().size();
    }

    /**
     * Este metodo recorre el arbol mediante el recorrido INORDEN y almacena cada elemento.
     * en una lista que se devuelve al terminar el recorrido.
     * @return lista Lista en inorden con el contenido del arbol.
     */
     
    public ArrayList<Molde> inOrden(){
	ArrayList<Molde> lista = new ArrayList<Molde>();
    	Nodo nodo = this.getRaiz();
    	Stack<Nodo> pila = new Stack<Nodo>();
     	
     	while((nodo!=null &&nodo.getDato()!=null)|| !pila.empty()){
     		if(nodo!=null){
     			pila.push(nodo);
     			nodo = nodo.getIzquierda();
     		}else{
     			nodo = pila.pop();
     			lista.add(nodo.getDato());
     			nodo = nodo.getDerecha();
     		}
     	} 	
    	
    	return lista;
    }
    
    
    /**
     * Este metodo recorre el arbol mediante el recorrido PREORDEN y almacena cada
     * elemento en una lista que se devuelve al terminar el recorrido.
     * @return lista Lista en preorden con el contenido del arbol.
     */
    public ArrayList<Molde> preOrden(){
    	ArrayList<Molde> lista = new ArrayList<Molde>();
    	Nodo nodo = this.getRaiz();
    	Stack<Nodo> pila = new Stack<Nodo>();

     	while((nodo!=null && nodo.getDato()!=null) || !pila.empty()){
     		if(nodo!=null){
     			lista.add(nodo.getDato());
     			pila.push(nodo);
     			nodo = nodo.getIzquierda();
     		}else{
     			nodo = pila.pop();
     			nodo = nodo.getDerecha();
     		}
     	} 	
    	
    	return lista;
    }
    

    /**
     * Este metodo recorre el arbol mediante el recorrido POSTORDEN y almacena cada
     * elemento en una lista que se devuelve al terminar el recorrido.
     * @return lista Lista en postOrden con el contenido del arbol.
     */
    public List<Molde> postOrden(){
    	List<Molde> lista = new ArrayList<Molde>();
    	Nodo nodo = this.getRaiz();
    	Stack<Nodo> pila1 = new Stack<Nodo>();
    	Stack<Boolean> pila2 = new Stack<Boolean>();
    	
    	while((nodo!=null && nodo.getDato()!=null) || !pila1.empty()){
    		
    		if(nodo!=null){
    			pila1.push(nodo);
    			pila2.push(true);
    			nodo = nodo.getIzquierda();
    		}else{
    			nodo = pila1.pop();
    			if(pila2.pop()){
    				pila1.push(nodo);
    				pila2.push(false);
    				nodo = nodo.getDerecha();
    			}else{
    				lista.add(nodo.getDato());
    				nodo = null;
    			}
    		}
    	}
    	
    	return lista;
    }
    
    /**
     * Se devuelve la altura del Nodo que contiene al dato pasado por par�metro, si 
     * el dato nose encuentra en el arbol se devuelve -1.
     * @param dato dato del que buscamos la altura.
     * @return altura del nodo
     */
    public int altura(Molde dato){
    	Nodo nodo = this.getNodo(dato);
    	if(!this.contains(dato)){
    		return -1;
    	}
    	
    	return nodo.getAltura();
    }

    /**
     * Se devuelve la profundidad del Nodo que contiene al dato pasado por par�metro,
     * si eldato no se encuentra en el arbol se devuelve -1. La profundidad del nodo
     * raiz es 0.
     * @param dato dato del que buscamos su profundidad.
     * @return profundidad del dato pasado por parametro
     */
    public int profundidad(Molde dato){
    	Nodo nodo = new Nodo(dato);
    	int profundidad = 0;
    	while(compararDato(nodo.getDato().getEdad(), this.getRaiz().getDato().getEdad())!=0){
    		profundidad++;
    		nodo = padre(nodo);
    	}
    	
    	return profundidad;
    }
    
    /**
     * Devuelve el Nodo que es la ra�z del �rbol.
     * @return nodo raiz.
     */
    public Nodo getRaiz(){
    	return this.raiz;
    }
    
    /**
     * Devuelve el Nodo que contiene al dato pasado por parametro.
     * @param dato dato del nodo a buscar.
     * @return nodo con el dato a buscar.
     */
    public Nodo getNodo(Molde dato){
     	Nodo raizTmp = this.getRaiz();
     	
     	if(this.isEmpty()){
     		return null;
     	}
    	
   		while(raizTmp.getDerecha()!=null || raizTmp.getIzquierda()!=null){

	    	if(this.compararDato(dato.getEdad(), raizTmp.getDato().getEdad())>0){
	    		if(raizTmp.getDerecha()!=null){   		
	    			raizTmp = raizTmp.getDerecha();
	    		}else{
	    			return null;
	    		}
	    	}else if(this.compararDato(dato.getEdad(), raizTmp.getDato().getEdad())<0){
	    		if(raizTmp.getIzquierda()!=null){   
		    		raizTmp = raizTmp.getIzquierda();
	    		}else{
	    			return null;
	    		}
	    	}
	    	
	    	if(this.compararDato(dato.getEdad(), raizTmp.getDato().getEdad())==0){
	    		return raizTmp;
	    	}
    	}
    	
    	return raizTmp;
    }
    
    /**
     * Devuelve el comparator que utiliza el arbol.
     * @return comparador que utiliza el arbol.
     */
    private Comparator<Molde> getComparator(){
    	return this.comparador;
    }
    
    /**
     * Extrae el dato de un nodo.
     * @param nodo nodo del que buscamos extraer el el nodo.
     * @return dato
     */
    public Molde extraeDato(Nodo nodo){
    	return nodo.getDato();
    }
    
    /**
     * Funcion que compara mediante un modo u otro dependiendo de si el objeto 
     * que se pasa por parametro es comparable o necesita de una funcion especifica.
     * @param t1 dato1
     * @param t2 dato2
     * @return un int negativo, un cero, o un int positivo si el primer objeto es
     * menor, igual o mayor respectivametne con especto al segundo objeto pasado por parametro.
     */
    private int compararDato(int t1, int t2){
    	if(this.comparador==null){
    		return ((Comparable)t1).compareTo(t2); //t1.getEdad   t2.getEdad
    	}else{
    		return this.comparador.compare(t1,t2);
    	}
    }

	/******************************************************************/
	/******************************************************************/
	
	/**
	 * Implementa los metodos pertenecientes a la estructura de un nodo.
	 */
	private class Nodo{
		/**
		 * Dato contenido en el nodo.
		 */
		private Molde dato;
		
		/**
		 * Nodo hijo izquierda.
		 */
		private Nodo izquierda;
                
		
		/**
		 * Nodo hijo derecha.
		 */
		private Nodo derecha;
		
		/**
		 * Factor de equilibrio del nodo.
		 */
		private int factorE;
	
		/**
		 * constructor por defecto.
		 */
		public Nodo(){	
			dato = null;
			izquierda = null;
			derecha = null;
			factorE = 0;
		}    
	
		/**
		 * Constructor con un parametro.
		 * @param dato que contendra el nodo.
		 */
		public Nodo(Molde dato){
			this.dato = dato;
			izquierda = null;
			derecha = null;
			factorE = 0;
		}    
		
		/**
		 * Devuelve el nodo a la izquierda del actual.
		 * @return nodo hijo izquierdo.
		 */
		public Nodo getIzquierda(){
			return izquierda;
		}
		/**
		 * Devuelve el nodo a la derecha del actual.
		 * @return nodo hijo derecho.
		 */
		public Nodo getDerecha(){
			return derecha;
		}
		
		/**
		 * Devuelve el dato contenido en el nodo.
		 * @return dato contenido en el nodo.
		 */
		public Molde getDato(){
			return (Molde) dato;
		}
		
		/**
		 * Asigna un nodo al hijo derecho del nodo.
		 * @param derecha dato a insertar.
		 */
		public void setDerecha(Nodo derecha){
			this.derecha = derecha;

		}
		
		/**
		 * Asigna un nodo al hijo izquierdo del nodo.
		 * @param izquierda dato a insertar.
		 */
		public void setIzquierda(Nodo izquierda){

			this.izquierda = izquierda;

		}
		
		/**
		 * Asigna un dato al nodo.
		 * @param dato dato a insertar en el nodo.
		 */
		public void setDato(Molde dato){
			this.dato = dato;
		}
		
		/**
		 * Obtiene el factor de equilibrio del nodo.
		 * @return factor de equilibrio del nodo.
		 */
		public int getFactorE(){
			int altDer = 0;
			int altIzq = 0;
			if(this.getDerecha()!=null){
		    	altDer = this.getDerecha().getAltura();
		   	}
		   	if(this.getIzquierda()!=null){		    
		   		altIzq = this.getIzquierda().getAltura();
		   	}
			return (altDer - altIzq);
		}
		
		/**
		 * Asigna un valor al factor de equilibrio.
		 * @param fe factor de equilibro para asignar al nodo.
		 */
		public void setFactorE(int fe){
			this.factorE = fe;
		}
		
		/**
		 * Devuelve la altura del nodo.
		 * @return altura del nodo.
		 */	
		public int getAltura(){
			int hIzq = 0;
			int hDer = 0;
			
			if(this.getDato()==null){
			  return 0;
	    	}
	
	
			if(this.getIzquierda()!=null){	
				hIzq = this.getIzquierda().getAltura();
			}else{
				hIzq = 0;
			}
	    	
	    	if(this.getDerecha()!=null){   
	    		hDer = this.getDerecha().getAltura();
	    	}else{
	    		hDer = 0;
	    	}
	    	return Math.max(hIzq, hDer) + 1;
		}
	}
}
