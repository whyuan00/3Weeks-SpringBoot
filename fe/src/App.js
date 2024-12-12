import Colors from "./constants/colors";
import axiosInstance from "./network/axios";
import React, { useEffect, useState } from "react";
import Main from "./pages/Main";

function App() {
  return (
    <div className="App">
      <Main/>
    </div>
  );
}

export default App;
