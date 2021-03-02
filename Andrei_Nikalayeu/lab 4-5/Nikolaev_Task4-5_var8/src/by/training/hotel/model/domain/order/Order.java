package by.training.hotel.model.domain.order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import by.training.hotel.model.domain.room.RoomType;
import by.training.hotel.model.domain.user.User;
import by.training.hotel.model.domain.room.Room;
import by.training.hotel.model.domain.Entity;

public class Order extends Entity{
    private LocalDateTime creationDate;
    private User user;
    private byte roomSeats;
    private RoomType roomType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Room room;
    
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public byte getRoomSeats() {
        return roomSeats;
    }

    public void setRoomSeats(byte roomSeats) {
        this.roomSeats = roomSeats;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getTotalNights() {
        Period period = Period.between(startDate, endDate);
        return period.getDays();
    }

    public BigDecimal getTotalPrice() {
        BigDecimal itemCost  = BigDecimal.ZERO;
        BigDecimal totalPrice = BigDecimal.ZERO;

        int nights = (!(getTotalNights() == 0)) ? getTotalNights() : 1;

        if (getRoom() != null) {
            itemCost  = getRoom().getPrice().multiply(new BigDecimal(nights));
            totalPrice = totalPrice.add(itemCost);
        }
        return totalPrice;
    }

    public List<LocalDate> getDatesList(){
        return Stream.iterate(this.startDate, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(this.startDate, this.endDate))
                .collect(Collectors.toList());
    }

    public boolean isIntersect(Order order) {
        for (LocalDate localDate : this.getDatesList()) {
            for (LocalDate localDate2 : order.getDatesList()) {
                if (localDate.equals(localDate2)) {
                    return true;
                } 
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = prime * result + ((room == null) ? 0 : room.hashCode());
        result = prime * result + roomSeats;
        result = prime * result + ((roomType == null) ? 0 : roomType.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        if (creationDate == null) {
            if (other.creationDate != null)
                return false;
        } else if (!creationDate.equals(other.creationDate))
            return false;
        if (endDate == null) {
            if (other.endDate != null)
                return false;
        } else if (!endDate.equals(other.endDate))
            return false;
        if (room == null) {
            if (other.room != null)
                return false;
        } else if (!room.equals(other.room))
            return false;
        if (roomSeats != other.roomSeats)
            return false;
        if (roomType == null) {
            if (other.roomType != null)
                return false;
        } else if (!roomType.equals(other.roomType))
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Order [creationDate=" + creationDate + ", user=" + user + ", roomSeats=" + roomSeats + ", roomType="
                + roomType + ", startDate=" + startDate + ", endDate=" + endDate + ", room=" + room + "]";
    }
}
