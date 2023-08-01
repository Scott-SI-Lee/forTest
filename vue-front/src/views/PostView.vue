<template>
  <PostComp/>
</template>

<script>
import { defineComponent } from 'vue';

// Components
import PostComp from '@/components/Post.vue';

export default defineComponent({
  name: 'PostView',

  components: {
    PostComp,
  },
});
</script>
