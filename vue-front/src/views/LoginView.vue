<template>
  <v-container>
    <v-img
        :src="require('../assets/logo.svg')"
        class="my-3"
        contain
        height="200"
    />
    <LoginComp  v-bind:msg="msg1" />
  </v-container>
</template>

<script>
// @ is an alias to /src
import LoginComp from '@/components/Login.vue'

export default {
  name: 'LoginView',
  props:{
    msg1:{
      type : String,
      default : "User!"
    },

    msg2:{
      type : String,
      default : "Hello"
    }
  },
  components: {
    LoginComp
  }
}
</script>
