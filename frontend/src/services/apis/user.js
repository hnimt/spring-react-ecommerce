import axios from "axios";

const userApi = {
  register: async (user) => {
    try {
      return await axios.post(`/auth/register`, user);
    } catch (err) {
      console.log(err);
    }
  },
  login: async (user) => {
    try {
      return await axios.post(`/auth/login`, user);
    } catch (err) {
      console.log(err);
    }
  }
}

export default userApi