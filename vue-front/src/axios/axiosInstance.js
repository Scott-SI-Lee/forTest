import axios from "axios";

const AxiosInst = axios.create({
    baseURL : 'http://localhost:8080'   // baseURL 추가
})
// 2. 모든 요청을 intercept 해서 header값 설정(토큰)
// : Axios request일때 이 설정값을 사용한다는 것
AxiosInst.interceptors.request.use(
    (config) => {
        let access_token = localStorage.getItem("token");
        if(access_token){
            config.headers.Authorization = `${access_token}`;
        }
        return config;
    }
)
export default AxiosInst;

