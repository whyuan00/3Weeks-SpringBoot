import axiosInstance from "./axios";

export const todos = async()=>{
        return await axiosInstance.get("/todos/").data
}

