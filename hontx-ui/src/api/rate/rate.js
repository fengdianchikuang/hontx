import request from '@/utils/request'

// 查询费率设置列表
export function listRate(query) {
  return request({
    url: '/rate/rate/list',
    method: 'get',
    params: query
  })
}

// 查询费率设置详细
export function getRate(iId) {
  return request({
    url: '/rate/rate/' + iId,
    method: 'get'
  })
}

// 新增费率设置
export function addRate(data) {
  return request({
    url: '/rate/rate',
    method: 'post',
    data: data
  })
}

// 修改费率设置
export function updateRate(data) {
  return request({
    url: '/rate/rate',
    method: 'put',
    data: data
  })
}

// 删除费率设置
export function delRate(iId) {
  return request({
    url: '/rate/rate/' + iId,
    method: 'delete'
  })
}

// 导出费率设置
export function exportRate(query) {
  return request({
    url: '/rate/rate/export',
    method: 'get',
    params: query
  })
}