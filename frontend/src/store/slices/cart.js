import { createSlice } from "@reduxjs/toolkit";
import cartApi from "../../services/apis/cart";

const cartSlice = createSlice({
  name: 'cart',
  initialState: {
    cart: null,
  },
  reducers: {
    SET_CART: (state, action) => {
      state.cart = action.payload
    },
    CHANGE_QUANTITY: (state, action) => {
      let {cartItemId, quantity} = action.payload
      let { cartItems } = state.cart;
      for (let cartItem of cartItems) {
        if (cartItem.id === cartItemId) cartItem.quantity = quantity
      }
    },
    DELETE_CART_ITEM: (state, action) => {
      let {id} = action.payload
      console.log(id);
      let {cartItems} = state.cart;
      let foundIndex = cartItems.findIndex(cartItem => cartItem.id === id);
      cartItems.splice(foundIndex,1)
    },
    DELETE_ALL_CART: (state) => {
      let {cartItems} = state.cart;
      state.cart.total = 0.0;
      cartItems.splice(0, cartItems.length);
    }
  }
})

// Actions
export const { SET_CART, CHANGE_QUANTITY, DELETE_CART_ITEM, DELETE_ALL_CART } = cartSlice.actions;

export const getCart = (id, jwtToken) => async dispatch => {
  const res = await cartApi.getCartById(id, jwtToken);
  dispatch(SET_CART(res.data));
}

export const addToCart = (cartItem, token) => async dispatch => {
  const res = await cartApi.addToCart(cartItem, token);
  dispatch(SET_CART(res.data));
}

export const updateCart = (id, cart, token) => async dispatch => {
  const res = await cartApi.updateAllCartById(id, cart, token);
  dispatch(SET_CART(res.data));
}

export const updateQuantity = (id, req, token) => async dispatch => {
  const res = await cartApi.updateQuantity(id, req, token);
  dispatch(SET_CART(res.data));
}

export const deleteCartItemById = (id, req, token) => async dispatch => {
  const res = await cartApi.deleteCartItemById(id, req, token);
  dispatch(SET_CART(res.data));
}

export default cartSlice;