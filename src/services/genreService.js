import axios from 'axios';

const API_URL = 'http://localhost:8080/api/genres'; // Замените на ваш API URL

const getGenres = async () => {
  const response = await axios.get(API_URL);
  return response.data;
};

const getGenreById = async (id) => {
  const response = await axios.get(`${API_URL}/${id}`);
  return response.data;
};

const createGenre = async (genre) => {
  const response = await axios.post(API_URL, genre);
  return response.data;
};

const updateGenre = async (id, genre) => {
  const response = await axios.put(`${API_URL}/${id}`, genre);
  return response.data;
};

const deleteGenre = async (id) => {
  const response = await axios.delete(`${API_URL}/${id}`);
  return response.data;
};

export default {
  getGenres,
  getGenreById,
  createGenre,
  updateGenre,
  deleteGenre,
};
