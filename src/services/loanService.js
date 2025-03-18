import axios from 'axios';

const API_URL = 'http://localhost:8080/api/loans'; // Замените на ваш API URL

const getLoans = async () => {
  const response = await axios.get(API_URL);
  return response.data;
};

const getLoanById = async (id) => {
  const response = await axios.get(`${API_URL}/${id}`);
  return response.data;
};

const createLoan = async (loan) => {
  const response = await axios.post(API_URL, loan);
  return response.data;
};

const updateLoan = async (id, loan) => {
  const response = await axios.put(`${API_URL}/${id}`, loan);
  return response.data;
};

const deleteLoan = async (id) => {
  const response = await axios.delete(`${API_URL}/${id}`);
  return response.data;
};

export default {
  getLoans,
  getLoanById,
  createLoan,
  updateLoan,
  deleteLoan,
};
