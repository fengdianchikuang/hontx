package com.ruoyi.org.service.impl;

import java.util.List;

import com.ruoyi.org.domain.vo.OrganizationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.org.mapper.TbOrganizationMapper;
import com.ruoyi.org.domain.TbOrganization;
import com.ruoyi.org.service.ITbOrganizationService;

/**
 * 机构管理Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-20
 */
@Service
public class TbOrganizationServiceImpl implements ITbOrganizationService
{
    @Autowired
    private TbOrganizationMapper tbOrganizationMapper;

    /**
     * 查询机构管理
     *
     * @param oId 机构管理ID
     * @return 机构管理
     */
    @Override
    public TbOrganization selectTbOrganizationById(Long oId)
    {
        return tbOrganizationMapper.selectTbOrganizationById(oId);
    }

    /**
     * 查询机构管理列表
     *
     * @param tbOrganization 机构管理
     * @return 机构管理
     */
    @Override
    public List<OrganizationVo> selectTbOrganizationList(OrganizationVo tbOrganization)
    {
        return tbOrganizationMapper.selectTbOrganizationList(tbOrganization);
    }

    /**
     * 新增机构管理
     *
     * @param tbOrganization 机构管理
     * @return 结果
     */
    @Override
    public int insertTbOrganization(TbOrganization tbOrganization)
    {
        return tbOrganizationMapper.insertTbOrganization(tbOrganization);
    }

    /**
     * 修改机构管理
     *
     * @param tbOrganization 机构管理
     * @return 结果
     */
    @Override
    public int updateTbOrganization(TbOrganization tbOrganization)
    {
        return tbOrganizationMapper.updateTbOrganization(tbOrganization);
    }

    /**
     * 批量删除机构管理
     *
     * @param oIds 需要删除的机构管理ID
     * @return 结果
     */
    @Override
    public int deleteTbOrganizationByIds(Long[] oIds)
    {
        return tbOrganizationMapper.deleteTbOrganizationByIds(oIds);
    }

    /**
     * 删除机构管理信息
     *
     * @param oId 机构管理ID
     * @return 结果
     */
    @Override
    public int deleteTbOrganizationById(Long oId)
    {
        return tbOrganizationMapper.deleteTbOrganizationById(oId);
    }

    @Override
    public List<OrganizationVo> getParent() {
        return tbOrganizationMapper.getParent();
    }

    /**
     * 根据公司id查找
     * @param orgId
     * @return
     */
    @Override
    public TbOrganization findByCompanyId(Integer orgId) {
        return tbOrganizationMapper.findByCompanyId(orgId);
    }
}
