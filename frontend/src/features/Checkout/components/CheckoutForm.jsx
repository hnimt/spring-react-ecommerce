import React from 'react'

const CheckoutForm = (props) => {

  const { user } = props

  return (
    <>
      {user &&
        <form className="form" onSubmit={e => e.preventDefault()}>
          <div className="row">
            <div className="col-lg-6 col-md-6 col-12">
              <div className="form-group">
                <label>
                  Full Name<span>*</span>
                </label>
                <input
                  type="text"
                  required="required"
                  value={user.name == null ? '' : user.name}
                  onChange={e => props.setUser({...user, name: e.target.value})}
                />
              </div>
            </div>
            <div className="col-lg-6 col-md-6 col-12">
              <div className="form-group">
                <label>
                  Email Address<span>*</span>
                </label>
                <input
                  type="email"
                  required="required"
                  value={user.email == null ? '' : user.email}
                  onChange={e => props.setUser({...user, email: e.target.value})}
                />
              </div>
            </div>
            <div className="col-lg-6 col-md-6 col-12">
              <div className="form-group">
                <label>
                  Phone Number<span>*</span>
                </label>
                <input
                  type="tel"
                  required="required"
                  value={user.tel == null ? '' : user.tel}
                  onChange={e => props.setUser({...user, tel: e.target.value})}
                />
              </div>
            </div>
            <div className="col-lg-6 col-md-6 col-12">
              <div className="form-group">
                <label>
                  Address<span>*</span>
                </label>
                <input
                  type="text"
                  required="required"
                  value={user.address == null ? '' : user.address}
                  onChange={e => props.setUser({...user, address: e.target.value})}
                />
              </div>
            </div>
          </div>
        </form>
      }
    </>
  )
}

export default CheckoutForm
