package learn.java;

import org.junit.Test;

import java.util.concurrent.LinkedTransferQueue;

public class LinkedTransferQueueTest {
    @Test
    public void transfer() throws InterruptedException {
        LinkedTransferQueue<String> q = new LinkedTransferQueue<>();
        q.transfer("test");
    }
}
