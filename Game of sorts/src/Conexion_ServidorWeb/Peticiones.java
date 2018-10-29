/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion_ServidorWeb;

import Personajes.Dragon;
import Principal.Juego;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *Se encarga de generar la conexion entre el juego y la Rest Api, permitiendo usar el metodo Post()
 * y Get().
 * @author kevin Avevedo
 */
public class Peticiones {
   private Moldes items;
   private Juego juego;
   public Peticiones(Juego juego){
       this.juego = juego;
       
   }

    /**
     * Se encarga de obtener las respuestas que provienen del servidor web.
     * @return Un ArrayList que viene con las modificaciones de las oleadas de dragones
     */
    public  ArrayList<Molde> Get(){
        try {
            // Crear una conexiÃ³n con la URL del Servlet
            String strConnection = "http://192.168.137.115:8080/WebApplication1/RestItems";
            URL url = new URL(strConnection);
            URLConnection uc = url.openConnection();
            HttpURLConnection conn = (HttpURLConnection) uc;
            // La conexiÃ³n se va a realizar para poder enviar y recibir informaciÃ³n
            //   en formato XML
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-type", "text/xml");
            // Se va a realizar una peticiÃ³n con el mÃ©todo GET
            conn.setRequestMethod("GET");

            // Ejecutar la conexiÃ³n y obtener la respuesta
            InputStreamReader isr = new InputStreamReader(conn.getInputStream());

            // Procesar la respuesta (XML) y obtenerla como un objeto de tipo Dragones
            JAXBContext jaxbContext = JAXBContext.newInstance(Moldes.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Object response = jaxbUnmarshaller.unmarshal(isr);
            isr.close();

            // Convertir a la clase Items el objeto obtenido en la respuesta
            items = (Moldes)response;

            // Como ejemplo, se mostrarÃ¡ en la salida estÃ¡ndar alguno de los datos
            //  de los objetos contenidos en la lista que se encuentra en items

            
            
            
            /////////////////////////////////////////////////////////////

            for(int i = 0; i < items.getItemsList().size(); i++){
                Dragon dragon = juego.dracos.get(i);
                dragon.setX(items.getItemsList().get(i).getX());
                dragon.setY(items.getItemsList().get(i).getY());

              
            }
      
           
            ////////////////////////////////////////////////////////////////
        } catch (JAXBException ex) {
            Logger.getLogger(Peticiones.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
            Logger.getLogger(Peticiones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (ArrayList<Molde>) items.getItemsList();

    }
    
    
    /**
     * Se encarga de enviar una peticion al servidor web con el fin de obtener los datos de los dragones modificados.
     * @param d Un ArrayList que contiene moldes con los datos de cada dragon existente en la pantalla.
     * @param info Un String que le va a indicar al servidor el ordenamiento deseado.
     */
    public void Post(ArrayList<Molde> d, String info){
        try {
            // Crear una conexiÃ³n con la URL del Servlet
            String strConnection = "http://192.168.137.115:8080/WebApplication1/RestItems";
            URL url = new URL(strConnection);
            URLConnection uc = url.openConnection();
            HttpURLConnection conn = (HttpURLConnection) uc;
            // La conexiÃ³n se va a realizar para poder enviar y recibir informaciÃ³n
            //   en formato XML
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-type", "text/xml");
            conn.setRequestMethod("POST");

            // Se crea un nuevo objeto, con contenido aleatorio, que serÃ¡ el que
            //  se envÃ­e al servidor
            items = new Moldes();
            items.setItemsList(d);
            items.setInfo(info);
            /*for(int i = 0; i < d.size(); i++){
                Molde molde = new Molde();
                molde.setEdad(d.get(i).getEdad());
                molde.setNombre(d.get(i).getNombre());
                molde.setVelocidad(d.get(i).getVelocidad());
            
            items.getItemsList().add(molde);         
        }*/

            // Aunque sÃ³lo se va a enviar un Ãºnico objeto, se utilizarÃ¡ un objeto
            //  de la clase Items que almacenarÃ¡ dicho objeto. Se va a hacer
            //  asÃ­ para usar siempre la clase Dragones al generar los XML


            // Convertir objeto items a XML y preparar para enviar al servidor
            JAXBContext jaxbContext = JAXBContext.newInstance(Moldes.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.marshal(items, conn.getOutputStream());
            // Ejecutar la conexiÃ³n y obtener la respuesta
            InputStreamReader isr = new InputStreamReader(conn.getInputStream());

        } catch (JAXBException ex) {
            Logger.getLogger(Peticiones.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
            Logger.getLogger(Peticiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
