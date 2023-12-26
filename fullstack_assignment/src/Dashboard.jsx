import React from "react";
import { Box, Container, Grid, Typography } from "@mui/material";
import { useSelector } from "react-redux";

const Dashboard = () => {
  const role = useSelector((state) => state.auth.roles);

  return (
    <Container component="main" maxWidth={false}>
      <Box sx={{ padding: 2, marginTop: 0 }}>
        <Typography variant="h2" align="center">
          {role[0]} DASHBOARD
        </Typography>
        <Grid container spacing={5}></Grid>
      </Box>
    </Container>
  );
};
export default Dashboard;
