
import Vuex from "vuex"
import axios from "axios"

export default new Vuex.Store({
    state: {
        userInfo: null,
        isLogin: false,
        token: null,
    },
    mutations: {
        loginSuccess(state) {
            state.isLogin = true
        },
        loginUserDataSave(state, payload) {
            state.userInfo = payload
            console.log("state : " +     state.userInfo.userId)
        },
        logout(state) {
            alert("로그아웃");
            state.isLogin = false;
            state.userInfo = null;
            localStorage.removeItem("token");
        }
    },
    getters: {
        isAuthenticated: function (state) {

            return state.isLogin
        },

        getUserData: function (state) {
            return state.userInfo
        },
        getToken : function (){
            return localStorage.getItem("token");
        }
    },
    actions: {
        getAccountInfo({commit}) {
            let token = localStorage.getItem("token");
            let userId = localStorage.getItem("userId");
            let userType=localStorage.getItem("userType");
            let searchJson = {
                 "userId": userId
                ,"userType":userType
                };
            console.log("토큰확인 : "+token);
            console.log("json확인"+JSON.stringify(searchJson));
            axios
                .post("/getUser",searchJson, {
                    headers: {
                        'Authorization': token ,
                    }
                })
                .then((response) => {
                    console.log("hi:"+JSON.stringify(response.data));
                    commit("loginUserDataSave",response.data);
                })
                .catch((error) => {
                    console.log(error)
                })
        }
        ,
        goLogout({ commit }) {
                  commit('logout');
        }
    },
    modules: {}
})