package com.ruoyi.vehicle.domain;

import lombok.Data;

@Data
public class VehicleInsrncEntity {

    private Long id;

    private String vehicleNo;

    private String rackNo;

    private String insrncCode;

    private String glassType;

    private String amount;

    private String franchiseFlag;

    private String deductPremium;

    private String nonDeductPremium;

    private String beforePremium;

    private String rate;

    private String premium;

    private String queryId;
}
