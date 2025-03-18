import React, { useEffect, useState } from 'react';
import { useParams, useNavigate, Link } from 'react-router-dom';
import bookService from '../services/bookService';

const BookForm = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [book, setBook] = useState({
    bookId: 0,
    title: '',
    publishedDate: new Date().toISOString().split('T')[0], // По умолчанию сегодняшняя дата
    genre: { genreId: 0, genreName: '' },
    authors: [],
  });
  const isEditMode = !!id;

  useEffect(() => {
    if (isEditMode) loadBook();
  }, [id]);

  const loadBook = async () => {
    const data = await bookService.getBookById(id);
    setBook(data);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (isEditMode) {
      await bookService.updateBook(id, book);
    } else {
      await bookService.createBook(book);
    }
    navigate('/books');
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setBook({ ...book, [name]: value });
  };

  return (
    <div>
      <h2>{isEditMode ? 'Редактировать книгу' : 'Добавить книгу'}</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Название:</label>
          <input
            type="text"
            name="title"
            value={book.title}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Дата публикации:</label>
          <input
            type="date"
            name="publishedDate"
            value={book.publishedDate}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Жанр:</label>
          <input
            type="text"
            name="genre.genreName"
            value={book.genre.genreName}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Авторы:</label>
          <input
            type="text"
            name="authors"
            value={book.authors?.map((author) => author.name).join(', ')}
            onChange={(e) => {
              const authors = e.target.value.split(',').map((name) => ({ name: name.trim() }));
              setBook({ ...book, authors });
            }}
            required
          />
        </div>
        <button type="submit">{isEditMode ? 'Обновить' : 'Создать'}</button>
      </form>
      <Link to="/books">Отмена</Link>
    </div>
  );
};

export default BookForm;
