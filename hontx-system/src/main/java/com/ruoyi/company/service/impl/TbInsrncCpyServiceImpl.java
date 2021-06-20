package com.ruoyi.company.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.company.mapper.TbInsrncCpyMapper;
import com.ruoyi.company.domain.TbInsrncCpy;
import com.ruoyi.company.service.ITbInsrncCpyService;

/**
 * companyService业务层处理
 *
 * @author ruoyi
 * @date 2021-04-12
 */
@Service
public class TbInsrncCpyServiceImpl implements ITbInsrncCpyService
{
    @Autowired
    private TbInsrncCpyMapper tbInsrncCpyMapper;

    /**
     * 查询company
     *
     * @param iId companyID
     * @return company
     */
    @Override
    public TbInsrncCpy selectTbInsrncCpyById(Long iId)
    {
        return tbInsrncCpyMapper.selectTbInsrncCpyById(iId);
    }

    /**
     * 查询company列表
     *
     * @param tbInsrncCpy company
     * @return company
     */
    @Override
    public List<TbInsrncCpy> selectTbInsrncCpyList(TbInsrncCpy tbInsrncCpy)
    {
        return tbInsrncCpyMapper.selectTbInsrncCpyList(tbInsrncCpy);
    }

    /**
     * 新增company
     *
     * @param tbInsrncCpy company
     * @return 结果
     */
    @Override
    public int insertTbInsrncCpy(TbInsrncCpy tbInsrncCpy)
    {
        return tbInsrncCpyMapper.insertTbInsrncCpy(tbInsrncCpy);
    }

    /**
     * 修改company
     *
     * @param tbInsrncCpy company
     * @return 结果
     */
    @Override
    public int updateTbInsrncCpy(TbInsrncCpy tbInsrncCpy)
    {
        return tbInsrncCpyMapper.updateTbInsrncCpy(tbInsrncCpy);
    }

    /**
     * 批量删除company
     *
     * @param iIds 需要删除的companyID
     * @return 结果
     */
    @Override
    public int deleteTbInsrncCpyByIds(Long[] iIds)
    {
        return tbInsrncCpyMapper.deleteTbInsrncCpyByIds(iIds);
    }

    /**
     * 删除company信息
     *
     * @param iId companyID
     * @return 结果
     */
    @Override
    public int deleteTbInsrncCpyById(Long iId)
    {
        return tbInsrncCpyMapper.deleteTbInsrncCpyById(iId);
    }

    /**
     * 根据companyId查找
     * @param companyId
     * @return
     */
    @Override
    public TbInsrncCpy findByCompanyId(Integer companyId) {
        return tbInsrncCpyMapper.findByCompanyId(companyId);
    }
}
