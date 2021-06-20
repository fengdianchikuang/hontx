package com.ruoyi.lifeholder.mapper;

import java.util.List;
import com.ruoyi.lifeholder.domain.TbLifeHolder;
import com.ruoyi.lifeholder.domain.vo.LifeHolderVo;
import org.apache.ibatis.annotations.Param;

/**
 * 寿险客户Mapper接口
 *
 * @author ruoyi
 * @date 2021-05-08
 */
public interface TbLifeHolderMapper
{
    /**
     * 查询寿险客户
     *
     * @param id 寿险客户ID
     * @return 寿险客户
     */
    public TbLifeHolder selectTbLifeHolderById(Long id);

    /**
     * 查询寿险客户列表
     *
     * @param tbLifeHolder 寿险客户
     * @return 寿险客户集合
     */
    public List<LifeHolderVo> selectTbLifeHolderList(LifeHolderVo tbLifeHolder);

    /**
     * 新增寿险客户
     *
     * @param tbLifeHolder 寿险客户
     * @return 结果
     */
    public int insertTbLifeHolder(TbLifeHolder tbLifeHolder);

    /**
     * 修改寿险客户
     *
     * @param tbLifeHolder 寿险客户
     * @return 结果
     */
    public int updateTbLifeHolder(TbLifeHolder tbLifeHolder);

    /**
     * 删除寿险客户
     *
     * @param id 寿险客户ID
     * @return 结果
     */
    public int deleteTbLifeHolderById(Long id);

    /**
     * 批量删除寿险客户
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbLifeHolderByIds(Long[] ids);


    TbLifeHolder selectTbLifeHolderByHolderIdNo(String holderidno);
    /**
     * 根据证件号 和 代理人Id
     * @param holderidno
     * @return
     */
    TbLifeHolder selectTbLifeHolderByHolderIdNoAndAgentId(@Param("holderidno") String holderidno, @Param("agentid") String agentid);
}
