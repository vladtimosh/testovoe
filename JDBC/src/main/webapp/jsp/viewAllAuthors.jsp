<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.model.Author" %>
<%@ page import="org.example.dao.AuthorDAO" %>
<%
    AuthorDAO authorDAO = new AuthorDAO();
    List<Author> authors = authorDAO.getAllAuthors();
%>
<html>
<head>
    <title>Все Авторы</title>
</head>
<body>
    <h1>Все Авторы</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Имя</th>
            <th>Дата рождения</th>
            <th>Действия</th>
        </tr>
        <%
            if (authors != null && !authors.isEmpty()) {
                for (Author author : authors) {
        %>
                    <tr>
                        <td><%= author.getId() %></td>
                        <td><%= author.getName() %></td>
                        <td><%= author.getBirthDate().map(date -> date.toString()).orElse("неизвестна") %></td>
                        <td>
                            <a href="updateAuthor.jsp?id=<%= author.getId() %>">Редактировать</a>
                            <form action="authors" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="<%= author.getId() %>">
                                <input type="submit" value="Удалить">
                            </form>
                        </td>
                    </tr>
        <%
                }
            } else {
        %>
                <tr>
                    <td colspan="4">Нет доступных авторов.</td>
                </tr>
        <%
            }
        %>
    </table>
    <a href="addAuthor.jsp">Добавить автора</a>
    <a href="index.jsp">Назад</a>
</body>
</html>