import React, { useState, useEffect } from "react";

import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Button,
  Container,
  Typography,
  createTheme,
  ThemeProvider,
  IconButton,
} from "@mui/material";
import baseUrl from "../util";
import { useNavigate } from "react-router-dom";

const DoctorLanding = () => {
  const defaultTheme = createTheme();
  const [users, setusers] = useState([]);
  const navigate = useNavigate();
  useEffect(() => {
    fetch(`${baseUrl}admin/getUsers`)
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        return data;
      })
      .then((data) => setusers(data));
  }, []);

  const addUser = () => {
    navigate("/addPatient");
  };

  return (
    <ThemeProvider theme={defaultTheme}>
      <Container maxWidth="false" sx={{ padding: 10, marginTop: 2 }}>
        <Paper elevation={3} sx={{ padding: 1 }}>
          <Typography variant="h5" align="center" sx={{ padding: 1 }}>
            List of All Users
          </Typography>
          <br></br>
          <Button
            sx={{ boxShadow: 4, bottom: 5 }}
            variant="contained"
            onClick={() => addUser()}
          >
            Add Patient
          </Button>

          <TableContainer component={Paper}>
            <Table>
              <TableHead>
                <TableRow
                  sx={{ border: 2, textDecorationColor: "ActiveCaption" }}
                >
                  <TableCell>Id</TableCell>
                  <TableCell>Name</TableCell>
                  <TableCell>Roles</TableCell>
                  <TableCell>Email</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {users.map((user) => (
                  <TableRow key={user.userId}>
                    <TableCell>{user.userId}</TableCell>
                    <TableCell>{user.name}</TableCell>
                    <TableCell>{user.roles[0].roleName}</TableCell>
                    <TableCell>{user.email}</TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
        </Paper>
      </Container>
    </ThemeProvider>
  );
};

export default DoctorLanding;
