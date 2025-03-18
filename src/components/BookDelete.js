import React, { useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import bookService from '../services/bookService';

const BookDelete = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    deleteBook();
  }, [id]);

  const deleteBook = async () => {
    try {
        await bookService.deleteBook(id);
    } catch (err) {
        
    }
    navigate('/books');
  };

  return (
    <div>
      <h2>Удаление книги...</h2>
      <p>Пожалуйста, подождите, пока книга удаляется.</p>
    </div>
  );
};

export default BookDelete;
