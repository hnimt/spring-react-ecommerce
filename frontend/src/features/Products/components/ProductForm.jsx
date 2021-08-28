import React, { useState } from 'react'

const ProductForm = (props) => {

  const { categories, product } = props

  const addFile = (e) => {
    props.addFile(e.target.files[0])
  }

  const onSubmit = (e) => {
    e.preventDefault()
    props.onSubmit()
  }

  return (
    <form
      className="form"
      onSubmit={onSubmit}
    >
      <div className="row">
        <div className="col-12">
          <div className="form-group">
            <label>
              Product Name<span>*</span>
            </label>
            <input
              type="text"
              value={product.name}
              onChange={e => props.setProduct({ ...product, name: e.target.value })}
              required="required"
            />
          </div>
        </div>
        <div className="col-12">
          <div className="form-group">
            <label>
              Product Description<span>*</span>
            </label>
            <input
              type="text"
              required="required"
              value={product.description}
              onChange={e => props.setProduct({ ...product, description: e.target.value })}
            />
          </div>
        </div>
        <div className="col-12">
          <div className="form-group">
            <label>
              Product Price<span>*</span>
            </label>
            <input
              type="number"
              required="required"
              value={product.price}
              onChange={e => props.setProduct({ ...product, price: e.target.value })}
            />
          </div>
        </div>
        <div className="col-12">
          <div className="form-group">
            <label>
              Product Stock<span>*</span>
            </label>
            <input
              type="number"
              required="required"
              value={product.stock}
              onChange={e => props.setProduct({ ...product, stock: e.target.value })}
            />
          </div>
        </div>
        <div className="col-12">
          <div className="form-group">
            <label>
              Category<span>*</span>
            </label>
            <select
              className="nice-select"
              value={product.categoryId}
              onChange={e => props.setProduct({ ...product, categoryId: e.target.value })}
            >
              {categories && categories.map(category => (
                <option value={category.id} key={category.id}>
                  {category.name}
                </option>
              ))}

            </select>
          </div>
        </div>
        <div className="col-12">
          <div className="form-group">
            <label>
              Image<span>*</span>
            </label>
            <input
              type="file"
              multiple
              onChange={e => addFile(e)}
              required="required"
              className="input_file btn"
            />

          </div>
        </div>
        <div className="col-12">
          <div className="form-group">
            <button type="submit" className="btn">Save</button>
          </div>
        </div>
      </div>
    </form>

  )
}

export default ProductForm
