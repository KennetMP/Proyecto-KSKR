
import Algoritmos_de_ordenamiento.Molde;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kenne
 */
public class Main  {

    public static void main(String[] args) {
        try {
            // Crear una conexión con la URL del Servlet
            String strConnection = "http://localhost:8080/WebApplication1/RestItems";
            URL url = new URL(strConnection);
            URLConnection uc = url.openConnection();
            HttpURLConnection conn = (HttpURLConnection) uc;
            // La conexión se va a realizar para poder enviar y recibir información
            //   en formato XML
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-type", "text/xml");
            // Se va a realizar una petición con el método GET
            conn.setRequestMethod("GET");
 
            // Ejecutar la conexión y obtener la respuesta
            InputStreamReader isr = new InputStreamReader(conn.getInputStream());
 
            // Procesar la respuesta (XML) y obtenerla como un objeto de tipo Dragones
            JAXBContext jaxbContext = JAXBContext.newInstance(Moldes.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Object response = jaxbUnmarshaller.unmarshal(isr);
            isr.close();
 
            // Convertir a la clase Items el objeto obtenido en la respuesta
            Moldes items = (Moldes)response;
 
            // Como ejemplo, se mostrará en la salida estándar alguno de los datos
            //  de los objetos contenidos en la lista que se encuentra en items
            for(Molde item : items.getItemsList()) {
                System.out.println(item.getX()+ ": " + item.getEdad() + "<br>");
            }
            conn.disconnect();
            
        } catch (JAXBException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
        
 
}
}