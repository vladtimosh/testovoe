<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.model.Loan" %>
<%@ page import="org.example.dao.LoanDAO" %>
<%
    int loanId = Integer.parseInt(request.getParameter("id"));
    LoanDAO loanDAO = new LoanDAO();
    Loan loans = loanDAO.getLoan(loanId);
%>
<html>
<head>
    <title>Обновить Займ</title>
</head>
<body>
    <h1>Обновить Займ</h1>
    <form action="update" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= loan.getId() %>">
        <label for="bookId">ID Книги:</label>
        <input type="number" name="bookId" value="<%= loan.getBookId() %>" required>
        <br>
        <label for="memberId">ID Члена:</label>
        <input type="number" name="memberId" value="<%= loan.getMemberId() %>" required>
        <br>
        <label for="loanDate">Дата Займа (YYYY-MM-DD):</label>
        <input type="date" name="loanDate" value="<%= loan.getLoanDate() %>" required>
        <br>
        <label for="returnDate">Дата Возврата (YYYY-MM-DD):</label>
        <input type="date" name="returnDate" value="<%= loan.getReturnDate() != null ? loan.getReturnDate() : "" %>">
        <br>
        <input type="submit" value="Обновить">
    </form>
    <a href="viewAllLoans.jsp">Назад</a>
</body>
</html>