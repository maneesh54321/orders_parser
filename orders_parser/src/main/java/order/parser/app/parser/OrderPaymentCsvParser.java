package order.parser.app.parser;

import order.parser.app.OrderPayment;
import order.parser.app.OrderPaymentLine;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class OrderPaymentCsvParser implements Runnable {

    private final BlockingQueue<OrderPaymentLine> blockingQueue;


    public OrderPaymentCsvParser(BlockingQueue<OrderPaymentLine> blockingQueue) {
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
                        String[] tokens = line.getLine().split(",");
                        if (tokens.length != 4) {
                            throw new Exception("Invalid line!!");
                        }
                        orderPayment = new OrderPayment(Integer.parseInt(tokens[0]), Double.parseDouble(tokens[1]), tokens[2], tokens[3]);
                        orderPayment.setFilename("orders.csv");
                        orderPayment.setLine(line.getNo());
                        orderPayment.setResult("OK");
                    } catch (Exception e) {
                        orderPayment = new OrderPayment();
                        orderPayment.setFilename("orders.csv");
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
