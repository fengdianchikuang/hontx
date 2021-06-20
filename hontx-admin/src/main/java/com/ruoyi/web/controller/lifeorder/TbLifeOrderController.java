package com.ruoyi.web.controller.lifeorder;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.cfy.DESUtil;
import com.ruoyi.common.utils.http.OkHttpUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.life.domain.TbLifeProduct;
import com.ruoyi.life.domain.TbProductRate;
import com.ruoyi.life.service.ITbProductRateService;
import com.ruoyi.lifefinance.domain.TbLifeTrans;
import com.ruoyi.lifefinance.service.ITbLifeTransService;
import com.ruoyi.lifeholder.domain.TbLifeHolder;
import com.ruoyi.lifeholder.service.ITbLifeHolderService;
import com.ruoyi.lifeorder.domain.TbLifeOrder;
import com.ruoyi.lifeorder.domain.vo.LifeOrderVo;
import com.ruoyi.lifeorder.service.ITbLifeOrderService;
import com.ruoyi.trans.service.ITbTransService;
import com.ruoyi.user.domain.TbUserInfo;
import com.ruoyi.user.domain.vo.TbUserInfoVo;
import com.ruoyi.user.service.ITbUserInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 寿险订单Controller
 *
 * @author ruoyi
 * @date 2021-05-08
 */
@RestController
@RequestMapping("/lifeorder/lifeorder")
public class TbLifeOrderController extends BaseController
{
    @Autowired
    private ITbLifeOrderService tbLifeOrderService;

    @Autowired
    private ITbLifeHolderService tbLifeHolderService;

    @Autowired
    private ITbUserInfoService tbUserInfoService;

    @Autowired
    private ITbProductRateService productRateService;

    @Autowired
    private ITbLifeTransService tbTransService;



    @Value("${cfy.url}")
    private String cfyUrl;

    @Value("${cfy.des}")
    private String cfy_des;

//    /**
//     * 查询创富云保单列表列表
//     */
//    @PreAuthorize("@ss.hasPermi('lifeorder:cfy:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(@RequestBody CfyOrderReq req)
//    {
//        String url = cfyUrl + "/merchant/policy/list";
//        //TODO 后续修改
//        /**
//         * 默认一天之内
//         */
//        if(req.getStartTime() == null || req.getEndTime() == null ){
//
//        }
//
//        String s = OkHttpUtils.postJSON(url,null,null);
//        return null;
//    }
    @PreAuthorize("@ss.hasPermi('lifeorder:lifeorder:list')")
    @GetMapping("/order/{id}")
    public AjaxResult updateOrder(@PathVariable Long id){
        TbLifeOrder tbLifeOrder = tbLifeOrderService.selectTbLifeOrderById(id);
        String url = cfyUrl + "/merchant/policy/detail";
        JSONObject params = getParams();
        JSONObject body = new JSONObject();
        body.put("order_no",tbLifeOrder.getOrderid());
        String DesData = DESUtil.encryptData(body.toJSONString(), cfy_des);
        params.put("body",DesData);
        String result = OkHttpUtils.postJSON(url, params.toJSONString(), null);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String resultBody = jsonObject.getString("body");
        String decryptData = DESUtil.decryptData(resultBody, cfy_des);
        JSONObject jsonObject1 = JSONObject.parseObject(decryptData);
        logger.info("保单详情信息:"+decryptData);
        if(jsonObject1.getInteger("code") == 0){

            JSONObject data = jsonObject1.getJSONObject("data");

            JSONObject base = data.getJSONObject("base");

            //已承保的不计算佣金
            if(!"accepted_insure".equals(tbLifeOrder.getStatus())){
                //计算佣金
                if("accepted_insure".equals(base.getString("policy_status"))){
                    setCommission(base,tbLifeOrder);
                }
            }

            if(base != null){
                if(StringUtils.isNotEmpty(base.getString("policy_sn"))){
                    tbLifeOrder.setPolicyno(base.getString("policy_sn"));
                    tbLifeOrder.setStatus(base.getString("policy_status"));
                    tbLifeOrder.setPaytime(new Date(base.getLong("proposal_at")));
                }

            }
            JSONObject holder = data.getJSONObject("holder");
            if(holder != null){
                tbLifeOrder.setHolderidno(holder.getString("certificate_number"));
                TbLifeHolder tbLifeHolder = tbLifeHolderService.selectTbLifeHolderByHolderIdNoAndAgentId(holder.getString("certificate_number"),tbLifeOrder.getAgentid());
                if(tbLifeHolder == null){
                    tbLifeHolder = new TbLifeHolder();
                    tbLifeHolder.setAgentid(base.getString("custom_key"));
                    tbLifeHolder.setCiphermobile(holder.getString("mobile"));
                    tbLifeHolder.setIdno(holder.getString("certificate_number"));
                    tbLifeHolder.setCreatetime(new Date());
                    tbLifeHolder.setIdtype("00"); //默认未身份证
                    tbLifeHolder.setPlainmobile(holder.getString("mobile"));
                    tbLifeHolder.setName(holder.getString("name"));
                    tbLifeHolderService.insertTbLifeHolder(tbLifeHolder);
                }else {
                    tbLifeHolder.setAgentid(base.getString("custom_key"));
                    tbLifeHolder.setCiphermobile(holder.getString("mobile"));
                    tbLifeHolder.setIdno(holder.getString("certificate_number"));
                    tbLifeHolder.setCreatetime(new Date());
                    tbLifeHolder.setIdtype("00"); //默认未身份证
                    tbLifeHolder.setPlainmobile(holder.getString("mobile"));
                    tbLifeHolder.setName(holder.getString("name"));
                    tbLifeHolderService.updateTbLifeHolder(tbLifeHolder);
                }
            }
            JSONArray recognizee = data.getJSONArray("recognizee");
            if(recognizee != null && !recognizee.isEmpty()){
                JSONObject insured = recognizee.getJSONObject(0);
                if(insured != null){
                    tbLifeOrder.setInsurederidno(insured.getString("certificate_number"));
                    tbLifeOrder.setInsurederidtype("00"); //默认
                    tbLifeOrder.setInsuredername(insured.getString("name"));
                }

            }
            return toAjax(tbLifeOrderService.updateTbLifeOrder(tbLifeOrder));
        }else {
            //更新失败
            return toAjax(0);
        }

    }

