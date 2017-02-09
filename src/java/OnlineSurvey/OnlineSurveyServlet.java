/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnlineSurvey;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lukasmohs
 */
@WebServlet(name = "OnlineSurveyServlet",
        urlPatterns = {"/submit","/getResults"})
public class OnlineSurveyServlet extends HttpServlet {
    
    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher view;

        //Check if user entered a valid username
        if(request.getParameter("userName") != null && !request.getParameter("userName").isEmpty()) {
            //Retrieve username parameter
            String userName = request.getParameter("userName");
            
            //Check if user has completed all questions -> redirect to results page
            if(OnlineSurveyModel.getInstance().completedAllQuestions(userName)){
                if(request.getParameter("answer") != null && !request.getParameter("answer").isEmpty()) {
                    Integer answerId = new Integer(request.getParameter("answer"));
                    OnlineSurveyModel.getInstance().submitAnswer(userName, answerId);
                }               
                view = request.getRequestDispatcher("results.jsp");
            //Save answer and show next question
            } else {
                if(request.getParameter("answer") != null && !request.getParameter("answer").isEmpty()) {
                    Integer answerId = new Integer(request.getParameter("answer"));
                    OnlineSurveyModel.getInstance().submitAnswer(userName, answerId);
                }               
                view = request.getRequestDispatcher("question.jsp");
            }
        //Without a valid username -> redirect to start page
        } else {
            view = request.getRequestDispatcher("index.html");
        }
        
        //If getResults Pattern requested, forward directly there:
        if("/getResults".equalsIgnoreCase(request.getServletPath()))
        {
            view = request.getRequestDispatcher("results.jsp");
        }
        
        // determine what type of device our user is
        String userAgent = request.getHeader("User-Agent");
        
        boolean mobile;
        // prepare the appropriate DOCTYPE for the view pages
        if (userAgent != null && ((userAgent.indexOf("Android") != -1) || (userAgent.indexOf("iPhone") != -1))) {
            mobile = true;
            /*
             * This is the latest XHTML Mobile doctype. To see the difference it
             * makes, comment it out so that a default desktop doctype is used
             * and view on an Android or iPhone.
             */
            request.setAttribute("doctype", "<!DOCTYPE html PUBLIC \"-//WAPFORUM//DTD XHTML Mobile 1.2//EN\" \"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd\">");
        } else {
            mobile = false;
            request.setAttribute("doctype", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        }
        
        view.forward(request, response);
    }
}
