package org.rjankowski.services;

import org.rjankowski.dto.Guest;
import org.rjankowski.dto.Reservation;
import org.rjankowski.dto.Room;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EconomyRoomManager {

    public List<Room> bookRooms(final List<Guest> guests, final int quantityFreeEconomyRooms, final int reservedPremiumRooms) {
        List<Room> reservedRooms = new ArrayList<>();
        int quantityAvailableFreeEconomyRooms = quantityFreeEconomyRooms;
        for (int i = reservedPremiumRooms; i < guests.size(); i++) {
            if (guests.get(i).getPrice().compareTo(Reservation.MINIMAL_PRICE_PREMIUM_ROOM) < 0 && quantityAvailableFreeEconomyRooms > 0) {
                reservedRooms.add(new Room(guests.get(i).getPrice()));
                quantityAvailableFreeEconomyRooms--;
            }
        }
        return reservedRooms;
    }
}
