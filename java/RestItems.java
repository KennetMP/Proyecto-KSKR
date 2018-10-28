/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Algoritmos_de_ordenamiento.AsignarPos;
import Algoritmos_de_ordenamiento.Familia;
import Algoritmos_de_ordenamiento.Ordenador1;
import Arboles.ArbolAVL1;
import Arboles.ArbolBi;
import Arboles.NodoA;
import Algoritmos_de_ordenamiento.Molde;
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
    private Moldes items = new Moldes();
    private static ArrayList<Integer> lnum = new ArrayList();
    private Ordenador1 ordenador = new Ordenador1();
    private ArbolBi arbolbi;
    private Familia family = new Familia();
    private AsignarPos pos = new AsignarPos();
    private ArbolAVL1 arbolAVL;
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
      
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try  {
            /* TODO output your page here. You may use following sample code. */
            
            JAXBContext jaxbContext = JAXBContext.newInstance(Moldes.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            switch (tipoPeticion) {
                case PETICION_GET:
                   jaxbMarshaller.marshal(items, out);
                   break;
                case PETICION_POST:
                    Moldes newItems = (Moldes) jaxbUnmarshaller.unmarshal(request.getInputStream());
                    // Recorrer la lista obteniendo cada objeto contenido en ella
                    items.setItemsList(newItems.getItemsList());
                    //pos.linea(items.getItemsList()); 
                    int num = (int) (Math.random() * 2);
                    //items.setItemsList(newItems.getItemsList());
                    if(newItems.getInfo().equals("Arbol AVL")){
                        arbolAVL = new ArbolAVL1();
                        System.out.println("Arbol AVL");
                        for(Molde draco : newItems.getItemsList()){
                            arbolAVL.add(draco);
                        }
                    items.setItemsList((ArrayList) arbolAVL.preOrden());
                    items.getInfo();
                    }
                    else if(newItems.getInfo().equals("Arbol Binario")){
                       arbolbi = new ArbolBi();
                       System.out.println("Arbol Binario");
                       for(Molde draco : newItems.getItemsList()){
                            arbolbi.addNodo(new NodoA(draco));
                        }
                       
                    arbolbi.recorrer();
                   
                    pos.jerarquicas(arbolbi.getLista());
                     //family.family(arbolbi.getLista());
                    
                    items.setItemsList(arbolbi.getLista());
                    }
                    else if(newItems.getInfo().equals("Selection Sort")){
                      items.setItemsList(ordenador.Selection(newItems.getItemsList(),num));              
                      pos.linea(items.getItemsList());              
                      System.out.println("Selection Sort");
                      Logger.getLogger("llegó");
                    }
                    else if(newItems.getInfo().equals("Quick Sort")){
                      items.setItemsList(ordenador.QuickSort((ArrayList)newItems.getItemsList(),num));
                      System.out.println("Quick Sort");
                    }
                    else if(newItems.getInfo() == "Insertion Sort"){
                        items.setItemsList(ordenador.insertionSort((ArrayList)newItems.getItemsList(),num));
                        System.out.println("Insertion Sort");
                    }
                    break;
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
