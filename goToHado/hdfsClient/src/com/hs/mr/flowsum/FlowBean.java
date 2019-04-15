package com.hs.mr.flowsum;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 需要序列化传输数据,实现Writable接口即可
 */
public class FlowBean implements Writable {

    private Long upFlow;
    private Long dFlow;
    private Long sumFlow;

    /**
     * 需要一个无参构造，用于反序列化后反射调用。
     */
    public FlowBean() {
    }

    public FlowBean(Long upFlow, Long dFlow) {
        this.upFlow = upFlow;
        this.dFlow = dFlow;
        this.sumFlow = upFlow +dFlow;
    }

    public Long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(Long upFlow) {
        this.upFlow = upFlow;
    }

    public Long getdFlow() {
        return dFlow;
    }

    public void setdFlow(Long dFlow) {
        this.dFlow = dFlow;
    }

    public Long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(Long sumFlow) {
        this.sumFlow = sumFlow;
    }

    /**
     * 序列化对象
     * @param dataOutput
     * @throws IOException
     */
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(upFlow);
        dataOutput.writeLong(dFlow);
        dataOutput.writeLong(sumFlow);
    }

    /**
     * 反序列化
     * 注意:序列化和反序列化顺序要一致
     * @param dataInput
     * @throws IOException
     */
    @Override
    public void readFields(DataInput dataInput) throws IOException {
        upFlow = dataInput.readLong();
        dFlow = dataInput.readLong();
        sumFlow = dataInput.readLong();
    }

    @Override
    public String toString() {
        return "FlowBean{" +
                "upFlow=" + upFlow +
                ", dFlow=" + dFlow +
                ", sumFlow=" + sumFlow +
                '}';
    }
}
