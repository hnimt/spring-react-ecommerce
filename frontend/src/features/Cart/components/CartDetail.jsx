import React, { useState } from 'react'
import { useDispatch } from 'react-redux'
import InputQuantity from './InputQuantity'
import { CHANGE_QUANTITY } from '../../../store/slices/cart'
import { formatTotal } from '../../../services/format/formatNumber'

const CartDetail = (props) => {

  const { cartItem } = props;

  const changeQuantity = (quantity) => {
    props.changeQuantity(cartItem.id, quantity)
  }

  return (
    <>
      {cartItem != undefined &&
        <tr>
          <td className="image" data-title="No">
            <img
              src={cartItem.product.images != undefined ?
                `/${cartItem.product.images[0].fileName}` :
                "https://via.placeholder.com/100x100"
              }
            />
          </td>
          <td className="product-des" data-title="Description">
            <p className="product-name">
              <a>{cartItem.product.name}</a>
            </p>
            <p className="product-des">
              {cartItem.product.description}
            </p>
          </td>
          <td className="price" data-title="Price">
            <span>${cartItem.product.price} </span>
          </td>
          <td className="qty" data-title="Qty">
            {/* Input Order */}
            <InputQuantity
              quantity={cartItem.quantity}
              product={cartItem.product}
              cartItem={cartItem}
              changeQuantity={changeQuantity}
            />
            {/*/ End Input Order */}
          </td>
          <td className="total-amount" data-title="Total">
            <span>${formatTotal(cartItem.total)}</span>
          </td>
          <td
            className="action"
            onClick={() => props.deleteCartItem(cartItem.id)}
          >
            <a>
              <i className="ti-trash remove-icon" />
            </a>
          </td>
        </tr>
      }
    </>
  )
}

export default CartDetail
