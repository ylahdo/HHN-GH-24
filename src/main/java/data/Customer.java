package data;

public class Customer {

    private String customerId;
    private long consumption; //

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", consumption=" + consumption +
                '}';
    }

    public Customer(String customerId, long consumption) {
        this.customerId = customerId;
        this.consumption = consumption;
    }


}
