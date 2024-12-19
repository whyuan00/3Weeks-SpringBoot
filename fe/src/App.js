import Colors from "./constants/colors";
import axiosInstance from "./network/axios";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import React, { useEffect, useState } from "react";
import Main from "./pages/Main";
import Minsu from "./pages/Minsu";
import Sanghun from "./pages/Sanghun";
import Yuan from "./pages/Yuan";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/김민수" element={<Minsu />}></Route>
          <Route path="/연상헌" element={<Sanghun />}></Route>
          <Route path="/이유안" element={<Yuan />}></Route>
          <Route path="/" element={<Main />}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
