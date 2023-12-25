import { createSlice } from "@reduxjs/toolkit";

const authSlice = createSlice({
  name: "auth",
  initialState: { isLoggedIn: false, userName: "", roles: [] },
  reducers: {
    login(state, action) {
      debugger;
      state.isLoggedIn = true;
      state.userName = action.payload.email;
      state.roles = [...action.payload.role];
      if (localStorage.getItem("logicCredentials") === null) {
        persistLoginData(state.isLoggedIn, state.userName, state.roles);
      }
    },
    logout(state) {
      state.state.isLoggedIn = false;
      state.userName = "";
      state.roles = [];
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
