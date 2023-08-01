<template>
  <UptPostComp/>
</template>

<script>
import { defineComponent } from 'vue';

// Components
import UptPostComp from '@/components/UptPost.vue';

export default defineComponent({
  name: 'UptPostView',

  components: {
    UptPostComp,
  },
});
</script>
