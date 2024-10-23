
import data.Customer;
import data.Dataset;
import data.Event;
import data.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataProcessor {

    public Result processDataset(Dataset dataset) {
        Map<String, Long> usageMap = new HashMap<>();

        List<Event> events = dataset.getEvents().stream()
                .sorted()
                .collect(Collectors.toList());


        for (Event event : events) {
            String customerId = event.getCustomerId();
            if (event.getEventType().equals("start")) {
                usageMap.put(customerId, usageMap.getOrDefault(customerId, 0L) - event.getTimestamp());
            } else if (event.getEventType().equals("stop")) {
                usageMap.put(customerId, usageMap.getOrDefault(customerId, 0L) + event.getTimestamp());
            }
        }

        List<Customer> customers = usageMap.entrySet().stream()
                .map(entry -> new Customer(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return new Result(customers);
    }
}