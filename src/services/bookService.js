import axios from 'axios';

const API_URL = 'http://localhost:8080/api/books'; // Замените на ваш API URL

const getBooks = async () => {
  const response = await axios.get(API_URL);
  return response.data;
};

const getBookById = async (id) => {
  const response = await axios.get(`${API_URL}/${id}`);
  return response.data;
};

const createBook = async (book) => {
  const response = await axios.post(API_URL, book);
  return response.data;
};

const updateBook = async (id, book) => {
  const response = await axios.put(`${API_URL}/${id}`, book);
  return response.data;
};

const deleteBook = async (id) => {
  const response = await axios.delete(`${API_URL}/${id}`);
  return response.data;
};

export default {
  getBooks,
  getBookById,
  createBook,
  updateBook,
  deleteBook,
};
