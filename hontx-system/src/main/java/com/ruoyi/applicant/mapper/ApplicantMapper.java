package com.ruoyi.applicant.mapper;

import java.util.List;
import com.ruoyi.applicant.domain.Applicant;

/**
 * 保险关系人信息Mapper接口
 *
 * @author ruoyi
 * @date 2021-06-15
 */
public interface ApplicantMapper
{
    /**
     * 查询保险关系人信息
     *
     * @param id 保险关系人信息ID
     * @return 保险关系人信息
     */
    public Applicant selectApplicantById(Long id);

    /**
     * 查询保险关系人信息列表
     *
     * @param applicant 保险关系人信息
     * @return 保险关系人信息集合
     */
    public List<Applicant> selectApplicantList(Applicant applicant);

    /**
     * 新增保险关系人信息
     *
     * @param applicant 保险关系人信息
     * @return 结果
     */
    public int insertApplicant(Applicant applicant);

    /**
     * 修改保险关系人信息
     *
     * @param applicant 保险关系人信息
     * @return 结果
     */
    public int updateApplicant(Applicant applicant);

    /**
     * 删除保险关系人信息
     *
     * @param id 保险关系人信息ID
     * @return 结果
     */
    public int deleteApplicantById(Long id);

    /**
     * 批量删除保险关系人信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteApplicantByIds(Long[] ids);

    /**
     * 根据车架号查找车辆关系人信息
     * @param frameNo
     * @return
     */
    List<Applicant> findByFrameNo(String frameNo);
}
