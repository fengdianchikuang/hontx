import request from '@/utils/request'

// 查询提现列表列表
export function listWithdrawal(query) {
  return request({
    url: '/ifefinance/details/withdrawalList',
    method: 'get',
    params: query
  })
}


// 修改提现列表
export function updateWithdrawal(id) {
  return request({
    url: '/ifefinance/details/change/' + id,
    method: 'put'

  })
}

