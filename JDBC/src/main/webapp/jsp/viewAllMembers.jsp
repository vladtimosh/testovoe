<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.model.Member" %>
<%@ page import="org.example.dao.MemberDAO" %>
<%
    MemberDAO memberDAO = new MemberDAO();
    List<Member> members = memberDAO.getAllMembers();
%>
<html>
<head>
    <title>Все Члены</title>
</head>
<body>
    <h1>Все Члены</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Полное Имя</th>
            <th>Дата Членства</th>
            <th>Действия</th>
        </tr>
        <%
            if (members != null && !members.isEmpty()) {
                for (Member member : members) {
        %>
                    <tr>
                        <td><%= member.getId() %></td>
                        <td><%= member.getFullName() %></td>
                        <td><%= member.getMembershipDate() %></td>
                        <td>
                            <a href="updateMember.jsp?id=<%= member.getId() %>">Редактировать</a>
                            <form action="members" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="<%= member.getId() %>">
                                <input type="submit" value="Удалить">
                            </form>
                        </td>
                    </tr>
        <%
                }
            } else {
        %>
                <tr>
                    <td colspan="4">Нет доступных членов.</td>
                </tr>
        <%
            }
        %>
    </table>
    <a href="addMember.jsp">Добавить члена</a>
    <a href="index.jsp">Назад</a>
</body>
</html>