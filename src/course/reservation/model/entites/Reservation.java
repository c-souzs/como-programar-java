package course.reservation.model.entites;

import course.reservation.model.exceptions.DomainException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
    private int numberRoom;
    private Date checkIn;
    private Date checkOut;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Reservation(int numberRoom, Date checkIn, Date checkOut) throws DomainException {
        if(!checkOut.after(checkIn)) {
            throw new DomainException("Check-out date must be after check-in date");
        }

        this.numberRoom = numberRoom;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public long duration() {
        long diff = checkOut.getTime() - checkIn.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public void updateDates(Date checkIn, Date checkOut) throws DomainException {
        Date now = new Date();

        if(checkIn.before(now) || checkOut.before(now)) {
            throw new DomainException("Reservation dates for update must be future dates");
        }
        if(!checkOut.after(checkIn)) {
            throw new DomainException("Check-out date must be after check-in date");
        }

        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public int getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(int numberRoom) {
        this.numberRoom = numberRoom;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    @Override
    public String toString() {
        return String.format("Room %d, check-in: %s, check-out: %s, %d nights",
                numberRoom,
                sdf.format(checkIn),
                sdf.format(checkOut),
                duration());
    }
}
