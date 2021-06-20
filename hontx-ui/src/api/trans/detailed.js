import request from '@/utils/request'

// 查询交易明细列表
export function listDetailed(query) {
  return request({
    url: '/trans/detailed/list',
    method: 'get',
    params: query
  })
}

// 查询交易明细详细
export function getDetailed(tId) {
  return request({
    url: '/trans/detailed/' + tId,
    method: 'get'
  })
}

// 新增交易明细
export function addDetailed(data) {
  return request({
    url: '/trans/detailed',
    method: 'post',
    data: data
  })
}

// 修改交易明细
export function updateDetailed(data) {
  return request({
    url: '/trans/detailed',
    method: 'put',
    data: data
  })
}

// 删除交易明细
export function delDetailed(tId) {
  return request({
    url: '/trans/detailed/' + tId,
    method: 'delete'
  })
}

// 导出交易明细
export function exportDetailed(query) {
  return request({
    url: '/trans/detailed/export',
    method: 'get',
    params: query
  })
}