import React, { useEffect, useState } from "react";
import Carousel from "../components/main/Carousel";
import { todos } from "../network/todoService";

export default function Main() {

  const [isShownAll, setisShownAll] = useState(false);
  function onClick (){
    setisShownAll(prev => !prev);
  }
    const [todoList, setTodoList] = useState([]);

    useEffect(() => {
      const loadTodos = async () => {
        try {
          const data = await todos();
          setTodoList(data);
        } catch (e) {
          throw e;
        }
      };
      loadTodos();
    }, []);

    console.log(todoList);
    
  // const todoList = [
  //   {
  //     id: 1,
  //     todo: "알고리즘 풀기",
  //   },
  //   {
  //     id: 2,
  //     todo: "운동하기",
  //   },
  // ];

  // tailwind 컨벤션
  // 컴포넌트 역할: header,
  // 레이아웃: flex, grid 등
  // 간격: margin, padding
  // 타이포그래피: text, font
  // 색상: bg, text-color
  return (
    <>
      {/* 헤더 컴포넌트 */}
      <div class="flex justify-between px-10 py-2 shadow-md">
        <div class=""> 메인 페이지 </div>
        <div>
          <a href="#" class="mx-2">
            로그인
          </a>
          <a href="#" class="mx-2">
            회원가입
          </a>
        </div>
      </div>

      {/* 컨텐츠 컴포넌트 */}
      <div class="main-p-10 main-limit-width">
        {/* 캐러셀 */}
        <Carousel />
        {/* 게시판 */}
        <div class="px-20">
          <p class="p-2 my-2 bg-white rounded-xl"> Todo 게시판 </p>
          {/* 게시글 */}
          <div class="text-right mt-3 mr-3">
            {isShownAll
              ? [<button onClick={onClick}>오늘의 TODO만 보기 </button>]
              : [<button onClick={onClick}> 전체보기</button>]}
          </div>
          <div class="p-2 bg-white">
            <p>여기 게시글 내용 들어감</p>
            <p>여기 게시글 내용 들어감</p>
            <p>여기 게시글 내용 들어감</p>
            <p>여기 게시글 내용 들어감</p>
            <p>여기 게시글 내용 들어감</p>
            <p>여기 게시글 내용 들어감</p>
          </div>
        </div>
      </div>
    </>
  );
}
