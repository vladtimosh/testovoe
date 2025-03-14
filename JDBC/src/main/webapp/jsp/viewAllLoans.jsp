<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.model.Loan" %>
<%@ page import="org.example.dao.LoanDAO" %>
<%
    LoanDAO loanDAO = new LoanDAO();
    List<Loan> loans = loanDAO.getAllLoans();
%>
<html>
<head>
    <title>Все Займы</title>
</head>
<body>
    <h1>Все Займы</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>ID Книги</th>
            <th>ID Члена</th>
            <th>Дата Займа</th>
            <th>Дата Возврата</th>
            <th>Действия</th>
        </tr>
        <%
            if (loans != null && !loans.isEmpty()) {
                for (Loan loan : loans) {
        %>
                    <tr>
                        <td><%= loan.getId() %></td>
                        <td><%= loan.getBookId() %></td>
                        <td><%= loan.getMemberId() %></td>
                        <td><%= loan.getLoanDate() %></td>
                        <td><%= loan.getReturnDate() != null ? loan.getReturnDate() : "Не возвращён" %></td>
                        <td>
                            <a href="updateLoan.jsp?id=<%= loan.getId() %>">Редактировать</a>
                            <form action="loans" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="<%= loan.getId() %>">
                                <input type="submit" value="Удалить">
                            </form>
                        </td>
                    </tr>
        <%
                }
            } else {
        %>
                <tr>
                    <td colspan="6">Нет доступных займов.</td>
                </tr>
        <%
            }
        %>
    </table>
    <a href="addLoan.jsp">Добавить займ</a>
    <a href="index.jsp">Назад</a>
</body>
</html>