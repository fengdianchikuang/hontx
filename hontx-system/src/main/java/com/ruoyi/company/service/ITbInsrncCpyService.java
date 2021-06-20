package com.ruoyi.company.service;

import java.util.List;
import com.ruoyi.company.domain.TbInsrncCpy;

/**
 * companyService接口
 *
 * @author ruoyi
 * @date 2021-04-12
 */
public interface ITbInsrncCpyService
{
    /**
     * 查询company
     *
     * @param iId companyID
     * @return company
     */
    public TbInsrncCpy selectTbInsrncCpyById(Long iId);

    /**
     * 查询company列表
     *
     * @param tbInsrncCpy company
     * @return company集合
     */
    public List<TbInsrncCpy> selectTbInsrncCpyList(TbInsrncCpy tbInsrncCpy);

    /**
     * 新增company
     *
     * @param tbInsrncCpy company
     * @return 结果
     */
    public int insertTbInsrncCpy(TbInsrncCpy tbInsrncCpy);

    /**
     * 修改company
     *
     * @param tbInsrncCpy company
     * @return 结果
     */
    public int updateTbInsrncCpy(TbInsrncCpy tbInsrncCpy);

    /**
     * 批量删除company
     *
     * @param iIds 需要删除的companyID
     * @return 结果
     */
    public int deleteTbInsrncCpyByIds(Long[] iIds);

    /**
     * 删除company信息
     *
     * @param iId companyID
     * @return 结果
     */
    public int deleteTbInsrncCpyById(Long iId);

    /**
     * 根据保险Id查找
     * @param companyId
     * @return
     */
    TbInsrncCpy findByCompanyId(Integer companyId);
}
