import React, { useEffect, useState } from 'react';
import { useParams, useNavigate, Link } from 'react-router-dom';
import genreService from '../services/genreService';

const GenreForm = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [genre, setGenre] = useState({
    // genreId: 0,
    genreName: '',
  });
  const isEditMode = !!id;

  useEffect(() => {
    if (isEditMode) loadGenre();
  }, [id]);

  const loadGenre = async () => {
    const data = await genreService.getGenreById(id);
    setGenre(data);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (isEditMode) {
      await genreService.updateGenre(id, genre);
    } else {
      await genreService.createGenre(genre);
    }
    navigate('/genres');
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setGenre({ ...genre, [name]: value });
  };

  return (
    <div>
      <h2>{isEditMode ? 'Редактировать жанр' : 'Добавить жанр'}</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Название:</label>
          <input
            type="text"
            name="genreName"
            value={genre.genreName}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit">{isEditMode ? 'Обновить' : 'Создать'}</button>
      </form>
      <Link to="/genres">Отмена</Link>
    </div>
  );
};

export default GenreForm;
