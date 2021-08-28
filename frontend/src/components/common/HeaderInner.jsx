import React from 'react'
import { Link } from 'react-router-dom'

const HeaderInner = (props) => {

  const { categories } = props;

  return (
    <div className="header-inner">
      <div className="container">
        <div className="cat-nav-head">
          <div className="row">
            <div className="col-lg-9 col-12">
              <div className="menu-area">
                {/* Main Menu */}
                <nav className="navbar navbar-expand-lg">
                  <div className="navbar-collapse">
                    <div className="nav-inner">
                      <ul className="nav main-menu menu navbar-nav">
                        <li className="active">
                          <Link to="/">Home</Link>
                        </li>
                        {categories && categories.map(category => (
                          <li key={category.id}>
                            <Link to={`/products?catId=${category.id}`}>{category.name}</Link>
                          </li>
                        ))}
                      </ul>
                    </div>
                  </div>
                </nav>
                {/*/ End Main Menu */}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  )
}

export default HeaderInner
