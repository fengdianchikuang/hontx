package com.ruoyi.system.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.income.domain.InformEntity;
import com.ruoyi.income.service.ITbIncomeService;
import com.ruoyi.org.domain.TbOrganization;
import com.ruoyi.org.mapper.TbOrganizationMapper;
import com.ruoyi.system.domain.QueryInsrnc;
import com.ruoyi.system.domain.TbQueryInsrnc;
import com.ruoyi.system.domain.VehicleFile;
import com.ruoyi.system.domain.req.QueryReq;
import com.ruoyi.system.domain.req.RegionReq;
import com.ruoyi.system.domain.vo.*;
import com.ruoyi.system.mapper.TbQueryInsrncMapper;
import com.ruoyi.vehicle.domain.TbVehicle;
import com.ruoyi.vehicle.domain.VehicleInsrncEntity;
import com.ruoyi.vehicle.service.ITbVehicleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TbQueryMapper;
import com.ruoyi.system.domain.TbQuery;
import com.ruoyi.system.service.ITbQueryService;

/**
 * 已询价列表Service业务层处理
 *
 * @author ruoyi
 * @date 2021-03-30
 */
@Service
public class TbQueryServiceImpl implements ITbQueryService
{
    @Autowired
    private TbQueryMapper tbQueryMapper;

    @Autowired
    private TbOrganizationMapper tbOrganizationMapper;

    @Autowired
    private ITbIncomeService incomeService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ITbVehicleService vehicleService;

    @Autowired
    private TbQueryInsrncMapper tbQueryInsrncMapper;



    /**
     * 查询已询价列表
     *
     * @param qId 已询价列表ID
     * @return 已询价列表
     */
    @Override
    public TbQuery selectTbQueryById(Long qId)
    {
        return tbQueryMapper.selectTbQueryById(qId);
    }

    /**
     * 查询已询价列表列表
     *
     * @param tbQuery 已询价列表
     * @return 已询价列表
     */
    @Override
    public List<QueryVo> selectTbQueryList(TbQuery tbQuery)
    {
        return tbQueryMapper.selectTbQueryList(tbQuery);
    }

    /**
     * 新增已询价列表
     *
     * @param tbQuery 已询价列表
     * @return 结果
     */
    @Override
    public int insertTbQuery(TbQuery tbQuery)
    {
        return tbQueryMapper.insertTbQuery(tbQuery);
    }

