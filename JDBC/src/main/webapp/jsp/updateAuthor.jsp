<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.model.Author" %>
<%@ page import="org.example.dao.AuthorDAO" %>
<%
    int authorId = Integer.parseInt(request.getParameter("id"));
    AuthorDAO authorDAO = new AuthorDAO();
    Author authors = authorDAO.getAuthor(authorId);
%>
<html>
<head>
    <title>Обновить Автора</title>
</head>
<body>
    <h1>Обновить Автора</h1>
    <form action="update" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= author.getId() %>">
        <label for="name">Имя:</label>
        <input type="text" name="name" value="<%= author.getName() %>" required>
        <br>
        <label for="birthDate">Дата рождения (YYYY-MM-DD):</label>
        <input type="date" name="birthDate" value="<%= author.getBirthDate().map(date -> date.toString()).orElse("") %>">
        <br>
        <input type="submit" value="Обновить">
    </form>
    <a href="viewAllAuthors.jsp">Назад</a>
</body>
</html>