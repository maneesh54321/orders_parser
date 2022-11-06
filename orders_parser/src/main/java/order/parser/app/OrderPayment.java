package order.parser.app;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;

public class OrderPayment {
    @JsonAlias("orderId")
    private Integer id;
    private Double amount;
    private String currency;
    private String comment;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String filename;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer line;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String result;

    public OrderPayment(Integer id, Double amount, String currency, String comment) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.comment = comment;
    }

    public OrderPayment(String filename, Integer line) {
        this.filename = filename;
        this.line = line;
    }

    public OrderPayment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", comment='" + comment + '\'' +
                ", filename='" + filename + '\'' +
                ", line=" + line +
                ", result='" + result + '\'' +
                '}';
    }
}
