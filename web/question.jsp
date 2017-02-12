<%-- 
    Document   : question
    Created on : Feb 3, 2017, 4:04:45 PM
    Author     : lukasmohs
--%>

<%@page contentType="text/html" 
        pageEncoding="UTF-8"
        import="javafx.util.Pair,java.util.ArrayList,OnlineSurvey.OnlineSurveyModelQuestion,OnlineSurvey.OnlineSurveyModel"
        %>
<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Survey</title>
    </head>
    <h3><i><%= OnlineSurveyModel.getInstance().getAnswerToLastQuestion(request.getParameter("userName")) %></i></h3>
    <h1>Next Question for <i><%= request.getParameter("userName") %> </i> :</h1>
        <form action="submit">
            <input type="hidden" name="userName" value="<%= request.getParameter("userName") %>"/>
            <%= OnlineSurveyModel.getInstance().getNextQuestion(request.getParameter("userName")).getQuestion() %>
            <% int answerCount = 0; %>
            <%for(Pair<String, Boolean> s:OnlineSurveyModel.getInstance().getNextQuestion(request.getParameter("userName")).getAllAnswers()){%>
                <div style="background-color:lightgray;padding:20px;margin:20px;">
                    <input name="answer" type="radio" value='<%= answerCount %>'> <b><%= answerCount+1 %>:</b> <%= s.getKey() %> <br> </input>
                </div>
                <% answerCount++; %>
            <%}%>
            <button style="padding:20px;margin:20px;"> Submit Answer </button>
        </form>

            
    

