package com.geektcp.common.metric.model;


/**
 * @author tanghaiyang 021/7/7 11:14
 */
public class MonitorInfoVo {

    private long totalMemory;

    private long freeMemory;

    private long maxMemory;

    private long totalMemorySize;

    private long freePhysicalMemorySize;

    private long usedMemory;

    private int activeThread;

    private long totalThread;

    private double cpuRatio;


    public long getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(long totalMemory) {
        this.totalMemory = totalMemory;
    }

    public long getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(long freeMemory) {
        this.freeMemory = freeMemory;
    }

    public long getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(long maxMemory) {
        this.maxMemory = maxMemory;
    }

    public long getTotalMemorySize() {
        return totalMemorySize;
    }

    public void setTotalMemorySize(long totalMemorySize) {
        this.totalMemorySize = totalMemorySize;
    }

    public long getFreePhysicalMemorySize() {
        return freePhysicalMemorySize;
    }

    public void setFreePhysicalMemorySize(long freePhysicalMemorySize) {
        this.freePhysicalMemorySize = freePhysicalMemorySize;
    }

    public long getUsedMemory() {
        return usedMemory;
    }

    public void setUsedMemory(long usedMemory) {
        this.usedMemory = usedMemory;
    }

    public int getActiveThread() {
        return activeThread;
    }

    public void setActiveThread(int activeThread) {
        this.activeThread = activeThread;
    }

    public long getTotalThread() {
        return totalThread;
    }

    public void setTotalThread(long totalThread) {
        this.totalThread = totalThread;
    }

    public double getCpuRatio() {
        return cpuRatio;
    }

    public void setCpuRatio(double cpuRatio) {
        this.cpuRatio = cpuRatio;
    }
}
