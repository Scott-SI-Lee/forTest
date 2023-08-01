<template>
  <div class="강의노트">
    <h1>{{ msg }}</h1>
    <p>강의노트 화면입니다.</p>
    <v-btn @click="getPostList">조회</v-btn>
    <v-container class="overflow-auto">
      <v-card
        class="ma-2 pa-2"
        min-width="300"
        v-for="item in data"
        :key="item.postId"
      >
        <v-card-item>
          <v-card-title class="d-flex justify-space-between"
            >{{ item.postTit }}
            <v-sheet>
              <v-icon
                @click="uptPost(item.postId)"
                size="small"
                icon="mdi-pencil"
              ></v-icon>
              <v-icon
                @click="delPost(item.postId)"
                size="small"
                icon="mdi-delete"
              ></v-icon>
            </v-sheet>
          </v-card-title>
          <v-card-text
            >{{ item.postCntt }}
            <v-divider></v-divider>
            <v-label class="d-flex justify-end">{{ item.postDtm }}</v-label>
          </v-card-text>
        </v-card-item>
      </v-card>
    </v-container>
    <v-divider></v-divider>
    <v-container class="d-flex justify-end">
      <v-btn @click="addPost" density="compact" icon="mdi-plus"></v-btn>
    </v-container>
  </div>
</template>

<script>
import AxiosInst from "@/axios/axiosInstance";

export default {
  name: "PostComp",
  data: function () {
    return {
      data: [
        {
          postId: "gggg",
          postTit: "suggggg",
          postCntt: "texttext",
          postDtm: "20230718160000",
        },
        {
          postId: "gggg2",
          postTit: "suggggg",
          postCntt: "texttext2",
          postDtm: "20230718160000",
        },
        {
          postId: "gggg3",
          postTit: "suggggg",
          postCntt: "texttext3",
          postDtm: "20230718160000",
        },
      ],
    };
  },
  props: {
    msg: String,
  },
  methods: {
    getPostList: function () {
      let formJson = {};
      AxiosInst.post("http://localhost:8080/v1/getPostList", formJson)
        .then((result) => {
          this.data = result.data;
          //    alert(this.data);
        })
        .catch((error) => {
          alert(error);
        })
        .finally(() => {});
    },
    addPost: function () {
      this.$router.push("/addPost");
    },
    delPost: async function (postId) {
      let formJson = { postId: postId };
      let result = await AxiosInst.post(
        "http://localhost:8080/v1/delPost",
        formJson
      );

      if (result) {
        alert("삭제 되었습니다.");
        this.getPostList();
      } else {
        alert("delete 실패");
      }
    },
    uptPost: function (postId) {
 //       this.$router.push("/uptPost/"+postId);
      //params 로 넘길경우 path가 아닌 name으로 넘겨야함
      console.log(postId);
      this.$router.push({path:"/uptPost", name : "UptPostView", params:{"paramPostId":postId}});
    },
  },
  mounted() {
    this.getPostList();
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
