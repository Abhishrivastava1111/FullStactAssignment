import { createSlice } from "@reduxjs/toolkit";

const authSlice = createSlice({
  name: "auth",
  initialState: { isLoggedIn: false, userName: "", roles: [], currentRole: "" },
  reducers: {
    login(state, action) {
      state.isLoggedIn = true;
      state.userName = action.payload.email;
      state.roles = [...action.payload.role];
      if (localStorage.getItem("logicCredentials") === null) {
        persistLoginData(state.isLoggedIn, state.userName, state.roles);
      }
    },
    setCurrentRole(state, action) {
      state.currentRole = action.payload.role;
      console.log(state.currentRole);
    },
    logout(state) {
      state.isLoggedIn = false;
      state.userName = "";
      state.roles = [];
      localStorage.clear();
    },
  },
});

function persistLoginData(isLoggedIn, userName, roles) {
  const dataToPersist = {
    isLoggedIn: isLoggedIn,
    userName: userName,
    roles: roles,
  };
  localStorage.setItem("logicCredentials", JSON.stringify(dataToPersist));
}
export const authActions = authSlice.actions;
export default authSlice;
