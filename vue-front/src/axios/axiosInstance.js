import axios from "axios";

const AxiosInst = axios.create({
  baseURL: "http://localhost:8080", // baseURL 추가
});
// 2. 모든 요청을 intercept 해서 header값 설정(토큰)
// : Axios request일때 이 설정값을 사용한다는 것
AxiosInst.interceptors.request.use((config) => {
  let access_token = localStorage.getItem("token");
  if (access_token) {
    config.headers.Authorization = `${access_token}`;
  }
  return config;
});

//3.api 요청 결과에서 403 에러인 경우 토큰 체크를 다시하고 에러인 경우 로그인 화면으로 ㄱㄱ
AxiosInst.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response && error.response.status === 403) {
      //  return Auth.refreshToken() // token 재발행 및 반환
      //.then((token) => {
      //          console.log(token);  //테스트용
      //        originalRequest.headers["Authorization"] = token;
      //  return AxiosInst.request(error.config); // error.config(origin API 정보)를 다시 요청
      //})
      //.catch((error) => {
      //                    console.log(error);
      //                  window.location.href = "/login";
      //});
      //}
      window.location.href = "/loginForm";
      return Promise.reject(error);
    }
  }
);

export default AxiosInst;
