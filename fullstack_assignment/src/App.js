import React from "react";
import "./App.css";
import Layout from "./components/Layout";
import { useSelector } from "react-redux";
import LoginForm from "./Auth";

function App() {
  const isLoggedIn = useSelector((state) => state.auth.isLoggedIn);
  const items = useSelector((state) => state.cart.itemList);
  console.log(items);
  console.log(isLoggedIn);
  return (
    <div className="App">
      {isLoggedIn ? <Layout /> : <LoginForm />}
      {/* <Layout /> */}
    </div>
  );
}

export default App;
