package com.leadgen.bot.dto;

import java.util.List;

public class ThreadsResponse {
    private List<ThreadDto> threads;
    private long totalCount;

    public ThreadsResponse() {}

    public ThreadsResponse(List<ThreadDto> threads, long totalCount) {
        this.threads = threads;
        this.totalCount = totalCount;
    }

    public List<ThreadDto> getThreads() {
        return threads;
    }

    public void setThreads(List<ThreadDto> threads) {
        this.threads = threads;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
