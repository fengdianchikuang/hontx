package com.ruoyi.user.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aliyun.oss.OSS;
import com.ruoyi.ai.domain.vo.IdCardVo;
import com.ruoyi.ai.service.AiService;
import com.ruoyi.common.constant.BaiDuConstant;
import com.ruoyi.common.constant.OssConstants;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.feign.ICarOwnerService;
import com.ruoyi.trans.domain.TbTrans;
import com.ruoyi.trans.mapper.TbTransMapper;
import com.ruoyi.user.domain.TbUserFile;
import com.ruoyi.user.domain.TbUserInfo;
import com.ruoyi.user.domain.req.*;
import com.ruoyi.user.domain.vo.TbUserInfoVo;
import com.ruoyi.user.domain.vo.UserTeamVo;
import com.ruoyi.user.mapper.TbUserInfoMapper;
import com.ruoyi.user.mapper.TbUserMapper;
import com.ruoyi.user.service.ITbUserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


/**
 * 用户扩展信息Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-07
 */
@Service
public class TbUserInfoServiceImpl implements ITbUserInfoService
{
    @Autowired
    private TbUserInfoMapper tbUserInfoMapper;

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private ICarOwnerService carOwnerService;

    @Autowired
    private TbTransMapper tbTransMapper;

    @Autowired
    private AiService aiService;


    /**
     * 查询用户信息(所有属性)根据Id
     * @param uId
     * @return
     */
    public TbUserInfo selectUserInfoById(Integer uId){
        return tbUserInfoMapper.selectUserInfoById(uId);
    }

    /**
     * 修改推荐人的上下级关系
     * @param tbUserInfo
     * @return
     */
    @Override
    public int updateUserParent(TbUserInfo tbUserInfo) {
        //查找推荐人
        TbUserInfo parentUser = tbUserInfoMapper.selectUserByPhone(tbUserInfo.getParentPhone());
        TbUserInfo userInfo = tbUserInfoMapper.selectUserInfoById(tbUserInfo.getUId());
        userInfo.setUParentId(parentUser.getUParentId());
        int res = tbUserInfoMapper.updateTbUserInfo(userInfo);
        //修改商城的上级

        return res;
    }

    /**
     * 修改用户银行账号资料
     * @param request
     * @return
     */
    @Override
    public int updateBank(UserAccountReq request) {
        TbUserInfo userInfo = tbUserInfoMapper.findByOwnerId(request.getOwnerId());
        TbUserInfo tbUserInfo = new TbUserInfo();
        tbUserInfo.setUId(userInfo.getUId());
        BeanUtils.copyProperties(request,tbUserInfo);
        int res = tbUserInfoMapper.updateTbUserInfo(tbUserInfo);
        if(res == 1){
            CarOwner carOwner = new CarOwner();
            carOwner.setOwnerId(request.getOwnerId());
            carOwner.setName(userInfo.getURealName());
            carOwner.setBankName(userInfo.getUBankName());
            carOwner.setAccno(userInfo.getUAccno());
            return carOwnerService.updateAmount(carOwner);
        }else {
            throw new CustomException("更新信息失败,请重试");
        }
    }

