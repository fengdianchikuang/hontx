package com.ruoyi.web.controller.queryfile;

import java.io.IOException;
import java.util.List;

import com.ruoyi.common.constant.OssConstants;
import com.ruoyi.common.utils.file.FileUploadUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ruoyi.queryfile.domain.TbQueryFile;
import com.ruoyi.queryfile.service.ITbQueryFileService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 保单文件Controller
 *
 * @author ruoyi
 * @date 2021-04-12
 */
@RestController
@RequestMapping("/queryfile/file")
public class TbQueryFileController extends BaseController
{
    @Autowired
    private ITbQueryFileService tbQueryFileService;

    @PreAuthorize("@ss.hasPermi('policy:query:add')")
    @PostMapping("/upload")
    public AjaxResult uploadFile(MultipartFile file,String type,String queryId) throws IOException {
        String fileName = FileUploadUtils.extractFilename(file);
        String path = "query/" + type + "/" + queryId + "/" + fileName;
        Boolean aBoolean = FileUploadUtils.putObjectToAliOss(OssConstants.bucketName, path, file.getInputStream());
        TbQueryFile queryFile = new TbQueryFile();
        queryFile.setQueryId(queryId);
        queryFile.setPath(path);
        queryFile.setFileType(type);
        queryFile.setFileName(fileName);
        return toAjax(tbQueryFileService.insertTbQueryFile(queryFile));
    }

    /**
     * 查询保单文件列表
     */
    @PreAuthorize("@ss.hasPermi('queryfile:file:list')")
    @GetMapping("/list")
    public TableDataInfo list(TbQueryFile tbQueryFile)
    {
        startPage();
        List<TbQueryFile> list = tbQueryFileService.selectTbQueryFileList(tbQueryFile);
        return getDataTable(list);
    }

    /**
     * 导出保单文件列表
     */
    @PreAuthorize("@ss.hasPermi('queryfile:file:export')")
    @Log(title = "保单文件", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TbQueryFile tbQueryFile)
    {
        List<TbQueryFile> list = tbQueryFileService.selectTbQueryFileList(tbQueryFile);
        ExcelUtil<TbQueryFile> util = new ExcelUtil<TbQueryFile>(TbQueryFile.class);
        return util.exportExcel(list, "file");
    }

    /**
     * 获取保单文件详细信息
     */
    @PreAuthorize("@ss.hasPermi('policy:query:add')")
    @GetMapping(value = "/{qId}")
    public AjaxResult getInfo(@PathVariable("qId") String qId)
    {
        return AjaxResult.success(tbQueryFileService.selectTbQueryFileById(qId));
    }

    /**
     * 新增保单文件
     */
    @PreAuthorize("@ss.hasPermi('queryfile:file:add')")
    @Log(title = "保单文件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbQueryFile tbQueryFile)
    {
        return toAjax(tbQueryFileService.insertTbQueryFile(tbQueryFile));
    }

    /**
     * 修改保单文件
     */
    @PreAuthorize("@ss.hasPermi('queryfile:file:edit')")
    @Log(title = "保单文件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbQueryFile tbQueryFile)
    {
        return toAjax(tbQueryFileService.updateTbQueryFile(tbQueryFile));
    }

    /**
     * 删除保单文件
     */
    @PreAuthorize("@ss.hasPermi('queryfile:file:remove')")
    @Log(title = "保单文件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{qIds}")
    public AjaxResult remove(@PathVariable Long[] qIds)
    {
        return toAjax(tbQueryFileService.deleteTbQueryFileByIds(qIds));
    }
}
