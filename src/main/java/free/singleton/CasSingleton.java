package free.singleton;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

// cas 单利 好处无锁算法性能高,缺点cas会引发cpu空转 消耗cpu
public class CasSingleton {
    public static final AtomicReference<CasSingleton> INSTANCE = new AtomicReference<>();

    public static final CasSingleton getInstance() {
        while (true) {
            CasSingleton casSingleton = INSTANCE.get();
            if (casSingleton != null) {
                return casSingleton;
            }
            casSingleton = new CasSingleton();
            // 比较并替换,只有在INSTANCE==null的情况下才替换,并且是线程安全的
            INSTANCE.compareAndSet(null, casSingleton);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        AtomicReference<CasSingleton> instance1 = new AtomicReference<>();
        AtomicReference<CasSingleton> instance2 = new AtomicReference<>();
        CountDownLatch latch = new CountDownLatch(2);
        new Thread(() -> {
            instance1.set(CasSingleton.getInstance());
            latch.countDown();
        }).start();
        new Thread(() -> {
            instance2.set(CasSingleton.getInstance());
            latch.countDown();
        }).start();
        latch.await();
        System.out.println(instance1.get() == instance2.get());
    }


}
