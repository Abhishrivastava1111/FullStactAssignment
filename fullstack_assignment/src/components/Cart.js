import React from "react";
import "./Cart.css";
import { useDispatch, useSelector } from "react-redux";
import { CartActions } from "../store/cart-slice";
const Cart = () => {
  const dispatch = useDispatch()
  const showCart =()=>{
    dispatch(CartActions.setShowCart())
  }
  const quantity = useSelector(state=> state.cart.totalQuantity);
  return (
    <div className="cartIcon">
      <h3 onClick={showCart}>Cart: {quantity} Items</h3>
    </div>
  );
};

export default Cart;
