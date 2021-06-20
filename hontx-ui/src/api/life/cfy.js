import request from '@/utils/request'

// 查询创富云产品列表列表
export function listCfy(query) {
  return request({
    url: '/life/product/productList/'+query.pageNum + '/' + query.pageSize,
    method: 'get',
    params: query
  })
}

// 查询创富云产品列表详细
export function getCfy(id) {
  return request({
    url: '/life/product/cfy/' + id,
    method: 'get'
  })
}

// 新增创富云产品列表
export function addCfy(data) {
  return request({
    url: '/life/product/cfy',
    method: 'post',
    data: data
  })
}

// 修改创富云产品列表
export function updateCfy(data) {
  return request({
    url: '/life/product/cfy',
    method: 'put',
    data: data
  })
}

// 删除创富云产品列表
export function delCfy(id) {
  return request({
    url: '/life/cfy/' + id,
    method: 'delete'
  })
}

// 导出创富云产品列表
export function exportCfy(query) {
  return request({
    url: '/life/cfy/export',
    method: 'get',
    params: query
  })
}
