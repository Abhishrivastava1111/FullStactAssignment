import React from "react";
import "./Product.css";
import { useDispatch, useSelector } from "react-redux";
import { CartActions } from "../store/cart-slice";


const Product = ({ name, id, imgURL, price }) => {
  const dispatch = useDispatch()
  

  const AddToCart=()=>{
    dispatch(CartActions.addToCart({name, id, price}))
  }
  return (
    <div className="card">
      <img src={imgURL} alt={name} />
      <h2>{name}</h2>
      <p>$ {price}</p>
      <button onClick={AddToCart}>Add to cart</button>
    </div>
  );
};

export default Product;
