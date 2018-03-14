/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.OperationManager;
import util.OperationType;

/**
 *
 * @author Иван
 */
public class Calculator extends HttpServlet {

    private HashMap<String, ArrayList<String>> mapOfUsersOperations = new HashMap();

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        //получаем параметры из запроса
        /* TODO output your page here. You may use following sample code. */
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>WebCalculator v1.0</title>");
        out.println("</head>");
        out.println("<body>");

        try {
            double arg1 = Double.parseDouble(request.getParameter("one"));
            double arg2 = Double.parseDouble(request.getParameter("two"));
            String operation = request.getParameter("operation");

            //получаем сессию из запроса
            HttpSession session = request.getSession();
            ArrayList<String> listOfOperations = (ArrayList<String>) session.getAttribute("list");

            OperationManager operationManager = new OperationManager();

            String result = operationManager.doOperation(arg1, arg2, OperationType.valueOf(operation.toUpperCase()));
            ArrayList<String> list;
            if (session.isNew()) {
                list = new ArrayList();
                list.add(result);
                mapOfUsersOperations.put(session.getId(), list);
                session.setAttribute("list", list);
            } else {
                list = (ArrayList<String>) session.getAttribute("list");
                list.add(0, result);
                mapOfUsersOperations.put(session.getId(), list);
            }

            out.println("<h3>You session id: " + session.getId() + "</br></h3>");

            out.println("<div style=\"float:left; width:200px; height:400px \">");
            out.println("<h3>Your operations:</h3>");
            out.println("<ol>");
            for (String s : list) {
                out.println("<li>" + s + "</li>");
            }
            out.println("</ol>");
            out.println("</div>");
            out.println("<div style=\"float:left; width:400px \">");
            //выводим таблицу левый столбец айдишники сессий, в правом список операций
            out.println("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\">");
            out.println("<tr>");
            out.println("<td>" + "id users" + "</td>");
            out.println("<td>" + "operations" + "</td>");
            for (Map.Entry<String, ArrayList<String>> entry : mapOfUsersOperations.entrySet()) {
                out.println("<tr>");
                out.println("<td>" + entry.getKey() + "</td>");
                out.println("<td>");
                for (String s : entry.getValue()) {
                    out.println(s + "</br>");
                }
                out.println("</td>");
                out.println("</tr");
            }
            out.println("</table>");
            out.println("</div>");
            /*  
            out.println("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\">");
            out.println("<tr>");
            out.println("<td>" + "id" + "</td>");
            out.println("<td>" + "operations" + "</td>");

            out.println("</tr>");
            for (int i = 0; i < 3; i++) {
                out.println("<tr>");
                out.println("<td>" + "i" + "</td>");
                for (int j = 0; j < 1; j++) {
                    out.println("<td>" + "j" + "</td>");
                }
                out.println("</tr>");
            }
            out.println("</table>");*/
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } finally {

            out.println("</body>");
            out.println("</html>");
            out.close();
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
        processRequest(request, response);
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
        processRequest(request, response);
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
