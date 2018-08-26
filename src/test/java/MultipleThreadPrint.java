import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author pp
 * @date 2018/8/21
 * @description
 */
public class MultipleThreadPrint {

    private static int str;

    private static class PrintThread implements Runnable{

        private Object object;

        public PrintThread(Object object){
            this.object = object;
        }

        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                synchronized (object){
                    if(str == 0){
                        System.out.println("a");
                        str = 1;
                        object.notifyAll();
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else if(str == 1){
                        System.out.println("b");
                        str = 2;
                        object.notifyAll();
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        System.out.println("c");
                        str = 0;
                        object.notifyAll();
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }



        }
    }

    public static void main(String[] args) {
        Object ob = new Object();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new PrintThread(ob));
        executorService.execute(new PrintThread(ob));
        executorService.execute(new PrintThread(ob));
        executorService.shutdown();

    }

}
