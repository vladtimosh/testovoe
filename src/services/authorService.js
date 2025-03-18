import axios from 'axios';

const API_URL = 'http://localhost:8080/api/authors'; // Replace with your backend API URL

const getAuthors = async () => {
  const response = await axios.get(API_URL);
  return response.data;
};

const getAuthorById = async (id) => {
  const response = await axios.get(`${API_URL}/${id}`);
  return response.data;
};

const createAuthor = async (author) => {
  const response = await axios.post(API_URL, author);
  return response.data;
};

const updateAuthor = async (id, author) => {
  const response = await axios.put(`${API_URL}/${id}`, author);
  return response.data;
};

const deleteAuthor = async (id) => {
  const response = await axios.delete(`${API_URL}/${id}`);
  return response.data;
};

export default {
  getAuthors,
  getAuthorById,
  createAuthor,
  updateAuthor,
  deleteAuthor,
};
