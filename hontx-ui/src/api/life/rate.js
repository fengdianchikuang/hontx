import request from '@/utils/request'

// 查询产品费率表列表
export function listRate(query) {
  return request({
    url: '/life/rate/list',
    method: 'get',
    params: query
  })
}

// 查询产品费率表详细
export function getRate(id) {
  return request({
    url: '/life/rate/' + id,
    method: 'get'
  })
}

// 新增产品费率表
export function addRate(data) {
  return request({
    url: '/life/rate',
    method: 'post',
    data: data
  })
}

// 修改产品费率表
export function updateRate(data) {
  return request({
    url: '/life/rate',
    method: 'put',
    data: data
  })
}

// 删除产品费率表
export function delRate(id) {
  return request({
    url: '/life/rate/' + id,
    method: 'delete'
  })
}

// 导出产品费率表
export function exportRate(query) {
  return request({
    url: '/life/rate/export',
    method: 'get',
    params: query
  })
}