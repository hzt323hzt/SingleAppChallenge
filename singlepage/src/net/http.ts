import axios, {AxiosRequestHeaders} from "axios";

axios.defaults.withCredentials = true;

axios.defaults.baseURL = 'http://localhost:8080'

export function get(url: string, param: ReqParam = {}) {
    return axios.get(url + toQueryString(param))
}

export function post(url: string, param: ReqParam = {}, data: ReqBody = {}) {
    return axios.post(url + toQueryString(param), data)
}

export function put(url: string, param: ReqParam = {}, data: ReqBody = {}) {
    return axios.put(url + toQueryString(param), data)
}

export function del(url: string, param: ReqParam = {}, data: ReqBody = {}) {
    return axios.delete(url + toQueryString(param), {data})
}
