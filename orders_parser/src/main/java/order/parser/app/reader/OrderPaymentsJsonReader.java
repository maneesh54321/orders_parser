package order.parser.app.reader;

import order.parser.app.OrderPaymentLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class OrderPaymentsJsonReader implements Runnable{

    private final BlockingQueue<OrderPaymentLine> blockingQueue;

    private final String path;

    public OrderPaymentsJsonReader(BlockingQueue<OrderPaymentLine> blockingQueue, String path) {
        this.blockingQueue = blockingQueue;
        this.path = path;
    }

    @Override
    public void run() {
        Path jsonFilePath = FileSystems.getDefault().getPath(this.path);
        AtomicInteger lineNo = new AtomicInteger(0);

        try(BufferedReader br = Files.newBufferedReader(jsonFilePath)){
            Stream<String> lines = br.lines();
            lines.forEach(line -> blockingQueue.offer(new OrderPaymentLine(lineNo.incrementAndGet(), line)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
