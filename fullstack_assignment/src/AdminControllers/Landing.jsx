import React, { useState, useEffect } from "react";
import DeleteIcon from "@mui/icons-material/Delete";
import EditIcon from "@mui/icons-material/Edit";
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

const Landing = () => {
  const defaultTheme = createTheme();
  const [users, setusers] = useState([]);

  useEffect(() => {
    fetch(`${baseUrl}admin/getUsers`)
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        return data;
      })
      .then((data) => setusers(data));
  }, []);

  const handleEdit = (userId) => {};

  const handleDelete = (userId) => {
    // Handle delete action
  };
  const addUser = () => {};

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
            Add User
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
                  <TableCell align="right">Actions</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {users.map((user) => (
                  <TableRow key={user.userId}>
                    <TableCell>{user.userId}</TableCell>
                    <TableCell>{user.name}</TableCell>
                    <TableCell>{user.roles[0].roleName}</TableCell>
                    <TableCell>{user.email}</TableCell>
                    <TableCell align="right">
                      <IconButton
                        aria-label="Edit"
                        onClick={() => handleEdit(user.userId)}
                      >
                        <DeleteIcon />
                      </IconButton>
                      <IconButton
                        aria-label="Delete"
                        onClick={() => handleDelete(user.userId)}
                      >
                        <EditIcon />
                      </IconButton>
                    </TableCell>
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

export default Landing;
