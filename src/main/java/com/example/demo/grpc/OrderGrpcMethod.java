package com.example.demo.grpc;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Item;
import com.example.order.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.List;

public class OrderGrpcMethod {
    int port = 7080;

    public void orderReport(){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", port).usePlaintext().build();
        orderServiceGrpc.orderServiceBlockingStub stub = orderServiceGrpc.newBlockingStub(channel);

        GetResponseOrder response = stub.getOrder(GetRequestOrder.newBuilder().build());
        channel.shutdown();

        System.out.println("Order Report:");
        List<ProtoOrder> orders = response.getOrderList();

        for (ProtoOrder protoOrder: orders) {
            System.out.println(protoOrder);
        }
    }

    public void orderedItemReport(){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", port).usePlaintext().build();
        orderServiceGrpc.orderServiceBlockingStub stub = orderServiceGrpc.newBlockingStub(channel);

        GetResponseOrderedItem response = stub.getOrderedThing(GetRequestOrderedItem.newBuilder().build());
        channel.shutdown();

        System.out.println("Ordered Thing Report:");
        List<ProtoOrderedItem> orderedThings = response.getOrderedItemsList();

        for (ProtoOrderedItem protoOrderedThing: orderedThings) {
            System.out.println(protoOrderedThing);
        }
    }



    public void makeOrder(Customer customer, List<Item> bucketForCustomer){
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("127.0.0.1", port)
                .usePlaintext()
                .build();
        orderServiceGrpc.orderServiceBlockingStub stub = orderServiceGrpc.newBlockingStub(channel);

        ProtoCustomer protoCustomer = ProtoCustomer.newBuilder()
                .setFirstName(customer.getFirstName())
                .setLastName(customer.getLastName())
                .build();

        for (Item item: bucketForCustomer){
            ProtoItem protoItem = ProtoItem.newBuilder()
                    .setName(item.getName())
                    .setPrice(item.getPrice())
                    .build();
            stub.create(CreateRequest.newBuilder()
                    .addItems(protoItem)
                    .build());
        }
        stub.create(CreateRequest.newBuilder()
                .setCustomer(protoCustomer)
                .build());
        channel.shutdown();

    }

}
