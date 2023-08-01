<template>
  <div class="StndWord" >
    <h1>{{ msg }}</h1>
    <p>표준단어 화면</p>
    <v-btn @click="getStndWordList">표준단어 조회</v-btn>
    <v-btn @click="delStndWord">표준단어 삭제</v-btn>

    <v-container>
      <DataTable ref="datatableView" v-bind:tabledata="data" />
    </v-container>
    <v-divider></v-divider>
    <v-card width="300" title="표준단어 등록">
      <v-form ref="frm" v-on:submit.prevent="">
        <v-text-field
          v-model="wordForm.wordKorNm"
          label="표준단어명"
        ></v-text-field>
        <v-text-field
          v-model="wordForm.wordEngNm"
          label="표준단어영문명"
        ></v-text-field>
        <v-text-field
          v-model="wordForm.wordAbrNm"
          label="표준단어약어명"
        ></v-text-field>
        <v-text-field
          v-model="wordForm.wordDescn"
          label="표준단어설명"
        ></v-text-field>
        <!--        <v-select-->
        <!--          v-model="userForm.userType"-->
        <!--          label="사용자유형"-->
        <!--          :items="['ADMIN', 'USER']"-->
        <!--        >-->
        <!--        </v-select>-->
      </v-form>
      <v-btn @click="regStndWord">표준단어 등록</v-btn>
    </v-card>
    <v-divider></v-divider>
  </div>
</template>

<script>
import AxiosInst from "@/axios/axiosInstance";
import DataTable from "@/components/DataTable.vue";

export default {
  name: "StndWord",
  components: { DataTable },
  data: function () {
    return {
      data: {
        itemsPerPage: 5,
        headers: [
          { title: "표준단어ID", align: "start", key: "wordId" },
          { title: "표준단어명", align: "end", key: "wordKorNm" },
          { title: "표준단어영문명", align: "end", key: "wordEngNm" },
          { title: "표준단어약어명", align: "end", key: "wordAbrNm" },
          { title: "표준단어설명", align: "end", key: "wordDescn" },
          { title: "등록일시", align: "end", key: "regDtm" },
        ],
        items: [{}],
      },
      wordForm: { wordKorNm: "", wordEngNm: "", wordAbrNm: "", wordDescn: "" },
    };
  },
  props: {
    msg: String,
  },
  methods: {
    getStndWordList: async function () {
      let formJson = {};
      try {
        let result = await AxiosInst.post("/v1/getStndWordList", formJson);
        this.data.items = result.data;
      } catch (error) {
        alert(error);
      }
    },
    regStndWord: async function () {
      let formJson = {
        wordKorNm: this.wordForm.wordKorNm,
        wordEngNm: this.wordForm.wordEngNm,
        wordAbrNm: this.wordForm.wordAbrNm,
        wordDescn: this.wordForm.wordDescn,
      };
      try {
        let result = await AxiosInst.post("/v1/addStndWord", formJson);
        if (result != null) {
          alert("단어 등록 되었습니다.");
          this.getStndWordList();

          this.$refs.frm.reset();
        }
      } catch (error) {
        alert(error);
      }
    },
    delStndWord: async function () {
      try {
        let url = "/v1/delStndWord";
        let msg = "단어가 삭제 되었습니다.";
        let result = await this.$refs.datatableView.sendSelectedDataToUrl(url, msg);
        if(result){
          this.getStndWordList();
        }
      } catch (error) {
        alert(error);
      }
    },
  },
  // created() {
  //   this.getStndWordList();
  // },
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
