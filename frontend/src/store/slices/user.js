import { createSlice } from "@reduxjs/toolkit";
import userApi from "../../services/apis/user";

const userSlice = createSlice({
  name: 'user',
  initialState:{
    user: null,
  },
  reducers: {
    SET_USER: (state, action) => {
      state.user = action.payload
      sessionStorage.setItem('user', JSON.stringify(state.user))
    }
  }
})

// Actions
export const {SET_USER} = userSlice.actions;

export const register = (user) => async dispatch => {
  const res = await userApi.register(user);
  dispatch(SET_USER(res.data));
}

export const login = (user) => async dispatch => {
  const res = await userApi.login(user);
  dispatch(SET_USER(res.data));
}

export default userSlice