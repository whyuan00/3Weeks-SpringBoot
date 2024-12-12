const {
  DARK_BLUE,
  SKY_BLUE,
  GRAY,
  GRAY_GREEN,
  BEIGE,
  PINK_RED,
} = require("./src/constants/colors"); // colors.js에서 BEIGE를 가져옵니다.

/** @type {import('tailwindcss').Config} */
export const content = ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"];
export const theme = {
  extend: {
    colors: {
      darkBlue: DARK_BLUE,
      skyBlue: SKY_BLUE,
      gray: GRAY,
      grayGreen: GRAY_GREEN,
      beige: BEIGE,
      pinkRed: PINK_RED,
    },
  },
};
export const plugins = [];
