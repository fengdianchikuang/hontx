package com.ruoyi.platform.service;

import java.util.List;

import com.ruoyi.platform.domain.Platform;
import com.ruoyi.platform.mapper.PlatformMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 平台信息Service业务层处理
 *
 * @author ruoyi
 * @date 2021-06-15
 */
@Service
public class PlatformService
{
    @Autowired
    private PlatformMapper platformMapper;

    /**
     * 查询平台信息
     *
     * @param id 平台信息ID
     * @return 平台信息
     */

    public Platform selectPlatformById(Long id)
    {
        return platformMapper.selectPlatformById(id);
    }

    /**
     * 查询平台信息列表
     *
     * @param platform 平台信息
     * @return 平台信息
     */

    public List<Platform> selectPlatformList(Platform platform)
    {
        return platformMapper.selectPlatformList(platform);
    }

    /**
     * 新增平台信息
     *
     * @param platform 平台信息
     * @return 结果
     */

    public int insertPlatform(Platform platform)
    {
        return platformMapper.insertPlatform(platform);
    }

    /**
     * 修改平台信息
     *
     * @param platform 平台信息
     * @return 结果
     */

    public int updatePlatform(Platform platform)
    {
        return platformMapper.updatePlatform(platform);
    }

    /**
     * 批量删除平台信息
     *
     * @param ids 需要删除的平台信息ID
     * @return 结果
     */

    public int deletePlatformByIds(Long[] ids)
    {
        return platformMapper.deletePlatformByIds(ids);
    }

    /**
     * 删除平台信息信息
     *
     * @param id 平台信息ID
     * @return 结果
     */

    public int deletePlatformById(Long id)
    {
        return platformMapper.deletePlatformById(id);
    }

    /**
     * 根据车架查找平台信息
     * @param frameNo
     * @return
     */
    public Platform findByFrameNo(String frameNo) {
        return platformMapper.findByFrameNo(frameNo);
    }
}
