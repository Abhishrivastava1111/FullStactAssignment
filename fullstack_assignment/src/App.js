import React from "react";
import Header from "./components/Header";
import ProtectedRoute from "./components/ProtectedRoute";

function App() {
  return (
    <div className="App">
      <Header></Header>
      <ProtectedRoute />
    </div>
  );
}

export default App;
