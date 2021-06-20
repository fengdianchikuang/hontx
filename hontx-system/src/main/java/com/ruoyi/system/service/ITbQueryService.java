package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.QueryInsrnc;
import com.ruoyi.system.domain.TbQuery;
import com.ruoyi.system.domain.req.QueryReq;
import com.ruoyi.system.domain.req.RegionReq;
import com.ruoyi.system.domain.vo.*;

/**
 * 已询价列表Service接口
 *
 * @author ruoyi
 * @date 2021-03-30
 */
public interface ITbQueryService
{
    /**
     * 查询已询价列表
     *
     * @param qId 已询价列表ID
     * @return 已询价列表
     */
    public TbQuery selectTbQueryById(Long qId);

    /**
     * 查询已询价列表列表
     *
     * @param tbQuery 已询价列表
     * @return 已询价列表集合
     */
    public List<QueryVo> selectTbQueryList(TbQuery tbQuery);

    /**
     * 新增已询价列表
     *
     * @param tbQuery 已询价列表
     * @return 结果
     */
    public int insertTbQuery(TbQuery tbQuery);

    /**
     * 修改已询价列表
     *
     * @param tbQuery 已询价列表
     * @return 结果
     */
    public int updateTbQuery(TbQuery tbQuery);

    /**
     * 批量删除已询价列表
     *
     * @param qIds 需要删除的已询价列表ID
     * @return 结果
     */
    public int deleteTbQueryByIds(Long[] qIds);

    /**
     * 删除已询价列表信息
     *
     * @param qId 已询价列表ID
     * @return 结果
     */
    public int deleteTbQueryById(Long qId);

    /**
     * 根据queryId查询详情
     * @param queryId
     * @return
     */
    QueryDetailsVo queryByQueryId(String queryId);

    /**
     * 查询地区码
     * @param regionReq code 代号 type类型
     * @return
     */
    List<AreaCodeVo> queryRegion(RegionReq regionReq);

    int saveInsrnc(QueryInsrnc queryInsrnc);

    /**
     * 批量修改询价状态
     * @param list
     * @return
     */
    int updateList(List<TbQuery> list);

    /**
     * 查找开始询价的工单
     * @return
     */
    QueryDetailsVo findStartQuery(boolean preFlag);

    /**
     * 查找自由询价列表
     * @param tbQuery
     * @return
     */
    List<QueryFreeVo> listFreeQuery(QueryReq tbQuery);

    /**
     * 根据订单id查找
     * @param orderId
     * @return
     */
    TbQuery findByQueryId(String orderId);

}
