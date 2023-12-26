import React from "react";
import { useDispatch, useSelector } from "react-redux";
import LoginForm from "./Auth";
import { Route, Routes } from "react-router-dom";
import Home from "./Home";
import { authActions } from "../store/auth-slice";
import SignUpForm from "../SignUp";
import Dashboard from "../Dashboard";
const ProtectedRoute = (props) => {
  const dispatch = useDispatch();
  const isLoggedIn = useSelector((state) => state.auth.isLoggedIn);

  if (!isLoggedIn && localStorage.getItem("logicCredentials")) {
    console.log("Without redux but with local storage");

    var data = JSON.parse(localStorage.getItem("logicCredentials"));
    var arrOfRoles = data.roles;

    dispatch(authActions.login({ email: data.userName, role: arrOfRoles }));
  }
  const email = useSelector((state) => state.auth.userName);
  const roles = useSelector((state) => state.auth.roles);

  //   console.log(isLoggedIn);
  //   console.log(email);
  const routes = (
    <>
      <Route path="/" element={<Dashboard />} />
      <Route path="/" element={<Home />} />
      <Route path="/about" element={<Home />} />
      <Route path="/products" element={<Home />} />
      <Route path="/contact" element={<Home />} />
    </>
  );
  const routesForUnAuthUser = (
    <>
      <Route path="/" element={<LoginForm />} />
      <Route path="/signup" element={<SignUpForm />} />
    </>
  );

  const routesForAdmin = (
    <>
      <Route path="/" element={<Dashboard />} />
      <Route path="/signup" element={<SignUpForm />} />
    </>
  );

  const findRoute = () => {
    if (!isLoggedIn) {
      return routesForUnAuthUser;
    }
    for (let r of roles) {
      switch (r) {
        case "DOCTOR":
          return routes;

        case "ADMIN":
          return routesForAdmin;
        default:
          console.log("******&&&&&&&&&&&&");
          break;
      }
    }
  };

  return (
    <div>
      {isLoggedIn ? (
        <Routes> {findRoute()} </Routes>
      ) : (
        <Routes>{findRoute()}</Routes>
      )}
    </div>
  );
};
export default ProtectedRoute;
