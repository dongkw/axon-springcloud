package com.example.pledge;

import com.example.basic.Basic;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
public class Pledge extends Basic {


    private String instructionId;

    @ApiModelProperty(value = "全价", required = true, position = 4)
    @Column(name = "total_price", precision = 20, scale = 5)
    private BigDecimal totalPrice;

    private boolean multiOrder = true;

    @ApiModelProperty(value = "交易类型", required = true, position = 4)
    private String tradeType;

    @ApiModelProperty(value = "债券代码", required = true, position = 5)
    private String bondCode;
    @ApiModelProperty(value = "债券名称", required = true, position = 6)
    private String bondName;

    @ApiModelProperty(value = "到期收益率", required = true, position = 13)
    private String yieldToMaturity;

    @ApiModelProperty(value = "应计利息", required = true, position = 13)
    @JsonProperty("interest")
    @Column(name = "pre_interest", precision = 20, scale = 5)
    private BigDecimal preInterest;

    @ApiModelProperty(value = "债券名称", required = true, position = 8)
    private String accountNo;

    @ApiModelProperty(value = "票面总额", required = true, position = 11)
    private String bondValue;
    @ApiModelProperty(value = "净价", required = true, position = 12)
    private String netValue;

    @ApiModelProperty(value = "询价编号", required = true, position = 20)
    private String enquiryId;

    @ApiModelProperty(value = "累计交收量", required = true, position = 7)
    @Column(name = "total_settlement_volume", precision = 20, scale = 4)
    private long totalSettlementVolume;

    @ApiModelProperty(value = "本方21位码", required = true, position = 7)
    private String ourPartyAccountNo;


    @ApiModelProperty(value = "对手方ID", required = true, position = 4)
    private String counterPartyId;
    @ApiModelProperty(value = "对手方机构ID，21位", required = true, position = 4)
    private String counterPartyOrgId;
    @ApiModelProperty(value = "对手方机构名称", required = true, position = 5)
    private String counterPartyOrgName;

    @ApiModelProperty(value = "对手方产品ID", required = true, position = 6)
    private String counterPartyProductId;
    @ApiModelProperty(value = "对手方产品名称", required = true, position = 7)
    private String counterPartyProductName;

    @ApiModelProperty(value = "对手方交易员ID", required = true, position = 8)
    private String counterPartyTraderId;
    @ApiModelProperty(value = "对手方交易员名称", required = true, position = 9)
    private String counterPartyTraderName;

    @ApiModelProperty(value = "清算类型 6-净额清算 13-全额清算", required = true, position = 10)
    private int clearingType = 6;
    @ApiModelProperty(value = "清算类型名称", required = true, position = 7)
    private String clearingTypeName;
    @ApiModelProperty(value = "结算方式 1-券款对付 2-见券付款 3-见款付券", required = true, position = 11)
    private int paymentType;
    @ApiModelProperty(value = "结算日期", required = true, position = 11)
    private String paymentDate;

    @ApiModelProperty(value = "请求有效时间", position = 11)
    private String validTime;

    private boolean isPassive;

    @ApiModelProperty(value = "报价类型 1-对话报价 2-请求报价", required = true, position = 12)
    private int quoteType;

    @ApiModelProperty(value = "审批单Id", required = false)
    private long approvalSheetId;

    @ApiModelProperty(value = "创建人", required = true)
    private String createBy;

    @ApiModelProperty(value = "投资分类")
    @Enumerated(EnumType.ORDINAL)
    private int investmentCategory = 0;

    @ApiModelProperty(value = "席位号", required = true, position = 20)
    private String seatNo;
    @ApiModelProperty(value = "证券账户", required = true, position = 20)
    private String securityAccount;
    @ApiModelProperty(value = "基金ID", required = true, position = 20)
    private String fundId;
    @ApiModelProperty(value = "证券编号（外部编号，6位码）", required = true, position = 20)
    private String fundCode;
    @ApiModelProperty(value = "产品名称", required = true, position = 20)
    private String fundName;

    @ApiModelProperty(value = "组合ID", required = true, position = 2)
    private String portfolioId;
    @ApiModelProperty(value = "组合名称", required = true, position = 3)
    private String portfolioName;

    @ApiModelProperty(value = "清算速度 0~N， 前端显示T+N", required = true, position = 7)
    private int  clearingLatency;

    @ApiModelProperty(value = "交易所", required = true, position = 4)
    private String exchangeCode;

    @ApiModelProperty(value = "证券类型 0-银行间债券 1-股票 2-期货 3-场外质押式回购", required = true, position = 4)
    @Column(name = "security_type")
    private int securityType = 0;

    @ApiModelProperty(value = "交易方向 数值参照协议OrderSide", required = true, position = 7)
    private int direction;

    @ApiModelProperty(value = "证券代码", required = true, position = 4)
    private String securityCode;

    @ApiModelProperty(value = "证券名称", required = true, position = 4)
    private String securityName;

    @ApiModelProperty(value = "指令价格", required = true, position = 8)
    private String price;

    @ApiModelProperty(value = "指令总量", required = true, position = 9)
    private long volume = 0L;

    @ApiModelProperty(value = "指令金额", required = true, position = 10)
    private String amount;

    @ApiModelProperty(value = "交易日期", required = true, position = 16)
    private String tradeDate;

    @ApiModelProperty(value = "成交均价", required = true, position = 18)
    private String averageFillPrice;

    @ApiModelProperty(value = "委托进度", required = true, position = 11)
    private BigDecimal entrustRatio;

    @ApiModelProperty(value = "成交进度", required = true, position = 12)
    private BigDecimal fillRatio;

    @ApiModelProperty(value = "指令剩余数量", required = true, position = 13)
    private long leftVolume;

    @ApiModelProperty(value = "指令剩余金额", required = true, position = 13)
    private String leftAmount;

    @ApiModelProperty(value = "累计委托数量", required = true, position = 14)
    private long totalEntrustedVolume;

    @ApiModelProperty(value = "累计委托金额", required = true, position = 15)
    private long totalEntrustedAmount;

    @ApiModelProperty(value = "累计成交数量", required = true, position = 16)
    private long totalFilledVolume;

    @ApiModelProperty(value = "累计成交金额", required = true, position = 17)
    private String totalFilledAmount = "0.00";

    @ApiModelProperty(value = "累计挂单数量", required = true, position = 17)
    private long totalEntrustingVolume;

    @ApiModelProperty(value = "累计挂单数量", required = true, position = 17)
    private String totalEntrustingAmount;


    @ApiModelProperty(value = "意向编号", required = true, position = 20)
    private String intentionId;
    @ApiModelProperty(value = "意向下达人ID", required = true, position = 20)
    private String intentionCommanderId;

    @ApiModelProperty(value = "外部系统类型")
    private int vendorSystemType = 0;

    @ApiModelProperty(value = "外部系统指令ID")
    private String vendorInstructionId;



    @ApiModelProperty(value = "错误消息", required = false, position = 26)
    private String errMsg;

    private boolean isAutoDispatch;



    @ApiModelProperty("触犯风控")
    List<String> instructionViolations;
}