    /**
     * 修改已询价列表
     *
     * @param tbQuery 已询价列表
     * @return 结果
     */
    @Override
    public int updateTbQuery(TbQuery tbQuery)
    {
        //status 05 已完成投保,计算佣金
        if(StringUtils.isNotEmpty(tbQuery.getStatus())){
            if("05".equals(tbQuery.getStatus())){
                //查找车辆
                List<TbVehicle> vehicles = vehicleService.findByVehicleNoAndRackNO(tbQuery.getVehicleNo(),tbQuery.getRackNo(),tbQuery.getFlag());
                TbVehicle vehicle = vehicles.get(0);
                if(tbQuery.getInsrncBgnTmJq() != null && !"".equals(tbQuery.getInsrncBgnTmJq())){
                    vehicle.setInsrncBgnTmJq(tbQuery.getInsrncBgnTmJq());
                    vehicle.setInsrncEndTmJq(tbQuery.getInsrncEndTmJq());
                }
                if(tbQuery.getInsrncBgnTm() != null && !"".equals(tbQuery.getInsrncBgnTm())){
                    vehicle.setInsrncBgnTmSy(tbQuery.getInsrncBgnTm());
                    vehicle.setInsrncBgnTmSy(tbQuery.getInsrncEndTm());
                }
                //更新车辆信息
                if(tbQuery.getRackNo() != null && !"".equals(tbQuery.getRackNo())){

                    vehicleService.updateTbVehicle(vehicle);
                }
                tbQueryMapper.updateTbQuery(tbQuery);
                //算收益
                incomeService.calculationIncome(tbQuery);
                //计算积分
                incomeService.insertIntegral(tbQuery);

                //查找询价Id
                TbQuery newQuery = tbQueryMapper.selectTbQueryById(tbQuery.getqId());


                //删除旧数据
                //查找旧数据
                List<QueryVo> queryVos = tbQueryMapper.selectTbQueryList(tbQuery);

                //查找保险项
                List<TbQueryInsrnc> insrncList = tbQueryInsrncMapper.selectByQueryId(tbQuery.getQueryId());
                vehicleService.deleteByVehicleNoAndRackNo(tbQuery.getVehicleNo(),tbQuery.getRackNo());
                for (TbQueryInsrnc qie : insrncList) {
                    VehicleInsrncEntity vehicleInsrncEntity = new VehicleInsrncEntity();
                    vehicleInsrncEntity.setVehicleNo(tbQuery.getVehicleNo());
                    vehicleInsrncEntity.setRackNo(tbQuery.getRackNo());
                    vehicleInsrncEntity.setInsrncCode(qie.getInsrncCode());
                    vehicleInsrncEntity.setGlassType(qie.getGlassType());
                    vehicleInsrncEntity.setAmount(qie.getAmount());
                    vehicleInsrncEntity.setFranchiseFlag(qie.getFranchiseFlag());
                    vehicleInsrncEntity.setDeductPremium(qie.getDeductPremium());
                    vehicleInsrncEntity.setNonDeductPremium(qie.getNonDeductPremium());
                    vehicleInsrncEntity.setBeforePremium(qie.getBeforePremium());
                    vehicleInsrncEntity.setRate(qie.getRate());
                    vehicleInsrncEntity.setPremium(qie.getPremium());
                    vehicleInsrncEntity.setQueryId(qie.getQueryId());
                    vehicleService.insertVehicleInsrnc(vehicleInsrncEntity);
                }

                String ticker = "订单处理结果";
                String title = "保单投保完成！";
                String text = "您有保单已完成投保，请查看！";

                //通知记录流水
                InformEntity informEntity = new InformEntity();
                informEntity.setAegntId(tbQuery.getAgentId());
                informEntity.setTitle(title);
                informEntity.setText(text);
                informEntity.setDeviceType("00");
                return incomeService.insertInform(informEntity);


//                if(queryVos != null && !queryVos.isEmpty()){
//                    List<Long> qIdList = queryVos.stream().map(o -> o.getqId()).collect(Collectors.toList());
//                    Long[] ids = qIdList.toArray(new Long[queryVos.size()]);
//                    tbQueryMapper.deleteTbQueryByIds(ids);
//                    //删除保险项
//                    String queryId = queryVos.get(0).getQueryId();
//                    tbQueryInsrncMapper.deleteByQueryId(queryId);
//                }



            }

        }
       return  tbQueryMapper.updateTbQuery(tbQuery);

    }

    /**
     * 批量删除已询价列表
     *
     * @param qIds 需要删除的已询价列表ID
     * @return 结果
     */
    @Override
    public int deleteTbQueryByIds(Long[] qIds)
    {
        return tbQueryMapper.deleteTbQueryByIds(qIds);
    }

    /**
     * 删除已询价列表信息
     *
     * @param qId 已询价列表ID
     * @return 结果
     */
    @Override
    public int deleteTbQueryById(Long qId)
    {
        return tbQueryMapper.deleteTbQueryById(qId);
    }

    /**
     * 查询询价详情
     * @param queryId
     * @return
     */
    @Override
    public QueryDetailsVo queryByQueryId(String queryId) {
        return tbQueryMapper.queryByQueryId(queryId);
    }

    /**
     * 查询地区码
     * @param regionReq code 代号 type类型
     * @return
     */
    @Override
    public List<AreaCodeVo> queryRegion(RegionReq regionReq) {
        List<AreaCodeVo> areaCodeVos = tbQueryMapper.queryRegion(regionReq.getCode(), regionReq.getType());

        return areaCodeVos;
    }

