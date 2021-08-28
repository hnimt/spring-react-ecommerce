import React, { useEffect } from 'react'
import { useHistory, useLocation } from 'react-router'
import NavCategory from '../../../components/common/NavCategory'
import productApi from '../../../services/apis/product'
import ProductItem from './ProductItem'

const ProductList = (props) => {
  const history = useHistory();
  const location = useLocation()

  return (
    <>
      <div className="row">
        <div className="col-12">
          <div className="product-info">
            <div className="nav-main">
              {location.pathname !== "/" ? '' :
                < NavCategory
                  categories={props.categories}
                  navSetCategory={props.navSetCategory}
                />
              }
            </div>
            <div className="tab-content" id="myTabContent">
              {/* Start Single Tab */}
              <div className="tab-pane fade show active" id="man" role="tabpanel">
                <div className="tab-single">
                  <div className="row">
                    {props.products && props.products.map(product => (
                      <div className="col-xl-3 col-lg-4 col-md-4 col-12"
                        key={product.id}>
                        <ProductItem 
                        product={product} 
                        deleteProduct={props.deleteProduct}
                        />
                      </div>
                    ))}
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  )
}

export default ProductList
