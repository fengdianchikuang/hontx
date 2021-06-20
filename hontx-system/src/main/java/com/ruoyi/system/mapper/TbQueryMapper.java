package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.QueryInsrnc;
import com.ruoyi.system.domain.TbQuery;
import com.ruoyi.system.domain.req.QueryReq;
import com.ruoyi.system.domain.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 已询价列表Mapper接口
 *
 * @author ruoyi
 * @date 2021-03-30
 */
public interface TbQueryMapper
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
     * 删除已询价列表
     *
     * @param qId 已询价列表ID
     * @return 结果
     */
    public int deleteTbQueryById(Long qId);

    /**
     * 批量删除已询价列表
     *
     * @param qIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbQueryByIds(Long[] qIds);

    QueryDetailsVo queryByQueryId(@Param("queryId") String queryId);

    /**
     * 查询地区码
     * @param code
     * @param name
     * @return
     */
    List<AreaCodeVo> queryRegion(@Param("code") Integer code, @Param("type") Integer type);

    /**
     * 新增保险子项
     * @param queryInsrnc
     * @return
     */
    int insertInsrnc(QueryInsrnc queryInsrnc);

    /**
     * 修改保险子项
     * @param queryInsrnc
     * @return
     */
    int updateInsrnc(QueryInsrnc queryInsrnc);

    /**
     * 批量修改query状态
     * @param list
     * @return
     */
    int updateList(List<TbQuery> list);

    /**
     * 查询自由询价列表
     * @param query
     * @return
     */
    List<QueryFreeVo> listFreeQuery(QueryReq query);

    /**
     * 根据订单Id查找
     * @param orderId
     * @return
     */
    TbQuery findByQueryId(String orderId);
}
