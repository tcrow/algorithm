import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.tcrow.Trie;

import java.io.IOException;
import java.io.RandomAccessFile;

public class TestTrie {

    String filePath = "D://testData/testData.txt";

    @Test
    public void test() {
        Trie trie = new Trie();
        trie.insertStr("I love China");
        trie.insertStr("I love China");
        trie.insertStr("I love China");
        trie.insertStr("Java is the best language");
        trie.insertStr("Java is the best language");
        trie.insertStr("Java is the best language");
        trie.insertStr("Java is the best language");
        trie.insertStr("Company");
        trie.insertStr("Company");
        trie.insertStr("Company");
        trie.insertStr("Company");
        trie.insertStr("Will");
        trie.insertStr("Congratulations");
        trie.insertStr("Lost the world");
        trie.insertStr("Big brother is watching you");
        trie.insertStr("Big brother is watching you");
        trie.insertStr("Java is the best language");
        trie.insertStr("Redis");
        trie.insertStr("Mysql");
        trie.insertStr("Mongo");
        trie.insertStr("And So On");
        trie.insertStr("Just Do It");
        trie.insertStr("Just Do It");
        trie.insertStr("Just Do It");
        trie.insertStr("Just Do It");
        trie.insertStr("Just Do It");
        trie.insertStr("Just Do It");
        trie.insertStr("Just Do It");
        trie.insertStr("Just Do It");
        trie.insertStr("Just Do It");
        trie.insertStr("Just Do It");
        trie.insertStr("lost the world");
        trie.printAllStr();
        System.out.println(JSONObject.toJSONString(trie.tops(10)));
        System.out.println(trie.countPrefix("what fuck? no this word?"));
        System.out.println(trie.countPrefix("I love China"));
    }

    @Test
    public void largeData() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(filePath,"rw");
//        Trie trie = new Trie();
//        for (int i = 0; i < 10000000; i++) {
//            System.out.println(i);
//            if(i%1000 == 0){
//                System.gc();
//            }
//            trie.insertStr(randomAccessFile.readLine());
//        }
//        trie.printAllStr();
    }
}
