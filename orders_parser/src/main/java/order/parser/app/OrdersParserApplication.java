package order.parser.app;

import order.parser.app.parser.OrderPaymentCsvParser;
import order.parser.app.parser.OrderPaymentJsonParser;
import order.parser.app.reader.OrderPaymentsCsvReader;
import order.parser.app.reader.OrderPaymentsJsonReader;

import java.util.concurrent.*;

public class OrdersParserApplication {

	public static void main(String[] args) {

		BlockingQueue<OrderPaymentLine> jsonQueue = new LinkedBlockingDeque<>();
		OrderPaymentsJsonReader orderPaymentsJsonReader = new OrderPaymentsJsonReader(jsonQueue, args[1]);

		OrderPaymentJsonParser jsonParser1 = new OrderPaymentJsonParser(jsonQueue);
		OrderPaymentJsonParser jsonParser2 = new OrderPaymentJsonParser(jsonQueue);
		OrderPaymentJsonParser jsonParser3 = new OrderPaymentJsonParser(jsonQueue);
		OrderPaymentJsonParser jsonParser4 = new OrderPaymentJsonParser(jsonQueue);

		BlockingQueue<OrderPaymentLine> csvQueue = new LinkedBlockingDeque<>();
		OrderPaymentsCsvReader orderPaymentsCsvReader = new OrderPaymentsCsvReader(csvQueue, args[0]);


		OrderPaymentCsvParser csvParser1 = new OrderPaymentCsvParser(csvQueue);
		OrderPaymentCsvParser csvParser2 = new OrderPaymentCsvParser(csvQueue);
		OrderPaymentCsvParser csvParser3 = new OrderPaymentCsvParser(csvQueue);
		OrderPaymentCsvParser csvParser4 = new OrderPaymentCsvParser(csvQueue);

		ExecutorService executorService = Executors.newFixedThreadPool(10);

		try {
			executorService.execute(orderPaymentsJsonReader);
			executorService.execute(jsonParser1);
			executorService.execute(jsonParser2);
			executorService.execute(jsonParser3);
			executorService.execute(jsonParser4);


			executorService.execute(orderPaymentsCsvReader);
			executorService.execute(csvParser1);
			executorService.execute(csvParser2);
			executorService.execute(csvParser3);
			executorService.execute(csvParser4);
		} finally {
			executorService.shutdown();
		}

	}

}
