package main.Java.Servlet;

import java.io.*;
import java.util.List;


import jakarta.servlet.*;
import jakarta.servlet.http.*;

import main.Java.Model.*;

public class DepenseServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try{
            CreditDAO creditDAO = new CreditDAO();
            List<BaseModel> listCredit = creditDAO.findAll();
            request.setAttribute("listeCredit", listCredit);
            request.getRequestDispatcher("formDepense.jsp").forward(request,response);
            
        }catch(Exception e){
            e.printStackTrace();
            out.println(e.getMessage());
        }
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int idCredit = Integer.parseInt(request.getParameter("credit"));
        float montantDepense = Float.parseFloat(request.getParameter("montantDepense"));
        try{
            Depense depense = new Depense(0, montantDepense, idCredit);
            DepenseDAO depenseDAO  = new DepenseDAO();
            depenseDAO.save(depense);
            CreditDAO creditDAO = new CreditDAO();
            List<BaseModel> listCredit = creditDAO.findAll();
            request.setAttribute("listeCredit", listCredit);
            request.getRequestDispatcher("formDepense.jsp").forward(request,response);
            
        }catch(Exception e){
            e.printStackTrace();
            out.println(e.getMessage());
        }
        
    }
}
