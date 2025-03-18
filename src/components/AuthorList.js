import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import authorService from '../services/authorService';

const AuthorList = () => {
  const [authors, setAuthors] = useState([]);

  useEffect(() => {
    loadAuthors();
  }, []);

  const loadAuthors = async () => {
    const data = await authorService.getAuthors();
    setAuthors(data);
  };

  return (
    <div>
      <h2>Authors</h2>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Birth Date</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {authors.map((author) => (
            <tr key={author.authorId}>
              <td>{author.authorId}</td>
              <td>{author.name}</td>
              <td>{new Date(author.birthDate).toLocaleDateString()}</td>
              <td>
                <Link to={`/authors/${author.authorId}`}>View</Link>
                <Link to={`/authors/${author.authorId}/edit`}>Edit</Link>
                <Link to={`/authors/${author.authorId}/delete`}>Delete</Link>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <Link to="/authors/create">Add New Author</Link>
    </div>
  );
};

export default AuthorList;
