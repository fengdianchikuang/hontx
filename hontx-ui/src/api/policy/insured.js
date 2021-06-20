import request from '@/utils/request'

export function uploadFile(data) {
  return request({
    url: '/queryfile/file/upload',
    headers: {
      "Content-type": "multipart/form-data"
    },
    method: 'post',
    data: data
  })
}

// 查询保单文件详细
export function getFile(qId) {
  return request({
    url: '/queryfile/file/' + qId,
    method: 'get'
  })
}

// 查询已投保列表
export function listInsured(query) {
  return request({
    url: '/policy/query/list',
    method: 'get',
    params: query
  })
}

// 查询已投保详细
export function getInsured(qId) {
  return request({
    url: '/policy/query/' + qId,
    method: 'get'
  })
}

// 新增已投保
export function addInsured(data) {
  return request({
    url: '/policy/query',
    method: 'post',
    data: data
  })
}

// 修改已投保
export function updateInsured(data) {
  return request({
    url: '/policy/query',
    method: 'put',
    data: data
  })
}

// 删除已投保
export function delInsured(qId) {
  return request({
    url: '/policy/query/' + qId,
    method: 'delete'
  })
}

// 导出已投保
export function exportInsured(query) {
  return request({
    url: '/policy/query/export',
    method: 'get',
    params: query
  })
}
