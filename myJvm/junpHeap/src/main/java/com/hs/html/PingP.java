package com.hs.html;

public class PingP {
    private String IP;
    private int packetLoss;
    private int aveMs;

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public int getPacketLoss() {
        return packetLoss;
    }

    public void setPacketLoss(int packetLoss) {
        this.packetLoss = packetLoss;
    }

    public int getAveMs() {
        return aveMs;
    }

    public void setAveMs(int aveMs) {
        this.aveMs = aveMs;
    }

    @Override
    public String toString() {
        return "PingP{" +
                "IP='" + IP + '\'' +
                ", packetLoss=" + packetLoss +
                ", aveMs=" + aveMs +
                '}';
    }
}
