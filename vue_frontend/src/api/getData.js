import base from './index'
import request from '@/utils/request'
let axios = base.axios
let baseUrl = base.baseUrl

// 获取好友
export const getFriend = id => {
    
    /*
    return axios({
      method: 'post',
      baseURL: `${baseUrl}/friend/friendList`,
      data: id
    }).then(res => res.data)
    */
    return request({
      url: '/api/user/repository',
      method: 'get',
    })
    
  }

  // 获取聊天信息
export const getChatMsg = params => {
  return axios({
    method: 'post',
    baseURL: `${baseUrl}/friend/chatMsg`,
    data: params
  }).then(res => res.data)
}

  // 获取聊天信息
  export function chatgpt (params) {
    /*
    return axios({
      method: 'post',
      url: `https://api.openai.com/v1/completions`,
      data: params,
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer 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`,
      }
    }).then(res => res.data)
    */

    return request({
      url: '/api/user/chat',
      method: 'post',
      data:params
    })
  }