    /**
     * 新增实名用户
     * @param userInfo
     * @param request
     * @return
     */
    @Override
    public int createUser(UserInfoVo userInfo, HttpServletRequest request) {
        //根据手机号码,查询用户是已注册
        TbUserInfo tbUserInfo = tbUserInfoMapper.findByPhone(userInfo.getUTelephone());
        //平台存在的老用户,新增信息车主Id
        if(tbUserInfo != null && "03".equals(tbUserInfo.getURealNameStatus())){
            UserReq userReq = new UserReq();
            userReq.setCreateTime(new Date());
            userReq.setOwnerId(userInfo.getOwnerId());
            if(StringUtils.isNotEmpty(userInfo.getUEmail())){
                userReq.setCreatorName(userInfo.getUEmail());
                userReq.setAccountName(userInfo.getUEmail());
            }
            //修改主表用户
            return tbUserMapper.updateTbUser(userReq);
        }else if(tbUserInfo != null && "03".equals(tbUserInfo.getURealNameStatus())) {
            throw new CustomException("请不要重复提交,等待审核通过");
            //新注册用户
        }else {
            UserReq userReq = new UserReq();
            userReq.setOwnerId(userInfo.getOwnerId());
            userReq.setAccountName(userInfo.getUTelephone());
            userReq.setCreatorName(userInfo.getUTelephone());
            int i = tbUserMapper.insertTbUser(userReq);
            Integer userId = userReq.getId();
            TbUserInfo newUserInfo = new TbUserInfo();
            //推荐人详情表
            TbUserInfo parentUserInfo = tbUserInfoMapper.findByPhone(userInfo.getUParentPhone());
            if(parentUserInfo != null){
                if(StringUtils.isNotEmpty(parentUserInfo.getTId())){
                    newUserInfo.setTId(parentUserInfo.getTId());
                }
                if(StringUtils.isNotEmpty(parentUserInfo.getUOrgId())){
                    newUserInfo.setUOrgId(parentUserInfo.getOpenid());
                }
            }else {
                String companyId = request.getHeader("companyId");
                switch (companyId){
                    case "0":
                        newUserInfo.setUOrgId("2");
                        break;
                    case "1":
                        newUserInfo.setUOrgId("9");
                        break;
                    default:
                        newUserInfo.setUOrgId("2");
                }
                if(StringUtils.isNotEmpty(userInfo.getUBankName())){
                    newUserInfo.setUBankName(userInfo.getUBankName());
                }
                if(StringUtils.isNotEmpty(userInfo.getUBankBranch())){
                    newUserInfo.setUBankBranch(userInfo.getUBankBranch());
                }
                if(StringUtils.isNotEmpty(userInfo.getUAccno())){
                    newUserInfo.setUAccno(userInfo.getUAccno());
                }
                newUserInfo.setUId(userId);
                if(StringUtils.isNotEmpty(userInfo.getUIdentity())){
                    newUserInfo.setCreateTime(new Date());
                    newUserInfo.setUIdentity(userInfo.getUIdentity());
                }
                if(StringUtils.isNotEmpty(userInfo.getUAddress())){
                    newUserInfo.setUAddress(userInfo.getUAddress());
                }
                newUserInfo.setURealName(userInfo.getURealName());
                //待审核
                newUserInfo.setURealNameStatus("01");
                //更新用户角色表
                tbUserMapper.insertUserRole(userId,5);

                int res = tbUserInfoMapper.insertTbUserInfo(newUserInfo);

            }

        }

        return 0;
    }

    /**
     * 根据车主Id获取保险团队信息
     * @param ownerId
     * @return
     */
    @Override
    public UserTeamVo queryTeamInfoByUserId(Long ownerId) {
        UserTeamVo userTeamVo = tbUserInfoMapper.queryTeamInfoByUserId(ownerId);
        if(userTeamVo != null && userTeamVo .getBTeams() != null && userTeamVo.getBTeams().size() > 0){
            userTeamVo.setBTeamNum(userTeamVo.getBTeams().size());
        }
        return userTeamVo;
    }

    /**
     * 申请提现请求
     * @param request
     * @return
     */
    @Override
    public int withdrawalRequest(WithdrawalRequest request) {
        TbUserInfo userInfo = tbUserInfoMapper.findByOwnerId(request.getOwnerId());
        //申请资金少于可用资金则返回0
        if(Float.parseFloat(userInfo.getUAvailBal()) > request.getAmount()){
            BigDecimal avilBal =new BigDecimal(userInfo.getUAvailBal());
            BigDecimal subtract = avilBal.subtract(new BigDecimal(request.getAmount()));
            userInfo.setUAvailBal(subtract.toString());
            TbTrans trans = new TbTrans();
            trans.setTransNo(IdUtils.getFixLengthString(12) +"");
            trans.setTransType("03");
            trans.setStatus("00");
            trans.setUserAmount(subtract.toString());
            trans.settCreateTime(new Date());
            trans.setUserId(userInfo.getUId()+"");
            trans.setAmount("-"+request.getAmount());
            tbTransMapper.insertTbTrans(trans);
            return tbUserInfoMapper.updateTbUserInfo(userInfo);
        }
        return 0;
    }

    /**
     * 查看保险提现列表
     * @param request
     * @return
     */
    @Override
    public List<TbTrans> withdrawalList(WithdrawalListRequest request) {
        TbUserInfo userInfo = tbUserInfoMapper.findByOwnerId(request.getOwnerId());
        List<TbTrans> list = tbTransMapper.findByUserIdAndStatus(userInfo.getUId()+"");
        return list;
    }

