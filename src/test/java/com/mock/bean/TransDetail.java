package com.mock.bean;

import org.hibernate.validator.constraints.Length;

public class TransDetail {
    @Length(min = 5, max = 5, message = "平台流水号最大长度为5", groups = {update.class})
    private String sysTrxid;//平台流水号
    //    @Length(min = 5, max = 5, message = "平台流水号最大长度为5")
    @Length(min = 14, max = 14, groups = {update.class})
    private String msanTrxid;//外围系统流水号
    private String batchNo;//平台批次号
    private String detailNo;//平台批次明细号
    private String msanBatchNo;//外围系统批量批次号
    private String msanDetailNo;//外围系统批量明细号
    private String sfileName;//上送核心文件名
    private String acctType;//账户类型
    private String cardType;//卡折标志（1-卡 2-折号、账号）
    private String protocoNo;//协议号
    private String payAccNo;//付款账号
    private String payAccName;//付款人户名
    private String payIssinsCode;//付款人开户行代码
    private String payIssinsName;//付款人开户行名称
    private String payIssinsProvince;//付款人开户行所在省
    private String payIssinsCity;//付款人开户行所在市
    private String bankedAccNo;//收款人账号
    private String bankedIssinsCode;//收款人开户行代码
    private String bankedIssinsName;//收款人开户行户名
    private String payCertifTp;//付款人证件类型 (01-身份证  02-居住证  03-户口本)
    private String payCertifId;//付款人证件号码
    private String payPhoneNo;//付款人手机号
    private String expiryDate;//有效期
    private String cvn2;//CVN2
    private String currencycode;//币种
    private String txnAmt;//交易金额
    private String txnFee;//手续费
    private String txnStatus;//交易状态(00-交易初始化 01-交易成功 02-交易失败 03-状态未知 05-已撤销 06-已退款 07-交易受理成功 08-冲正失败)
    private String busiRspCode;//平台响应码
    private String busiRspMsg;//平台响应信息
    private String pssnCode;//支撑系统返回码
    private String pssnMsg;//支撑系统返回信息
    private String sysDate;//平台日期
    private String sysTime;//平台时间
    private String cdFlag;
    private String custType;
    private String remark;
    private String hostTxnid;
    private String hostTxnDate;
    private String misIndex;

    @Override
    public String toString() {
        return "TransDetail{" +
                "sysTrxid='" + sysTrxid + '\'' +
                ", msanTrxid='" + msanTrxid + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", detailNo='" + detailNo + '\'' +
                ", msanBatchNo='" + msanBatchNo + '\'' +
                ", msanDetailNo='" + msanDetailNo + '\'' +
                ", sfileName='" + sfileName + '\'' +
                ", acctType='" + acctType + '\'' +
                ", cardType='" + cardType + '\'' +
                ", protocoNo='" + protocoNo + '\'' +
                ", payAccNo='" + payAccNo + '\'' +
                ", payAccName='" + payAccName + '\'' +
                ", payIssinsCode='" + payIssinsCode + '\'' +
                ", payIssinsName='" + payIssinsName + '\'' +
                ", payIssinsProvince='" + payIssinsProvince + '\'' +
                ", payIssinsCity='" + payIssinsCity + '\'' +
                ", bankedAccNo='" + bankedAccNo + '\'' +
                ", bankedIssinsCode='" + bankedIssinsCode + '\'' +
                ", bankedIssinsName='" + bankedIssinsName + '\'' +
                ", payCertifTp='" + payCertifTp + '\'' +
                ", payCertifId='" + payCertifId + '\'' +
                ", payPhoneNo='" + payPhoneNo + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", cvn2='" + cvn2 + '\'' +
                ", currencycode='" + currencycode + '\'' +
                ", txnAmt='" + txnAmt + '\'' +
                ", txnFee='" + txnFee + '\'' +
                ", txnStatus='" + txnStatus + '\'' +
                ", busiRspCode='" + busiRspCode + '\'' +
                ", busiRspMsg='" + busiRspMsg + '\'' +
                ", pssnCode='" + pssnCode + '\'' +
                ", pssnMsg='" + pssnMsg + '\'' +
                ", sysDate='" + sysDate + '\'' +
                ", sysTime='" + sysTime + '\'' +
                ", cdFlag='" + cdFlag + '\'' +
                ", custType='" + custType + '\'' +
                ", remark='" + remark + '\'' +
                ", hostTxnid='" + hostTxnid + '\'' +
                ", hostTxnDate='" + hostTxnDate + '\'' +
                ", misIndex='" + misIndex + '\'' +
                '}';
    }

    public String getSysTrxid() {
        return sysTrxid;
    }

    public void setSysTrxid(String sysTrxid) {
        this.sysTrxid = sysTrxid;
    }

    public String getMsanTrxid() {
        return msanTrxid;
    }

