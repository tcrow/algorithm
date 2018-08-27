package org.tcrow;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Trie树
 *
 * @author tcrow.luo
 */
@NotThreadSafe
public class Trie {

    /**
     * 树深度
     */
    private int depth = 0;

    /**
     * 节点总数
     */
    private int nodeNum = 0;

    /**
     * 子节点数，这里设置为支持全部ASCII码178个字符，如果确保输入字符串只有字母可改为26个字符
     */
    private final int SIZE = 178;

    private TrieNode root;

    /**
     * 构造Trie树初始化数据
     */
    public Trie() {
        this.nodeNum = 0;
        this.depth = 0;
        this.root = new TrieNode();
    }


    /**
     * 子类节点
     */
    private class TrieNode {
        private int passed;
        private int ended;
        private boolean isEnd;
        private char value;
        private TrieNode parent;
        private TrieNode[] children;

        TrieNode() {
            this.passed = 0;
            this.ended = 0;
            this.children = new TrieNode[SIZE];
        }

        TrieNode(TrieNode parent) {
            this.passed = 0;
            this.ended = 0;
            this.parent = parent;
            this.children = new TrieNode[SIZE];
        }

    }


    /**
     * 插入字符串
     *
     * @param str
     * @return
     */
    public boolean insertStr(String str) {
        char[] strArr = str.toCharArray();
        TrieNode current = this.root;
        for (char c : strArr) {
            if (null == current.children[c]) {
                current.children[c] = new TrieNode(current);
                current.children[c].value = c;
                current.children[c].passed = 1;
                this.nodeNum++;
            } else {
                current.children[c].passed++;
            }
            current = current.children[c];
        }
        current.isEnd = true;
        current.ended++;
        if (strArr.length > this.depth) {
            this.depth = strArr.length;
        }

        return true;
    }

    /**
     * 统计字符串出现次数
     *
     * @param str
     * @return
     */
    public int countPrefix(String str) {
        TrieNode current = this.root;
        char[] strArr = str.toCharArray();
        for (char c : strArr) {
            if (null == current.children[c]) {
                return 0;
            } else {
                current = current.children[c];
            }

        }
        return current.ended;
    }

    /**
     * 统计出现次数最多的字符串
     *
     * @param rank 名次数
     * @return
     */
    public String[] tops(int rank) {
        TrieNode current = this.root;
        LinkedList<Integer> topTimes = new LinkedList<>();
        LinkedList<String> result = new LinkedList<>();
        ergodic(topTimes, result, current, rank);
        List<String> retList = new ArrayList<>();
        int length = rank > result.size() ? result.size() : rank;
        for (int i = 0; i < length; i++) {
            retList.add("单词：" + result.get(i) + "，词频：" + topTimes.get(i));
        }
        return retList.toArray(new String[rank]);
    }


    /**
     * TOP统计递归
     *
     * @param topTimes 词频次数链表
     * @param result   字符串链表
     * @param current  当前节点
     * @param rank     统计项目数
     */
    private void ergodic(LinkedList<Integer> topTimes, LinkedList<String> result, TrieNode current, int rank) {
        TrieNode[] children = current.children;
        for (TrieNode child : children) {
            if (null != child) {
                if (child.ended > 0) {
                    if (topTimes.size() == 0) {
                        topTimes.add(child.ended);
                        result.add(getStr(child));
                    } else {
                        for (int i = 0; i < topTimes.size(); i++) {
                            if (topTimes.get(i).intValue() > child.ended) {
                                continue;
                            }
                            topTimes.add(i, child.ended);
                            result.add(i, getStr(child));
                            if (topTimes.size() > rank) {
                                topTimes.removeLast();
                                result.removeLast();
                            }
                            break;
                        }
                    }
                }
                if (child.passed > 0) {
                    ergodic(topTimes, result, child, rank);
                }
            }
        }

    }

    /**
     * 获取节点字符串
     *
     * @param current
     * @return
     */
    private String getStr(TrieNode current) {
        String str = new String(new char[]{current.value});
        if (this.root.equals(current.parent)) {
            return str;
        } else {
            return getStr(current.parent) + str;
        }
    }

    /**
     * 遍历节点
     *
     * @param current
     */
    private void ergodic(TrieNode current) {
        TrieNode[] children = current.children;
        String word;
        for (TrieNode child : children) {
            if (null != child) {
                if (child.ended > 0) {
                    word = getStr(child);
                    System.out.println("单词:" + word + ",词频:" + countPrefix(word));
                }
                if (child.passed > 0) {
                    ergodic(child);
                }
            }
        }
    }

    /**
     * 遍历整个树，打印出现的字符串
     */
    public void printAllStr() {
        TrieNode current = this.root;
        ergodic(current);
    }

}
