import React from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { useHistory } from 'react-router'
import Service from '../../components/common/Service'
import { formatTotal } from '../../services/format/formatNumber'
import { deleteCartItemById, updateQuantity } from '../../store/slices/cart'
import CartDetail from './components/CartDetail'

const CartPage = () => {
  const cart = useSelector(state => state.cartStore.cart);
  const dispatch = useDispatch();
  const history = useHistory()
  const user = JSON.parse(sessionStorage.getItem('user'))

  const changeQuantity = async (cartItemId, quantity) => {
    const req = {
      id: cartItemId,
      quantity
    }
    try {
      await dispatch(updateQuantity(user.id, req, user.token))
    } catch (error) {
      console.log(error);
    }
  }

  const deleteCartItem = async (cartItemId) => {
    const req = {
      id: cartItemId
    }
    try {
      await dispatch(deleteCartItemById(user.id, req, user.token))
    } catch (error) {
      console.log(error);
    }
  }

  return (

    <div>
      {cart != null &&
        <div className="shopping-cart section">
          <div className="container">
            <div className="row">
              <div className="col-12">
                {/* Shopping Summery */}
                <table className="table shopping-summery">
                  <thead>
                    <tr className="main-hading">
                      <th>PRODUCT</th>
                      <th>NAME</th>
                      <th className="text-center">UNIT PRICE</th>
                      <th className="text-center">QUANTITY</th>
                      <th className="text-center">TOTAL</th>
                      <th className="text-center">
                        <i className="ti-trash remove-icon" />
                      </th>
                    </tr>
                  </thead>
                  <tbody>
                    {cart.cartItems.map(cartItem => (
                      <CartDetail
                        key={cartItem.id}
                        cartItem={cartItem}
                        changeQuantity={changeQuantity}
                        deleteCartItem={deleteCartItem}
                      />
                    ))}
                  </tbody>
                </table>
                {/*/ End Shopping Summery */}
              </div>
            </div>
            <div className="row">
              <div className="col-12">
                {/* Total Amount */}
                <div className="total-amount">
                  <div className="row">
                    <div className="col-lg-8 col-md-5 col-12">
                      <div className="left">
                        <div className="coupon">
                          <form action="#" target="_blank">
                            <input name="Coupon" placeholder="Enter Your Coupon" />
                            <button className="btn">Apply</button>
                          </form>
                        </div>
                      </div>
                    </div>
                    <div className="col-lg-4 col-md-7 col-12">
                      <div className="right">
                        <ul>
                          <li>
                            Tax<span>{cart.tax * 100}%</span>
                          </li>
                          <li>
                            Discount<span>${cart.discount}</span>
                          </li>
                          <li className="last">
                            You Pay<span>${formatTotal(cart.total)}</span>
                          </li>
                        </ul>
                        <div className="button5">
                          <a href="#" className="btn" onClick={() => history.push(`/checkout`)}>
                            Checkout
                          </a>
                          <a href="#" className="btn">
                            Continue shopping
                          </a>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                {/*/ End Total Amount */}
              </div>
            </div>
          </div>
        </div>
      }
      <Service />
    </div>
  )
}

export default CartPage
