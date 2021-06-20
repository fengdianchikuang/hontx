package com.ruoyi.risks.mapper;

import java.util.List;
import com.ruoyi.risks.domain.Risks;
import org.apache.ibatis.annotations.Param;

/**
 * 保险CodeMapper接口
 *
 * @author ruoyi
 * @date 2021-06-15
 */
public interface RisksMapper
{
    /**
     * 查询保险Code
     *
     * @param id 保险CodeID
     * @return 保险Code
     */
    public Risks selectRisksById(Long id);

    /**
     * 查询保险Code列表
     *
     * @param risks 保险Code
     * @return 保险Code集合
     */
    public List<Risks> selectRisksList(Risks risks);

    /**
     * 新增保险Code
     *
     * @param risks 保险Code
     * @return 结果
     */
    public int insertRisks(Risks risks);

    /**
     * 修改保险Code
     *
     * @param risks 保险Code
     * @return 结果
     */
    public int updateRisks(Risks risks);

    /**
     * 删除保险Code
     *
     * @param id 保险CodeID
     * @return 结果
     */
    public int deleteRisksById(Long id);

    /**
     * 批量删除保险Code
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRisksByIds(Long[] ids);

    /**
     * 根据车架查找保险项
     * @param frameNo
     * @return
     */
    List<Risks> findByFrameNo(String frameNo);

    /**
     * 根据车架号删除旧的保险项
     * @param frameNo
     * @return
     */
    int deleteByFrameNo(String frameNo);

    /**
     * 根据车架，公司Id查找
     * @param frameNo
     * @param companyId
     * @return
     */
    List<Risks> findByFrameNoAndCompanyId(@Param("frameNo") String frameNo, @Param("companyId") Integer companyId);

}
