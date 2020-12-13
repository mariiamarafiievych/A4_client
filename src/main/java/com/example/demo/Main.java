package com.example.demo;

import com.example.demo.entities.*;
import com.example.demo.entities.dto.CreateOrderDTO;
import com.example.demo.grpc.CustomerGrpcMethod;
import com.example.demo.grpc.OrderGrpcMethod;
import com.example.demo.grpc.SupplierGrpcMethod;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    private static final String URL = "http://localhost:7080";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    private static final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    public static void main(String[] args) {

        Supplier supplier1 = new Supplier("Masha111", "Orl11");
        List<Item> addedThings = createItemList(supplier1);
        List<Integer> quantities = Arrays.asList(1,2,3,4,5);

        //gRPC
        System.out.println("gRPC:");

        CustomerGrpcMethod customers = new CustomerGrpcMethod();
        customers.customerReport();
        Customer customer1 = customers.createCustomer("Sasha111Grpc", "DiduhGrpc");
        Customer customer2 = customers.createCustomer("Cris111Grpc", "DktyrGrpc");
        customers.customerReport();

        SupplierGrpcMethod sellers = new SupplierGrpcMethod();
        sellers.supplierReport();

        sellers.addItemsOnSite(supplier1, addedThings, quantities);
        sellers.supplierReport();
        List<Item> thingsForSale = sellers.itemReport();

        OrderGrpcMethod orders = new OrderGrpcMethod();

        orders.orderReport();
        orders.orderedItemReport();

        List<Item> bucketForCustomer1 = new ArrayList<>(thingsForSale.subList(0, 2));
        makeOrder(customer1, bucketForCustomer1);
        List<Item> bucketForCustomer2 = new ArrayList<>(thingsForSale.subList(2, 5));
        makeOrder(customer2, bucketForCustomer2);

        orders.orderReport();
        orders.orderedItemReport();


//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        //added items on site
//        addThingsOnSite(seller1, addedItems, quantities);
//
//        //get items from DB
//        ResponseEntity<ItemssDTO> response2 = restTemplate
//                .exchange(URL + "/items", HttpMethod.GET, headersEntity, ItemsDTO.class);
//        List<Item> items = Objects.requireNonNull(response2.getBody()).getItems();
//        System.out.println(items);
//
//        //get things from DB
//        response2 = restTemplate
//                .exchange(URL + "/items", HttpMethod.GET, headersEntity, ItemsDTO.class);
//        List<Item> itemsForSale = Objects.requireNonNull(response2.getBody()).getItems();
//        System.out.println(itemsForSale);
//
//
//        Customer customer1 = new Customer( "Sasha1", "Diduh");
//        Customer customer2 = new Customer( "Cris1", "Dktyr");
//
//        //creating orders
//        System.out.println("Trying to create new order");
//        List<Item> bucketForCustomer1 = new ArrayList<>(itemsForSale.subList(0, 2));
//        makeOrder(customer1, bucketForCustomer1);
//
//        System.out.println("Trying to create new order");
//        List<Item> bucketForCustomer2 = new ArrayList<>(itemsForSale.subList(2, 5));
//        makeOrder(customer2, bucketForCustomer2);
//
//        //get items from DB
//        response2 = restTemplate
//                .exchange(URL + "/items", HttpMethod.GET, headersEntity, ItemsDTO.class);
//
//        //get customers from DB
//        ResponseEntity<Customer[]> response4 = restTemplate
//                .exchange(URL + "/customers", HttpMethod.GET, headersEntity, Customer[].class);
//        System.out.println("___________________________________________" + "\nCustomers: ");
//        List<Customer> customers = Arrays.asList(Objects.requireNonNull(response4.getBody()));
//        System.out.println(customers);
//        System.out.println("\n___________________________________________");
//
//        ResponseEntity<Order[]> response5 = restTemplate
//                .exchange(URL + "/orders", HttpMethod.GET, headersEntity, Order[].class);
//        System.out.println("___________________________________________" + "\nOrders: ");
//        List<Order> orders = Arrays.asList(Objects.requireNonNull(response5.getBody()));
//        System.out.println(orders);
//        System.out.println("\n___________________________________________");
    }

    private static void makeOrder(Customer customer, List<Item> bucketForCustomer) {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO();
        createOrderDTO.setCustomer(customer);
        createOrderDTO.setItems(bucketForCustomer);
        HttpEntity<CreateOrderDTO> createOrder = new HttpEntity<>(createOrderDTO);
        ResponseEntity<Void> response4 = restTemplate
                .exchange(URL + "/orders", HttpMethod.POST,
                        createOrder, Void.class);
    }

    public static List<Item> createItemList(Supplier supplier) {

        Item item1 = new Item("bodyCreamEvene", 12);
        Item item2 = new Item("shampooGarnier", 41);
        Item item3 = new Item("balmGarnier", 52);
        Item item4 = new Item("lotionMrscrub", 61);
        Item item5 = new Item("CreamLaroche", 57);

        return Arrays.asList(item1, item2, item3, item4, item5);
    }


//    private static void addItemsOnSite(Supplier supplier1, List<Item> addedItems, List<Integer> quantities) {
//        SupplyDTO supplyDTO = new SupplyDTO();
//        supplyDTO.setSupplier(supplier1);
//        supplyDTO.setItem(addedItems);
//        supplyDTO.setItemQuantities(quantities);
//        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
//        String serveJsonStr = gson.toJson(supplyDTO);
//
//        HttpEntity<String> serveJson = new HttpEntity<>(serveJsonStr, headers);
//        ResponseEntity<Void> response1 = restTemplate
//                .exchange(URL + "/supplier", HttpMethod.POST, serveJson, Void.class);
//
//        System.out.println("Supplier " + seller1.getLastName() + " has added " + addedItems);
//    }
}
