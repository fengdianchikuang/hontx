package com.ruoyi.user.domain.req;

import lombok.Data;

@Data
public class WithdrawalListRequest {
    private Integer page;
    private Integer limit;
    private Long ownerId;
}
