// LoginForm.js
import React from "react";
import { useForm } from "react-hook-form";
import { Button, TextField, Grid, Typography, Container } from "@mui/material";
import { useDispatch } from "react-redux";
import { authActions } from "./store/auth-slice";
import axios from "axios";
import baseUrl from "./util";
const LoginForm = () => {
  const dispatch = useDispatch();
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const onSubmit = (data) => {
    axios({
      method: "post",
      url: baseUrl + `users/login`,
      headers: {},
      data: { email: data.email, password: data.password },
    }).then((response) => {
      console.log(response);
    });

    dispatch(authActions.login(data.email, "DOCTOR"));
  };

  return (
    <Container component="main" maxWidth="xs">
      <Typography variant="h5" align="center">
        Login
      </Typography>
      <form onSubmit={handleSubmit(onSubmit)}>
        <TextField
          {...register("email", {
            required: "Email is required",
            pattern: /^\S+@\S+$/i,
          })}
          label="Email"
          fullWidth
          margin="normal"
          error={!!errors.email}
          helperText={errors.email?.message}
        />
        <TextField
          {...register("password", { required: "Password is required" })}
          label="Password"
          fullWidth
          margin="normal"
          type="password"
          error={!!errors.password}
          helperText={errors.password?.message}
        />
        <Button type="submit" fullWidth variant="contained" color="primary">
          Login
        </Button>
      </form>
    </Container>
  );
};

export default LoginForm;
