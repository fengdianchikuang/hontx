package com.ruoyi.InsrncOrder.mapper;

import com.ruoyi.InsrncOrder.domain.vo.InsrncOrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InsrncOrderMapper {
    /**
     * 根据车架查找
     * @param frameNo
     * @return
     */
    List<InsrncOrderVo> findByFrameNo(String frameNo);

    /**
     * 根据车架保险公司Id，车主Id查找
     * @param frameNo
     * @param companyId
     * @param ownerId
     * @return
     */
    InsrncOrderVo findByFrameNoAndCompanyIdAndOwnerId(@Param("frameNo") String frameNo, @Param("companyId") Integer companyId, @Param("ownerId") Long ownerId);

    /**
     * 保存保险订单
     * @param insrncOrderVo
     * @return
     */
    int insertTbInsrncOrder(InsrncOrderVo insrncOrderVo);

    /**
     * 根据id修改
     * @param insrncOrderVo
     * @return
     */
    int updateTbInsrncOrder(InsrncOrderVo insrncOrderVo);

    /**
     * 根据订单Id查找
     * @param orderId
     * @return
     */
    InsrncOrderVo findByOrderId(String orderId);

    /**
     * 根据车架号删除所有订单状态不是8的数据
     * @param frameNo
     * @param status
     * @return
     */
    int deleteByFrameNoAndNotStatus(@Param("frameNo") String frameNo, @Param("status") int status);
}
