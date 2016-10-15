package com.luo.magiclamp.entity;

import java.io.Serializable;

/**
 * ChatList
 * <p/>
 * Created by Administrator on 2016/10/16.
 */
public class ChatList implements Serializable {
    private String text; //我可以在会议中给你帮助的机器人
    private String text_speed; //"5"
    private String text_pitch; //"5"
    private String text_sound; //"0"

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextSpeed() {
        return text_speed;
    }

    public void setTextSpeed(String textSpeed) {
        this.text_speed = textSpeed;
    }

    public String getTextPitch() {
        return text_pitch;
    }

    public void setTextPitch(String textPitch) {
        this.text_pitch = textPitch;
    }

    public String getTextSound() {
        return text_sound;
    }

    public void setTextSound(String texSound) {
        this.text_sound = texSound;
    }
}
