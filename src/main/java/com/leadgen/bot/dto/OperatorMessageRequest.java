package com.leadgen.bot.dto;

public class OperatorMessageRequest {
    private String text;

    public OperatorMessageRequest() {}

    public OperatorMessageRequest(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
