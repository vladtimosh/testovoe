import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import bookService from '../services/bookService';

const BookList = () => {
  const [books, setBooks] = useState([]);

  useEffect(() => {
    loadBooks();
  }, []);

  const loadBooks = async () => {
    const data = await bookService.getBooks();
    setBooks(data);
  };

  return (
    <div>
      <h2>Книги</h2>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Название</th>
            <th>Дата публикации</th>
            <th>Жанр</th>
            <th>Авторы</th>
            <th>Действия</th>
          </tr>
        </thead>
        <tbody>
          {books.map((book) => (
            <tr key={book.bookId}>
              <td>{book.bookId}</td>
              <td>{book.title}</td>
              <td>{new Date(book.publishedDate).toLocaleDateString()}</td>
              <td>{book.genre.genreName}</td>
              <td>
                {book.authors?.map((author) => (
                  <span key={author.authorId}>{author.name} </span>
                ))}
              </td> 
              <td>
                <Link to={`/books/${book.bookId}`}>Просмотр</Link>
                <Link to={`/books/${book.bookId}/edit`}>Редактировать</Link>
                <Link to={`/books/${book.bookId}/delete`}>Удалить</Link>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <Link to="/books/create">Добавить новую книгу</Link>
    </div>
  );
};

export default BookList;
