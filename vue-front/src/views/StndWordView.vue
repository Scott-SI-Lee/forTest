<template>
  <StndWord />
</template>

<script>
import { defineComponent } from "vue";

// Components
import StndWord from "@/components/StndWord.vue";

export default defineComponent({
  name: "StndWordView",

  components: {
    StndWord,
  },
});
</script>