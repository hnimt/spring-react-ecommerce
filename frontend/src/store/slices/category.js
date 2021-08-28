import { createSlice } from "@reduxjs/toolkit";
import categoryApi from "../../services/apis/category";

const categorySlice = createSlice({
  name: 'category',
  initialState: {
    categories: []
  },
  reducers: {
    SET_CATEGORIES : (state, action) => {
      state.categories = action.payload
    }
  }
})

// Actions:
const {actions} = categorySlice;

export const getAllCategories = () => async dispatch => {
  const res = await categoryApi.getAllCategories();
  dispatch(actions.SET_CATEGORIES(res.data))
}


export default categorySlice;