<template>
  <div class="AddPost">
    <h1>{{ msg }}</h1>
    <p>게시물 등록창</p>
    <v-card width="300" title="게시물 등록">
      <v-form ref="" v-on:submit.prevent="">
<!--        <v-text-field v-model="postId" label="게시물ID"></v-text-field>-->
        <v-text-field v-model="postTit" label="제목"></v-text-field>
        <v-text-field v-model="postCntt" label="내용"></v-text-field>
      </v-form>
      <v-btn @click="addPost">게시물 등록</v-btn>
    </v-card>
  </div>
</template>

<script>
import AxiosInst from "@/axios/axiosInstance";
export default {
  name: "AddPostComp",
  data: function () {
    return {      postId:""        ,postTit:""      ,postCntt:""      ,postDtm:""    };
  },
  props: {
    msg: String,
  },
  methods: {
    addPost: function () {
      let formJson = {
        postId: this.postId,
        postTit: this.postTit,
        postCntt: this.postCntt
      };

      AxiosInst.post("/v1/addPost", formJson)
        .then((result) => {
          if(result.data.postId!=null) {
            alert("게시물 등록 완료"+process.env["VUE_APP_TEST"]);  //환경변수 .env 테스트
            this.$router.push("/post");
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