    /**
     * 计算佣金
     * @param base
     */
    private void setCommission(JSONObject base,TbLifeOrder tbLifeOrder) {
        //查询费率
//        Map<String, Object> rateParam = new HashMap<>();
//        rateParam.put("productId", base.getString("product_no"));
//        //缴费类型:1:年缴,2:月缴(没有月缴,暂时不考虑,默认为年缴)
//        rateParam.put("payType", tbLifeOrder.getPaytype());
//        // 缴费期限：xx 年
//        rateParam.put("payment", tbLifeOrder.getPayment());
//        // 返佣期，暂时为第一年，明年再说
//        rateParam.put("backTerm", tbLifeOrder.getPayperm());
        TbProductRate tbProductRate = new TbProductRate();
        tbProductRate.setProductid(base.getString("product_no"));
        tbProductRate.setPaytype("3"); //默认为年缴
        tbProductRate.setPayment(tbLifeOrder.getPayment());
        tbProductRate.setBackterm("1"); //暂时默认为一年
        List<TbProductRate> lrl = productRateService.selectTbProductRateList(tbProductRate);
        if (lrl == null || lrl.size() == 0) {
            logger.info("啊啊啊，费率未设置！！！！");
            return;
        }
        TbProductRate lr = lrl.get(0);

        TbUserInfo userInfo = tbUserInfoService.selectUserInfoById(Integer.parseInt(base.getString("custom_key")));
        String aId = userInfo.getUParentId();
        // 计算直接代理人的费率
        TbLifeTrans t = new TbLifeTrans();
        BigDecimal premium = new BigDecimal(base.getFloat("premium")+"");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMhhHHmmssSSS");
        String transNo = sdf.format(new Date());
        t.setTransno(transNo);
        t.setTranstype("00");
        t.setUserid(base.getString("custom_key"));
        BigDecimal bAmount = premium.multiply(new BigDecimal(lr.getAgentrate())).divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP);
        t.setAmount(bAmount.toString());
        BigDecimal userAmount = bAmount.add(new BigDecimal(userInfo.getLifeAvailBal()));
        t.setUseramount(userAmount.toString());
        t.setOrderid(tbLifeOrder.getOrderid());
        tbTransService.insertTbLifeTrans(t);
        logger.info("代理人余额为:"+userAmount.toString());

        userInfo.setLifeAvailBal(userAmount.toString());

        BigDecimal bTotalLifeAmount = bAmount.add(new BigDecimal(userInfo.getLifeTotalBal()));

        userInfo.setLifeTotalBal(bTotalLifeAmount.toString());
        tbUserInfoService.updateTbUserInfo(userInfo);

