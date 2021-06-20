import request from '@/utils/request'

//获取上级机构Id
export function getParentOrg() {
  return request({
    url: '/org/list/type',
    method: 'get'
  })
}

// 查询机构管理列表
export function listList(query) {
  return request({
    url: '/org/list/list',
    method: 'get',
    params: query
  })
}

// 查询机构管理详细
export function getList(oId) {
  return request({
    url: '/org/list/' + oId,
    method: 'get'
  })
}

// 新增机构管理
export function addList(data) {
  return request({
    url: '/org/list',
    method: 'post',
    data: data
  })
}

// 修改机构管理
export function updateList(data) {
  return request({
    url: '/org/list',
    method: 'put',
    data: data
  })
}

// 删除机构管理
export function delList(oId) {
  return request({
    url: '/org/list/' + oId,
    method: 'delete'
  })
}

// 导出机构管理
export function exportList(query) {
  return request({
    url: '/org/list/export',
    method: 'get',
    params: query
  })
}
