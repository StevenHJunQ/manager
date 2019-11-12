import axios from 'axios';
import router from '../router';
import { Message, Loading } from 'element-ui';//引入elm组件

const service = axios.create({
    // process.env.NODE_ENV === 'development' 来判断是否开发环境
    baseURL: 'http://127.0.0.1:8888/v1',
    timeout: 5000
})

service.interceptors.request.use(config => {
    let token = localStorage.getItem('token');
    if (token) {
        config.headers.common['token'] = localStorage.getItem('token');
        config.headers.common['username'] = localStorage.getItem('ms_username');
    }
    return config;
}, error => {
    console.log(error);
    return Promise.reject();
})

service.interceptors.response.use(response => {
    if (response.status === 200) {
        return response.data;
    } else {
        Promise.reject();
    }
}, error => {
    console.log(error);
    if (error.response.status === 401) {
        Message.error('登录超时');
        localStorage.removeItem('token');
        localStorage.removeItem('ms_username');
        router.replace({
            path: 'login',
            query: { redirect: router.currentRoute.fullPath }
        })
    } else {
        Message.error('服务器异常，请稍后再试');
    }
    return Promise.reject();
})

export default service;