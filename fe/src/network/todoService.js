import axiosInstance from "./axios";

export const todos = async()=>{
        const response = await axiosInstance.get("/todos/")
        return response.data
}

