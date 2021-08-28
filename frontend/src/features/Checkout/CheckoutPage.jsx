import React, { useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import Service from '../../components/common/Service'
import orderApi from '../../services/apis/order'
import { formatTotal } from '../../services/format/formatNumber'
import { DELETE_ALL_CART } from '../../store/slices/cart'
import { SET_USER } from '../../store/slices/user'
import CheckoutForm from './components/CheckoutForm'

const CheckoutPage = () => {
  const dispatch = useDispatch()

  const user = JSON.parse(sessionStorage.getItem('user'))
  const cart = useSelector(state => state.cartStore.cart);

  const [curUser, setCurUser] = useState(user)
  const [shipping, setShipping] = useState(10.0)
  const [total, setTotal] = useState(shipping + cart.total)

  const onSubmit = async (e) => {
    e.preventDefault()
    const order = {
      userId : curUser.id,
      name: curUser.name,
      email: curUser.email,
      tel: curUser.tel,
      address: curUser.address,
      subTotal: cart.total,
      shipping,
      total
    }
    try {
      orderApi.createOrder(order, user.token)
      dispatch(SET_USER(curUser))
      dispatch(DELETE_ALL_CART())
      console.log("Success");
    } catch (error) {
      console.log(error);
    }
  }

  return (
    <>
      {(user != null && cart.cartItems.length != 0) &&
        <section className="shop checkout section">
          <div className="container">
            <div className="row">
              <div className="col-lg-8 col-12">
                <div className="checkout-form">
                  <h2>Make Your Checkout Here</h2>
                  <p>Please register in order to checkout more quickly</p>
                  {/* Form */}
                  <CheckoutForm 
                    user={curUser}
                    setUser={setCurUser}
                  />
                  {/*/ End Form */}
                </div>
              </div>
              <div className="col-lg-4 col-12">
                <div className="order-details">
                  {/* Order Widget */}
                  <div className="single-widget">
                    <h2>CART TOTALS</h2>
                    <div className="content">
                      <ul>
                        <li>
                          Sub Total<span>${formatTotal(cart.total)}</span>
                        </li>
                        <li>
                          (+) Shipping<span>$10.00</span>
                        </li>
                        <li className="last">
                          Total<span>${formatTotal(cart.total + 10.00)}</span>
                        </li>
                      </ul>
                    </div>
                  </div>
                  <div className="single-widget payement"></div>
                  {/*/ End Payment Method Widget */}
                  {/* Button Widget */}
                  <div className="single-widget get-button">
                    <div className="content">
                      <div className="button">
                        <a className="btn" onClick={onSubmit}>
                          proceed to checkout
                        </a>
                      </div>
                    </div>
                  </div>
                  {/*/ End Button Widget */}
                </div>
              </div>
            </div>
          </div>
        </section>
      }
      <Service />
    </>
  )
}

export default CheckoutPage
