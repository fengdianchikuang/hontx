package com.ruoyi.trans.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.trans.domain.req.ProfitRequest;
import com.ruoyi.trans.domain.vo.CompletionVo;
import com.ruoyi.trans.domain.vo.TransDetailVo;
import com.ruoyi.trans.domain.vo.TransVo;
import com.ruoyi.user.domain.TbUserInfo;
import com.ruoyi.user.mapper.TbUserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.trans.mapper.TbTransMapper;
import com.ruoyi.trans.domain.TbTrans;
import com.ruoyi.trans.service.ITbTransService;

/**
 * 提交列表Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-16
 */
@Service
public class TbTransServiceImpl implements ITbTransService
{
    @Autowired
    private TbTransMapper tbTransMapper;

    @Autowired
    private TbUserInfoMapper tbUserInfoMapper;

    /**
     * 查询提交列表
     *
     * @param tId 提交列表ID
     * @return 提交列表
     */
    @Override
    public TbTrans selectTbTransById(Long tId)
    {
        return tbTransMapper.selectTbTransById(tId);
    }

    /**
     * 查询提交列表列表
     *
     * @param tbTrans 提交列表
     * @return 提交列表
     */
    @Override
    public List<TransVo> selectTbTransList(TransVo tbTrans)
    {
        return tbTransMapper.selectTbTransList(tbTrans);
    }

    /**
     * 新增提交列表
     *
     * @param tbTrans 提交列表
     * @return 结果
     */
    @Override
    public int insertTbTrans(TbTrans tbTrans)
    {
        return tbTransMapper.insertTbTrans(tbTrans);
    }

    /**
     * 修改提交列表
     *
     * @param tbTrans 提交列表
     * @return 结果
     */
    @Override
    public int updateTbTrans(TbTrans tbTrans)
    {
        return tbTransMapper.updateTbTrans(tbTrans);
    }

    /**
     * 批量删除提交列表
     *
     * @param tIds 需要删除的提交列表ID
     * @return 结果
     */
    @Override
    public int deleteTbTransByIds(Long[] tIds)
    {
        return tbTransMapper.deleteTbTransByIds(tIds);
    }

    /**
     * 删除提交列表信息
     *
     * @param tId 提交列表ID
     * @return 结果
     */
    @Override
    public int deleteTbTransById(Long tId)
    {
        return tbTransMapper.deleteTbTransById(tId);
    }

    /**
     * 提现申请
     * @param id
     * @return
     */
    @Override
    public int withdrawal(Long id) {
        TbTrans tbTrans = tbTransMapper.selectTbTransById(id);
        tbTrans.setStatus("01");
        return tbTransMapper.updateTbTrans(tbTrans);
    }

    /**
     * 获取交易明细信息List
     * @param tbTrans
     * @return
     */
    @Override
    public List<TransDetailVo> selectTbTransInfoList(TransDetailVo tbTrans) {
        return tbTransMapper.selectTbTransInfoList(tbTrans);
    }

    /**
     * 获取用户收益里欸奥
     * @param request
     * @return
     */
    @Override
    public List<TransVo> profit(ProfitRequest request) {
        TbUserInfo tbUserInfo = tbUserInfoMapper.findByOwnerId(request.getOwnerId());
        List<TransVo> list = tbTransMapper.findMyProfit(tbUserInfo.getUId(),request.getType());
        return list;
    }

    /**
     * 当余额保险收益与可用余额
     * @param request
     * @return
     */
    @Override
    public Map<String, Object> balance(ProfitRequest request) {
        TbUserInfo one = tbUserInfoMapper.findByOwnerId(request.getOwnerId());
        Map<String,Object> params = new HashMap<>();
        if(one == null){
            params.put("total","0");
            params.put("balance","0");
            return params;
        }
        // 本月起始
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime firstDay = LocalDateTime.of(now.getYear(), now.getMonth(), 1, 0, 0);
        // 本月最后一天
        LocalDateTime lastDay = now.with(TemporalAdjusters.lastDayOfMonth());

        String firstDayString = firstDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String lastDayString = lastDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<String> amounts =tbTransMapper.selectAmount(firstDayString,lastDayString,one.getUId());
        //总收益
        BigDecimal total = new BigDecimal("0");
        for (String tran : amounts) {
            BigDecimal amout = new BigDecimal(tran);
            total = total.add(amout);
        }
        params.put("total",total.toString());
        if(one != null && one.getUAvailBal() != null){
            params.put("balance",one.getUAvailBal());
        }else {
            params.put("balance","0");
        }

        return params;
    }
    /**
     * 计算团队完成商业险金额及个数
     * @param userId
     */
    @Override
    public CompletionVo CompletionRate(Integer userId) {
        LocalDateTime nowTime = LocalDateTime.now();
        LocalDateTime firstDay = nowTime.with(TemporalAdjusters.firstDayOfMonth());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String first = dateTimeFormatter.format(firstDay);
        String now = dateTimeFormatter.format(nowTime);
        List<TbTrans> trans = tbTransMapper.selectTrans(first,now,userId+"");
        //计算用户的商业险总额（自己的出单件数）,计算用户+A团队的商业总额(A团队的出单件数)
        //代理人单月出单金额
        BigDecimal agentTotal = new BigDecimal("0");
        //代理人当月出单件数
        int agentCount = 0;

        for (TbTrans tran : trans) {
            if("00".equals(tran.getTransType())){
                agentCount ++;
                agentTotal = agentTotal.add(new BigDecimal(tran.getVciAmount()));
            }
        }
        CompletionVo completionVo = new CompletionVo();
        completionVo.setAgentCount(agentCount);
        completionVo.setVciPremium(agentTotal.toString());
        return completionVo;
    }


}
