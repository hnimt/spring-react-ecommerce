import React from 'react'

const Pagination = (props) => {
  const {page} = props

  const goToPrevPage = () => {
    page > 0 && props.changePage(page-1)
  }

  const goToNextPage = () => {
    props.changePage(page+1)
  }

  return (
    <>
      <button onClick={goToPrevPage}>
        <i className="fas fa-angle-double-left"></i>
      </button>
      <button onClick={goToNextPage}>
        <i className="fas fa-angle-double-right"></i>
      </button>
    </>
  )
}

export default Pagination
