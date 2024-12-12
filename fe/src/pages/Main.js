import React, { useEffect, useState } from "react";
import { todos } from "../network/todoService";

export default function Main() {
  //   const [todoList, setTodoList] = useState([]);

  //   useEffect(() => {
  //     const loadTodos = async () => {
  //       try {
  //         const data = await todos();
  //         setTodoList(data);
  //       } catch (e) {
  //         throw e;
  //       }
  //     };
  //     loadTodos();
  //   });

  const todoList = [
    {
      id: 1,
      todo: "알고리즘 풀기",
    },
    {
      id: 2,
      todo: "운동하기",
    },
  ];

  return (
    <div>
      <div className="text-center text-4xl font-extrabold">
        {" "}
        This is the MainPage !{" "}
      </div>
    </div>
  );
}
