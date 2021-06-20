import request from '@/utils/request'

// 查询销售业绩列表
export function listIncome(query) {
  return request({
    url: '/trans/income/list',
    method: 'get',
    params: query
  })
}

// 查询销售业绩详细
export function getIncome(iId) {
  return request({
    url: '/trans/income/' + iId,
    method: 'get'
  })
}

// 新增销售业绩
export function addIncome(data) {
  return request({
    url: '/trans/income',
    method: 'post',
    data: data
  })
}

// 修改销售业绩
export function updateIncome(data) {
  return request({
    url: '/trans/income',
    method: 'put',
    data: data
  })
}

// 删除销售业绩
export function delIncome(iId) {
  return request({
    url: '/trans/income/' + iId,
    method: 'delete'
  })
}

// 导出销售业绩
export function exportIncome(query) {
  return request({
    url: '/trans/income/export',
    method: 'get',
    params: query
  })
}