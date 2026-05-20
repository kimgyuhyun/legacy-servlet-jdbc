import axios from 'axios';

const client = axios.create({
   baseURL: import.meta.env.VITE_API_BASE_URL || '',
   timeout: 5000,
});

export const getUserById = (id) => client.get(`/user/api/${id}`);