import request from '@/utils/request'
//上传证件照
export function uploadVehiclefile(data) {
  return request({
    url: '/vehicle/index/upload',
    headers: {
      "Content-type": "multipart/form-data"
    },
    method: 'post',
    data: data
  })
}

export function delVehicleFile(id) {
  return request({
    url: '/vehicle/index/upload/'+id,
    method: 'delete'
  })

}
// 查询car列表
export function listIndex(query) {
  return request({
    url: '/vehicle/index/list',
    method: 'get',
    params: query
  })
}

// 查询car详细
export function getIndex(vId) {
  return request({
    url: '/vehicle/index/' + vId,
    method: 'get'
  })
}

// 新增car
export function addIndex(data) {
  return request({
    url: '/vehicle/index',
    method: 'post',
    data: data
  })
}

// 修改car
export function updateIndex(data) {
  return request({
    url: '/vehicle/index',
    method: 'put',
    data: data
  })
}

// 删除car
export function delIndex(vId) {
  return request({
    url: '/vehicle/index/' + vId,
    method: 'delete'
  })
}

// 导出car
export function exportIndex(query) {
  return request({
    url: '/vehicle/index/export',
    method: 'get',
    params: query
  })
}
