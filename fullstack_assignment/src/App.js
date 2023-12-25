import React from "react";
import Header from "./components/Header";
import ProtectedRoute from "./components/ProtectedRoute";
import Home from "./components/Home";

function App() {
  return (
    <div className="App">
      <Header></Header>
      <ProtectedRoute element={<Home />} />
    </div>
  );
}

export default App;
