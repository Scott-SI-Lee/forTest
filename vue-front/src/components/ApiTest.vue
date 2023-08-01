<template>
  <div class="apiTest">
    <h1>{{ msg }}</h1>
    <p>API테스트 화면</p>
    <v-btn @click="getUserList">전체 유저정보 획득</v-btn>
    <v-container>
      <DataTable v-bind:tabledata="data" />
    </v-container>
    <!--    <v-container>{{ data }}</v-container>-->
    <v-divider></v-divider>
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
    <v-divider></v-divider>
    <v-btn @click="testEvent">이벤트테스트용</v-btn>
  </div>
</template>

<script>
import AxiosInst from "@/axios/axiosInstance";
import DataTable from "@/components/DataTable.vue";

export default {
  name: "ApiTest",
  components: { DataTable },
  data: function () {
    return {
      data: {
        itemsPerPage: 5,
        headers: [
          { title: "유저ID", align: "start", key: "userId" },
          { title: "유저명", align: "end", key: "userNm" },
          { title: "유저패스워드", align: "end", key: "userPw" },
          { title: "유저타입", align: "end", key: "userType" },
        ],
        items: [{}],
      },
      userForm: { userId: "", userNm: "", userPw: "", userType: "" },
    };
  },
  props: {
    msg: String,
  },
  methods: {
    getUserList: async function () {
      let formJson = {};
      try {
        let result = await AxiosInst.post("/v1/getUserList", formJson);
        this.data.items = result.data;
        console.log(this.data.items)
      } catch (error) {
        alert(error);
      }
    },
    regUser: function () {
      let formJson = {
        userId: this.userForm.userId,
        userPw: this.userForm.userPw,
        userNm: this.userForm.userNm,
        userType: this.userForm.userType,
      };

      AxiosInst.post("/v1/regUser", formJson)
        .then((result) => {
          this.data = result.data;
          //    alert(this.data);
        })
        .catch((error) => {
          alert(error);
        })
        .finally(() => {});
    },
    testEvent: function () {
      this.$emit("test");
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
