<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить Займ</title>
</head>
<body>
    <h1>Добавить Займ</h1>
    <form action="add" method="post">
        <input type="hidden" name="action" value="add">
        <label for="bookId">ID Книги:</label>
        <input type="number" name="bookId" required>
        <br>
        <label for="memberId">ID Члена:</label>
        <input type="number" name="memberId" required>
        <br>
        <label for="loanDate">Дата Займа (YYYY-MM-DD):</label>
        <input type="date" name="loanDate" required>
        <br>
        <label for="returnDate">Дата Возврата (YYYY-MM-DD):</label>
        <input type="date" name="returnDate">
        <br>
        <input type="submit" value="Добавить">
    </form>
    <a href="viewAllLoans.jsp">Назад</a>
</body>
</html>