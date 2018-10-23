/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author kenne
 */
@WebServlet(name = "RestItems", urlPatterns = {"/RestItems"})
public class RestItems extends HttpServlet {
    public static final byte PETICION_GET = 0;    // SELECT
    public static final byte PETICION_POST = 1;   // INSERT
    public static final byte PETICION_PUT = 2;    // UPDATE
    public static final byte PETICION_DELETE = 3; // DELETE
 
    private byte tipoPeticion;
    private Dragones items = new Dragones();
    private static ArrayList<Integer> lnum = new ArrayList();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, PropertyException, JAXBException {
        if(items.getItemsList().isEmpty()) {
        int cont = 1;
        ArrayList list1 = new ArrayList();
        for(int i = 0; i < 100; i++){
            int num = (int) (Math.random() * 1000);
            while(lnum.contains(num)){
                 num = (int) (Math.random() * 1000);
            }
            lnum.add(num);
            items.getItemsList().add(new Dragon(Integer.toString(cont),num));
            cont++;
        }
        }
        lnum.clear();
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try  {
            /* TODO output your page here. You may use following sample code. */
            
            JAXBContext jaxbContext = JAXBContext.newInstance(Dragones.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            switch (tipoPeticion) {
                case PETICION_GET:
                   jaxbMarshaller.marshal(items, out);
                   break;
                case PETICION_POST:
                    Dragones newItems = (Dragones) jaxbUnmarshaller.unmarshal(request.getInputStream());
                    // Recorrer la lista obteniendo cada objeto contenido en ella
                    for(Dragon item :  newItems.getItemsList()) {
                        // Añadir cada objeto a la lista general
                        items.getItemsList().add(item); 
                        System.out.println(item.getEdad());
                    }break;
                case PETICION_PUT:
                    // Escribir aquí las accciones para peticiones por PUT
                    break;
                case PETICION_DELETE:
                    // Escribir aquí las accciones para peticiones por DELETE
                    break;
                default:
                    break;
            }     
          } catch (JAXBException ex) {
            Logger.getLogger(RestItems.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        tipoPeticion = PETICION_DELETE;
        try {
            processRequest(req, resp);
        } catch (JAXBException ex) {
            Logger.getLogger(RestItems.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        tipoPeticion = PETICION_PUT;
        try {
            processRequest(req, resp);
        } catch (JAXBException ex) {
            Logger.getLogger(RestItems.class.getName()).log(Level.SEVERE, null, ex);
        }
 }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        tipoPeticion = PETICION_GET;
        try {
            processRequest(request, response);
        } catch (JAXBException ex) {
            Logger.getLogger(RestItems.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        tipoPeticion = PETICION_POST;
        try {
            processRequest(request, response);
        } catch (JAXBException ex) {
            Logger.getLogger(RestItems.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
