import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { useHistory } from 'react-router'
import { Link } from 'react-router-dom'
import { getCart, SET_CART } from '../../store/slices/cart'
import { getAllCategories } from '../../store/slices/category'
import { SET_USER } from '../../store/slices/user'
import HeaderInner from './HeaderInner'

const Header = () => {
  const history = useHistory();
  const dispatch = useDispatch();
  const cart = useSelector(state => state.cartStore.cart);
  const categories = useSelector(state => state.categoryStore.categories);
  const user = JSON.parse(sessionStorage.getItem('user'))

  const [search, setSearch] = useState("")

  useEffect(() => {
    const fetchCategories = async () => {
      try {
        await dispatch(getAllCategories());
      } catch (error) {
        console.log(error);
      }
    }
    const fetchCart = async () => {
      try {
        if (user != null && user.role == 'USER' && cart == null) {
          await dispatch(getCart(user.id, user.token))
        }
      } catch (error) {
        console.log(error);
      }
    }
    fetchCategories()
    fetchCart()
  }, [])

  const searchName = (e) => {
    e.preventDefault()
    history.push(`/products?name=${search}`)
    setSearch("")
  }

  const displayAddProduct = () => {
    if (user != null) {
      if (user.role === 'ADMIN')
        return <li>
          <i class="fas fa-calendar-plus" /><Link to="/products/add">Add Product</Link>
        </li>
    } else {
      return '';
    }
  }

  const loginLogout = () => {
    if (user != null) {
      return <Link onClick={logout}>Logout</Link>
    } else {
      return <Link to="/login">Login</Link>;
    }
  }

  const logout = () => {
    dispatch(SET_USER(null))
    dispatch(SET_CART(null))
    history.push("/login")
  }

  return (
    <>
      <header className="header shop">
        {/* Topbar */}
        <div className="topbar">
          <div className="container">
            <div className="row">
              <div className="col-lg-5 col-md-12 col-12">
                {/* Top Left */}
                <div className="top-left">
                  <ul className="list-main">
                    <li>
                      <i className="ti-headphone-alt" /> +060 (800) 801-582
                    </li>
                    <li>
                      <i className="ti-email" /> email@gmail.com
                    </li>
                  </ul>
                </div>
                {/*/ End Top Left */}
              </div>
              <div className="col-lg-7 col-md-12 col-12">
                {/* Top Right */}
                <div className="right-content">
                  <ul className="list-main">
                    {displayAddProduct()}
                    <li>
                      <i className="ti-user" /> <a href="#">My account</a>
                    </li>
                    <li>
                      <i className="ti-power-off" />
                      {loginLogout()}
                    </li>
                  </ul>
                </div>
                {/* End Top Right */}
              </div>
            </div>
          </div>
        </div>
        {/* End Topbar */}
        <div className="middle-inner">
          <div className="container">
            <div className="row">
              <div className="col-lg-2 col-md-2 col-12">
                {/* Logo */}
                <div className="logo">
                  <Link to="/">
                    <img src="images/logo.png" alt="logo" />
                  </Link>
                </div>
                {/*/ End Logo */}
              </div>
              {/* Search Form */}
              <div className="col-lg-8 col-md-7 col-12">
                <div className="search-bar-top">
                  <div className="search-bar">
                    <form
                      onSubmit={searchName}
                      style={{ width: "90%" }}
                    >
                      <input
                        name="search"
                        placeholder="Search Products Here....."
                        type="search"
                        style={{ width: "100%" }}
                        value={search}
                        onChange={(e) => setSearch(e.target.value)}
                      />
                      <button className="btnn" type="submit">
                        <i className="ti-search" />
                      </button>
                    </form>
                  </div>
                </div>
              </div>
              {/* End Search */}
              <div className="col-lg-2 col-md-3 col-12">
                <div className="right-bar">
                  {/* Cart Icon */}
                  <div className="sinlge-bar">
                    <a href="#" className="single-icon">
                      <i className="fa fa-heart-o" aria-hidden="true" />
                    </a>
                  </div>
                  <div className="sinlge-bar">
                    <a href="#" className="single-icon">
                      <i className="fa fa-user-circle-o" aria-hidden="true" />
                    </a>
                  </div>
                  <div className="sinlge-bar shopping">
                    <Link to="/cart/1" className="single-icon">
                      <i className="ti-bag" /> {cart != null &&
                        <span className="total-count">{cart.cartItems.length}</span>
                      }
                    </Link>
                  </div>
                  {/* Cart Icon */}
                </div>
              </div>
            </div>
          </div>
        </div>
        {/* Header Inner */}
        <HeaderInner categories={categories} />
        {/*/ End Header Inner */}
      </header>
    </>
  )
}

export default Header
