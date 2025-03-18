import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import genreService from '../services/genreService';

const GenreDetails = () => {
  const { id } = useParams();
  const [genre, setGenre] = useState(null);

  useEffect(() => {
    loadGenre();
  }, [id]);

  const loadGenre = async () => {
    const data = await genreService.getGenreById(id);
    setGenre(data);
  };

  if (!genre) return <div>Загрузка...</div>;

  return (
    <div>
      <h2>Детали жанра</h2>
      <p>ID: {genre.genreId}</p>
      <p>Название: {genre.genreName}</p>
      <Link to="/genres">Назад к списку</Link>
    </div>
  );
};

export default GenreDetails;
