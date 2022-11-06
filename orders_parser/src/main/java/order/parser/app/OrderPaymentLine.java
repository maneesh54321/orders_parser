package order.parser.app;

public class OrderPaymentLine {

    private Integer no;

    private String line;

    public OrderPaymentLine(Integer no, String line) {
        this.no = no;
        this.line = line;
    }

    public Integer getNo() {
        return no;
    }

    public String getLine() {
        return line;
    }
}
