package com.chiyu.domin;

public class DorLog {

    private String DateTime; //刷卡时间
    private Integer TerminalID;//终端机编号
    private Integer DoorNo;//终端机下的门号
    private Integer LogIndex;//终端机下的记录流水号，超过65535后从1开始
    private String UserID;// 用户编号
    private String EmployeeID;//工号
    private String CardNo;//卡号
    private String EventAlarmCode;//事件描述
    private String InOutIndication;//进出方向描述
    private String VerificationSource;//验证方式描述
    private String LogArrivalDateTime;//记录存入数据库的时间
    private Integer EventID;//事件ID
    private Integer InOutID;//进出方向ID
    private Integer VerificationID;//验证方式ID
    private Integer ID;//流水号
    private String IsExported; //是否导出过
    private Integer FKeyID;//功能键ID

    public DorLog() {
    }

    public DorLog(String DateTime, Integer TerminalID, Integer DoorNo, Integer LogIndex, String UserID, String EmployeeID, String CardNo, String EventAlarmCode, String InOutIndication, String VerificationSource, String LogArrivalDateTime, Integer EventID, Integer InOutID, Integer VerificationID, Integer ID, String IsExported, Integer FKeyID) {
        this.DateTime = DateTime;
        this.TerminalID = TerminalID;
        this.DoorNo = DoorNo;
        this.LogIndex = LogIndex;
        this.UserID = UserID;
        this.EmployeeID = EmployeeID;
        this.CardNo = CardNo;
        this.EventAlarmCode = EventAlarmCode;
        this.InOutIndication = InOutIndication;
        this.VerificationSource = VerificationSource;
        this.LogArrivalDateTime = LogArrivalDateTime;
        this.EventID = EventID;
        this.InOutID = InOutID;
        this.VerificationID = VerificationID;
        this.ID = ID;
        this.IsExported = IsExported;
        this.FKeyID = FKeyID;
    }

    /**
     * 获取
     * @return DateTime
     */
    public String getDateTime() {
        return DateTime;
    }

    /**
     * 设置
     * @param DateTime
     */
    public void setDateTime(String DateTime) {
        this.DateTime = DateTime;
    }

    /**
     * 获取
     * @return TerminalID
     */
    public Integer getTerminalID() {
        return TerminalID;
    }

    /**
     * 设置
     * @param TerminalID
     */
    public void setTerminalID(Integer TerminalID) {
        this.TerminalID = TerminalID;
    }

    /**
     * 获取
     * @return DoorNo
     */
    public Integer getDoorNo() {
        return DoorNo;
    }

    /**
     * 设置
     * @param DoorNo
     */
    public void setDoorNo(Integer DoorNo) {
        this.DoorNo = DoorNo;
    }

    /**
     * 获取
     * @return LogIndex
     */
    public Integer getLogIndex() {
        return LogIndex;
    }

    /**
     * 设置
     * @param LogIndex
     */
    public void setLogIndex(Integer LogIndex) {
        this.LogIndex = LogIndex;
    }

    /**
     * 获取
     * @return UserID
     */
    public String getUserID() {
        return UserID;
    }

    /**
     * 设置
     * @param UserID
     */
    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    /**
     * 获取
     * @return EmployeeID
     */
    public String getEmployeeID() {
        return EmployeeID;
    }

    /**
     * 设置
     * @param EmployeeID
     */
    public void setEmployeeID(String EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    /**
     * 获取
     * @return CardNo
     */
    public String getCardNo() {
        return CardNo;
    }

    /**
     * 设置
     * @param CardNo
     */
    public void setCardNo(String CardNo) {
        this.CardNo = CardNo;
    }

    /**
     * 获取
     * @return EventAlarmCode
     */
    public String getEventAlarmCode() {
        return EventAlarmCode;
    }

    /**
     * 设置
     * @param EventAlarmCode
     */
    public void setEventAlarmCode(String EventAlarmCode) {
        this.EventAlarmCode = EventAlarmCode;
    }

    /**
     * 获取
     * @return InOutIndication
     */
    public String getInOutIndication() {
        return InOutIndication;
    }

    /**
     * 设置
     * @param InOutIndication
     */
    public void setInOutIndication(String InOutIndication) {
        this.InOutIndication = InOutIndication;
    }

    /**
     * 获取
     * @return VerificationSource
     */
    public String getVerificationSource() {
        return VerificationSource;
    }

    /**
     * 设置
     * @param VerificationSource
     */
    public void setVerificationSource(String VerificationSource) {
        this.VerificationSource = VerificationSource;
    }

    /**
     * 获取
     * @return LogArrivalDateTime
     */
    public String getLogArrivalDateTime() {
        return LogArrivalDateTime;
    }

    /**
     * 设置
     * @param LogArrivalDateTime
     */
    public void setLogArrivalDateTime(String LogArrivalDateTime) {
        this.LogArrivalDateTime = LogArrivalDateTime;
    }

    /**
     * 获取
     * @return EventID
     */
    public Integer getEventID() {
        return EventID;
    }

    /**
     * 设置
     * @param EventID
     */
    public void setEventID(Integer EventID) {
        this.EventID = EventID;
    }

    /**
     * 获取
     * @return InOutID
     */
    public Integer getInOutID() {
        return InOutID;
    }

    /**
     * 设置
     * @param InOutID
     */
    public void setInOutID(Integer InOutID) {
        this.InOutID = InOutID;
    }

    /**
     * 获取
     * @return VerificationID
     */
    public Integer getVerificationID() {
        return VerificationID;
    }

    /**
     * 设置
     * @param VerificationID
     */
    public void setVerificationID(Integer VerificationID) {
        this.VerificationID = VerificationID;
    }

    /**
     * 获取
     * @return ID
     */
    public Integer getID() {
        return ID;
    }

    /**
     * 设置
     * @param ID
     */
    public void setID(Integer ID) {
        this.ID = ID;
    }

    /**
     * 获取
     * @return IsExported
     */
    public String getIsExported() {
        return IsExported;
    }

    /**
     * 设置
     * @param IsExported
     */
    public void setIsExported(String IsExported) {
        this.IsExported = IsExported;
    }

    /**
     * 获取
     * @return FKeyID
     */
    public Integer getFKeyID() {
        return FKeyID;
    }

    /**
     * 设置
     * @param FKeyID
     */
    public void setFKeyID(Integer FKeyID) {
        this.FKeyID = FKeyID;
    }

    public String toString() {
        return "DorLog{DateTime = " + DateTime + ", TerminalID = " + TerminalID + ", DoorNo = " + DoorNo + ", LogIndex = " + LogIndex + ", UserID = " + UserID + ", EmployeeID = " + EmployeeID + ", CardNo = " + CardNo + ", EventAlarmCode = " + EventAlarmCode + ", InOutIndication = " + InOutIndication + ", VerificationSource = " + VerificationSource + ", LogArrivalDateTime = " + LogArrivalDateTime + ", EventID = " + EventID + ", InOutID = " + InOutID + ", VerificationID = " + VerificationID + ", ID = " + ID + ", IsExported = " + IsExported + ", FKeyID = " + FKeyID + "}";
    }
}
