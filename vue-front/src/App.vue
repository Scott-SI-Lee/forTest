<template>
  <v-app >
    <v-main>
      <v-app-bar :elevation="2" v-if="$route.name !== 'LoginView' && $route.name !== 'SignUp'">
        <v-container>
          <v-card width="120" height="50">
          <v-card-title class="justify-center">테스트용</v-card-title>
          </v-card>
        </v-container>
       <v-spacer></v-spacer> <v-btn @click="$store.dispatch('goLogout').then(()=>this.$router.push('loginForm'))">로그아웃</v-btn>
      </v-app-bar>
      <v-navigation-drawer v-if="$route.name !== 'LoginView' && $route.name !== 'SignUp' ">
<!--        <v-btn><router-link to="/"> Home </router-link></v-btn>-->
<!--        <v-btn><router-link to="/about"> About </router-link></v-btn>-->
<!--        <v-btn><router-link to="/goodbye"> GoodBye </router-link></v-btn>-->
        <v-list>
          <v-list-item
              v-for="item in items"
              :key="item.text"
              :title="item.text"
              :to="item.route"
              subtitle=""
          ></v-list-item>
        </v-list>
      </v-navigation-drawer>

      <router-view v-on:abc="testFunc">
      </router-view>
    </v-main>
  </v-app>


</template>



<script>


export default {
  name: 'App',
  data: () => ({
    //
    items :
      [
          {text:'Home',route:'/'},
          {text:'about',route:'/about'},
          {text:'goodbye',route:'/goodbye'},
          {text:'apiTest',route:'/apiTest'},
          {text:'post',route:'/post'},
          {text:'stndword',route:'/stndword'},
      ]
  }),
  methods:{
    testFunc: function(){
         alert("안녕2");
    }
  }

}
</script>
