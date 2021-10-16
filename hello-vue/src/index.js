import Vue from "../node_modules/vue/dist/vue";
import VueRouter from "../node_modules/vue-router/dist/vue-router";
import loginForm from "./js/login";
import registerForm from "./js/register";
import './css/main.css';
// 指定使用模块化加载
Vue.use(VueRouter);
// 创建VueRouter路由实例
const router = new VueRouter({
routes:[{
    path:'/login',  // 路由路径
    component:loginForm // 路由组件名
},
    {
        path:'/register',
        component:registerForm
    }]
});
const vue = new Vue({
    el:'#app',
    components:{
        loginForm,
        registerForm
    },
    router  // 引用上面定义的路由对象
});