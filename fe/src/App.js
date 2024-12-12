import "./App.css";
import Colors from "./constants/colors";
import axiosInstance from "./network/axios";
import React, { useEffect, useState } from "react";

function App() {
  const [todos, setTodos] = useState([]);

  const fontStyle = {
    color: Colors.GRAY_GREEN,
  };

  useEffect(() => {
    axiosInstance
      .get("/todos/")
      .then((res) => {
        setTodos(res.data);
        console.log(res.data);
      })
      .catch((e) => {
        console.error("todo조회 에러: ", e);
      });
  }, []);

  return (
    <div className="App">
      <div>
        <h1> 메인 페이지 </h1>
        <p> api 연결 확인 </p>
        <ul style={fontStyle}>
          {todos?.map((todo) => {
            return <li key={todo.id}> {todo.todo} </li>;
          })}
        </ul>
      </div>
    </div>
  );
}

export default App;
