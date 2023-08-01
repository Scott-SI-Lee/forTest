<template>
  <div class="GoodByeWorld">
    <v-img
        :src="require('@/assets/logo.svg')"
        class="my-3"
        contain
        height="200"
    />
    <GoodByeWorld msg="GoodBye to Your Vue.js App"/>
  </div>
</template>

<script>
// @ is an alias to /src
import GoodByeWorld from '@/components/GoodByeWorld.vue'

export default {
  name: 'GoodByeVue',
  components: {
    GoodByeWorld
  }
}
</script>
