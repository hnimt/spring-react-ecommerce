import React from 'react'

const NavCategory = (props) => {
  const { categories } = props

  return (
    <>
      {/* Tab Nav */}
      <ul className="nav nav-tabs" id="myTab" role="tablist">
        <li className="nav-item">
          {categories && categories.map(category => (
            <a
              className="nav-link"
              data-toggle="tab"
              href="#man"
              role="tab"
              key={category.id}
              onClick={() => props.navSetCategory(category.id)}
            >
              {category.name}
            </a>
          ))}
        </li>
      </ul>
      {/*/ End Tab Nav */}
    </>
  )
}

export default NavCategory
