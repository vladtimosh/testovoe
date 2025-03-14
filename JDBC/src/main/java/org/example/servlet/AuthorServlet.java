package org.example.servlet;

import org.example.dao.AuthorDAO;
import org.example.model.Author;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/authors")
public class AuthorServlet extends HttpServlet {
    private AuthorDAO authorDAO;

    @Override
    public void init() {
        // Инициализируйте DAO здесь
        authorDAO = new AuthorDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "add":
                addAuthor(request, response);
                break;
            case "update":
                updateAuthor(request, response);
                break;
            case "delete":
                deleteAuthor(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
        }
    }

    private void addAuthor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));
        Author author = new Author(0, name, birthDate);
        authorDAO.insertAuthor(author);
        response.sendRedirect("index.jsp");
    }

    private void updateAuthor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));
        Author author = new Author(id, name, birthDate);
        authorDAO.updateAuthor(author);
        response.sendRedirect("viewAllAuthors.jsp");
    }

    private void deleteAuthor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        authorDAO.deleteAuthor(id);
        response.sendRedirect("viewAllAuthors.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("viewAll".equals(action)) {
            List<Author> authors = authorDAO.getAllAuthors();
            request.setAttribute("authors", authors);
            request.getRequestDispatcher("viewAllAuthors.jsp").forward(request, response);
        }
    }
}