package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.TbQueryInsrnc;

/**
 * insrncService接口
 *
 * @author ruoyi
 * @date 2021-04-07
 */
public interface ITbQueryInsrncService
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
     * 批量删除insrnc
     *
     * @param qIds 需要删除的insrncID
     * @return 结果
     */
    public int deleteTbQueryInsrncByIds(Long[] qIds);

    /**
     * 删除insrnc信息
     *
     * @param qId insrncID
     * @return 结果
     */
    public int deleteTbQueryInsrncById(Long qId);

    /**
     * 删除询价Id相同的
     * @param orderId
     * @return
     */
    int deleteByQueryId(String orderId);
}
