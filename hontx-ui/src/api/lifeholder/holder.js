import request from '@/utils/request'

// 查询寿险客户列表
export function listHolder(query) {
  return request({
    url: '/lifeholder/holder/list',
    method: 'get',
    params: query
  })
}

// 查询寿险客户详细
export function getHolder(id) {
  return request({
    url: '/lifeholder/holder/' + id,
    method: 'get'
  })
}

// 新增寿险客户
export function addHolder(data) {
  return request({
    url: '/lifeholder/holder',
    method: 'post',
    data: data
  })
}

// 修改寿险客户
export function updateHolder(data) {
  return request({
    url: '/lifeholder/holder',
    method: 'put',
    data: data
  })
}

// 删除寿险客户
export function delHolder(id) {
  return request({
    url: '/lifeholder/holder/' + id,
    method: 'delete'
  })
}

// 导出寿险客户
export function exportHolder(query) {
  return request({
    url: '/lifeholder/holder/export',
    method: 'get',
    params: query
  })
}