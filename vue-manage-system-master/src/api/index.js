import request from '../utils/request';

export const fetchData = (url, method, query) => {
    return request({
        url: url,
        method: method,
        data: query
    })
}