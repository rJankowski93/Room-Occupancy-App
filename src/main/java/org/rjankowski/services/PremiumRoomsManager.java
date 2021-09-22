package org.rjankowski.services;

import org.rjankowski.dto.Guest;
import org.rjankowski.dto.Reservation;
import org.rjankowski.dto.Room;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PremiumRoomsManager {

    public List<Room> bookRooms(final List<Guest> guests, final int quantityFreePremiumRooms, final int quantityFreeEconomyRooms) {
        List<Room> reservedRooms = bookPremiumRoomsForExpectedPrice(guests, quantityFreePremiumRooms);
        int quantityAvailableFreePremiumRooms = quantityFreePremiumRooms - reservedRooms.size();
        int quantityReservedPremiumRooms = reservedRooms.size();
        int quantityReservedAllRooms = quantityReservedPremiumRooms + quantityFreeEconomyRooms;
        int guestsWithoutRoom = guests.size() - quantityReservedAllRooms;
        if (guestsWithoutRoom > 0) {
            int quantityPremiumRoomsForLessMoney = Integer.min(quantityAvailableFreePremiumRooms, guestsWithoutRoom);
            for (int i = 0; i < quantityPremiumRoomsForLessMoney; i++) {
                reservedRooms.add(new Room(guests.get(quantityReservedPremiumRooms + i).getPrice()));
            }
        }
        return reservedRooms;
    }

    private List<Room> bookPremiumRoomsForExpectedPrice(final List<Guest> guests, final int quantityFreePremiumRooms) {
        List<Room> premium = new ArrayList<>();
        int quantityAvailableFreePremiumRooms = quantityFreePremiumRooms;
        for (Guest guest : guests) {
            if (guest.getPrice().compareTo(Reservation.MINIMAL_PRICE_PREMIUM_ROOM) < 0 || quantityAvailableFreePremiumRooms == 0) {
                break;
            }
            premium.add(new Room(guest.getPrice()));
            quantityAvailableFreePremiumRooms--;
        }
        return premium;
    }
}
