import React, { useState } from 'react'

const InputQuantity = (props) => {
  const {quantity, product} = props

  const onDecrease = () => {
    quantity > 1 && props.changeQuantity(quantity - 1)
  }

  const onIncrease = () => {
    if (quantity < product.stock)
     props.changeQuantity(quantity + 1)
  }

  const onChange = (e) => {
    props.changeQuantity(e.target.value)
  }

  return (
    <div className="my-quantity-form">
      <div
        className="btn-quantity"
        onClick={onDecrease}
      >
        <i className="ti-minus" />
      </div>
      <input type="number" className="input-quantity"
        value={quantity}
        onChange={(e) => onChange(e)}
      />
      <div
        className="btn-quantity"
        onClick={onIncrease}
      >
        <i className="ti-plus" />
      </div>
    </div>
  )
}

export default InputQuantity
