import request from '@/utils/request'

// 查询费率设置列表
export function listRegion(query) {
  return request({
    url: '/region/address/list',
    method: 'get',
    params: query
  })
}


