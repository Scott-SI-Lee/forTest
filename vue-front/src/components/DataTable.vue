<template>
  <v-data-table
    v-model:items-per-page="data.itemsPerPage"
    :headers="data.headers"
    :items="items"
    item-value="wordId"
    class="elevation-1"
    return-object
    show-select
    v-model="selected"
  ></v-data-table>
</template>

<script>
import { VDataTable } from "vuetify/labs/VDataTable";
import AxiosInst from "@/axios/axiosInstance";

export default {
  name: "DataTable",
  components: {
    VDataTable,
  },
  props: {
    tabledata: String,
  },
  computed: {
    getItemsPerPage() {
      return this.tabledata.itemsPerPage;
    },
    getHeaders() {
      return this.tabledata.headers;
    },
    items: function () {
      return this.tabledata.items;
    },
  },
  data() {
    return {
      selected: [],
      data: {
        itemsPerPage: this.tabledata.itemsPerPage,
        headers: this.tabledata.headers,
      },
    };
  },
  methods: {
    sendSelectedDataToUrl: async function (url, msg) {
       if(this.selected.length==0){
         alert("선택된 데이터가 없습니다.");
         return;
       }
       try {
         let result = await AxiosInst.post(url, this.selected);
         alert(msg);
         return result;
       }catch(error){
         alert(error);
         return false;
       }

    },
  },
};
</script>