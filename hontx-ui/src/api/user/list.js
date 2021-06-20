import request from '@/utils/request'

/**
 * 删除用户图片
 * @param data
 */
export function delFile(id) {
  return request({
    url: '/user/list/upload/'+id,
    method: 'delete'

  })
}

/**
 * 上传用户团片
 * @param data
 */
export function uploadFile(data) {
  return request({
    url: '/user/list/upload',
    headers: {
      "Content-type": "multipart/form-data"
    },
    method: 'post',
    data: data
  })
}

// 查询用户扩展信息列表
export function listList(query) {
  return request({
    url: '/user/list/list',
    method: 'get',
    params: query
  })
}

// 查询用户扩展信息详细
export function getList(uId) {
  return request({
    url: '/user/list/' + uId,
    method: 'get'
  })
}

// 新增用户扩展信息
export function addList(data) {
  return request({
    url: '/user/list',
    method: 'post',
    data: data
  })
}

// 修改用户扩展信息
export function updateList(data) {
  return request({
    url: '/user/list',
    method: 'put',
    data: data
  })
}

// 删除用户扩展信息
export function delList(uId) {
  return request({
    url: '/user/list/' + uId,
    method: 'delete'
  })
}

// 导出用户扩展信息
export function exportList(query) {
  return request({
    url: '/user/list/export',
    method: 'get',
    params: query
  })
}
