package com.offcial.factory.resp;


import com.offcial.base.BaseMessageResp;

public class VideoMessage extends BaseMessageResp {

    /**
     * 视频
     */
    private Video Video;

    public Video getVideo() {
        return Video;
    }

    public void setVideo(Video video) {
        Video = video;
    }

}
