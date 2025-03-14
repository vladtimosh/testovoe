<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.model.Book" %>
<%@ page import="org.example.dao.BookDAO" %>
<%
    BookDAO bookDAO = new BookDAO();
    List<Book> books = bookDAO.getAllBooks();
%>
<html>
<head>
    <title>Все Книги</title>
</head>
<body>
    <h1>Все Книги</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Название</th>
            <th>Дата публикации</th>
            <th>ID Жанра</th>
            <th>Действия</th>
        </tr>
        <%
            if (books != null && !books.isEmpty()) {
                for (Book book : books) {
        %>
                    <tr>
                        <td><%= book.getId() %></td>
                        <td><%= book.getTitle() %></td>
                        <td><%= book.getPublicationDate() %></td>
                        <td><%= book.getGenreId() %></td>
                        <td>
                            <a href="updateBook.jsp?id=<%= book.getId() %>">Редактировать</a>
                            <form action="books" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="<%= book.getId() %>">
                                <input type="submit" value="Удалить">
                            </form>
                        </td>
                    </tr>
        <%
                }
            } else {
        %>
                <tr>
                    <td colspan="5">Нет доступных книг.</td>
                </tr>
        <%
            }
        %>
    </table>
    <a href="addBook.jsp">Добавить книгу</a>
    <a href="index.jsp">Назад</a>
</body>
</html>