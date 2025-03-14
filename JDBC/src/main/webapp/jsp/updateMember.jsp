<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.model.Member" %>
<%@ page import="org.example.dao.MemberDAO" %>
<%
    int memberId = Integer.parseInt(request.getParameter("id"));
    MemberDAO memberDAO = new MemberDAO();
    Member members = memberDAO.getMember(memberId);
%>
<html>
<head>
    <title>Обновить Члена</title>
</head>
<body>
    <h1>Обновить Члена</h1>
    <form action="update" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= member.getId() %>">
        <label for="fullName">Полное Имя:</label>
        <input type="text" name="fullName" value="<%= member.getFullName() %>" required>
        <br>
        <label for="membershipDate">Дата Членства (YYYY-MM-DD):</label>
        <input type="date" name="membershipDate" value="<%= member.getMembershipDate() %>" required>
        <br>
        <input type="submit" value="Обновить">
    </form>
    <a href="viewAllMembers.jsp">Назад</a>
</body>
</html>