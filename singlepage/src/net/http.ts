import axios, {AxiosRequestHeaders} from "axios";

axios.defaults.withCredentials = true;

axios.defaults.baseURL = 'http://backend:8080'

export function toQueryString(param: any = {}): string {
    let paramStr: string = ''
    for (let key in param) {
        paramStr += key + '=' + param[key] + '&'
    }
    if (paramStr) {
        paramStr = '?' + paramStr.substring(0, paramStr.length - 1)
    }
    return paramStr
}


export function get(url: string, param: any = {}) {
    return axios.get(url + toQueryString(param))
}

export function post(url: string, param: any = {}, data: any = {}) {
    return axios.post(url + toQueryString(param), data)
}

export function put(url: string, param: any = {}, data: any = {}) {
    return axios.put(url + toQueryString(param), data)
}

export function del(url: string, param: any = {}, data: any = {}) {
    return axios.delete(url + toQueryString(param), {data})
}
