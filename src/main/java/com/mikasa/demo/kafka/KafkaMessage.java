package com.mikasa.demo.kafka;

import java.io.Serializable;

/**
 * Created by root2 on 16/8/10.
 */
public class KafkaMessage implements Serializable{

    private String code;
    private Object message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "KafkaMessage{" +
                "code='" + code + '\'' +
                ", message=" + message +
                '}';
    }
}
