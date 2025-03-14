<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.model.Book" %>
<%@ page import="org.example.dao.BookDAO" %>
<%
    int bookId = Integer.parseInt(request.getParameter("id"));
    BookDAO bookDAO = new BookDAO();
    Book books = bookDAO.getBook(bookId);
%>
<html>
<head>
    <title>Обновить Книгу</title>
</head>
<body>
    <h1>Обновить Книгу</h1>
    <form action="update" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= book.getId() %>">
        <label for="title">Название:</label>
        <input type="text" name="title" value="<%= book.getTitle() %>" required>
        <br>
        <label for="publicationDate">Дата публикации (YYYY-MM-DD):</label>
        <input type="date" name="publicationDate" value="<%= book.getPublicationDate() %>" required>
        <br>
        <label for="genreId">ID Жанра:</label>
        <input type="number" name="genreId" value="<%= book.getGenreId() %>" required>
        <br>
        <input type="submit" value="Обновить">
    </form>
    <a href="viewAllBooks.jsp">Назад</a>
</body>
</html>