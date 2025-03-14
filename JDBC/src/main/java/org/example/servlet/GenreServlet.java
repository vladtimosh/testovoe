package org.example.servlet;

import org.example.dao.GenreDAO;
import org.example.model.Genre;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/genres")
public class GenreServlet extends HttpServlet {
    private GenreDAO genreDAO;

    @Override
    public void init() {
        genreDAO = new GenreDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "add":
                addGenre(request, response);
                break;
            case "update":
                updateGenre(request, response);
                break;
            case "delete":
                deleteGenre(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
        }
    }

    private void addGenre(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String genreName = request.getParameter("genreName");
        Genre genre = new Genre(0, genreName);
        genreDAO.insertGenre(genre);
        response.sendRedirect("viewAllGenres.jsp");
    }

    private void updateGenre(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String genreName = request.getParameter("genreName");
        Genre genre = new Genre(id, genreName);
        genreDAO.updateGenre(genre);
        response.sendRedirect("viewAllGenres.jsp");
    }

    private void deleteGenre(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        genreDAO.deleteGenre(id);
        response.sendRedirect("viewAllGenres.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("viewAll".equals(action)) {
            List<Genre> genres = genreDAO.getAllGenres();
            request.setAttribute("genres", genres);
            request.getRequestDispatcher("viewAllGenres.jsp").forward(request, response);
        }
    }
}