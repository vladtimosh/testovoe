import axios from 'axios';

const API_URL = 'http://localhost:8080/api/reviews'; // Замените на ваш API URL

const getReviews = async () => {
  const response = await axios.get(API_URL);
  return response.data;
};

const getReviewById = async (id) => {
  const response = await axios.get(`${API_URL}/${id}`);
  return response.data;
};

const createReview = async (review) => {
  const response = await axios.post(API_URL, review);
  return response.data;
};

const updateReview = async (id, review) => {
  const response = await axios.put(`${API_URL}/${id}`, review);
  return response.data;
};

const deleteReview = async (id) => {
  const response = await axios.delete(`${API_URL}/${id}`);
  return response.data;
};

export default {
  getReviews,
  getReviewById,
  createReview,
  updateReview,
  deleteReview,
};
