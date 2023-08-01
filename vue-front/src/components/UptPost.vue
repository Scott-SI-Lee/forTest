<template>
  <div class="UptPost">
    <p>게시물 수정창</p>
    <v-card width="300" title="게시물 수정">
      <v-form ref="" v-on:submit.prevent="">
<!--        <v-text-field-->
<!--          v-model="postId"-->
<!--          label="게시물ID"-->
<!--          readonly="true"-->
<!--        ></v-text-field>-->
        <v-text-field v-model="postTit" label="제목"></v-text-field>
        <v-text-field v-model="postCntt" label="내용"></v-text-field>
      </v-form>
      <v-btn @click="uptPost">게시물 수정</v-btn>
    </v-card>
  </div>
</template>

<script>
import AxiosInst from "@/axios/axiosInstance";

export default {
  name: "UptPostComp",
  data: function () {
    return {
      postId: this.$route.params.paramPostId,
      postCntt: "-",
      postTit: "-",
    };
  },

  methods: {
    uptPost: async function () {
      let formJson = {
        postId: this.postId,
        postTit: this.postTit,
        postCntt: this.postCntt,
      };

      let result = await AxiosInst.post("/v1/uptPost", formJson);
      if (result) {
        alert("게시물 수정 완료" + process.env["VUE_APP_TEST"]); //환경변수 .env 테스트
        this.$router.push("/post");
      } else {
        alert("게시물 수정 실패");
      }
    },
    getPost: async function () {
      let formJson = {
        postId: this.postId,
      };
      try {
        let result = await AxiosInst.post("/v1/getPost", formJson);
        this.postCntt = result.data.postCntt;
        this.postTit = result.data.postTit;
      } catch (error) {
        alert(error);
      }
    },
  },
  created() {
    this.getPost();
  },
  // computed: {
  //   postId: function () {
  //     return ""  //""
  // //   },
  // },
};
</script>
