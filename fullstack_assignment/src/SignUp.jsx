import React from "react";
import { useForm } from "react-hook-form";
import axios from "axios";
import {
  Button,
  TextField,
  Grid,
  Typography,
  Container,
  Box,
  MenuItem,
  Select,
  InputLabel,
} from "@mui/material";
import { Link, useNavigate } from "react-router-dom"; // Import for login link
import baseUrl from "./util";

const SignUpForm = () => {
  const navigate = useNavigate();
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const onSubmit = (data) => {
    var name = `${data.firstName}${" "}${data.lastName}`;
    axios({
      method: "post",
      url: baseUrl + `users/addUser`,
      headers: {},
      data: {
        name: name,
        email: data.email,
        password: data.password,
        accountType: data.accountType,
      },
    }).then(() => navigate("/"));
    console.log(data);
  };

  return (
    <Container component="main" maxWidth="xs">
      <Box
        sx={{ boxShadow: 4, borderRadius: 2, p: 2, padding: 3, marginTop: 20 }}
      >
        <Typography variant="h5" align="center">
          Sign Up
        </Typography>
        <form onSubmit={handleSubmit(onSubmit)}>
          <Grid container spacing={2}>
            <Grid item xs={12} sm={6}>
              <TextField
                {...register("firstName", {
                  required: "First Name is required",
                })}
                label="First Name"
                fullWidth
                margin="normal"
                error={!!errors.firstName}
                helperText={errors.firstName?.message}
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                {...register("lastName", { required: "Last Name is required" })}
                label="Last Name"
                fullWidth
                margin="normal"
                error={!!errors.lastName}
                helperText={errors.lastName?.message}
              />
            </Grid>
          </Grid>
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

          <Grid item xs={10} sm={12} sx={{ padding: 1 }}>
            <InputLabel id="select">Account Type</InputLabel>
            <Select
              sx={{ height: 1 }}
              id="select"
              fullWidth
              margin="normal"
              {...register("accountType", {
                required: "Account type is required",
              })}
            >
              <MenuItem value="DOCTOR">Doctor</MenuItem>
              <MenuItem value="COMPOUNDER">Compounder</MenuItem>
              <MenuItem value="PATIENT">Patient</MenuItem>
            </Select>
          </Grid>

          <Button type="submit" fullWidth variant="contained" color="primary">
            Sign Up
          </Button>
          <Typography variant="body2" align="center" mt={2}>
            Already have an account? <Link to="/">Log in</Link>{" "}
          </Typography>
        </form>
      </Box>
    </Container>
  );
};

export default SignUpForm;
