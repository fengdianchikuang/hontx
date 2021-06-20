import request from '@/utils/request'

//更新创富云保单信息
export function updateOrderById(row) {
  return request({
    url: '/lifeorder/lifeorder/order/'+row.id,
    method: 'get'
  })
}

// 查询寿险订单列表
export function listLifeorder(query) {
  return request({
    url: '/lifeorder/lifeorder/list',
    method: 'get',
    params: query
  })
}

// 查询寿险订单详细
export function getLifeorder(id) {
  return request({
    url: '/lifeorder/lifeorder/' + id,
    method: 'get'
  })
}

// 新增寿险订单
export function addLifeorder(data) {
  return request({
    url: '/lifeorder/lifeorder',
    method: 'post',
    data: data
  })
}

// 修改寿险订单
export function updateLifeorder(data) {
  return request({
    url: '/lifeorder/lifeorder',
    method: 'put',
    data: data
  })
}

// 删除寿险订单
export function delLifeorder(id) {
  return request({
    url: '/lifeorder/lifeorder/' + id,
    method: 'delete'
  })
}

// 导出寿险订单
export function exportLifeorder(query) {
  return request({
    url: '/lifeorder/lifeorder/export',
    method: 'get',
    params: query
  })
}
