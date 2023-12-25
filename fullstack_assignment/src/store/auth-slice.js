import { createSlice } from "@reduxjs/toolkit";

const authSlice = createSlice({
  name: "auth",
  initialState: { isLoggedIn: false, userName: "", roles: [] },
  reducers: {
    login(state, action) {
      state.isLoggedIn = true;
      state.userName = action.payload.userName;
      state.roles = state.roles.push(action.payload.role);
    },
    logout(state) {
      state.state.isLoggedIn = false;
      state.userName = "";
      state.roles = [];
    },
  },
});
export const authActions = authSlice.actions;

export default authSlice;
