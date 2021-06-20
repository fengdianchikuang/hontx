import request from '@/utils/request'

// 查询交易明细列表
export function listDetails(query) {
  return request({
    url: '/ifefinance/details/list',
    method: 'get',
    params: query
  })
}

// 查询交易明细详细
export function getDetails(id) {
  return request({
    url: '/ifefinance/details/' + id,
    method: 'get'
  })
}

// 新增交易明细
export function addDetails(data) {
  return request({
    url: '/ifefinance/details',
    method: 'post',
    data: data
  })
}

// 修改交易明细
export function updateDetails(data) {
  return request({
    url: '/ifefinance/details',
    method: 'put',
    data: data
  })
}

// 删除交易明细
export function delDetails(id) {
  return request({
    url: '/ifefinance/details/' + id,
    method: 'delete'
  })
}

// 导出交易明细
export function exportDetails(query) {
  return request({
    url: '/ifefinance/details/export',
    method: 'get',
    params: query
  })
}