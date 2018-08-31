package org.tcrow.compute;

/**
 * @author tcrow.luo
 *         计算养老金
 */
public class ComputePension {
    /**
     * 计算养老金
     *
     * @param year    储蓄年限
     * @param perSave 每年储蓄额
     * @param rate    无风险收益率  默认为5%
     * @return
     */
    public static int compute(int year, int perSave, int rate) {
        int amt = 0;
        for (int i = 0; i < year; i++) {
            amt += perSave * Math.pow((1 + (rate - 0.0) / 100), i);
        }
        return amt;
    }
}
