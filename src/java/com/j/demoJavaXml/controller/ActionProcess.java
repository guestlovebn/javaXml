/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.j.demoJavaXml.controller;

import com.j.demoJavaXml.model.DAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ValidationException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import com.j.demoJavaXml.model.ProcessingXML;
import nu.xom.ParsingException;

/**
 *
 * @author LongChimNgan
 */
public class ActionProcess extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, SQLException, ParsingException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("act");
        String error = "";
        HttpSession session = request.getSession();
        String url = getServletContext().getRealPath("/");
        if (action.equals("getdata")) {
            DAO dao = new DAO();
            try {
                dao.writeXML(url);
                request.setAttribute("info", "creating file is ok!");
                session.setAttribute("wrote", "ok");
                getServletContext().getRequestDispatcher("/Products.xml").forward(request, response);
            } catch (ParserConfigurationException ex) {
                error = ex.getMessage();
                Logger.getLogger(ActionProcess.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerException ex) {
                error = ex.getMessage();
                Logger.getLogger(ActionProcess.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                error = ex.getMessage();
            }
        } else if (action.equals("showdata")) {
            if (session.getAttribute("wrote") != null) {
                ProcessingXML xulyXML = new ProcessingXML();
                try {
                    ArrayList aL = xulyXML.readXML(url);
                    request.setAttribute("products", aL);
                    getServletContext().getRequestDispatcher("/product.jsp").forward(request, response);
                } catch (ParsingException ex) {
                    error = ex.getMessage();
                    Logger.getLogger(ActionProcess.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                request.setAttribute("info", "you should \"getdata\" first!");
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } else if (action.equals("filteddata")) {
            if (session.getAttribute("wrote") != null) {
                String filter = request.getParameter("filter");
                try {
                    int IntFilter = Integer.parseInt(filter);
                } catch (NumberFormatException numberFormatException) {
                    request.setAttribute("info", "you should enter numberic format for value to filt data!");
                    getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

                }
                ProcessingXML xulyXML = new ProcessingXML();
                try {
                    ArrayList aL = xulyXML.filtedContent(url, filter);
                    request.setAttribute("products", aL);
                    getServletContext().getRequestDispatcher("/product.jsp").forward(request, response);
                } catch (ValidationException ex) {
                    error = ex.getMessage();
                } catch (ParsingException ex) {
                    error = ex.getMessage();
                    Logger.getLogger(ActionProcess.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                request.setAttribute("info", "you should \"get data\" first!");
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }
            if (!"".equals(error)) {
                request.setAttribute("error", error);
                getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ActionProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ActionProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParsingException ex) {
            Logger.getLogger(ActionProcess.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ActionProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ActionProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParsingException ex) {
            Logger.getLogger(ActionProcess.class.getName()).log(Level.SEVERE, null, ex);
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
