import React, { useEffect, useState } from 'react';
import { useParams, useNavigate, Link } from 'react-router-dom';
import authorService from '../services/authorService';

const AuthorForm = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [author, setAuthor] = useState({
    // authorId: 0,
    name: '',
    birthDate: new Date().toISOString().split('T')[0], // По умолчанию сегодняшняя дата
  });
  const isEditMode = !!id;

  useEffect(() => {
    if (isEditMode) loadAuthor();
  }, [id]);

  const loadAuthor = async () => {
    const data = await authorService.getAuthorById(id);
    setAuthor(data);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (isEditMode) {
      await authorService.updateAuthor(id, author);
    } else {
      await authorService.createAuthor(author);
    }
    navigate('/authors');
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setAuthor({ ...author, [name]: value });
  };

  return (
    <div>
      <h2>{isEditMode ? 'Редактировать автора' : 'Добавить автора'}</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Имя:</label>
          <input
            type="text"
            name="name"
            value={author.name}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Дата рождения:</label>
          <input
            type="date"
            name="birthDate"
            value={author.birthDate}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit">{isEditMode ? 'Обновить' : 'Создать'}</button>
      </form>
      <Link to="/authors">Отмена</Link> {/* Теперь Link определен */}
    </div>
  );
};

export default AuthorForm;
