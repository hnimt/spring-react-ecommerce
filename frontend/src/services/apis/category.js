import axios from "axios"

const categoryApi = {
  getAllCategories : async () => {
    try {
      return await axios.get(`/api/categories`)
    } catch (error) {
      console.log(error);
    }
  }
}

export default categoryApi;