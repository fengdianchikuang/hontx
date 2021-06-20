import request from '@/utils/request'


export function updateTrans() {
  return request({
    url: '/trans/'
  })

}

// 查询提交列表列表
export function listIndex(query) {
  return request({
    url: '/trans/index/list',
    method: 'get',
    params: query
  })
}

// 查询提交列表详细
export function getIndex(tId) {
  return request({
    url: '/trans/index/' + tId,
    method: 'get'
  })
}

// 新增提交列表
export function addIndex(data) {
  return request({
    url: '/trans/index',
    method: 'post',
    data: data
  })
}

// 修改提交列表
export function updateIndex(id) {
  return request({
    url: '/trans/index/'+id,
    method: 'put',
    // data: data
  })
}

// 删除提交列表
export function delIndex(tId) {
  return request({
    url: '/trans/index/' + tId,
    method: 'delete'
  })
}

// 导出提交列表
export function exportIndex(query) {
  return request({
    url: '/trans/index/export',
    method: 'get',
    params: query
  })
}
