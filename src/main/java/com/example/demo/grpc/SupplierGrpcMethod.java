package com.example.demo.grpc;

import com.example.demo.entities.Supplier;
import com.example.demo.entities.Item;
import com.example.demo.entities.dto.SupplyDTO;
import com.example.seller.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.ArrayList;
import java.util.List;


public class SupplierGrpcMethod {
    int port = 7080;
    public void supplierReport(){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", port).usePlaintext().build();
        supplierServiceGrpc.supplierServiceBlockingStub stub = supplierServiceGrpc.newBlockingStub(channel);

        GetResponseSupplier response = stub.getSuppliers(GetRequestSupplier.newBuilder().build());
        channel.shutdown();

        System.out.println("Supplier Report:");
        List<ProtoSupplier> suppliers = response.getSuppliersList();

        for (ProtoSupplier protoSupplier: suppliers) {
            System.out.println(protoSupplier);
        }
    }

    public List<Item> itemReport(){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", port).usePlaintext().build();
        supplierServiceGrpc.supplierServiceBlockingStub stub = supplierServiceGrpc.newBlockingStub(channel);

        GetResponseItem response = stub.getItems(GetRequestItem.newBuilder().build());
        channel.shutdown();

        System.out.println("Item Report:");
        List<ProtoItem> items = response.getItemsList();

        List<Item> itemsForSale = new ArrayList<>();
        for (ProtoItem protoItem: items) {
            Item item = new Item(protoItem.getName(),protoItem.getPrice());
            itemsForSale.add(item);
            System.out.println(protoItem);
        }
        return itemsForSale;
    }

    public void addItemsOnSite(Supplier supplier, List<Item> addedItems, List<Integer> quantities){
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("127.0.0.1", port)
                .usePlaintext()
                .build();
        supplierServiceGrpc.supplierServiceBlockingStub stub = supplierServiceGrpc.newBlockingStub(channel);

        SupplyDTO supplyDTO = new SupplyDTO();
        supplyDTO.setSupplier(supplier);
        supplyDTO.setItem(addedItems);
        supplyDTO.setItemQuantities(quantities);
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String serveJsonStr = gson.toJson(supplyDTO);

        stub.create(CreateRequest.newBuilder().setServeJson(serveJsonStr).build());
        channel.shutdown();
    }

}
