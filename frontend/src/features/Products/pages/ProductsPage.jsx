import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router';
import ProductList from '../components/ProductList';
import productApi from '../../../services/apis/product';
import Pagination from '../../../components/common/Pagination';

const ProductsPage = () => {
  const params = new URLSearchParams(useLocation().search)
  const catId = params.get("catId")
  const searchName = params.get("name")

  const [products, setProducts] = useState([]);
  const [page, setPage] = useState(0)
  const [sort, setSort] = useState("");

  useEffect(() => {
    const fetch = async () => {
      try {
        if (params.has("catId") && sort === "") {
          const res = await productApi.getProductsByCategory(catId, page);
          setProducts(res.data);
        }
        else if (params.has("name")) {
          const res = await productApi.getProductsByName(searchName, page);
          setProducts(res.data);
        }
        else if (params.has("catId") && (sort === "price asc" || sort === "price desc")) {
          const sortType = sort === "price asc" ? "asc" : "desc";
          const res = await productApi.getProductsByCategorySortByPrice(catId, page, sortType);
          setProducts(res.data)
        }
        else if (params.has("catId") && (sort === "name asc" || sort === "name desc")) {
          const sortType = sort === "name asc" ? "asc" : "desc";
          const res = await productApi.getProductsByCategorySortByName(catId, page, sortType);
          setProducts(res.data)
        }
      } catch (error) {
        console.log(error);
      }
    }
    fetch()
  }, [catId, searchName, page, sort])

  const changePage = (page) => {
    setPage(page);
  }

  const deleteProduct = async (id) => {
    await productApi.deleteProductById(id);
    const newProducts = products.filter(product => product.id !== id);
    setProducts(newProducts);
  }

  return (
    <>
      <div className="product-area section">
        <div className="container">
          <div className="row">
            <div className="col-12">
              <div className="section-title">
                <h2>Trending Item</h2>
              </div>
              <div className="d-flex justify-content-end select-product">
                <select
                  defaultValue={sort}
                  onChange={(e) => setSort(e.target.value)}
                >
                  <option value="price asc">Sort low to high price</option>
                  <option value="price desc">Sort high to low price</option>
                  <option value="name asc">Sort name A-Z</option>
                  <option value="name desc">Sort name Z-A</option>
                </select>
              </div>
            </div>
          </div>
          <ProductList
            products={products}
            deleteProduct={deleteProduct}
          />
          <div className="pagination d-flex justify-content-end">
            <Pagination
              page={page}
              changePage={changePage}
            />
          </div>
        </div>
      </div>

    </>
  )
}

export default ProductsPage
