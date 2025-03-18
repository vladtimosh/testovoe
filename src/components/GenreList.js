import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import genreService from '../services/genreService';

const GenreList = () => {
  const [genres, setGenres] = useState([]);

  useEffect(() => {
    loadGenres();
  }, []);

  const loadGenres = async () => {
    const data = await genreService.getGenres();
    setGenres(data);
  };

  return (
    <div>
      <h2>Жанры</h2>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Название</th>
            <th>Действия</th>
          </tr>
        </thead>
        <tbody>
          {genres.map((genre) => (
            <tr key={genre.genreId}>
              <td>{genre.genreId}</td>
              <td>{genre.genreName}</td>
              <td>
                <Link to={`/genres/${genre.genreId}`}>Просмотр</Link>
                <Link to={`/genres/${genre.genreId}/edit`}>Редактировать</Link>
                <Link to={`/genres/${genre.genreId}/delete`}>Удалить</Link>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <Link to="/genres/create">Добавить новый жанр</Link>
    </div>
  );
};

export default GenreList;
