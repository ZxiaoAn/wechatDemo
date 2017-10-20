package com.offcial.factory.req;


import com.offcial.base.BaseMessageReq;

public class VideoMessage extends BaseMessageReq {

    /**
     * 媒体ID
     */
    private String MediaId;
    /**
     * 语音格式
     */
    private String ThumbMediaId;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }

}
