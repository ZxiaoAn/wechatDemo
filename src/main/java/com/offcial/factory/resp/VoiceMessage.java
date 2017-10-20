package com.offcial.factory.resp;


import com.offcial.base.BaseMessageResp;

public class VoiceMessage extends BaseMessageResp {

    /**
     * 语音
     */
    private Voice Voice;

    public Voice getVoice() {
        return Voice;
    }

    public void setVoice(Voice voice) {
        Voice = voice;
    }

}
