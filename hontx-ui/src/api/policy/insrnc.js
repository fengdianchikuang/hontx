import request from '@/utils/request'

//根据id,删除保险子项
export function deleteQueryInsrnc(ids) {
  return request({
    url: '/policy/insrnc/'+ ids,
    method: 'delete'
  })
}

// 修改保险项
export function updateInsrnc(insrnc) {
  return request({
    url: '/policy/insrnc',
    method: 'put',
    data: insrnc
  })
}

// 新增保险项
export function addInsrnc(insrnc) {
  return request({
    url: '/policy/insrnc',
    method: 'post',
    data: insrnc
  })
}
