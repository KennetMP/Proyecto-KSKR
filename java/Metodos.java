import Algoritmos_de_ordenamiento.Molde;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Metodos {
   private Moldes items;

    
    public ArrayList<Molde> Get(){
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
            items = (Moldes)response;

            // Como ejemplo, se mostrará en la salida estándar alguno de los datos
            //  de los objetos contenidos en la lista que se encuentra en items
            for(Molde item : items.getItemsList()) {
                System.out.println(item.getEdad() + ": " + item.getEdad() + "<br>");
            }
            return (ArrayList<Molde>) items.getItemsList();

        } catch (JAXBException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (ArrayList<Molde>) items.getItemsList();

    }

    public void Post(ArrayList<Molde> d, String info){
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
            items = new Moldes();
            items.setItemsList(d);
            items.setInfo(info);

            // Aunque sólo se va a enviar un único objeto, se utilizará un objeto
            //  de la clase Items que almacenará dicho objeto. Se va a hacer
            //  así para usar siempre la clase Dragones al generar los XML


            // Convertir objeto items a XML y preparar para enviar al servidor
            JAXBContext jaxbContext = JAXBContext.newInstance(Moldes.class);
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
