import axios from 'axios';

const API_URL = 'http://localhost:8080/api/members'; // Замените на ваш API URL

const getMembers = async () => {
  const response = await axios.get(API_URL);
  return response.data;
};

const getMemberById = async (id) => {
  const response = await axios.get(`${API_URL}/${id}`);
  return response.data;
};

const createMember = async (member) => {
  const response = await axios.post(API_URL, member);
  return response.data;
};

const updateMember = async (id, member) => {
  const response = await axios.put(`${API_URL}/${id}`, member);
  return response.data;
};

const deleteMember = async (id) => {
  const response = await axios.delete(`${API_URL}/${id}`);
  return response.data;
};

export default {
  getMembers,
  getMemberById,
  createMember,
  updateMember,
  deleteMember,
};
