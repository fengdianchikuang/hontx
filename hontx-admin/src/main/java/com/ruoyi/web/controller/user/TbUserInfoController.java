package com.ruoyi.web.controller.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.constant.OssConstants;
import com.ruoyi.common.constant.UrlConstants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.trans.domain.TbTrans;
import com.ruoyi.user.domain.TbUserFile;
import com.ruoyi.user.domain.TbUserInfo;
import com.ruoyi.user.domain.req.UserAccountReq;
import com.ruoyi.user.domain.req.UserInfoVo;
import com.ruoyi.user.domain.req.WithdrawalListRequest;
import com.ruoyi.user.domain.req.WithdrawalRequest;
import com.ruoyi.user.domain.vo.TbUserInfoVo;
import com.ruoyi.user.domain.vo.UserTeamVo;
import com.ruoyi.user.mapper.TbUserMapper;
import com.ruoyi.user.service.ITbUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * ??????????????????Controller
 *
 * @author ruoyi
 * @date 2021-04-07
 */
@RestController
@RequestMapping("/user/list")
@Slf4j
@Api(tags = "??????????????????",description = "????????????",value = "??????????????????")
public class TbUserInfoController extends BaseController
{
    @Autowired
    private ITbUserInfoService tbUserInfoService;




    @ApiOperation(value = "????????????(??????)??????")
    @PostMapping(value = "/update/userInfo")
    public Object updateUserInfo(@RequestBody UserAccountReq request){
        log.info("??????????????????????????????:"+ JSON.toJSONString(request));
        int i = tbUserInfoService.updateBank(request);
        if(i == 0){
            return AjaxResult.error("??????????????????");
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "?????????????????????????????????")
    @PostMapping(value = "/authen")
    @Transactional
    public Object authenUser(@RequestBody UserInfoVo userInfo, HttpServletRequest request) {
        log.info("??????????????????????????????:{}", JSON.toJSONString(userInfo));
        int userId = tbUserInfoService.createUser(userInfo,request);
        log.info("?????????:userId:"+userId);
        log.info("???????????????????????????:" + JSON.toJSONString(userInfo));
        return AjaxResult.success("????????????,????????????????????????");
    }


    @ApiOperation(value = "????????????Id????????????????????????")
    @PostMapping(value = "/itemInfo/{ownerId}")
    public Object queryTeamInfoByUserId(@PathVariable Long ownerId){
        UserTeamVo userTeamVo = tbUserInfoService.queryTeamInfoByUserId(ownerId);
        return AjaxResult.success(userTeamVo);
    }

    @ApiOperation(value = "??????????????????")
    @PostMapping(value = "/Withdrawal")
    public Object Withdrawal(@RequestBody WithdrawalRequest request){
        int code = tbUserInfoService.withdrawalRequest(request);
        if(code == 0){
            return AjaxResult.error("????????????");
        }
        return AjaxResult.success("????????????");
    }


    @ApiOperation(value = "????????????????????????")
    @PostMapping(value = "/withdrawalList")
    public Object withdrawalList(@RequestBody WithdrawalListRequest request){
        startPage();
        List<TbTrans> list = tbUserInfoService.withdrawalList(request);

        return getDataTable(list);
    }


    @ApiOperation(value = "00?????????????????? 01?????????????????? 02?????????????????? 03?????????????????? 04?????????????????? 05:????????????")
    @PostMapping(value = "/upload/{type}")
    public Object uploadRealUserFile(MultipartFile file, @PathVariable  String type, Long ownerId) throws Exception {
        Map<String,Object> userFile = tbUserInfoService.uploadRealUserFile(file, type, ownerId+"");
        return AjaxResult.success(userFile);
    }

    @ApiOperation(value = "?????????????????????????????????")
    @PostMapping(value = "/download/{ownerId}")
    public Object downloadUserFile(@PathVariable Long ownerId)  {
         TbUserInfoVo tbUserInfoVo = tbUserInfoService.findUserDetailByOwnerId(ownerId+"");
        return AjaxResult.success(tbUserInfoVo);
    }

    /**
     * ???????????? ????????????????????????????????????
     * @param phone
     * @return
     */
    @GetMapping(value = "/info/{phone}")
    @ApiOperation(value = "??????????????????????????????")
    public Object getUserInfo(@PathVariable String phone){
        log.info("????????????????????????????????????{}",phone);
        TbUserInfo user = tbUserInfoService.findByPhone(phone);
        return user;
    }
    /**
     * ???????????????????????????Id
     * @return
     */
    @RequestMapping(value = "/updateOwner")
    public int updateCarOwnerId(@RequestBody UserInfoVo oldUser){
        return tbUserInfoService.updateUserInfo(oldUser);
    }

    /**
     * ??????????????????????????????
     */
    @PreAuthorize("@ss.hasPermi('user:list:list')")
    @GetMapping("/list")
    public TableDataInfo list(TbUserInfo tbUserInfo)
    {
        startPage();
        //??????????????????????????????
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser user = loginUser.getUser();
        tbUserInfo.setUOrgId(user.getOrgId()+"");
        List<TbUserInfo> list = tbUserInfoService.selectTbUserInfoList(tbUserInfo);
        return getDataTable(list);
    }

    /**
     * ??????????????????????????????
     */
    @PreAuthorize("@ss.hasPermi('user:list:export')")
    @Log(title = "??????????????????", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TbUserInfo tbUserInfo)
    {
        //??????????????????????????????
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser user = loginUser.getUser();
        tbUserInfo.setUOrgId(user.getOrgId()+"");
        List<TbUserInfo> list = tbUserInfoService.selectTbUserInfoList(tbUserInfo);
        ExcelUtil<TbUserInfo> util = new ExcelUtil<TbUserInfo>(TbUserInfo.class);
        return util.exportExcel(list, "list");
    }

    /**
     * ????????????????????????????????????
     */
    @PreAuthorize("@ss.hasPermi('user:list:query')")
    @GetMapping(value = "/{uId}")
    public AjaxResult getInfo(@PathVariable("uId") Integer uId)
    {
        TbUserInfoVo tbUserInfoVo = tbUserInfoService.selectTbUserInfoById(uId);
        List<TbUserFile> files = tbUserInfoVo.getFiles();
        if(files != null && files.size() > 0){
            for (TbUserFile file : files) {
                setUrl(file,tbUserInfoVo);
            }
        }
        return AjaxResult.success(tbUserInfoService.selectTbUserInfoById(uId));
    }

    @PreAuthorize("@ss.hasPermi('user:list:add')")
    @Log(title = "??????????????????", businessType = BusinessType.OTHER)
    @PostMapping("/upload")
    public AjaxResult uploadUserFile(MultipartFile file,Long userId,String type) throws IOException {
        String filename = FileUploadUtils.extractFilename(file);
        String path = "user/" + type +"/" + userId + "/"+ filename;
        Boolean aBoolean = FileUploadUtils.putObjectToAliOss(OssConstants.bucketName, path, file.getInputStream());
        if(aBoolean){
            TbUserFile tbUserFile = new TbUserFile();
            tbUserFile.setFileName(filename);
            tbUserFile.setPath(path);
            tbUserFile.setType(type);
            tbUserFile.setUserId(userId+"");
            tbUserInfoService.insertUserFile(tbUserFile);
            return AjaxResult.success(tbUserFile);
        }
        return AjaxResult.error();
    }
//    //??????URL
    private void setUrl(TbUserFile file, TbUserInfoVo result) {
        String path = file.getPath();
        if(StringUtils.isEmpty(path)){
            return;
        }
        boolean flg = judgeUrlPrefix(path);
        switch (file.getType()){
            case "00":
                if(flg){

                    result.setUrl01(UrlConstants.URL_PREFIX + path.substring(10,path.length()) + file.getFileName());
                }else {
                    result.setUrl01(UrlConstants.OSS_PREFIX + path);
                }
                break;
            case "01":

                if(flg){
                    result.setUrl02(UrlConstants.URL_PREFIX + path.substring(10,path.length()) + file.getFileName());
                }else {
                    result.setUrl02(UrlConstants.OSS_PREFIX + path);
                }
                break;
            case "02":
                if(flg){
                    result.setUrl03(UrlConstants.URL_PREFIX + path.substring(10,path.length()) + file.getFileName());
                }else {
                    result.setUrl03(UrlConstants.OSS_PREFIX + path + file.getFileName());
                }
                break;
            case "03":
                if(flg){
                    result.setUrl04(UrlConstants.URL_PREFIX + path.substring(10,path.length()) + file.getFileName());
                }else {
                    result.setUrl04(UrlConstants.OSS_PREFIX + path);
                }
                break;
        }
    }

    private boolean judgeUrlPrefix(String path) {
        return path.contains("/home");

    }

    /**
     * ????????????????????????
     */
    @PreAuthorize("@ss.hasPermi('user:list:add')")
    @Log(title = "??????????????????", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbUserInfo tbUserInfo)
    {
        return toAjax(tbUserInfoService.insertTbUserInfo(tbUserInfo));
    }



    /**
     * ????????????????????????
     */
    @PreAuthorize("@ss.hasPermi('user:list:edit')")
    @Log(title = "??????????????????", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbUserInfo tbUserInfo)
    {
        if(StringUtils.isNotEmpty(tbUserInfo.getParentPhone())){
            tbUserInfoService.updateUserParent(tbUserInfo);
        }
        return toAjax(tbUserInfoService.updateTbUserInfo(tbUserInfo));
    }

    /**
     * ??????????????????
     */
    @PreAuthorize("@ss.hasPermi('user:list:remove')")
    @Log(title = "??????????????????", businessType = BusinessType.DELETE)
    @DeleteMapping("/upload/{id}")
    public AjaxResult delFile(@PathVariable String id){
        TbUserFile tbUserFile = tbUserInfoService.findByUid(Long.valueOf(id));
        if(tbUserFile != null){
            FileUploadUtils.deleteFileOne(OssConstants.bucketName,tbUserFile.getPath());
            return toAjax(tbUserInfoService.delUserFileById(tbUserFile.getuId()));
        }
        return AjaxResult.error();
    }


    /**
     * ????????????????????????
     */
    @PreAuthorize("@ss.hasPermi('user:list:remove')")
    @Log(title = "??????????????????", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uIds}")
    public AjaxResult remove(@PathVariable Integer[] uIds)
    {
        return toAjax(tbUserInfoService.deleteTbUserInfoByIds(uIds));
    }
}
