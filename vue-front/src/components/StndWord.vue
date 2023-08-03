<template>
  <div class="StndWord">
    <h1>{{ msg }}</h1>

    <v-container>
      <p>* 표준단어 조회</p>
      <v-row class="w-auto">
        <v-col>
          <v-btn class="w-auto ma-1" @click="getStndWordList">표준단어 조회</v-btn>
          <v-btn class="w-auto ma-1" @click="delStndWord">표준단어 삭제</v-btn>
        </v-col>
      </v-row>
    </v-container>
    <v-container>
      <DataTable ref="datatableView" v-bind:tabledata="data" />
    </v-container>
    <v-divider></v-divider>
    <v-container class="d-flex justify-center">
      <v-card width="80%" title="표준단어 등록">
        <v-form ref="frm" v-on:submit.prevent="">
          <v-row class="w-auto ma-1">
            <v-col class="w-auto">
              <v-text-field
                v-model="wordForm.wordKorNm"
                label="* 표준단어명"
              ></v-text-field>
            </v-col>
            <v-col class="w-auto">
              <v-text-field
                v-model="wordForm.wordEngNm"
                label="* 표준단어영문명"
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row class="w-auto ma-1">
            <v-col class="w-auto">
              <v-text-field
                v-model="wordForm.wordAbrNm"
                label="* 표준단어약어명"
              ></v-text-field>
            </v-col>
            <v-col class="w-auto">
              <v-text-field
                v-model="wordForm.wordDescn"
                label="표준단어설명"
              ></v-text-field>
            </v-col>
          </v-row>
          <!--        <v-select-->
          <!--          v-model="userForm.userType"-->
          <!--          label="사용자유형"-->
          <!--          :items="['ADMIN', 'USER']"-->
          <!--        >-->
          <!--        </v-select>-->
        </v-form>
        <v-row class="w-auto ma-1">
          <v-col class="d-flex justify-center">
            <v-btn class="ma-1" @click="regStndWord">표준단어 등록</v-btn>
            <v-btn class="ma-1" @click="formReset">입력폼 초기화</v-btn>
          </v-col>
        </v-row>
      </v-card>
    </v-container>
    <v-divider></v-divider>
  </div>
</template>

<script>
import AxiosInst from "@/axios/axiosInstance";
import DataTable from "@/components/DataTable.vue";
import { reactive, ref } from "vue";

export default {
  //컴포지션 API(Compisition API). this 컨텍스트가 존재하지 않음

  setup() {
    //컴포지션에서 dom에 접근할 경우 ref와 같은 이름으로 ref 값 생성하여 사용
    let frm = ref(null); // value를 통해 접근
    let datatableView = ref(null); // value를 통해 접근

    let data = reactive({
      itemsPerPage: 5,
      headers: [
        { title: "표준단어ID", align: "start", key: "wordId" },
        { title: "*표준단어명", align: "end", key: "wordKorNm" },
        { title: "*표준단어영문명", align: "end", key: "wordEngNm" },
        { title: "*표준단어약어명", align: "end", key: "wordAbrNm" },
        { title: "표준단어설명", align: "end", key: "wordDescn" },
        { title: "등록일시", align: "end", key: "regDtm" },
      ],
      items: [{}],
    });
    let wordForm = reactive({
      wordKorNm: "",
      wordEngNm: "",
      wordAbrNm: "",
      wordDescn: "",
    });

    const getStndWordList = async () => {
      let formJson = {};
      try {
        let result = await AxiosInst.post("/v1/getStndWordList", formJson);
        //console.log(JSON.stringify(result));
        if(result.data.code!="200"){
          alert("조회 중 에러 : "+result.data.message);
          return;
        }
        data.items = result.data.data;
      } catch (error) {
        alert(error);
      }
    };
    const regStndWord = async () => {
      let formJson = {
        wordEngNm: wordForm.wordEngNm,
        wordAbrNm: wordForm.wordAbrNm,
        wordDescn: wordForm.wordDescn,
        wordKorNm: wordForm.wordKorNm,
      };
      try {
        let result = await AxiosInst.post("/v1/addStndWord", formJson);
        if (result.data.code =="200") {
          alert("단어 등록 되었습니다..");
          await getStndWordList();
          frm.value.reset();
        }else{
          alert(result.data.message);
        }
      } catch (error) {
        alert(error);
      }
    };
    const delStndWord = async () => {
      try {
        let url = "/v1/delStndWord";
        let msg = "단어가 삭제 되었습니다.";
        let result = await datatableView.value.sendSelectedDataToUrl(url, msg);
        if (result) {
          await getStndWordList();
        }
      } catch (error) {
        alert(error);
      }
    };

    //폼초기화
    const formReset = () => {
      frm.value.reset();
    };
    return {
      data,
      wordForm,
      datatableView,
      frm,
      getStndWordList,
      regStndWord,
      delStndWord,
      formReset,
    };
  },
  name: "StndWord",
  components: { DataTable },
  props: {
    msg: String,
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
