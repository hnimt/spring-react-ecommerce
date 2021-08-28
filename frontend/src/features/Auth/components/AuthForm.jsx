import React from 'react'
import { Link } from 'react-router-dom'
import { login } from '../../../store/slices/user'

const AuthForm = (props) => {
  const {user, success} = props

  const onSubmit = (e) => {
    e.preventDefault()
    props.handleSubmit()
  }

  return (
    <>
      <form className="form" onSubmit={e => onSubmit(e)}>
        <div className="row">
          <div className="col-12">
            <div className="form-group">
              <label>
                Username<span>*</span>
              </label>
              <input
                type="text"
                name="name"
                placeholder
                required="required"
                value={user.username}
                onChange={e => props.setUser({...user, username: e.target.value})}
              />
            </div>
          </div>
          <div className="col-12">
            <div className="form-group">
              <label>
                Password<span>*</span>
              </label>
              <input
                type="password"
                name="name"
                placeholder
                required="required"
                value={user.password}
                onChange={e => props.setUser({...user, password: e.target.value})}
              />
            </div>
          </div>
          <div className="col-12">
            <div className="form-group create-account">
              <button type="submit" className="btn" style={{marginRight: "20px"}}>Submit</button>
              <Link style={{color:"blue"}} to={`/${props.logOrRes}`}>{props.logOrRes}?</Link>
            </div>
          </div>
        </div>
      </form>

    </>
  )
}

export default AuthForm
