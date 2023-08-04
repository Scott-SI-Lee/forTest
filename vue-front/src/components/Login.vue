<template>
  <div id="loginForm">
    <h1>{{ msg }}</h1>
    <p>로그인 화면입니다.!!</p>
    <v-form ref="loginForm" v-on:submit.prevent="goLogin">
      <v-text-field v-model="userId" label="userId" />
      <v-text-field v-model="userPw" label="userPw" />
    </v-form>
    <v-btn @click="goLogin">로그인</v-btn>
    <v-btn @click="goSignUp">회원가입</v-btn>
    <p>{{ data }}</p>
  </div>
</template>

<script>
import AxiosInst from "@/axios/axiosInstance";

export default {
  name: "LoginComp",
  props: {
    msg: String,
  },
  data() {
    return {
      data: "",
      drawer: "",
      userId: "scott",
      userPw: "tiger",
    };
  },

  methods: {
    async goLogin() {
      if (!this.userId) {
        alert("ID required.");
        return;
      }
      if (!this.userPw) {
        alert("PW required.");
        return;
      }

      const formJson = JSON.parse("{}");

      formJson.userId = this.userId;
      formJson.userPw = this.userPw;
      try {
       // console.log("gigi" + JSON.stringify(formJson));
        let result = await AxiosInst.post(
          "/loginProceed",
          formJson
        );

        this.data = result.data;
        console.log(result.data.token);
        console.log(result.data.userId);
        console.log(result.data.userType);

        localStorage.setItem("token", "Bearer " + result.data.token);
        localStorage.setItem("userId", result.data.userId);
        localStorage.setItem("userType", result.data.userType);
        this.$store.commit("loginSuccess", result.data);
        this.$store.dispatch("getAccountInfo");
        alert("Finish!" + process.env.VUE_APP_TEST);
      } catch (error) {
        alert(error);
      } finally {

        this.$router.push("/");
      }

      // .then((result) => {
      //           this.data = result.data;
      //           console.log(result.data.token);
      //           console.log(result.data.userId);
      //           console.log(result.data.userType);
      //
      //           localStorage.setItem("token", 'Bearer '+result.data.token);
      //           localStorage.setItem("userId", result.data.userId);
      //           localStorage.setItem("userType", result.data.userType);
      //           this.$store.commit("loginSuccess", result.data);
      //           this.$store.dispatch("getAccountInfo");
      //               // .then(()=>this.$router.push("/"));
      // }
      // ).catch((error) => {
      //       alert(error);
      //     }
      // ).finally(() =>{
      //   alert("Finish!"+process.env.VUE_APP_TEST);
      //   this.$router.push("/");
      // })
    },
    goSignUp() {
      this.$router.push("/signUp");
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<!--<style scoped>-->
<!--h3 {-->
<!--  margin: 40px 0 0;-->
<!--}-->
<!--ul {-->
<!--  list-style-type: none;-->
<!--  padding: 0;-->
<!--}-->
<!--li {-->
<!--  display: inline-block;-->
<!--  margin: 0 10px;-->
<!--}-->
<!--a {-->
<!--  color: #42b983;-->
<!--}-->
<!--</style>-->