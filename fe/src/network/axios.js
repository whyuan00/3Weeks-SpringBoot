import axios from "axios";

// .env 파일 만들어서 넣기 API 기본 URL
// REACT_APP_API_URL=http://localhost:8080/api

// 기본 URL 설정
const axiosInstance = axios.create({
  baseURL: process.env.REACT_APP_API_URL, // Spring Boot API의 기본 URL
});

export default axiosInstance;
