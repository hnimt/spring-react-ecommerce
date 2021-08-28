import axios from "axios";

const authorize = (token) => ({headers: { "Authorization": `Bearer ${token}` }})

const productApi = {
  getAllProducts: async () => {
    try {
      return await axios.get(`/api/products`)
    } catch (error) {
      console.log(error);
    }
  },
  addProduct: async (product, token) => {
    console.log(product);
    console.log(token);
    try {
      return await axios.post(
        `/api/products`,
        product,
        authorize(token),
      )
    } catch (error) {
      console.log(error);
    }
  },
  getProductById: async (id) => {
    try {
      return await axios.get(`/api/products/${id}`)
    } catch (error) {
      console.log(error);
    }
  },
  getProductsByCategory: async (catId, page) => {
    try {
      return await axios.get(`/api/products/search?catId=${catId}&page=${page}`)
    } catch (error) {
      console.log(error);
    }
  },
  getProductsByCategorySortByPrice: async (catId, page, sort) => {
    try {
      return await axios.get(`/api/products/search?catId=${catId}&page=${page}&sort_price=${sort}`)
    } catch (error) {
      console.log(error);
    }
  },
  getProductsByCategorySortByName: async (catId, page, sort) => {
    try {
      return await axios.get(`/api/products/search?catId=${catId}&page=${page}&sort_name=${sort}`)
    } catch (error) {
      console.log(error);
    }
  },
  getProductsByName: async (searchName, page) => {
    try {
      return await axios.get(`/api/products/search?name=${searchName}&page=${page}`)
    } catch (e) {
      console.log(e);
    }
  },
  deleteProductById: async (id) => {
    try {
      await axios.delete(`/api/products/${id}`)
    } catch (error) {
      console.log(error);
    }
  }
}

export default productApi;