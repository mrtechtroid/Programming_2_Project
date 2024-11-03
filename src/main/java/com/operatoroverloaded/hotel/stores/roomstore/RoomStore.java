package com.operatoroverloaded.hotel.stores.roomstore;

import java.util.List;

import com.operatoroverloaded.hotel.models.Room;


public interface RoomStore {
    public static RoomStore roomStore = null;
    public static RoomStore getInstance(){
        return roomStore;
    }
    public static void setInstance(RoomStore roomStore){
        roomStore = roomStore;
    }
    void addRoom(Room room);
    List<Room> getRooms();
    Room deleteRoom(int roomNumber);
    // void saveAll(); // To save to .tmp files for the in-memory version
    void saveToFile();
    void updateRoom(int roomId, Room room);
    Room findRoom(int roomId);
    void loadFromFile();
}