package learn.java;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockThreadPoolExecutor extends ThreadPoolExecutor {

    public BlockThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, int queueSize, String namePrefix) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, new LinkedBlockingQueue<>(queueSize), new BlockThreadFactory(namePrefix));
    }

    static class BlockThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        BlockThreadFactory() {
            this("block-pool-" + poolNumber.getAndIncrement() + "-thread");
        }

        BlockThreadFactory(String namePrefix) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.namePrefix = namePrefix + "-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }


    @Override
    public void execute(Runnable r) {
        try {
            super.execute(r);
        } catch (RejectedExecutionException e) {
            try {
                getQueue().put(r);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
