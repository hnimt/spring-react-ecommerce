import React, { useState } from 'react'
import { useSelector } from 'react-redux'
import { useHistory } from 'react-router';
import productApi from '../../../services/apis/product';
import ProductForm from '../components/ProductForm'

const user = JSON.parse(sessionStorage.getItem('user'))

const ProductAdd = () => {
  const history = useHistory();

  const categories = useSelector(state => state.categoryStore.categories);

  const [product, setProduct] = useState({
    name: "",
    description: "",
    price: 0,
    stock: 0,
    images: [],
    categoryId: 1,
  })

  const addFile = (file) => {
    const tmpImages = product.images;
    tmpImages.push(file);
    setProduct({ ...product, images: tmpImages })
    console.log("addFile");
  }

  const handleSubmit = async () => {
    const formData = new FormData()
    formData.append("name", product.name);
    formData.append("description", product.description);
    formData.append("price", product.price);
    formData.append("stock", product.stock);
    formData.append("categoryId", product.categoryId);
    product.images.forEach(image => {
      formData.append("images", image);
    });
    try {
      await productApi.addProduct(formData, user.token);
      console.log("Success");
      history.push("/")
    } catch (error) {
      console.log(error);
    }

  }

  return (
    <section className="shop checkout section">
      <div className="container">
        <div className="row">
          <div>
            <div className="checkout-form">
              <h2>Product Form</h2>
              <p>Please register in order to checkout more quickly</p>
              {/* Form */}
              <ProductForm
                categories={categories}
                product={product}
                addFile={addFile}
                onSubmit={handleSubmit}
                setProduct={setProduct}
              />
              {/*/ End Form */}
            </div>
          </div>
        </div>
      </div>
    </section>

  )
}

export default ProductAdd
