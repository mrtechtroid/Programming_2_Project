package com.operatoroverloaded.hotel.stores.hotelcustomerstore;

import java.util.ArrayList;

import com.operatoroverloaded.hotel.models.HotelCustomer;

public abstract class HotelCustomerStore {
    public static HotelCustomerStore hotelCustomerStore = null;

    public static HotelCustomerStore getInstance() {
        return hotelCustomerStore;
    }

    public static void setInstance(HotelCustomerStore hotelCustomerStore) {
        HotelCustomerStore.hotelCustomerStore = hotelCustomerStore;
    }
    public abstract void loadFromFile();
    public abstract void storeToFile();
    public abstract HotelCustomer addCustomer(HotelCustomer customer);
    public abstract void deleteCustomer(int id);
    public abstract ArrayList<HotelCustomer> getCustomers();
    public abstract HotelCustomer getCustomer(int id);
    public abstract int getCustomerId(HotelCustomer customer);
    public abstract void updateCustomer(int id, HotelCustomer customer);
} 
