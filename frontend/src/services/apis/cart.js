import axios from "axios";

const authorize = (token) => ({ headers: { "Authorization": `Bearer ${token}` } })

const cartApi = {
  addToCart: async (cartItem, token) => {
    console.log(cartItem);
    console.log(token);
    return await axios.post(
      `/api/carts/add-to-cart`,
      cartItem,
      authorize(token),
    );
  },
  getCartById: async (id, token) => {
    console.log(id, token);
    return await axios.get(
      `/api/carts/${id}`,
      authorize(token),
    )

  },
  updateAllCartById: async (id, cart, token) => {
    console.log(cart);
    return await axios.put(
      `/api/carts/${id}`,
      cart,
      authorize(token),
    )

  },
  updateQuantity: async (id, req, token) => {
    return await axios.put(
      `/api/carts/${id}/update-cart-item`,
      req,
      authorize(token),
    )

  },
  deleteCartItemById: async (id, req, token) => {
    console.log(id);
    console.log(req.id);
    console.log(token);
    return await axios.put(
      `/api/carts/${id}/update-cart-item?rmCartItemId=${req.id}`,
      {},
      authorize(token),
    )
  }
}

export default cartApi