import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { useHistory } from 'react-router'
import { getCart } from '../../../store/slices/cart'
import { register } from '../../../store/slices/user'
import AuthForm from '../components/AuthForm'

const Register = () => {
  const dispatch = useDispatch()
  const history = useHistory()
  const userRedux = useSelector(state => state.userStore.user)

  const [user, setUser] = useState({
    username: "",
    password: ""
  })

  useEffect(() => {
    const fetch = async () => {
      try {
        if (userRedux != null && userRedux.role != "ADMIN") {
          await dispatch(getCart(userRedux.id, userRedux.token))
          history.push(`/`)
        }
      } catch (error) {
        console.log(error);
      }
    }
    fetch()
  }, [userRedux])

  const handleSubmit = async () => {
    try {
      const userReq = {...user, role: "USER"}
      await dispatch(register(userReq))
    } catch (error) {
      console.log(error);
    }
  }

  return (
    <>
      <section className="shop checkout section">
        <div className="container">
          <div className="row">
            <div className="col-12">
              <div className="checkout-form">
                <h1>Register</h1>
                <br />
                {/* Form */}
                <AuthForm
                  user={user}
                  logOrRes={"login"}
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

export default Register
