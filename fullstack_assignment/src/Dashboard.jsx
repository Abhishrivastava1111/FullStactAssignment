import React, { useState } from "react";
import { Box, Container, Typography, Select, MenuItem } from "@mui/material";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { authActions } from "./store/auth-slice";

const Dashboard = ({ element }) => {
  const role = useSelector((state) => state.auth.roles);
  const currentRole = useSelector((store) => store.auth.currentRole);
  const [selectedRole, setSelectedRole] = useState(currentRole);
  const dispatch = useDispatch();

  const handleRoleChange = (event) => {
    const selectedValue = event.target.value;
    setSelectedRole(selectedValue);
    dispatch(authActions.setCurrentRole({ role: selectedValue }));
  };

  return (
    <Container component="main" maxWidth={false}>
      <Box sx={{ padding: 2, marginTop: 0 }}>
        <Typography variant="h2" align="center">
          {currentRole} DASHBOARD
        </Typography>

        <Select value={selectedRole} onChange={handleRoleChange}>
          {role.map((r, index) => (
            <MenuItem key={index} value={r}>
              {r}
            </MenuItem>
          ))}
        </Select>

        {element}
      </Box>
    </Container>
  );
};

export default Dashboard;