    /**
     * 上传用户证件照
     * @param file
     * @param type
     * @param s
     * @return
     */
    @Override
    public Map<String, Object> uploadRealUserFile(MultipartFile file, String type, String ownerId) throws IOException {
        IdCardVo idCardVo = null;
        Map<String,Object> param = new HashMap<>();
        if("00".equals(type)){
             idCardVo = aiService.distinguishIdCard(file, BaiDuConstant.ID_CARD_Front);
            if(idCardVo != null){
                param.put("result",idCardVo);
            }
        }
        String fileName = FileUploadUtils.getFileName(file);
        String path = OssConstants.userPath + ownerId + "/" + type + "/" + fileName;
        FileUploadUtils.putObjectToAliOss(OssConstants.bucketName,path,file.getInputStream());
        //根据车主Id查找用户文件
        List<TbUserFile> userFiles = tbUserInfoMapper.findUserFileByUserId(ownerId);
        TbUserFile tbUserFile = null;
        for (TbUserFile userFile : userFiles) {
            if(type.equals(userFile.getType())){
                FileUploadUtils.deleteFileOne(OssConstants.bucketName,userFile.getPath());
                tbUserInfoMapper.delUserFileById(userFile.getuId());
                tbUserFile = userFile;
            }
        }
//        新增证件照
        if(tbUserFile == null){
            tbUserFile = new TbUserFile();
            tbUserFile.setFileName(fileName);
            tbUserFile.setUserId(ownerId);
            tbUserFile.setPath(path);
            tbUserFile.setType(type);
            tbUserFile.setuCreateTime(new Date());
            tbUserInfoMapper.insertUserFile(tbUserFile);
        }else {
            tbUserFile.setPath(path);
            tbUserInfoMapper.updateUserFile(tbUserFile);
        }
        param.put("userFile",tbUserFile);
        return param;
    }

    @Override
    public TbUserInfoVo findUserDetailByOwnerId(String ownerId) {
        TbUserInfoVo tbUserInfoVo = tbUserInfoMapper.findUserDetailByOwnerId(ownerId);
        return tbUserInfoVo;
    }

    @Override
    public TbUserInfo findByPhone(String phone) {
        return tbUserInfoMapper.findByPhone(phone);
    }

    /**
     * 更新用户信息
     * @param userInfoVo
     * @return
     */
    @Override
    public int updateUserInfo(UserInfoVo userInfoVo) {
        TbUserInfo tbUserInfo = new TbUserInfo();
        tbUserInfo.setUId(userInfoVo.getId());
        BeanUtils.copyProperties(userInfoVo,tbUserInfo);
        return tbUserInfoMapper.updateTbUserInfo(tbUserInfo);
    }

    /**
     * 查询用户扩展信息
     *
     * @param uId 用户扩展信息ID
     * @return 用户扩展信息
     */
    @Override
    public TbUserInfoVo selectTbUserInfoById(Integer uId)
    {
        return tbUserInfoMapper.selectTbUserInfoById(uId);
    }

    /**
     * 查询用户扩展信息列表
     *
     * @param tbUserInfo 用户扩展信息
     * @return 用户扩展信息
     */
    @Override
    public List<TbUserInfo> selectTbUserInfoList(TbUserInfo tbUserInfo)
    {
        return tbUserInfoMapper.selectTbUserInfoList(tbUserInfo);
    }

    /**
     * 新增用户扩展信息
     *
     * @param tbUserInfo 用户扩展信息
     * @return 结果
     */
    @Override
    public int insertTbUserInfo(TbUserInfo tbUserInfo)
    {
        return tbUserInfoMapper.insertTbUserInfo(tbUserInfo);
    }

    /**
     * 修改用户扩展信息
     *
     * @param userInfoVo 用户扩展信息
     * @return 结果
     */
    @Override
    public int updateTbUserInfo(TbUserInfo userInfoVo)
    {

        return tbUserInfoMapper.updateTbUserInfo(userInfoVo);
    }

    /**
     * 批量删除用户扩展信息
     *
     * @param uIds 需要删除的用户扩展信息ID
     * @return 结果
     */
    @Override
    public int deleteTbUserInfoByIds(Integer[] uIds)
    {
        return tbUserInfoMapper.deleteTbUserInfoByIds(uIds);
    }

    /**
     * 删除用户扩展信息信息
     *
     * @param uId 用户扩展信息ID
     * @return 结果
     */
    @Override
    public int deleteTbUserInfoById(Integer uId)
    {
        return tbUserInfoMapper.deleteTbUserInfoById(uId);
    }

    @Override
    public int insertUserFile(TbUserFile tbUserFile) {
        return tbUserInfoMapper.insertUserFile(tbUserFile);
    }

    @Override
    public TbUserFile findByFileName(String fileName) {
        return tbUserInfoMapper.findByFileName(fileName);
    }

    @Override
    public TbUserFile findByUid(Long uId) {

        return tbUserInfoMapper.findByUid(uId);
    }

    @Override
    public int delUserFileById(Long getuId) {
        return tbUserInfoMapper.delUserFileById(getuId);
    }

    /**
     * 查询机构今天的新增代理
     * @param orgId
     * @param today
     * @param yesterday
     * @return
     */
    @Override
    public int queryListByTodayNum(String orgId, String today, String yesterday) {
        return tbUserInfoMapper.queryListByTodayNum(orgId,today,yesterday);
    }


}
