import request from '@/utils/request'

export function getAllList() {
  return request({
    url: '/life/product/all',
    method: 'get'
  })

}

// 查询寿险产品列表列表
export function listProduct(query) {
  return request({
    url: '/life/product/list',
    method: 'get',
    params: query
  })
}

// 查询寿险产品列表详细
export function getProduct(id) {
  return request({
    url: '/life/product/' + id,
    method: 'get'
  })
}

// 新增寿险产品列表
export function addProduct(data) {
  return request({
    url: '/life/product',
    method: 'post',
    data: data
  })
}

// 修改寿险产品列表
export function updateProduct(data) {
  return request({
    url: '/life/product',
    method: 'put',
    data: data
  })
}

// 删除寿险产品列表
export function delProduct(id) {
  return request({
    url: '/life/product/' + id,
    method: 'delete'
  })
}

// 导出寿险产品列表
export function exportProduct(query) {
  return request({
    url: '/life/product/export',
    method: 'get',
    params: query
  })
}
