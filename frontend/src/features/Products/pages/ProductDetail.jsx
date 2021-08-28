import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import { useDispatch } from 'react-redux';
import { useHistory, useParams } from 'react-router';
import productApi from '../../../services/apis/product';
import { addToCart } from '../../../store/slices/cart';
import InputQuantity from '../../Cart/components/InputQuantity';

const ProductDetail = () => {
  let { id } = useParams();
  const history = useHistory()
  const dispatch = useDispatch();
  const user = JSON.parse(sessionStorage.getItem('user'))
  const cart = useSelector(state => state.cartStore.cart)

  const [product, setProduct] = useState({});
  const [quantity, setQuantity] = useState(0);
  const [existed, setExisted] = useState(false);

  useEffect(() => {
    const fetch = async () => {
      try {
        const res = await productApi.getProductById(id);
        setProduct(res.data);
      } catch (error) {
        console.log(error);
      }
    }
    fetch()
  }, [id])

  const changeQuantity = (quantity) => {
    setQuantity(quantity);
  }

  const onSubmit = async (e) => {
    e.preventDefault(e)
    const cartItem = {
      productId: product.id,
      cartId: cart.id,
      quantity
    }
    try {
      await dispatch(addToCart(cartItem, user.token));
      history.push(`/cart/${cart.id}`)
    } catch (error) {
      setExisted(true)
      console.log(error);
    }
  }

  return (
    <section className="shop-home-list section">
      
      {product !== undefined &&
        <div className="container">
          <div className="row">
            <div className="single-list">
              <div className="row">
                <div className="col-lg-6 col-md-6 col-12">
                  <div className="list-image overlay">
                    <img
                      src={product.images !== undefined ?
                        `/${product.images[0].fileName}` :
                        "https://via.placeholder.com/115x140"
                      }
                      alt="#"
                    />
                  </div>
                </div>
                <div className="col-lg-6 col-md-6 col-12 no-padding">
                  <div className="content">
                    <h5 className="title">
                      <a href="#">{product.name}</a>
                    </h5>
                    <p className="price with-discount">
                      ${product.price}
                      <span className="sale-price">$80</span>
                    </p>
                    <div className="description">
                      <h6>Description:</h6>
                      <p>
                        {product.description}
                      </p>
                    </div>
                    <p className="mb-3"><b>In Stock: {product.stock}</b></p>
                    <p>Quantity</p>
                    {/* Btn */}
                    <form onSubmit={onSubmit}>
                      <div className="mb-5">
                        <InputQuantity
                          quantity={quantity}
                          product={product}
                          changeQuantity={changeQuantity}
                        />
                      </div>
                      <button
                        type="submit"
                        className="button btn add-cart"
                      >
                        Add to cart
                      </button>
                      {existed && <p style={{color:"red"}}>"Product is exited in your cart"</p>}
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      }
    </section>
  )
}

export default ProductDetail
