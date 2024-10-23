package data;

import java.util.List;

public class Result {

    public List<Customer> getResult() {
        return result;
    }

    public void setResult(List<Customer> result) {
        this.result = result;
    }

    private List<Customer> result;

    public Result(List<Customer> customers) {
        this.result = customers;
    }


}
