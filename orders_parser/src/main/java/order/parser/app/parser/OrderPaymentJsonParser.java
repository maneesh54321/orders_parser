package order.parser.app.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import order.parser.app.OrderPayment;
import order.parser.app.OrderPaymentLine;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class OrderPaymentJsonParser implements Runnable{

    private final BlockingQueue<OrderPaymentLine> blockingQueue;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public OrderPaymentJsonParser(BlockingQueue<OrderPaymentLine> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            OrderPaymentLine line;
            try {
                line = blockingQueue.poll(200, TimeUnit.MILLISECONDS);
                if (line == null) {
                    break;
                } else {
                    OrderPayment orderPayment;
                    try {
                        orderPayment = OBJECT_MAPPER.readValue(line.getLine(), OrderPayment.class);
                        orderPayment.setFilename("orders.json");
                        orderPayment.setLine(line.getNo());
                        orderPayment.setResult("OK");
                    } catch (JsonProcessingException e) {
                        orderPayment = new OrderPayment();
                        orderPayment.setFilename("orders.json");
                        orderPayment.setLine(line.getNo());
                        orderPayment.setResult(e.getMessage());
                    }
                    System.out.println(orderPayment);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
