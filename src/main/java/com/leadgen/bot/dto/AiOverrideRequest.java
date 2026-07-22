package com.leadgen.bot.dto;

public class AiOverrideRequest {
    private String handlerMode; // human_attention, ai_assistant, pending
    private String overrideReason;

    public AiOverrideRequest() {}

    public AiOverrideRequest(String handlerMode, String overrideReason) {
        this.handlerMode = handlerMode;
        this.overrideReason = overrideReason;
    }

    public String getHandlerMode() {
        return handlerMode;
    }

    public void setHandlerMode(String handlerMode) {
        this.handlerMode = handlerMode;
    }

    public String getOverrideReason() {
        return overrideReason;
    }

    public void setOverrideReason(String overrideReason) {
        this.overrideReason = overrideReason;
    }
}
