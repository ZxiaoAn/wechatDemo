package com.offcial.factory.req;


import com.offcial.base.BaseMessageReq;

public class ImageMessage extends BaseMessageReq {

    /**
     * 图片链接
     */
    private String PicUrl;
    private String MediaId;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

}
