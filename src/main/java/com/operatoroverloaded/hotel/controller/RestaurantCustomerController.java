package com.operatoroverloaded.hotel.controller;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.operatoroverloaded.hotel.models.DateTime;
import com.operatoroverloaded.hotel.models.RestaurantCustomer;
import com.operatoroverloaded.hotel.stores.restaurantcustomerstore.RestaurantCustomerStore;

@RestController
@RequestMapping("/api/resturantcustomer")
public class RestaurantCustomerController {
    private final RestaurantCustomerStore restaurantCustomerStore;
    public RestaurantCustomerController(){
        this.restaurantCustomerStore = RestaurantCustomerStore.getInstance();
    }
    @GetMapping("/{customerId}")
    public ResponseEntity<?> getBill(@PathVariable int customerId) {
        RestaurantCustomer customer = restaurantCustomerStore.getCustomer(customerId);
        if (customer == null) {
            return ResponseEntity.status(404).body("Customer not found");
        }
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCustomer(@RequestBody JsonNode json) {
        JsonNode dishesNode = json.get("dishes"); // ArrayNode
        int tabelId = json.get("tableId").asInt();
        int serverId = -1;
        ArrayList<Integer> dishes = new ArrayList<>();
        for (JsonNode dish : dishesNode) {
            dishes.add(dish.asInt());
        }
        DateTime reservedTo = DateTime.fromISOString("2024-11-30T19:10:00.000Z");
        DateTime reservedFrom = DateTime.fromISOString("2024-11-30T19:10:00.000Z");

        ArrayList<Integer> bills = new ArrayList<>();
        double bill_amt = 0;
        double bill_left = 0;
        double bill_payed = 0;
        String address = "mail";
        String phone = json.get("phone").asText();
        String email = "mail@customer.in";
        String name = json.get("name").asText();
        RestaurantCustomer customer = new RestaurantCustomer(name, email, phone, address, bill_amt, bill_payed, bill_left, bills, reservedFrom, reservedTo, dishes, tabelId, serverId);

        restaurantCustomerStore.addCustomer(customer);
        return ResponseEntity.ok().body("Done"); //it will return the id of the customer added
        // return ResponseEntity.ok().body(restaurantCustomerStore.addCustomer(customer)); //it will return the id of the customer added
    }
    @PostMapping("/remove/{customerId}")
    public ResponseEntity<?> removeBill(@PathVariable int customerId) {
        restaurantCustomerStore.deleteCustomer(customerId);
        return ResponseEntity.ok().body("Customer removed successfully");
    }
    @PostMapping("/update/{customerId}")
    public ResponseEntity<?> updateCustomer(@PathVariable int customerId, @RequestBody JsonNode json) {
        JsonNode dishesNode = json.get("dishes"); // ArrayNode
        int tabelId = json.get("tableId").asInt();
        int serverId = json.get("serverId").asInt();
        ArrayList<Integer> dishes = new ArrayList<>();
        for (JsonNode dish : dishesNode) {
            dishes.add(dish.asInt());
        }
        String reservedToNode = json.get("reservedTo").asText();
        String reservedFromNode = json.get("reservedFrom").asText();
        DateTime reservedFrom = DateTime.fromISOString(reservedFromNode);
        DateTime reservedTo = DateTime.fromISOString(reservedToNode);
        JsonNode billsNode = json.get("bills"); // ArrayNode
        ArrayList<Integer> bills = new ArrayList<>();
        for (JsonNode bill : billsNode) {
            bills.add(bill.asInt());
        }
        double bill_amt = json.get("billAmount").asDouble();
        double bill_left = json.get("billLeft").asDouble();
        double bill_payed = json.get("billPayed").asDouble();
        String address = json.get("address").asText();
        String phone = json.get("phone").asText();
        String email = json.get("email").asText();
        String name = json.get("name").asText();
        RestaurantCustomer customer = new RestaurantCustomer(name, email, phone, address, bill_amt, bill_payed, bill_left, bills, reservedFrom, reservedTo, dishes, tabelId, serverId);
        restaurantCustomerStore.updateCustomer(customerId, customer);
        return ResponseEntity.ok().body("Customer details updated successfully");
    }
    @GetMapping("/list")
    public ResponseEntity<?> getAllCustomers(){
        ArrayList<RestaurantCustomer> customers = restaurantCustomerStore.getCustomers();
        return ResponseEntity.ok().body(customers);
    }
    @PostMapping("/cleartable/{customerId}")
    public ResponseEntity<?> clearTable(@PathVariable int customerId) {
        restaurantCustomerStore.getCustomer(customerId).setTableId(-1);
        return ResponseEntity.ok().body("Table cleared successfully");
    }
}
