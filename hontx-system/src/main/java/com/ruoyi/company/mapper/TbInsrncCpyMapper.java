package com.ruoyi.company.mapper;

import java.util.List;
import com.ruoyi.company.domain.TbInsrncCpy;

/**
 * companyMapper接口
 *
 * @author ruoyi
 * @date 2021-04-12
 */
public interface TbInsrncCpyMapper
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
     * 删除company
     *
     * @param iId companyID
     * @return 结果
     */
    public int deleteTbInsrncCpyById(Long iId);

    /**
     * 批量删除company
     *
     * @param iIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbInsrncCpyByIds(Long[] iIds);

    /**
     * 根据companyId查找
     * @param companyId
     * @return
     */
    TbInsrncCpy findByCompanyId(Integer companyId);
}
