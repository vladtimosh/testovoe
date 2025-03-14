package org.example.servlet;

import org.example.dao.BookDAO;
import org.example.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/books")
public class BookServlet extends HttpServlet {
    private BookDAO bookDAO;

    @Override
    public void init() {
        // Инициализируйте DAO здесь
        bookDAO = new BookDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "add":
                addBook(request, response);
                break;
            case "update":
                updateBook(request, response);
                break;
            case "delete":
                deleteBook(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
        }
    }

    private void addBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        LocalDate publicationDate = LocalDate.parse(request.getParameter("publicationDate"));
        int genreId = Integer.parseInt(request.getParameter("genreId"));
        Book book = new Book(0, title, publicationDate, genreId);
        bookDAO.insertBook(book);
        response.sendRedirect("index.jsp");
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        LocalDate publicationDate = LocalDate.parse(request.getParameter("publicationDate"));
        int genreId = Integer.parseInt(request.getParameter("genreId"));
        Book book = new Book(id, title, publicationDate, genreId);
        bookDAO.updateBook(book);
        response.sendRedirect("viewAllBooks.jsp");
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        bookDAO.deleteBook(id);
        response.sendRedirect("viewAllBooks.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("viewAll".equals(action)) {
            List<Book> books = bookDAO.getAllBooks();
            request.setAttribute("books", books);
            request.getRequestDispatcher("viewAllBooks.jsp").forward(request, response);
        }
    }
}