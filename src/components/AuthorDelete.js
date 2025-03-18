import React, { useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import authorService from '../services/authorService';

const AuthorDelete = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    deleteAuthor();
  }, [id]);

  const deleteAuthor = async () => {
    try {
        await authorService.deleteAuthor(id);

    } catch (err) {

    }
    navigate('/authors');
  };

  return (
    <div>
      <h2>Deleting Author...</h2>
      <p>Please wait while the author is being deleted.</p>
    </div>
  );
};

export default AuthorDelete;
