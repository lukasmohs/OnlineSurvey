<%-- 
    Document   : results
    Created on : Feb 3, 2017, 6:18:12 PM
    Author     : lukasmohs
--%>

<%@page contentType="text/html" 
        pageEncoding="UTF-8"
        import="javafx.util.Pair,java.util.ArrayList,OnlineSurvey.OnlineSurveyModelQuestion,OnlineSurvey.OnlineSurveyModel"
        %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Survey</title>
    </head>
    <body>
        <% if(request.getParameter("userName")!=null) { %>
            <h1>Results for <i><%= request.getParameter("userName") %> </i> :</h1>
            <h3><a href="/Project1Task3/index.html">Start over</a></h3>
            <h2>Overall Summary</h2>
            <h3>Correctly answered: <%= OnlineSurveyModel.getInstance().getNumberOfCorrectAnswers(request.getParameter("userName")) %> / <%= OnlineSurveyModel.getInstance().getNumberOfQuestions() %></h3>
            <h2>Detailed Answers</h2>
            <%for(Pair<OnlineSurveyModelQuestion, Integer> s:OnlineSurveyModel.getInstance().getUserAnswers(request.getParameter("userName"))){%>
            <h3><b> Question: </b> <%= s.getKey().getQuestion() %> </h3> <br>
            <b> Your Answer: </b> <%= s.getKey().getAllAnswers().get(s.getValue()).getKey() %> <br>
            is  <b><%=s.getKey().getAllAnswers().get(s.getValue()).getValue() %> </b>
            <% } %>
        <% } else if(!OnlineSurveyModel.getInstance().getUserStatus().isEmpty()){ %>
             <% for(String s:OnlineSurveyModel.getInstance().getUserStatus().keySet()) { %>
             <h2>Results for <%= s %></h2>
             
             <% } %>
        <% } else { %>
            <br>
            <h2 style="color:red;"><i>Unfortunately, there are no results to show</i></h2>
        <% } %>
    </body>
</html>
