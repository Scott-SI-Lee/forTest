<template>
  <div class="signUp">
    <h1>{{ msg }}</h1>
    <p>회원가입창</p>
    <v-card width="300" title="유저정보 등록">
      <v-form ref="" v-on:submit.prevent="">
        <v-text-field v-model="userForm.userId" label="ID"></v-text-field>
        <v-text-field v-model="userForm.userPw" label="패스워드"></v-text-field>
        <v-text-field v-model="userForm.userNm" label="유저명"></v-text-field>
        <v-select
          v-model="userForm.userType"
          label="사용자유형"
          :items="['ADMIN', 'USER']"
        >
        </v-select>
      </v-form>
      <v-btn @click="regUser">유저정보 등록</v-btn>
    </v-card>
  </div>
</template>

<script>
import AxiosInst from "@/axios/axiosInstance";
export default {
  name: "SignUp",
  data: function () {
    return {
      userForm: { userId: "", userNm: "", userPw: "", userType: "" },
    };
  },
  props: {
    msg: String,
  },
  methods: {
    regUser: function () {
      let formJson = {
        userId: this.userForm.userId,
        userPw: this.userForm.userPw,
        userNm: this.userForm.userNm,
        userType: this.userForm.userType,
      };

      AxiosInst.post("/v1/regUser", formJson)
        .then((result) => {
          if(result.data.userId!=null) {
            alert("유저등록 완료"+process.env["VUE_APP_TEST"]);  //환경변수 .env 테스트
            this.$router.push("/");
          }
        })
        .catch((error) => {
          alert(error);
        })
        .finally(() => {

        });
    },
  },
};
</script>