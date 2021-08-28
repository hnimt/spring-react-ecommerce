import React, { useEffect, useState } from 'react'
import { useSelector } from 'react-redux'
import { useDispatch } from 'react-redux'
import { useHistory } from 'react-router'
import { getCart } from '../../../store/slices/cart'
import { login } from '../../../store/slices/user'
import AuthForm from '../components/AuthForm'

const Login = () => {

  const dispatch = useDispatch()
  const history = useHistory()
  const userRedux = useSelector(state => state.userStore.user)

  const [user, setUser] = useState({
    username: "",
    password: ""
  })
  const [success, setSuccess] = useState(true)

  useEffect(() => {
    const fetch = async () => {
      try {
        if (userRedux.role != "ADMIN") {
          await dispatch(getCart(userRedux.id, userRedux.token))
        }
        history.push(`/`)
      } catch (error) {
        console.log(error);
      }
    }
    fetch()
  }, [userRedux])

  const handleSubmit = async () => {
    try {
      await dispatch(login(user));
    } catch (error) {
      setSuccess(false)
    }
  }

  return (
    <>
      <section className="shop checkout section">
        <div className="container">
          <div className="row">
            <div className="col-12">
              <div className="checkout-form">
                <h1>Login</h1>
                {success ? '' : "Wrong username or password"}
                <br />
                {/* Form */}
                <AuthForm
                  user={user}
                  logOrRes={"register"}
                  success={success}
                  setUser={setUser}
                  handleSubmit={handleSubmit}
                />
                {/*/ End Form */}
              </div>
            </div>
          </div>
        </div>
      </section>
    </>
  )
}

export default Login
