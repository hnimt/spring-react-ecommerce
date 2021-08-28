import { configureStore } from "@reduxjs/toolkit"
import cartSlice from "./slices/cart";
import categorySlice from "./slices/category";
import userSlice from "./slices/user";

const rootReducer = {
  categoryStore: categorySlice.reducer,
  cartStore: cartSlice.reducer,
  userStore: userSlice.reducer
}

const store = configureStore({
  reducer: rootReducer
})

export default store;