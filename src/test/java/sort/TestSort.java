package sort;

import com.google.common.base.Stopwatch;
import org.junit.Assert;
import org.junit.Test;
import org.tcrow.sort.Sort;
import org.tcrow.util.RandomUtil;

import java.math.BigDecimal;
import java.util.Arrays;


/**
 * @author tcrow.luo
 * @date 2018/8/20
 * @description
 */
public class TestSort {

//    final Integer[] arr = {813,718,782,286,584,676,103,252,734,16,587,836,521,33,844,830,654,892,718,985,96,178,868,752,474,914,741,598,674,727,435,907,280,865,678,803,138,903,137,582,709,267,667,133,287,154,397,811,223,590,691,179,790,26,54,275,311,391,533,453,375,271,929,505,496,968,842,445,73,76,555,247,581,19,674,225,467,549,363,499,56,427,240,986,684,273,835,762,133,408,277,826,41,246,896,332,864,815,850,837,628,742,156,203,265,149,45,122,658,155,388,695,441,130,947,759,329,465,45,994,384,932,61,254,852,296,282,955,164,478,589,667,784,299,286,767,152,972,761,108,532,801,604,890,563,878,566,609,926,713,611,153,675,562,52,156,516,751,17,739,890,407,339,403,169,26,278,384,269,955,17,514,732,858,430,903,102,307,918,433,445,492,737,911,425,495,732,362,740,982,328,42,403,522,941,644,528,170,233,356,352,859,298,993,384,620,280,714,816,113,423,110,78,787,101,371,707,796,622,902,772,615,107,296,713,438,217,360,542,615,283,15,134,843,743,374,635,548,156,979,63,930,808,274,514,534,425,224,440,796,14,498,669,417,34,213,30,658,923,807,771,954,91,148,319,957,413,195,674,173,904,362,204,289,510,49,334,98,558,505,836,410,576,385,704,860,923,654,788,780,198,958,165,200,531,915,660,758,755,801,627,711,808,231,985,362,709,268,972,446,238,266,343,558,974,189,223,280,742,503,620,770,37,394,86,160,556,29,797,108,817,960,711,816,73,48,52,930,477,325,357,607,564,28,762,737,612,776,259,976,843,407,918,713,197,53,971,460,674,331,239,578,98,422,721,800,439,154,60,748,807,722,121,13,469,111,78,33,498,798,881,703,837,12,783,950,172,100,506,686,926,534,644,244,493,736,943,539,972,555,601,604,970,681,235,674,284,449,314,507,285,95,547,969,355,222,222,89,450,196,169,661,979,914,959,530,931,559,147,318,905,1,241,192,661,220,314,172,214,890,558,500,746,559,91,137,349,897,808,729,392,763,373,786,278,319,568,872,76,719,511,141,659,409,572,283,339,31,702,70,92,238,320,797,933,17,563,353,929,75,570,278,280,0,313,987,759,588,886,824,54,805,188,272,472,514,854,68,515,860,359,423,931,328,451,496,535,581,703,339,369,741,130,83,705,170,82,319,102,622,707,621,853,147,589,716,762,987,833,250,170,338,821,68,614,961,885,286,515,775,603,800,737,201,587,158,450,590,643,442,476,192,37,108,70,895,609,301,686,447,119,441,959,525,95,396,210,479,425,682,618,297,983,395,868,261,851,419,258,126,133,489,414,678,800,366,411,573,240,8,112,890,501,339,77,117,403,117,79,546,172,121,117,732,694,325,459,290,945,646,423,821,645,289,112,435,810,846,748,197,777,400,476,630,707,616,679,655,166,869,120,162,292,362,875,640,965,537,373,47,104,74,782,908,789,432,543,97,285,100,870,385,695,714,571,950,50,517,114,951,87,125,624,629,822,699,946,796,505,880,780,768,90,235,407,861,994,688,883,252,929,19,227,354,519,543,389,635,750,427,433,224,405,773,927,11,824,403,127,880,782,947,469,424,846,437,850,134,236,6,281,560,807,332,348,836,227,866,800,731,801,909,640,33,11,436,665,852,965,856,680,806,97,694,516,120,258,562,745,310,249,375,348,960,647,991,329,892,464,376,136,871,34,658,276,125,481,655,791,634,589,542,695,758,544,627,816,883,894,244,615,370,337,313,104,154,821,269,673,219,177,288,902,36,674,18,515,471,913,652,681,281,199,419,720,246,749,541,313,154,82,421,536,908,939,133,391,824,84,346,112,24,651,955,609,166,57,798,388,454,237,7,844,293,115,938,132,890,501,873,501,196,616,818,94,90,37,893,638,312,749,295,45,871,92,298,985,369,59,953,670,193,908,674,352,718,270,744,447,963,803,972,888,666,300,822,761,331,895,841,299,339,739,162,178,838,734,992,851,437,523,698,318,121,70,329,473,284,934,607,444,659,645,420,372,30,557,848,201,746,862,374,73,293,232,744,227,934,569,501,307,565,363,237,514,282,794,436,164,97,983,534,548,447,134,567,971,36,143,969,954,12,34,500,461,783,794,603,633,105,860,49,610,97,296,54,685,921,866,714,328,963,490,218,60,513,962,324,115,807,430,57,765,628,458,595,623,141,715,244,2,568,618,336,94,682,122,869,928,102,779,100,114,160,743,813,342,345,410,586,642,96,122,734,166,637,936,117,991,408};
    final Comparable[] arr = generateArray();
    final int len = 10000;

