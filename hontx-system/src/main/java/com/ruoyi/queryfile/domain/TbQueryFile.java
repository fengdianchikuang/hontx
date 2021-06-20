package com.ruoyi.queryfile.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 保单文件对象 tb_query_file
 * 
 * @author ruoyi
 * @date 2021-04-12
 */
public class TbQueryFile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long qId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String queryId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String fileType;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String fileName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String path;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date qCreateTime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date qUpdateTime;

    public void setqId(Long qId) 
    {
        this.qId = qId;
    }

    public Long getqId() 
    {
        return qId;
    }
    public void setQueryId(String queryId) 
    {
        this.queryId = queryId;
    }

    public String getQueryId() 
    {
        return queryId;
    }
    public void setFileType(String fileType) 
    {
        this.fileType = fileType;
    }

    public String getFileType() 
    {
        return fileType;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setPath(String path) 
    {
        this.path = path;
    }

    public String getPath() 
    {
        return path;
    }
    public void setqCreateTime(Date qCreateTime) 
    {
        this.qCreateTime = qCreateTime;
    }

    public Date getqCreateTime() 
    {
        return qCreateTime;
    }
    public void setqUpdateTime(Date qUpdateTime) 
    {
        this.qUpdateTime = qUpdateTime;
    }

    public Date getqUpdateTime() 
    {
        return qUpdateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("qId", getqId())
            .append("queryId", getQueryId())
            .append("fileType", getFileType())
            .append("fileName", getFileName())
            .append("path", getPath())
            .append("qCreateTime", getqCreateTime())
            .append("qUpdateTime", getqUpdateTime())
            .toString();
    }
}
