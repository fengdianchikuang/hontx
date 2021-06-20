package com.ruoyi.applicant.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.applicant.mapper.ApplicantMapper;
import com.ruoyi.applicant.domain.Applicant;


/**
 * 保险关系人信息Service业务层处理
 *
 * @author ruoyi
 * @date 2021-06-15
 */
@Service
public class ApplicantService
{
    @Autowired
    private ApplicantMapper applicantMapper;

    /**
     * 查询保险关系人信息
     *
     * @param id 保险关系人信息ID
     * @return 保险关系人信息
     */

    public Applicant selectApplicantById(Long id)
    {
        return applicantMapper.selectApplicantById(id);
    }

    /**
     * 查询保险关系人信息列表
     *
     * @param applicant 保险关系人信息
     * @return 保险关系人信息
     */

    public List<Applicant> selectApplicantList(Applicant applicant)
    {
        return applicantMapper.selectApplicantList(applicant);
    }

    /**
     * 新增保险关系人信息
     *
     * @param applicant 保险关系人信息
     * @return 结果
     */

    public int insertApplicant(Applicant applicant)
    {
        return applicantMapper.insertApplicant(applicant);
    }

    /**
     * 修改保险关系人信息
     *
     * @param applicant 保险关系人信息
     * @return 结果
     */

    public int updateApplicant(Applicant applicant)
    {
        return applicantMapper.updateApplicant(applicant);
    }

    /**
     * 批量删除保险关系人信息
     *
     * @param ids 需要删除的保险关系人信息ID
     * @return 结果
     */

    public int deleteApplicantByIds(Long[] ids)
    {
        return applicantMapper.deleteApplicantByIds(ids);
    }

    /**
     * 删除保险关系人信息信息
     *
     * @param id 保险关系人信息ID
     * @return 结果
     */

    public int deleteApplicantById(Long id)
    {
        return applicantMapper.deleteApplicantById(id);
    }

    /**
     * 根据车架号查找关系人信息
     * @param frameNo
     * @return
     */
    public List<Applicant> findByFrameNo(String frameNo) {
        return applicantMapper.findByFrameNo(frameNo);
    }
}