    /**
     * 生成符合正态分布的数组
     *
     * @return
     */
    private Comparable[] gaussianRandom() {
        Comparable[] arr = new Comparable[len];
        for (int i = 0; i < len; i++) {
            arr[i] = new BigDecimal(getNumberInNormalDistribution(180, 10)).intValue();
        }
        return arr;
    }

    private double getNumberInNormalDistribution(double mean, double std_dev) {
        return mean + (randomNormalDistribution() * std_dev);
    }

    private double randomNormalDistribution() {
        double u, v, w, c;
        do {
            //获得两个（-1,1）的独立随机变量
            u = Math.random() * 2 - 1.0;
            v = Math.random() * 2 - 1.0;
            w = u * u + v * v;
        } while (w == 0.0 || w >= 1.0);
        //这里就是 Box-Muller转换
        c = Math.sqrt((-2 * Math.log(w)) / w);
        //返回2个标准正态分布的随机数，封装进一个数组返回
        //当然，因为这个函数运行较快，也可以扔掉一个
        //return [u*c,v*c];
        return u * c;
    }

    private Comparable[] generateArray() {
        Comparable[] arr = new Comparable[len];
        for (int i = 0; i < len; i++) {
            arr[i] = Integer.valueOf(RandomUtil.getIntegerBetween(0, len));
        }
        return arr;
    }

//    @Test
    public void testBubble() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Comparable[] result = Sort.sort(arr, null, "bubble");
        stopwatch.stop();
        System.out.println("bubble sort spend time:" + stopwatch.toString());
        Assert.assertTrue(Sort.isSorted(result));
    }

//    @Test
    public void testSimple() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Comparable[] result = Sort.sort(arr, null, "simple");
        stopwatch.stop();
        System.out.println("simple sort spend time:" + stopwatch.toString());
        Assert.assertTrue(Sort.isSorted(result));
    }

//    @Test
    public void testInsert() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Comparable[] result = Sort.sort(arr, null, "insert");
        stopwatch.stop();
        System.out.println("insert sort spend time:" + stopwatch.toString());
        Assert.assertTrue(Sort.isSorted(result));
    }

//    @Test
    public void testShell() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Comparable[] result = Sort.sort(arr, null, "shell");
        stopwatch.stop();
        System.out.println("shell sort spend time:" + stopwatch.toString());
        Assert.assertTrue(Sort.isSorted(result));
    }

    @Test
    public void testQuick() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Comparable[] result = Sort.sort(arr, null, "quick");
        stopwatch.stop();
        System.out.println("quick sort spend time:" + stopwatch.toString());
        Assert.assertTrue(Sort.isSorted(result));
    }

    @Test
    public void testQuick3Way() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Comparable[] result = Sort.sort(arr, null, "quick3way");
        stopwatch.stop();
        System.out.println("quick3way sort spend time:" + stopwatch.toString());
        Assert.assertTrue(Sort.isSorted(result));
    }

    @Test
    public void testMerge() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Comparable[] result = Sort.sort(arr, null, "merge");
        stopwatch.stop();
        System.out.println("merge sort spend time:" + stopwatch.toString());
        Assert.assertTrue(Sort.isSorted(result));
    }

    @Test
    public void testMergeBu() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Comparable[] result = Sort.sort(arr, null, "mergeBu");
        stopwatch.stop();
        System.out.println("bottom-up merge sort spend time:" + stopwatch.toString());
        Assert.assertTrue(Sort.isSorted(result));
    }

    @Test
    public void testSystemSort(){
        Stopwatch stopwatch = Stopwatch.createStarted();
        Arrays.sort(arr);
        stopwatch.stop();
        System.out.println("system sort spend time:" + stopwatch.toString());
        Assert.assertTrue(Sort.isSorted(arr));
    }

}
