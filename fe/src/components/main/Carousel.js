import React, { useState, useEffect, useCallback } from "react";
import {useNavigate } from "react-router-dom";

export default function Carousel() {
  const [currentIndex, setCurrentIndex] = useState(1);
  // 중복 애니메이션 실행 방지
  const [isTransitioning, setIsTransitioning] = useState(false);

  const navigate = useNavigate();
  function moveToMyPage(name) {
    navigate(`/${name}`);
    console.log(name)
  }

  const slides = [
    { id: 1, content: "김민수" },
    { id: 2, content: "연상헌" },
    { id: 3, content: "이유안" },
  ];

  // 무한캐러셀 애니메이션을 위한 확장 슬라이드
  const extendedSlides = [slides[slides.length - 1], ...slides, slides[0]];

  // 기본 이동
  const handlePrev = useCallback(() => {
    if (isTransitioning) return;
    setIsTransitioning(true);
    setCurrentIndex((prevIndex) => prevIndex - 1);
  },[isTransitioning]);
  const handleNext = useCallback(() => {
    if (isTransitioning) return;
    setIsTransitioning(true);
    setCurrentIndex((prevIndex) => prevIndex + 1);
  },[isTransitioning]);

  // 자동 이동
  useEffect(() => {
    const interval = setInterval(() => {
      handleNext();
    }, 3000);
    return () => clearInterval(interval);
  }, [handleNext]);

  // 눈속임 용에서는 transition애니메이션 정지
  useEffect(() => {
    if (currentIndex === 0) {
      setTimeout(() => {
        setCurrentIndex(extendedSlides.length - 2);
      }, 500);
    } else if (currentIndex === extendedSlides.length - 1) {
      setTimeout(() => {
        setCurrentIndex(1);
      }, 500);
    }
    setTimeout(() => {
      setIsTransitioning(false);
    }, 500);
  });

  return (
    <div className="relative overflow-hidden">
      <div
        className={`flex ${
          isTransitioning && "transition-transform duration-500"
        }`}
        style={{ transform: `translateX(-${currentIndex * 100}%)` }}
      >
        {extendedSlides.map((slide) => (
          <div className="flex justify-center items-center flex-shrink-0 w-full">
            <div
              className="pointer-events-auto z-10 flex flex-col p-10 place-items-center bg-pinkRed w-40 h-40 rounded-xl"
              onClick={() => moveToMyPage(slide.content)}
            >
              <p className="font-extrabold text-white text-xl">Todos</p>
              <p className="font-extrabold text-white text-2xl">Create </p>
              <p className="mt-2 w-20 text-center bg-grayGreen text-black text-sm rounded">
                {slide.content}
              </p>
            </div>
          </div>
        ))}
      </div>
      <div className="absolute inset-x-0 bottom-8 flex justify-between p-10">
        <button onClick={handlePrev} disabled={isTransitioning}>
          이전
        </button>
        <button onClick={handleNext} disabled={isTransitioning}>
          다음
        </button>
      </div>
    </div>
  );
}
