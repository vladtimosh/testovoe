import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import bookService from '../services/bookService';

const BookDetails = () => {
  const { id } = useParams();
  const [book, setBook] = useState(null);

  useEffect(() => {
    loadBook();
  }, [id]);

  const loadBook = async () => {
    const data = await bookService.getBookById(id);
    setBook(data);
  };

  if (!book) return <div>Загрузка...</div>;

  return (
    <div>
      <h2>Детали книги</h2>
      <p>ID: {book.bookId}</p>
      <p>Название: {book.title}</p>
      <p>Дата публикации: {new Date(book.publishedDate).toLocaleDateString()}</p>
      <p>Жанр: {book.genre.genreName}</p>
      <p>Авторы:</p>
      <ul>
        {book.authors?.map((author) => (
          <li key={author.authorId}>{author.name}</li>
        ))}
      </ul>
      <Link to="/books">Назад к списку</Link>
    </div>
  );
};

export default BookDetails;
