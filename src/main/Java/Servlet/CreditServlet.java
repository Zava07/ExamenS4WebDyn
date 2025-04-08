package main.Java.Servlet;

import java.io.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import main.Java.Model.*;

public class CreditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
        String libelle  = request.getParameter("libelle");
        float montant = Float.parseFloat(request.getParameter("montant"));    
    
        try{
            Credit credit = new Credit(0, libelle, montant);
            CreditDAO creditDao = new CreditDAO();
            creditDao.save(credit);

            request.getRequestDispatcher("index.html").forward(request,response);
        }catch(Exception e){
            e.printStackTrace();
            // System.out.println();
        }
    }
}
