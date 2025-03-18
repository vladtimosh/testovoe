import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import authorService from '../services/authorService';

const AuthorDetails = () => {
  const { id } = useParams();
  const [author, setAuthor] = useState(null);

  useEffect(() => {
    loadAuthor();
  }, [id]);

  const loadAuthor = async () => {
    const data = await authorService.getAuthorById(id);
    setAuthor(data);
  };

  if (!author) return <div>Loading...</div>;

  return (
    <div>
      <h2>Author Details</h2>
      <p>ID: {author.authorId}</p>
      <p>Name: {author.name}</p>
      <p>Birth Date: {new Date(author.birthDate).toLocaleDateString()}</p>
      <Link to="/authors">Back to List</Link>
    </div>
  );
};

export default AuthorDetails;