    @Override
    public int saveInsrnc(QueryInsrnc queryInsrnc) {
        //新增
        if(queryInsrnc.getqId() == null || queryInsrnc.getqId() == 0){
            return tbQueryMapper.insertInsrnc(queryInsrnc);
        //修改
        }else {
            return tbQueryMapper.updateInsrnc(queryInsrnc);
        }

    }

    @Override
    public int updateList(List<TbQuery> list) {
        int row = tbQueryMapper.updateList(list);
        return 0;
    }

    /**
     * 查找开始询价的工单
     * @return
     */
    @Override
    public QueryDetailsVo findStartQuery(boolean preFlag) {
        /**
         * 核心询价逻辑在此
         * 1,该客服正在询价中的工单(之前未完成操作的)
         * 2,排序第一个service为空的返回
         * 3，serviceId为自己的返回
         * 4,serviceId不为自己的且不在线的返回
         */
        TbQuery tbQuery = new TbQuery();
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser user = loginUser.getUser();
        if(user != null){
            Long orgId = user.getOrgId();
            TbOrganization org = tbOrganizationMapper.selectTbOrganizationById(orgId);
            if(org != null){
                if(!"00".equals(org.getType())){
                    tbQuery.setOrgId(orgId + "");
                }
            }
        }
        //按人工标识查询
        tbQuery.setFlag("1");
        tbQuery.setStatus("01");
        String currServiceId = user.getUserId()+"";
        tbQuery.setServiceId(currServiceId);
        List<QueryVo> query01 = tbQueryMapper.selectTbQueryList(tbQuery);
        QueryVo queryVo = new QueryVo();
        boolean findFlag = false;
        if(query01 != null && query01.size() > 0){
             queryVo = query01.get(0);
             findFlag = true;
        }else {
            tbQuery.setStatus("00");
            tbQuery.setServiceId("");
            List<QueryVo> query00s = tbQueryMapper.selectTbQueryList(tbQuery);
            for (QueryVo query00 : query00s) {
                String serviceId = query00.getServiceId();
                if(serviceId == null || "".equals(serviceId) || user.getUserId().equals(serviceId)){
                    queryVo = query00;
                    findFlag = true;
                    break;
                }
                //检查在线情况
                Collection<String> keys = redisCache.keys(Constants.LOGIN_TOKEN_KEY + "*");
                boolean onlineFlag = false;
                for (String key : keys) {
                    LoginUser sysUser = redisCache.getCacheObject(key);
                    SysUser user1 = sysUser.getUser();
                    //用户不在线
                    if(user1 == null){
                        continue;
                    }
                    String userId = String.valueOf(user1.getUserId());
                    //有客服在线，跳过
                    if(userId.equals(query00.getServiceId())){
                        onlineFlag = true;
                        continue;
                    }
                }

                if(!onlineFlag){
                    queryVo = query00;
                    findFlag = true;
                }
            }
            //修改当前修改的状态为询价中
            //待询价(00) --> 询价中(01) --> 待支付(02) --> 待核保(03) --> 核保中(04) -->已投保
            TbQuery tbQuery1 = tbQueryMapper.selectTbQueryById(queryVo.getqId());
            if(tbQuery1 != null){
                tbQuery1.setStatus("01");
                tbQuery1.setServiceId(user.getUserId()+"");
                if(!preFlag){
                    int i = tbQueryMapper.updateTbQuery(tbQuery1);
                }
            }

        }
        //查找详情
        if(findFlag){
            QueryDetailsVo queryDetailsVo = tbQueryMapper.queryByQueryId(queryVo.getQueryId());
            return queryDetailsVo;
        }
        return null;
    }

    /**
     * 查找自由询价列表
     * @param tbQuery
     * @return
     */
    @Override
    public List<QueryFreeVo> listFreeQuery(QueryReq tbQuery) {
        List<QueryFreeVo> list = tbQueryMapper.listFreeQuery(tbQuery);
        return list;
    }

    /**
     * 根据订单Id查找
     * @param orderId
     * @return
     */
    @Override
    public TbQuery findByQueryId(String orderId) {
        return tbQueryMapper.findByQueryId(orderId);
    }

}
