import request from '@/utils/request'

// 查询待投保单列表
export function listQuery(query) {
  return request({
    url: '/policy/query/list',
    method: 'get',
    params: query
  })
}

// 查询已询价列表详细
export function getQuery(qId) {
  return request({
    url: '/policy/query/' + qId,
    method: 'get'
  })
}

// 新增已询价列表
export function addQuery(data) {
  return request({
    url: '/policy/query',
    method: 'post',
    data: data
  })
}

// 修改已询价列表
export function updateQuery(data) {
  return request({
    url: '/policy/query',
    method: 'put',
    data: data
  })
}

// 删除已询价列表
export function delQuery(qId) {
  return request({
    url: '/policy/query/' + qId,
    method: 'delete'
  })
}

// 导出已询价列表
export function exportQuery(query) {
  return request({
    url: '/policy/query/export',
    method: 'get',
    params: query
  })
}
