
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kenne
 */
public class Main2 {
   private static ArrayList<Integer> lnum = new ArrayList();
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
            conn.setRequestMethod("POST");
 
            // Se crea un nuevo objeto, con contenido aleatorio, que será el que
            //  se envíe al servidor
        Dragones items = new Dragones();

        int cont = 1;
        ArrayList list1 = new ArrayList();
        for(int i = 0; i < 20; i++){
            int num = (int) (Math.random() * 1000);
            while(lnum.contains(num)){
                num = (int) (Math.random() * 1000);
            }
            lnum.add(num);
            items.getItemsList().add(new Dragon(Integer.toString(cont),num));
            cont++;
        }
      
        lnum.clear();
 
            // Aunque sólo se va a enviar un único objeto, se utilizará un objeto
            //  de la clase Items que almacenará dicho objeto. Se va a hacer
            //  así para usar siempre la clase Dragones al generar los XML
          
 
            // Convertir objeto items a XML y preparar para enviar al servidor
            JAXBContext jaxbContext = JAXBContext.newInstance(Dragones.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.marshal(items, conn.getOutputStream());
            // Ejecutar la conexión y obtener la respuesta
            InputStreamReader isr = new InputStreamReader(conn.getInputStream());
 
        } catch (JAXBException ex) {
            Logger.getLogger(Main2.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
            Logger.getLogger(Main2.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
}

