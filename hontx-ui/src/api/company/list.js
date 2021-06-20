import request from '@/utils/request'

// 查询company列表
export function listList(query) {
  return request({
    url: '/company/list/list',
    method: 'get',
    params: query
  })
}

// 查询company详细
export function getList(iId) {
  return request({
    url: '/company/list/' + iId,
    method: 'get'
  })
}

// 新增company
export function addList(data) {
  return request({
    url: '/company/list',
    method: 'post',
    data: data
  })
}

// 修改company
export function updateList(data) {
  return request({
    url: '/company/list',
    method: 'put',
    data: data
  })
}

// 删除company
export function delList(iId) {
  return request({
    url: '/company/list/' + iId,
    method: 'delete'
  })
}

// 导出company
export function exportList(query) {
  return request({
    url: '/company/list/export',
    method: 'get',
    params: query
  })
}