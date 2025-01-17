import axios from 'axios'
import type { AxiosRequestConfig, AxiosInstance, AxiosResponse, AxiosError } from 'axios';

const baseURL = 'http://chat-service.touchbiz.tech:8080/api'
// const baseURL = 'http://127.0.0.1:8080/api'
const requestTimeout = 15000
const instance = axios.create({
  baseURL,
  timeout: requestTimeout,
  headers: {
    'Content-Type': 'application/json;charset=UTF-8'
  },
})

instance.interceptors.request.use(
  config => {
    return config
  },
  error => {
    return Promise.reject(error)
  }
)
instance.interceptors.response.use(
  res => {
    if (res.status !== 200) {
      return Promise.reject(res);
    } else {
      return res.data;
    }
  },
  error => {
    const { response } = error
    if (response && response.data) {
      return Promise.reject(error)
    } else {
      return Promise.reject(error)
    }
  }
)

export default instance