        // 计算A层级代理人费率
        if (org.apache.commons.lang.StringUtils.isNotEmpty(aId) && !"0".equals(aId)) {
            TbUserInfo aUserInfo = tbUserInfoService.selectTbUserInfoById(Integer.parseInt(aId));
            TbLifeTrans ta = new TbLifeTrans();
            ta.setTransno(transNo);
            ta.setTranstype("01");
            ta.setUserid(aId);
            bAmount = premium.multiply(new BigDecimal(lr.getTotalrate())).multiply(new BigDecimal(lr.getArate())).divide(new BigDecimal("100")).divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP);
            ta.setAmount(bAmount.toString());
            userAmount = bAmount.add(new BigDecimal(userInfo.getLifeAvailBal()));
            ta.setUseramount(userAmount.toString());
            ta.setOrderid(tbLifeOrder.getOrderid());
            tbTransService.insertTbLifeTrans(ta);
            aUserInfo.setLifeAvailBal(userAmount.toString());
            bTotalLifeAmount = bAmount.add(new BigDecimal(aUserInfo.getLifeTotalBal()));
            aUserInfo.setLifeTotalBal(bTotalLifeAmount.toString());

            logger.info("A层级余额为:"+userAmount.toString());
            tbUserInfoService.updateTbUserInfo(aUserInfo);
            String bId = aUserInfo.getUParentId();
            // 计算B层级代理人费率
            if (org.apache.commons.lang.StringUtils.isNotEmpty(bId) && !"0".equals(bId)) {
                TbUserInfo bUserInfo = tbUserInfoService.selectUserInfoById(Integer.parseInt(bId));
                TbLifeTrans tb = new TbLifeTrans();
                tb.setTransno(transNo);
                tb.setTranstype("02");
                tb.setUserid(bId);
                bAmount = premium.multiply(new BigDecimal(lr.getBrate())).multiply(new BigDecimal(lr.getTotalrate())).divide(new BigDecimal("100")).divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP);
                tb.setAmount(bAmount.toString());
                userAmount = bAmount.add(new BigDecimal(bUserInfo.getLifeAvailBal()));
                tb.setUseramount(userAmount.toString());
                tb.setOrderid(tbLifeOrder.getOrderid());
                tbTransService.insertTbLifeTrans(tb);
                logger.info("B层级余额为:"+userAmount.toString());
                bUserInfo.setLifeAvailBal(userAmount.toString());
                bTotalLifeAmount = bAmount.add(new BigDecimal(bUserInfo.getLifeTotalBal()));
                bUserInfo.setLifeTotalBal(bTotalLifeAmount.toString());
                tbUserInfoService.updateTbUserInfo(bUserInfo);
            }
        }


    }

    /**
     * 获取创富云通用请求参数
     * @return
     */
    private JSONObject getParams() {
        long nowTime = System.currentTimeMillis();
        JSONObject data = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("access_key","BTEfGg8o6nHUEmrK");
        header.put("access_secret","7Q2OX3DOP4VEdqZpz1nDPaBJKvTJ73yY");
        header.put("request_at",nowTime+"");
        header.put("business_no","jq"+nowTime);
        data.put("header",header);
        return data;
    }

    /**
     * 查询寿险订单列表
     */
    @PreAuthorize("@ss.hasPermi('lifeorder:lifeorder:list')")
    @GetMapping("/list")
    public TableDataInfo cfyList(LifeOrderVo tbLifeOrder)
    {
        startPage();
        List<LifeOrderVo> list = tbLifeOrderService.selectTbLifeOrderList(tbLifeOrder);
        return getDataTable(list);
    }

    /**
     * 导出寿险订单列表
     */
    @PreAuthorize("@ss.hasPermi('lifeorder:lifeorder:export')")
    @Log(title = "寿险订单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LifeOrderVo tbLifeOrder)
    {
        List<LifeOrderVo> list = tbLifeOrderService.selectTbLifeOrderList(tbLifeOrder);
        ExcelUtil<LifeOrderVo> util = new ExcelUtil<LifeOrderVo>(LifeOrderVo.class);
        return util.exportExcel(list, "lifeorder");
    }

    /**
     * 获取寿险订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('lifeorder:lifeorder:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tbLifeOrderService.selectTbLifeOrderById(id));
    }

    /**
     * 新增寿险订单
     */
    @PreAuthorize("@ss.hasPermi('lifeorder:lifeorder:add')")
    @Log(title = "寿险订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbLifeOrder tbLifeOrder)
    {
        return toAjax(tbLifeOrderService.insertTbLifeOrder(tbLifeOrder));
    }

    /**
     * 修改寿险订单
     */
    @PreAuthorize("@ss.hasPermi('lifeorder:lifeorder:edit')")
    @Log(title = "寿险订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbLifeOrder tbLifeOrder)
    {
        return toAjax(tbLifeOrderService.updateTbLifeOrder(tbLifeOrder));
    }

    /**
     * 删除寿险订单
     */
    @PreAuthorize("@ss.hasPermi('lifeorder:lifeorder:remove')")
    @Log(title = "寿险订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tbLifeOrderService.deleteTbLifeOrderByIds(ids));
    }
}
