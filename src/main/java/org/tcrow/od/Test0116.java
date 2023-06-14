package org.tcrow.od;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 聚餐地点
 * 题目描述
 * 小华和小为是很好的朋友，他们约定周末一起吃饭，
 * 通过手机交流，他们在地图上选择了很多聚餐地点
 * （由于自然地形等原因，部分聚餐地点不可达），
 * 求小华和小为能能达到的聚餐地点有多少个。
 * <p>
 * 输入描述
 * 第一行输入m和n，m表示地图长度，n表示地图宽度
 * 第二行开始具体输入地图信息，地图信息包括
 * 0为通畅的道路
 * 1为障碍物（且仅1为障碍物）
 * 2为小华或小为，地图中必定有且仅有两个（非障碍物）
 * 3为被选中的聚餐地点（非障碍物）
 * <p>
 * 输出描述
 * 可以两方都到达的聚餐地点的数量，行末无空格
 * <p>
 * 示例一
 * 输入
 * 4 4
 * 2 1 0 3
 * 0 1 2 1
 * 0 3 0 0
 * 0 0 0 0
 * 输出
 * 2
 * 说明
 * 第一行输入地图的长宽为三和四
 * 第二行开始为具体的地图，其中：
 * 3代表小华和小明的聚餐地点；
 * 2：代表小华或小明（确保有两个）；
 * 0代表可以通行的位置；
 * 1代表不可以出行的位置。
 * 此时两者都能达到的聚餐位置有两处
 * <p>
 * 备注：
 * 地图长宽为m和n,4 <= m <= 100 ，4 <= n <= 100
 * 聚餐的地点数量为k，则1 < k <= 100
 */
public class Test0116 {
    static class UnionFindSet {
        private int count;
        private int[] fa;

        public UnionFindSet(int n) {
            this.count = n;
            fa = new int[n];
            for (int i = 0; i < n; i++) {
                fa[i] = i;
            }
        }

        public int find(int x) {
            if (fa[x] != x) {
                fa[x] = find(fa[x]);
            }
            return fa[x];
        }

        public void merge(int x, int y) {
            int x_fa = find(x);
            int y_fa = find(y);

            if (x_fa != y_fa) {
                fa[y_fa] = x_fa;
                count--;
            }
        }
        public int count() {
            return count;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] matrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        UnionFindSet ufs = new UnionFindSet(n * m);

        List<Integer> huawei = new ArrayList<>(); // 记录小华位置
        List<Integer> restaurants = new ArrayList<>(); // 记录聚餐地点位置

        int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 偏移量

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] != 1) { // 如果是障碍物则不处理
                    int x = i * m + j; // 将二维坐标转换为一维坐标
                    if (matrix[i][j] == 2) huawei.add(x); // 记录小华位置
                    else if (matrix[i][j] == 3) restaurants.add(x); // 记录聚餐地点位置

                    for (int[] offset : offsets) { // 遍历四个方向
                        int newI = i + offset[0];
                        int newJ = j + offset[1];
                        if (newI >= 0 && newI < n && newJ >= 0 && newJ < m && matrix[newI][newJ] != 1) {
                            ufs.merge(x, newI * m + newJ); // 合并连通块
                        }
                    }
                }
            }
        }
        System.out.println(ufs.count());
        int hua_fa = ufs.find(huawei.get(0)); // 获取小华所在连通块的祖先
        int wei_fa = ufs.find(huawei.get(1)); // 获取小为所在连通块的祖先

        if (hua_fa != wei_fa) { // 如果小华和小为不在同一个连通块中，说明没有共同到达的聚餐地点
            System.out.println(0);
            return;
        }

        int ans = 0;
        for (int restaurant : restaurants) { // 遍历所有聚餐地点
            if (ufs.find(restaurant) == hua_fa) { // 如果聚餐地点和小华在同一个连通块中
                ans++; // 答案加一
            }
        }

        System.out.println(ans); // 输出答案
    }
}
