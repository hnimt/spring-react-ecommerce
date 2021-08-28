# Book Ecommerce Application

Ecommerce Website used sale books by entrepreneur

---
**Main Technology**:
1. Server:
  - SpringBoot
  - SpringSecurity
  - JPA, Hibernate
2. Database:
  - MySQL
3. Client:
  - Reactjs
  - Redux, ReduxToolkit

---

**Project Struture**
1. Backend:
.
├── main
│   ├── java
│   │   └── com
│   │       └── minh
│   │           └── Backend
│   │               ├── config
│   │               ├── constant
│   │               ├── controller
│   │               ├── dto
│   │               │   ├── cart
│   │               │   ├── cartitem
│   │               │   ├── category
│   │               │   ├── jwt
│   │               │   ├── order
│   │               │   ├── product
│   │               │   └── user
│   │               ├── entity
│   │               │   ├── enums
│   │               │   └── image
│   │               ├── exception
│   │               ├── filter
│   │               ├── repository
│   │               ├── service
│   │               │   └── impl
│   │               └── utility
│   ├── resources
│   │   └── templates
│   └── upload
│       └── static
│           └── images
│               ├── products
│               │   ├── product1
│               │   ├── product2
│               │   ├── product3
│               │   └── product4
│               └── user
│                   └── user1
└── test
    └── java
        └── com
            └── minh

2. Frontend:
.
├── components
│   └── common
├── features
│   ├── Auth
│   │   ├── components
│   │   └── pages
│   ├── Cart
│   │   └── components
│   ├── Checkout
│   │   └── components
│   ├── Home
│   │   └── components
│   └── Products
│       ├── components
│       └── pages
├── services
│   ├── apis
│   └── format
└── store
    └── slices

---
**Some Images from App**
1. Screen Homepage:
![alt text](screenshots/home.png)
2. Products List By Category:
![alt text](screenshots/products.png)
3. Product:
![alt text](screenshots/product.png)
4. Cart:
![alt text](screenshots/cart.png)
5. Checkout:
![alt text](screenshots/checkout.png)
6. Login:
![alt text](screenshots/login.png)
7. Register:
![alt text](screenshots/register.png)
7. Email:
![alt text](screenshots/email.png)








