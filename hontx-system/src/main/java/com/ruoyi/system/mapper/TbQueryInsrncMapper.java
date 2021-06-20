package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.TbQueryInsrnc;

/**
 * insrncMapper接口
 *
 * @author ruoyi
 * @date 2021-04-07
 */
public interface TbQueryInsrncMapper
{
    /**
     * 查询insrnc
     *
     * @param qId insrncID
     * @return insrnc
     */
    public TbQueryInsrnc selectTbQueryInsrncById(Long qId);

    /**
     * 查询insrnc列表
     *
     * @param tbQueryInsrnc insrnc
     * @return insrnc集合
     */
    public List<TbQueryInsrnc> selectTbQueryInsrncList(TbQueryInsrnc tbQueryInsrnc);

    /**
     * 新增insrnc
     *
     * @param tbQueryInsrnc insrnc
     * @return 结果
     */
    public int insertTbQueryInsrnc(TbQueryInsrnc tbQueryInsrnc);

    /**
     * 修改insrnc
     *
     * @param tbQueryInsrnc insrnc
     * @return 结果
     */
    public int updateTbQueryInsrnc(TbQueryInsrnc tbQueryInsrnc);

    /**
     * 删除insrnc
     *
     * @param qId insrncID
     * @return 结果
     */
    public int deleteTbQueryInsrncById(Long qId);

    /**
     * 批量删除insrnc
     *
     * @param qIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbQueryInsrncByIds(Long[] qIds);


    /**
     * 根据询价Id删除保险项
     * @param queryId
     */
    int deleteByQueryId(String queryId);

    /**
     * 根据询价Id 查找保险项
     * @param queryId
     * @return
     */
    List<TbQueryInsrnc> selectByQueryId(String queryId);
}
