import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '@/views/LoginView.vue'



const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: { transition: 'slide-right' , auth:true}
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '@/views/AboutView.vue'),
    meta: { transition: 'slide-right' , auth:true}

  },
  {
    path: '/goodbye',
    name: 'goodbye',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "goodbye" */ '../views/GoodByeView.vue'),
    meta: { transition: 'slide-right' , auth:true}
  },
  {
    path: '/loginForm',
    name: 'LoginView',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: LoginView
  },
  {
    path: '/apiTest',
    name: 'apiTest',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "goodbye" */ '../components/ApiTest.vue'),
    meta: { transition: 'slide-right' , auth:true}
  },{
    path: '/signUp',
    name: 'SignUp',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "goodbye" */ '@/components/SignUp.vue'),
  },{
    path: '/post',
    name: 'PostView',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "goodbye" */ '@/views/PostView.vue'),
    meta: { transition: 'slide-right' , auth:true}
  },{
    path: '/addPost',
    name: 'AddPostView',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "goodbye" */ '@/views/AddPostView.vue'),
    meta: { transition: 'slide-right' , auth:true}
  },{
    path: '/uptPost/:paramPostId',
    name: 'UptPostView',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "goodbye" */ '@/views/UptPostView.vue'),
    meta: { transition: 'slide-right' , auth:true},

  },{
    path: '/stndword',
    name: 'StndWordView',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "goodbye" */ '@/views/StndWordView.vue'),
    meta: { transition: 'slide-right' , auth:true},

  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {
  if (to.name !== 'LoginView' && to.name !== 'SignUp'&& localStorage.getItem("token")==null) next({ name: 'LoginView' })
  else next()
});

export default router
