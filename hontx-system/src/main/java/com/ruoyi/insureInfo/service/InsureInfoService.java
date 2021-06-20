package com.ruoyi.insureInfo.service;


import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.insureInfo.domain.InsureInfo;
import com.ruoyi.insureInfo.mapper.InsureInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 核保信息Service业务层处理
 *
 * @author ruoyi
 * @date 2021-06-16
 */
@Service
public class InsureInfoService
{
    @Autowired
    private InsureInfoMapper insureInfoMapper;

    /**
     * 查询核保信息
     *
     * @param id 核保信息ID
     * @return 核保信息
     */

    public InsureInfo selectInsureInfoById(Long id)
    {
        return insureInfoMapper.selectInsureInfoById(id);
    }

    /**
     * 查询核保信息列表
     *
     * @param insureInfo 核保信息
     * @return 核保信息
     */

    public List<InsureInfo> selectInsureInfoList(InsureInfo insureInfo)
    {
        return insureInfoMapper.selectInsureInfoList(insureInfo);
    }

    /**
     * 新增核保信息
     *
     * @param insureInfo 核保信息
     * @return 结果
     */

    public int insertInsureInfo(InsureInfo insureInfo)
    {
//        insureInfo.setCreateTime(DateUtils.getNowDate());
        return insureInfoMapper.insertInsureInfo(insureInfo);
    }

    /**
     * 修改核保信息
     *
     * @param insureInfo 核保信息
     * @return 结果
     */

    public int updateInsureInfo(InsureInfo insureInfo)
    {
        return insureInfoMapper.updateInsureInfo(insureInfo);
    }

    /**
     * 批量删除核保信息
     *
     * @param ids 需要删除的核保信息ID
     * @return 结果
     */

    public int deleteInsureInfoByIds(Long[] ids)
    {
        return insureInfoMapper.deleteInsureInfoByIds(ids);
    }

    /**
     * 删除核保信息信息
     *
     * @param id 核保信息ID
     * @return 结果
     */

    public int deleteInsureInfoById(Long id)
    {
        return insureInfoMapper.deleteInsureInfoById(id);
    }

    /**
     * 根据订单Id查找核保信息
     * @param orderId
     * @return
     */
    public InsureInfo findByOrderId(String orderId) {
        return insureInfoMapper.findByOrderId(orderId);
    }

    /**
     * 根据车架和保单状态删除
     * @param frameNo
     * @param status
     */
    public int deleteByFrameNoAndNotStatus(String frameNo, int status) {
        return insureInfoMapper.deleteByFrameNoAndNotStatus(frameNo,status);
    }
}
