package com.ruoyi.insureInfo.mapper;


import com.ruoyi.insureInfo.domain.InsureInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 核保信息Mapper接口
 *
 * @author ruoyi
 * @date 2021-06-16
 */
public interface InsureInfoMapper
{
    /**
     * 查询核保信息
     *
     * @param id 核保信息ID
     * @return 核保信息
     */
    public InsureInfo selectInsureInfoById(Long id);

    /**
     * 查询核保信息列表
     *
     * @param insureInfo 核保信息
     * @return 核保信息集合
     */
    public List<InsureInfo> selectInsureInfoList(InsureInfo insureInfo);

    /**
     * 新增核保信息
     *
     * @param insureInfo 核保信息
     * @return 结果
     */
    public int insertInsureInfo(InsureInfo insureInfo);

    /**
     * 修改核保信息
     *
     * @param insureInfo 核保信息
     * @return 结果
     */
    public int updateInsureInfo(InsureInfo insureInfo);

    /**
     * 删除核保信息
     *
     * @param id 核保信息ID
     * @return 结果
     */
    public int deleteInsureInfoById(Long id);

    /**
     * 批量删除核保信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteInsureInfoByIds(Long[] ids);

    /**
     * 根据订单Id查找核保信息
     * @param orderId
     * @return
     */
    InsureInfo findByOrderId(String orderId);


    /**
     * 根据车架和保单状态删除
     * @param frameNo
     * @param status
     */
    int deleteByFrameNoAndNotStatus(@Param("frameNo") String frameNo, @Param("status") int status);
}
