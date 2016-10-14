package com.luo.magiclamp.entity;

import java.io.Serializable;

/**
 * Joke
 * <p/>
 * Created by luoyingxing on 16/10/14.
 */
public class Joke implements Serializable {
    private int showapi_res_code;
    private String showapi_res_error;
    private JokeBody showapi_res_body;

    public Joke() {
    }

    public int getShowApiResCode() {
        return showapi_res_code;
    }

    public void setShowApiResCode(int code) {
        this.showapi_res_code = code;
    }

    public String getShowApiResError() {
        return showapi_res_error;
    }

    public void setShowApiResError(String error) {
        this.showapi_res_error = error;
    }

    public JokeBody getShowApiResBody() {
        return showapi_res_body;
    }

    public void setShowApiResBody(JokeBody body) {
        this.showapi_res_body = body;
    }
}
