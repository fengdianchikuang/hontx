package com.ruoyi.org.mapper;

import java.util.List;
import com.ruoyi.org.domain.TbOrganization;
import com.ruoyi.org.domain.vo.OrganizationVo;

/**
 * 机构管理Mapper接口
 *
 * @author ruoyi
 * @date 2021-04-20
 */
public interface TbOrganizationMapper
{
    /**
     * 查询机构管理
     *
     * @param oId 机构管理ID
     * @return 机构管理
     */
    public TbOrganization selectTbOrganizationById(Long oId);

    /**
     * 查询机构管理列表
     *
     * @param tbOrganization 机构管理
     * @return 机构管理集合
     */
    public List<OrganizationVo> selectTbOrganizationList(TbOrganization tbOrganization);

    /**
     * 新增机构管理
     *
     * @param tbOrganization 机构管理
     * @return 结果
     */
    public int insertTbOrganization(TbOrganization tbOrganization);

    /**
     * 修改机构管理
     *
     * @param tbOrganization 机构管理
     * @return 结果
     */
    public int updateTbOrganization(TbOrganization tbOrganization);

    /**
     * 删除机构管理
     *
     * @param oId 机构管理ID
     * @return 结果
     */
    public int deleteTbOrganizationById(Long oId);

    /**
     * 批量删除机构管理
     *
     * @param oIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbOrganizationByIds(Long[] oIds);

    /**
     * 获取上级 type 00,01的机构
     * @return
     */
    List<OrganizationVo> getParent();

    /**
     * 根据公司id查找
     * @param orgId
     * @return
     */
    TbOrganization findByCompanyId(Integer orgId);
}
