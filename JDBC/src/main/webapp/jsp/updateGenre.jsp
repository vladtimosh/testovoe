<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.model.Genre" %>
<%@ page import="org.example.dao.GenreDAO" %>
<%
    int genreId = Integer.parseInt(request.getParameter("id"));
    GenreDAO genreDAO = new GenreDAO();
    Genre genres = genreDAO.getGenre(genreId);
%>
<html>
<head>
    <title>Обновить Жанр</title>
</head>
<body>
    <h1>Обновить Жанр</h1>
    <form action="update" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= genre.getId() %>">
        <label for="genreName">Название жанра:</label>
        <input type="text" name="genreName" value="<%= genre.getGenreName() %>" required>
        <br>
        <input type="submit" value="Обновить">
    </form>
    <a href="viewAllGenres.jsp">Назад</a>
</body>
</html>