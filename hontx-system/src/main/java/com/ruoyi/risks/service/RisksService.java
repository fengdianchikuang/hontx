package com.ruoyi.risks.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.risks.mapper.RisksMapper;
import com.ruoyi.risks.domain.Risks;

/**
 * 保险CodeService业务层处理
 *
 * @author ruoyi
 * @date 2021-06-15
 */
@Service
public class RisksService
{
    @Autowired
    private RisksMapper risksMapper;

    /**
     * 查询保险Code
     *
     * @param id 保险CodeID
     * @return 保险Code
     */

    public Risks selectRisksById(Long id)
    {
        return risksMapper.selectRisksById(id);
    }

    /**
     * 查询保险Code列表
     *
     * @param risks 保险Code
     * @return 保险Code
     */

    public List<Risks> selectRisksList(Risks risks)
    {
        return risksMapper.selectRisksList(risks);
    }

    /**
     * 新增保险Code
     *
     * @param risks 保险Code
     * @return 结果
     */

    public int insertRisks(Risks risks)
    {
        return risksMapper.insertRisks(risks);
    }

    /**
     * 修改保险Code
     *
     * @param risks 保险Code
     * @return 结果
     */

    public int updateRisks(Risks risks)
    {
        return risksMapper.updateRisks(risks);
    }

    /**
     * 批量删除保险Code
     *
     * @param ids 需要删除的保险CodeID
     * @return 结果
     */

    public int deleteRisksByIds(Long[] ids)
    {
        return risksMapper.deleteRisksByIds(ids);
    }

    /**
     * 删除保险Code信息
     *
     * @param id 保险CodeID
     * @return 结果
     */
    public int deleteRisksById(Long id)
    {
        return risksMapper.deleteRisksById(id);
    }

    /**
     * 根据车架查找车辆保险项
     * @param rackNo
     * @return
     */
    public List<Risks> findByFrameNo(String rackNo) {
        return risksMapper.findByFrameNo(rackNo);
    }

    /**
     * 根据车架号删除旧的保险项
     * @param frameNo
     * @return
     */
    public int deleteByframeNo(String frameNo) {
        return risksMapper.deleteByFrameNo(frameNo);
    }

    /**
     * 根据车架，公司Id查找
     * @param frameNo
     * @param companyId
     * @return
     */
    public List<Risks> findByFrameNoAndCompanyId(String frameNo, Integer companyId) {
        return risksMapper.findByFrameNoAndCompanyId(frameNo,companyId);
    }
}
