import React, { useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import genreService from '../services/genreService';

const GenreDelete = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    deleteGenre();
  }, [id]);

  const deleteGenre = async () => {
    try {
        await genreService.deleteGenre(id);
    }catch (err){}
    navigate('/genres');
  };

  return (
    <div>
      <h2>Удаление жанра...</h2>
      <p>Пожалуйста, подождите, пока жанр удаляется.</p>
    </div>
  );
};

export default GenreDelete;
