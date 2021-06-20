package com.ruoyi.platform.mapper;

import java.util.List;
import com.ruoyi.platform.domain.Platform;

/**
 * 保司平台信息Mapper接口
 *
 * @author ruoyi
 * @date 2021-06-16
 */
public interface PlatformMapper
{
    /**
     * 查询保司平台信息
     *
     * @param id 保司平台信息ID
     * @return 保司平台信息
     */
    public Platform selectPlatformById(Long id);

    /**
     * 查询保司平台信息列表
     *
     * @param platform 保司平台信息
     * @return 保司平台信息集合
     */
    public List<Platform> selectPlatformList(Platform platform);

    /**
     * 新增保司平台信息
     *
     * @param platform 保司平台信息
     * @return 结果
     */
    public int insertPlatform(Platform platform);

    /**
     * 修改保司平台信息
     *
     * @param platform 保司平台信息
     * @return 结果
     */
    public int updatePlatform(Platform platform);

    /**
     * 删除保司平台信息
     *
     * @param id 保司平台信息ID
     * @return 结果
     */
    public int deletePlatformById(Long id);

    /**
     * 批量删除保司平台信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePlatformByIds(Long[] ids);

    /**
     * 根据车架查找平台信息
     * @param frameNo
     * @return
     */
    Platform findByFrameNo(String frameNo);
}
