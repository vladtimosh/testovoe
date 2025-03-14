package org.example.servlet;

import org.example.dao.LoanDAO;
import org.example.model.Loan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/loans")
public class LoanServlet extends HttpServlet {
    private LoanDAO loanDAO;

    @Override
    public void init() {
        loanDAO = new LoanDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "add":
                addLoan(request, response);
                break;
            case "update":
                updateLoan(request, response);
                break;
            case "delete":
                deleteLoan(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
        }
    }

    private void addLoan(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int memberId = Integer.parseInt(request.getParameter("memberId"));
        LocalDate loanDate = LocalDate.parse(request.getParameter("loanDate"));
        LocalDate returnDate = LocalDate.parse(request.getParameter("returnDate"));

        Loan loan = new Loan(0, memberId, bookId, loanDate, returnDate);
        loanDAO.insertLoan(loan);
        response.sendRedirect("viewAllLoans.jsp");
    }

    private void updateLoan(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int memberId = Integer.parseInt(request.getParameter("memberId"));
        LocalDate loanDate = LocalDate.parse(request.getParameter("loanDate"));
        LocalDate returnDate = LocalDate.parse(request.getParameter("returnDate"));

        Loan loan = new Loan(id, memberId, bookId, loanDate, returnDate);
        loanDAO.updateLoan(loan);
        response.sendRedirect("viewAllLoans.jsp");
    }

    private void deleteLoan(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        loanDAO.deleteLoan(id);
        response.sendRedirect("viewAllLoans.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("viewAll".equals(action)) {
            List<Loan> loans = loanDAO.getAllLoans();
            request.setAttribute("loans", loans);
            request.getRequestDispatcher("viewAllLoans.jsp").forward(request, response);
        }
    }
}