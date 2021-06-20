import request from '@/utils/request'

/**
 * 查询有多少个status 00 的询价信息
 */
export function getSize() {
  return request({
    url: '/policy/query/getSize',
    method: 'get'
  })
}

//保存询价信息
export function saveInsrnc(data) {
  return request({
    url: '/policy/query/insrnc',
    method: 'post',
    data
  })
}

//获取地区码
export function getRegionList(data) {
  return request({
    url: '/policy/query/region',
    method: 'post',
    data
  })
}
// 查询已询价列表根据询价Id
export function getPolicyById(queryId) {
  return request({
    url: '/policy/query/detail/'+queryId,
    method: 'post'
  })
}

//批量修改List状态
export function updateListStatus(rows) {
  return request({
    url: '/policy/query/updateList',
    method: 'post',
    data: rows
  });
}

// 查询已询价列表列表
export function listQuery(query) {
  return request({
    url: '/policy/query/list',
    method: 'get',
    params: query
  })
}

// 查询已询价列表详细
export function getQuery(qId) {
  return request({
    url: '/policy/query/' + qId,
    method: 'get'
  })
}

// 新增已询价列表
export function addQuery(data) {
  return request({
    url: '/policy/query',
    method: 'post',
    data: data
  })
}

// 修改已询价列表
export function updateQuery(data) {
  return request({
    url: '/policy/query',
    method: 'put',
    data: data
  })
}

// 删除已询价列表
export function delQuery(qId) {
  return request({
    url: '/policy/query/' + qId,
    method: 'delete'
  })
}

// 导出已询价列表
export function exportQuery(query) {
  return request({
    url: '/policy/query/export',
    method: 'get',
    params: query
  })
}
export function listFreeInquiry(query) {
  return request({
    url: '/policy/query/freeInquiry/list',
    method: 'get',
    params: query
  })
}
