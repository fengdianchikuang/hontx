package com.ruoyi.queryfile.service;

import java.util.List;
import com.ruoyi.queryfile.domain.TbQueryFile;

/**
 * 保单文件Service接口
 *
 * @author ruoyi
 * @date 2021-04-12
 */
public interface ITbQueryFileService
{
    /**
     * 查询保单文件
     *
     * @param qId 保单文件ID
     * @return 保单文件
     */
    public List<TbQueryFile> selectTbQueryFileById(String qId);

    /**
     * 查询保单文件列表
     *
     * @param tbQueryFile 保单文件
     * @return 保单文件集合
     */
    public List<TbQueryFile> selectTbQueryFileList(TbQueryFile tbQueryFile);

    /**
     * 新增保单文件
     *
     * @param tbQueryFile 保单文件
     * @return 结果
     */
    public int insertTbQueryFile(TbQueryFile tbQueryFile);

    /**
     * 修改保单文件
     *
     * @param tbQueryFile 保单文件
     * @return 结果
     */
    public int updateTbQueryFile(TbQueryFile tbQueryFile);

    /**
     * 批量删除保单文件
     *
     * @param qIds 需要删除的保单文件ID
     * @return 结果
     */
    public int deleteTbQueryFileByIds(Long[] qIds);

    /**
     * 删除保单文件信息
     *
     * @param qId 保单文件ID
     * @return 结果
     */
    public int deleteTbQueryFileById(Long qId);
}
