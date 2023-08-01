<template>
  <AddPostComp/>
</template>

<script>
import { defineComponent } from 'vue';

// Components
import AddPostComp from '@/components/AddPost.vue';

export default defineComponent({
  name: 'AddPostView',

  components: {
    AddPostComp,
  },
});
</script>
