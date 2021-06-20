package com.ruoyi.InsrncOrder.service;

import com.ruoyi.InsrncOrder.domain.vo.InsrncOrderVo;
import com.ruoyi.InsrncOrder.mapper.InsrncOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsrncOrderService {
    @Autowired
    private InsrncOrderMapper insrncOrderMapper;

    /**
     * 根据车架，保险公司Id，车主Id查找
      * @param frameNo
     * @param companyId
     * @param ownerId
     * @return
     */
    public  InsrncOrderVo findByFrameNoAndCompanyIdAndOwnerId(String frameNo, Integer companyId, Long ownerId) {

        return insrncOrderMapper.findByFrameNoAndCompanyIdAndOwnerId(frameNo,companyId,ownerId);
    }

    /**
     * 保存保险订单
      * @param insrncOrderVo
     * @return
     */
    public int insertTbInsrncOrder(InsrncOrderVo insrncOrderVo){

        return insrncOrderMapper.insertTbInsrncOrder(insrncOrderVo);
    }

    /**
     * 根据id修改
     * @param insrncOrderVo
     * @return
     */
    public int updateTbInsrncOrder(InsrncOrderVo insrncOrderVo){
        return insrncOrderMapper.updateTbInsrncOrder(insrncOrderVo);
    }

    /**
     * 根据orderId查找
     * @param orderId
     * @return
     */
    public InsrncOrderVo findByOrderId(String orderId) {
        return insrncOrderMapper.findByOrderId(orderId);
    }


    /**
     * 根据车架号删除所有订单状态不是8的数据
     * @param frameNo
     * @param status
     * @return
     */
    public int deleteByFrameNoAndNotStatus(String frameNo, int status) {
      return insrncOrderMapper.deleteByFrameNoAndNotStatus(frameNo,status);
    }
}
