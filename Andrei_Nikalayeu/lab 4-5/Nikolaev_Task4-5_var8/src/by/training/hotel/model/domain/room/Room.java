package by.training.hotel.model.domain.room;

import java.math.BigDecimal;

import by.training.hotel.model.domain.Entity;

public class Room extends Entity{
    private String roomNumber;
    private RoomType roomType;
    private byte seats;
    private BigDecimal price;
    
    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public byte getSeats() {
        return seats;
    }

    public void setSeats(byte seats) {
        this.seats = seats;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Room [roomNumber=" + roomNumber + ", roomType=" + roomType + ", seats=" + seats + ", price=" + price 
                + "]";
    }
}


