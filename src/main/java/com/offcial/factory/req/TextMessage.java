package com.offcial.factory.req;


import com.offcial.base.BaseMessageReq;

public class TextMessage extends BaseMessageReq {

    /**
     * 消息内容
     */
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

}
