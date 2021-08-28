import React, { useEffect, useState } from 'react'
import categoryApi from '../../services/apis/category'
import productApi from '../../services/apis/product'
import ProductList from '../Products/components/ProductList'
import HeroSlider from './components/HeroSlider'

const HomePage = () => {

  const [categories, setCategories] = useState([]);
  const [products, setProducts] = useState([])
  const [page, setPage] = useState(0)

  useEffect(() => {
    const fetch = async () => {
      try {
        const categoriesRes = await categoryApi.getAllCategories()
        setCategories(categoriesRes.data)
        const productsRes = await productApi.getAllProducts()
        setProducts(productsRes.data);
      } catch (error) {
        console.log(error);
      }
    }
    fetch()
  }, [])

  const handleNavSetCategory = async (catId) => {
    try {
      const res = await productApi.getProductsByCategory(catId, page);
      setProducts(res.data);
    } catch (error) {
      console.log(error);
    }

  }

  const deleteProduct = async (id) => {
    try {
      await productApi.deleteProductById(id);
      const newProducts = products.filter(product => product.id !== id);
      setProducts(newProducts);
    } catch (error) {
      console.log(error);
    }
  }

  return (
    <>
      <HeroSlider />
      <div className="product-area section">
        <div className="container">
          <div className="row">
            <div className="col-12">
              <div className="section-title">
                <h2>Trending Item</h2>
              </div>
            </div>
          </div>
          <ProductList
            categories={categories}
            navSetCategory={handleNavSetCategory}
            products={products}
            deleteProduct={deleteProduct}
          />
        </div>
      </div>
    </>
  )
}

export default HomePage
