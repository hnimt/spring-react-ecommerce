import axios from "axios";

const authorize = (token) => ({ headers: { "Authorization": `Bearer ${token}` } })

const orderApi = {
  createOrder: async(order, token) => {
    console.log(order)
    await axios.post(
      `/api/orders`,
      order,
      authorize(token),
    );
  }
}

export default orderApi