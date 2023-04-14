package free.singleton;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

// 枚举单例推荐
public enum EnumSingleton {
    INSTANCE;

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicReference<EnumSingleton> instance1 = new AtomicReference<>();
        AtomicReference<EnumSingleton> instance2 = new AtomicReference<>();
        CountDownLatch latch = new CountDownLatch(2);
        new Thread(() -> {
            instance1.set(EnumSingleton.getInstance());
            latch.countDown();
        }).start();
        new Thread(() -> {
            instance2.set(EnumSingleton.getInstance());
            latch.countDown();
        }).start();
        latch.await();
        System.out.println(instance1.get() == instance2.get());
    }
}
