<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.model.Genre" %>
<%@ page import="org.example.dao.GenreDAO" %>
<%
    GenreDAO genreDAO = new GenreDAO();
    List<Genre> genres = genreDAO.getAllGenres();
%>
<html>
<head>
    <title>Все Жанры</title>
</head>
<body>
    <h1>Все Жанры</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Название жанра</th>
            <th>Действия</th>
        </tr>
        <%
            if (genres != null && !genres.isEmpty()) {
                for (Genre genre : genres) {
        %>
                    <tr>
                        <td><%= genre.getId() %></td>
                        <td><%= genre.getGenreName() %></td>
                        <td>
                            <a href="updateGenre.jsp?id=<%= genre.getId() %>">Редактировать</a>
                            <form action="genres" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="<%= genre.getId() %>">
                                <input type="submit" value="Удалить">
                            </form>
                        </td>
                    </tr>
        <%
                }
            } else {
        %>
                <tr>
                    <td colspan="3">Нет доступных жанров.</td>
                </tr>
        <%
            }
        %>
    </table>
    <a href="addGenre.jsp">Добавить жанр</a>
    <a href="index.jsp">Назад</a>
</body>
</html>