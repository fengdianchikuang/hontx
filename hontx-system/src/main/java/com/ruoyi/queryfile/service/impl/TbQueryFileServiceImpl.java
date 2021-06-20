package com.ruoyi.queryfile.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.queryfile.mapper.TbQueryFileMapper;
import com.ruoyi.queryfile.domain.TbQueryFile;
import com.ruoyi.queryfile.service.ITbQueryFileService;

/**
 * 保单文件Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-12
 */
@Service
public class TbQueryFileServiceImpl implements ITbQueryFileService
{
    @Autowired
    private TbQueryFileMapper tbQueryFileMapper;

    /**
     * 查询保单文件
     *
     * @param qId 保单文件ID
     * @return 保单文件
     */
    @Override
    public List<TbQueryFile> selectTbQueryFileById(String qId)
    {
        return tbQueryFileMapper.selectTbQueryFileById(qId);
    }

    /**
     * 查询保单文件列表
     *
     * @param tbQueryFile 保单文件
     * @return 保单文件
     */
    @Override
    public List<TbQueryFile> selectTbQueryFileList(TbQueryFile tbQueryFile)
    {
        return tbQueryFileMapper.selectTbQueryFileList(tbQueryFile);
    }

    /**
     * 新增保单文件
     *
     * @param tbQueryFile 保单文件
     * @return 结果
     */
    @Override
    public int insertTbQueryFile(TbQueryFile tbQueryFile)
    {
        return tbQueryFileMapper.insertTbQueryFile(tbQueryFile);
    }

    /**
     * 修改保单文件
     *
     * @param tbQueryFile 保单文件
     * @return 结果
     */
    @Override
    public int updateTbQueryFile(TbQueryFile tbQueryFile)
    {
        return tbQueryFileMapper.updateTbQueryFile(tbQueryFile);
    }

    /**
     * 批量删除保单文件
     *
     * @param qIds 需要删除的保单文件ID
     * @return 结果
     */
    @Override
    public int deleteTbQueryFileByIds(Long[] qIds)
    {
        return tbQueryFileMapper.deleteTbQueryFileByIds(qIds);
    }

    /**
     * 删除保单文件信息
     *
     * @param qId 保单文件ID
     * @return 结果
     */
    @Override
    public int deleteTbQueryFileById(Long qId)
    {
        return tbQueryFileMapper.deleteTbQueryFileById(qId);
    }
}
