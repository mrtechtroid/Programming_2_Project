package com.operatoroverloaded.hotel.cli;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.stereotype.Component;

import com.operatoroverloaded.hotel.stores.roomstore.RoomStore;

@Component
@ConditionalOnNotWebApplication // Only run when the application is NOT a web application
public class ConsoleApplication implements CommandLineRunner {
    private RoomStore roomStore;

    public void setRoomStore(RoomStore roomStore) {
        this.roomStore = roomStore;
    }

    @Override
    public void run(String... args) {
        System.out.println("************************** CONSOLE APP *********************************");
        handleInput();
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private void handleInput() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nHotel Management System");
            System.out.println("Enter a command: ");
            System.out.println("1. Room Management");
            System.out.println("2. Customer Management");
            System.out.println("3. Restaurant Management");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    roomManagement();
                    break;
                case 2:
                    customerManagement();
                    break;
                case 3:
                    restaurantManagement();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private void roomManagement() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nRoom Management");
        System.out.println("1. Create Room");
        System.out.println("2. Book Room");
        System.out.println("3. Check Out Room");
        System.out.println("4. Back");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                // createRoom();
                System.out.println("Creating Room...");
                break;
            case 2:
                // bookRoom();
                System.out.println("Booking Room...");
                break;
            case 3:
                // checkOutRoom();
                System.out.println("Checking Out Room...");
                break;
            case 4:
                break;
            default:
                System.out.println("Invalid choice!");
        }

    }

    // ----------CUSTOMER MANAGMENT--------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private void customerManagement() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nCustomer Management");
        System.out.println("1. Create Customer");
        System.out.println("2. Add to Waiting List");
        System.out.println("3. Serve Customer");
        System.out.println("4. Back");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                // createCustomer();
                System.out.println("Creating Customer...");
                break;
            case 2:
                // addToWaitingList();
                System.out.println("Adding to Waiting List...");
                break;
            case 3:
                // serveCustomer();
                System.out.println("Serving Customer...");
                break;
            case 4:
                break;
            default:
                System.out.println("Invalid choice!");

        }
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private void restaurantManagement() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nRestaurant Management");
        System.out.println("1. Add Dish to Menu");
        System.out.println("2. Remove Dish from Menu");
        System.out.println("3. Generate Bill");
        System.out.println("4. Back");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                // addDishToMenu();
                System.out.println("Adding Dish to Menu...");
                break;
            case 2:
                // removeDishFromMenu();
                System.out.println("Removing Dish from Menu...");
                break;
            case 3:
                // generateBill();
                System.out.println("Generating Bill...");
                break;
            case 4:
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
}
// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// private void addRoom() {
// Scanner scanner = new Scanner(System.in);
// System.out.println("Enter room number: ");
// int roomNumber = scanner.nextInt();
// scanner.nextLine(); // Consume newline

// System.out.println("Enter room type: ");
// String roomType = scanner.nextLine();

// Room room = new Room(roomNumber, roomType);
// objectStore.addRoom(room); // Add to object store
// System.out.println("Room added successfully.");
// }

// private void viewRooms() {
// System.out.println("Listing all rooms:");
// for (Room room : objectStore.getRooms()) {
// System.out.println("Room Number: " + room.getRoomNumber() + ", Room Type: " +
// room.getRoomType());
// }
// }
