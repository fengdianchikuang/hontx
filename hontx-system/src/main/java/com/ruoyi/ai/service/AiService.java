package com.ruoyi.ai.service;

import com.ruoyi.ai.domain.vo.DrivingFrontVo;
import com.ruoyi.ai.domain.vo.IdCardVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 图像识别相关接口
 */
public interface AiService {
    /**
     * 识别身份证
      * @param file
     * @param id_card_side
     * @return
     */
  IdCardVo aiIdCard(MultipartFile file, String id_card_side);

    /**
     * 获取百度访问令牌
      * @return
     */
  String getAccessToken() throws IOException;

    /**
     * 身份证识别
     * @param file
     * @param id_card_front
     * @return
     */
    IdCardVo distinguishIdCard(MultipartFile file, String id_card_front);

    /**
     * 驾驶证识别
     * @param file
     * @param driving_front
     * @return
     */
    DrivingFrontVo distinguDriving(MultipartFile file, String driving_front);

}
