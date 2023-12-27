import React, { useState } from "react";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";

// Replace with your actual authentication function
const handleLogin = (role) => {
  // Perform authentication logic with the selected role
  // Redirect to the appropriate page or dashboard upon success
};

function LoginOptions({ roles }) {
  const [selectedRole, setSelectedRole] = useState(null);

  const handleRoleClick = (role) => {
    setSelectedRole(role);
    handleLogin(role);
  };

  return (
    <Box
      sx={{ display: "flex", flexDirection: "column", alignItems: "center" }}
    >
      <Typography variant="h5" sx={{ mb: 2 }}>
        Choose Your Role:
      </Typography>
      {roles.map((role) => (
        <Button
          key={role}
          variant="contained"
          onClick={() => handleRoleClick(role)}
          sx={{ mb: 1 }}
        >
          {role}
        </Button>
      ))}
    </Box>
  );
}

export default LoginOptions;
