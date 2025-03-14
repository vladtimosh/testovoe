package org.example.servlet;

import org.example.dao.MemberDAO;
import org.example.model.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/members")
public class MemberServlet extends HttpServlet {
    private MemberDAO memberDAO;

    @Override
    public void init() {
        memberDAO = new MemberDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "add":
                addMember(request, response);
                break;
            case "update":
                updateMember(request, response);
                break;
            case "delete":
                deleteMember(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
        }
    }

    private void addMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fullName = request.getParameter("fullName");
        LocalDate membershipDate = LocalDate.parse(request.getParameter("membershipDate"));

        Member member = new Member(0, fullName, membershipDate);
        memberDAO.insertMember(member);
        response.sendRedirect("viewAllMembers.jsp");
    }

    private void updateMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String fullName = request.getParameter("fullName");
        LocalDate membershipDate = LocalDate.parse(request.getParameter("membershipDate"));

        Member member = new Member(id, fullName, membershipDate);
        memberDAO.updateMember(member);
        response.sendRedirect("viewAllMembers.jsp");
    }

    private void deleteMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        memberDAO.deleteMember(id);
        response.sendRedirect("viewAllMembers.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("viewAll".equals(action)) {
            List<Member> members = memberDAO.getAllMembers();
            request.setAttribute("members", members);
            request.getRequestDispatcher("viewAllMembers.jsp").forward(request, response);
        }
    }
}