package com.ruoyi.life.domain;

import lombok.Data;

import java.util.List;

@Data
public class LifeProductVo {
    private String product_name;
    private String product_no;
    private String product_supplier_name;
    private String title;
    private List<String> subtitles;
    private String company_logo;
    private String min_price;
}
