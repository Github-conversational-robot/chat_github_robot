import request from '@/utils/request'

export function uploadfile(filePath) {
    console.log(filePath);
    return request({
        url: '/api/user/repository/loadfile',
        method: 'post',
        data:filePath
      })
}