import request from '@/utils/request'

// 查询创富云保单列表列表
export function listCfy(query) {
  return request({
    url: '/lifeorder/cfy/list',
    method: 'get',
    params: query
  })
}

// 查询创富云保单列表详细
export function getCfy(id) {
  return request({
    url: '/lifeorder/cfy/' + id,
    method: 'get'
  })
}

// 新增创富云保单列表
export function addCfy(data) {
  return request({
    url: '/lifeorder/cfy',
    method: 'post',
    data: data
  })
}

// 修改创富云保单列表
export function updateCfy(data) {
  return request({
    url: '/lifeorder/cfy',
    method: 'put',
    data: data
  })
}

// 删除创富云保单列表
export function delCfy(id) {
  return request({
    url: '/lifeorder/cfy/' + id,
    method: 'delete'
  })
}

// 导出创富云保单列表
export function exportCfy(query) {
  return request({
    url: '/lifeorder/cfy/export',
    method: 'get',
    params: query
  })
}