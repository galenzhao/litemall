package org.linlinjava.litemall.core.notify;

public class AliyunSmsResult {
    private String Message;
    private String RequestId;
    private String BizId;
    private String Code;

//            {
//                "Message": "OK",
//                    "RequestId": "051C618E-AD6F-4F8E-AC85-48FCF33D4D9F",
//                    "BizId": "181520964393800467^0",
//                    "Code": "OK"
//            }


    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }

    public String getBizId() {
        return BizId;
    }

    public void setBizId(String bizId) {
        BizId = bizId;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }
}
