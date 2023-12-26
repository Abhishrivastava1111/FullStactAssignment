import React from "react";
import Header from "./components/Header";
import ProtectedRoute from "./components/ProtectedRoute";
import Landing from "./AdminControllers/Landing";

function App() {
  return (
    <div className="App">
      <Header></Header>
      <Landing></Landing>
      {/* <ProtectedRoute /> */}
    </div>
  );
}

export default App;
