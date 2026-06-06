import axios from 'axios';

const client = axios.create({
   baseURL: import.meta.env.VITE_API_BASE_URL || '',
   timeout: 5000,
});

export const getUserById = (id) => client.get(`/user/api/${id}`);

// export const getUserById = async (id) => {
//    const res = await client.get(`/user/api/${id}`);  // await는 여기
//    return res;
//  };


export const getUserList = () => client.get('/user/api/list');

export const getDslUserList = () => client.get('/user/api/dsl/list');

export const getUserWithDetailById = (id) => client.get(`/user/api/detail/${id}`);

export const getUserWithDetailList = () => client.get('/user/api/detail/list');

export const createUser = (user) => client.post('/user/api/create', user);

export const createUserWithDetail = (user) => client.post('/user/api/join/create/user/Detail', user);

export const updateUser = (id, user) => client.put(`/user/api/update/put/${id}`, user);

export const updatePatchUser = (id, user) => client.patch(`/user/api/update/patch/${id}`, user);

export const deleteUser = (id) => client.delete(`/user/api/delete/${id}`);