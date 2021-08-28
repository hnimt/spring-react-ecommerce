import { withRouter } from "react-router";
import {
  Route, Switch
} from "react-router-dom";
import './app.css';
import Footer from "./components/common/Footer";
import Header from "./components/common/Header";
import Login from "./features/Auth/pages/Login";
import Register from "./features/Auth/pages/Register";
import CartPage from "./features/Cart/CartPage";
import CheckoutPage from "./features/Checkout/CheckoutPage";
import HomePage from "./features/Home/HomePage";
import ProductAdd from "./features/Products/pages/ProductAdd";
import ProductDetail from "./features/Products/pages/ProductDetail";
import ProductsPage from "./features/Products/pages/ProductsPage";

function App({ location }) {
  return (
    <>
      {(location.pathname !== '/login' || location.pathname !== '/register') && <Header />}
      <Switch>
        <Route exact path="/login" component={Login} />
        <Route exact path="/register" component={Register} />
        <Route exact path="/" component={HomePage} />
        <Route exact path="/products" component={ProductsPage} />
        <Route exact path="/products/add" component={ProductAdd} />
        <Route exact path="/products/:id" component={ProductDetail} />
        <Route exact path="/cart/:id" component={CartPage} />
        <Route exact path="/checkout" component={CheckoutPage} />
      </Switch>
      {(location.pathname !== '/login' || location.pathname !== '/register') && <Footer />}
    </>
  );
}

export default withRouter(App);