    public void setMsanTrxid(String msanTrxid) {
        this.msanTrxid = msanTrxid;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getDetailNo() {
        return detailNo;
    }

    public void setDetailNo(String detailNo) {
        this.detailNo = detailNo;
    }

    public String getMsanBatchNo() {
        return msanBatchNo;
    }

    public void setMsanBatchNo(String msanBatchNo) {
        this.msanBatchNo = msanBatchNo;
    }

    public String getMsanDetailNo() {
        return msanDetailNo;
    }

    public void setMsanDetailNo(String msanDetailNo) {
        this.msanDetailNo = msanDetailNo;
    }

    public String getSfileName() {
        return sfileName;
    }

    public void setSfileName(String sfileName) {
        this.sfileName = sfileName;
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getProtocoNo() {
        return protocoNo;
    }

    public void setProtocoNo(String protocoNo) {
        this.protocoNo = protocoNo;
    }

    public String getPayAccNo() {
        return payAccNo;
    }

    public void setPayAccNo(String payAccNo) {
        this.payAccNo = payAccNo;
    }

    public String getPayAccName() {
        return payAccName;
    }

    public void setPayAccName(String payAccName) {
        this.payAccName = payAccName;
    }

    public String getPayIssinsCode() {
        return payIssinsCode;
    }

    public void setPayIssinsCode(String payIssinsCode) {
        this.payIssinsCode = payIssinsCode;
    }

    public String getPayIssinsName() {
        return payIssinsName;
    }

    public void setPayIssinsName(String payIssinsName) {
        this.payIssinsName = payIssinsName;
    }

    public String getPayIssinsProvince() {
        return payIssinsProvince;
    }

    public void setPayIssinsProvince(String payIssinsProvince) {
        this.payIssinsProvince = payIssinsProvince;
    }

    public String getPayIssinsCity() {
        return payIssinsCity;
    }

    public void setPayIssinsCity(String payIssinsCity) {
        this.payIssinsCity = payIssinsCity;
    }

    public String getBankedAccNo() {
        return bankedAccNo;
    }

    public void setBankedAccNo(String bankedAccNo) {
        this.bankedAccNo = bankedAccNo;
    }

    public String getBankedIssinsCode() {
        return bankedIssinsCode;
    }

    public void setBankedIssinsCode(String bankedIssinsCode) {
        this.bankedIssinsCode = bankedIssinsCode;
    }

    public String getBankedIssinsName() {
        return bankedIssinsName;
    }

    public void setBankedIssinsName(String bankedIssinsName) {
        this.bankedIssinsName = bankedIssinsName;
    }

    public String getPayCertifTp() {
        return payCertifTp;
    }

    public void setPayCertifTp(String payCertifTp) {
        this.payCertifTp = payCertifTp;
    }

    public String getPayCertifId() {
        return payCertifId;
    }

    public void setPayCertifId(String payCertifId) {
        this.payCertifId = payCertifId;
    }

    public String getPayPhoneNo() {
        return payPhoneNo;
    }

    public void setPayPhoneNo(String payPhoneNo) {
        this.payPhoneNo = payPhoneNo;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvn2() {
        return cvn2;
    }

    public void setCvn2(String cvn2) {
        this.cvn2 = cvn2;
    }

    public String getCurrencycode() {
        return currencycode;
    }

    public void setCurrencycode(String currencycode) {
        this.currencycode = currencycode;
    }

    public String getTxnAmt() {
        return txnAmt;
    }

    public void setTxnAmt(String txnAmt) {
        this.txnAmt = txnAmt;
    }

    public String getTxnFee() {
        return txnFee;
    }

    public void setTxnFee(String txnFee) {
        this.txnFee = txnFee;
    }

    public String getTxnStatus() {
        return txnStatus;
    }

    public void setTxnStatus(String txnStatus) {
        this.txnStatus = txnStatus;
    }

    public String getBusiRspCode() {
        return busiRspCode;
    }

    public void setBusiRspCode(String busiRspCode) {
        this.busiRspCode = busiRspCode;
    }

    public String getBusiRspMsg() {
        return busiRspMsg;
    }

    public void setBusiRspMsg(String busiRspMsg) {
        this.busiRspMsg = busiRspMsg;
    }

    public String getPssnCode() {
        return pssnCode;
    }

    public void setPssnCode(String pssnCode) {
        this.pssnCode = pssnCode;
    }

    public String getPssnMsg() {
        return pssnMsg;
    }

    public void setPssnMsg(String pssnMsg) {
        this.pssnMsg = pssnMsg;
    }

    public String getSysDate() {
        return sysDate;
    }

    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    public String getSysTime() {
        return sysTime;
    }

    public void setSysTime(String sysTime) {
        this.sysTime = sysTime;
    }

    public String getCdFlag() {
        return cdFlag;
    }

    public void setCdFlag(String cdFlag) {
        this.cdFlag = cdFlag;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getHostTxnid() {
        return hostTxnid;
    }

    public void setHostTxnid(String hostTxnid) {
        this.hostTxnid = hostTxnid;
    }

    public String getHostTxnDate() {
        return hostTxnDate;
    }

    public void setHostTxnDate(String hostTxnDate) {
        this.hostTxnDate = hostTxnDate;
    }

    public String getMisIndex() {
        return misIndex;
    }

    public void setMisIndex(String misIndex) {
        this.misIndex = misIndex;
    }
}
