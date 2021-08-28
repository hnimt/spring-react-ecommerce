import React from 'react'
import { Link } from 'react-router-dom'

const ProductItem = (props) => {

  const { product } = props

  return (
    <>
      {product &&
        <div className="single-product">
          <div className="product-img">
            <Link to={`/products/${props.product.id}`}>
              <img
                className="default-img"
                src={product.images !== undefined ?
                  `/${product.images[0].fileName}` :
                  "https://via.placeholder.com/115x140"
                }
                alt="#"
              />
              <img
                className="hover-img"
                src={product.images[0] !== undefined ?
                  `/${product.images[0].fileName}` :
                  "https://via.placeholder.com/115x140"
                }
                alt="#"
              />
            </Link>
            <div className="button-head">
              <div className="product-action">
                <a
                  data-toggle="modal"
                  data-target="#exampleModal"
                  title="Quick View"
                  href="#"
                >
                  <i className=" ti-eye" />
                  <span>Quick Shop</span>
                </a>
                <a title="Wishlist" href="#">
                  <i className=" ti-heart " />
                  <span>Add to Wishlist</span>
                </a>
                <a title="Compare"
                  onClick={() => props.deleteProduct(product.id)}
                >
                  <i className="fas fa-times" />
                  <span>Delete product</span>
                </a>
              </div>
              <div className="product-action-2">
                <a title="Add to cart" href="#">
                  Add to cart
                </a>
              </div>
            </div>
          </div>
          <div className="product-content">
            <h3>
              <Link to={`/products/${props.product.id}`}>
                {props.product.name}
              </Link>
            </h3>
            <div className="product-price">
              <span>${props.product.price}</span>
            </div>
          </div>
        </div>
      }
    </>
  )
}

export default ProductItem
