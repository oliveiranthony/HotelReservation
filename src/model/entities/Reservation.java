package model.entities;
import model.exceptions.DomainException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
    private Integer roomNumber;
    private Date checkin;
    private Date checkout;

    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Reservation() {
    }
    public Reservation(Integer roomNumber, Date checkin, Date checkout) throws DomainException {
        if(!checkout.after(checkin)) {
            throw new DomainException("Check-out date must be after check-in date") ;
        }
        this.roomNumber = roomNumber;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    } public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }
    public Date getCheckin() {
        return checkin;
    }
    public Date getCheckout() {
        return checkout;
    }

    public long duration() {
        long diff = checkout.getTime() - checkin.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public void updateDates(Date checkin, Date checkout) throws DomainException{
        if(checkin.before(this.checkin) || checkout.before(this.checkout)) {
            throw new DomainException("Dates for update after check-in date") ;
        } if(!checkout.after(checkin)) {
            throw new DomainException("Check-out date must be after check-in date") ;
        }
        this.checkin = checkin;
        this.checkout = checkout;
    }

    @Override //toString é uma sobreposição
    public String toString() {
        return "Room: " + roomNumber
                + ", check-in: " + sdf.format(checkin)
                + ", check-out: " + sdf.format(checkout)
                + ", duration: " + duration() + " nights";
    }
}
