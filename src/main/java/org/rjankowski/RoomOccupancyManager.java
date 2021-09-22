package org.rjankowski;

import lombok.RequiredArgsConstructor;
import org.rjankowski.dto.Guest;
import org.rjankowski.dto.Reservation;
import org.rjankowski.dto.Room;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomOccupancyManager {

    private final PremiumRoomsManager premiumRoomsManager;
    private final EconomyRoomManager economyRoomManager;

    public Reservation bookRooms(final List<Guest> guests, final int quantityFreePremiumRooms, final int quantityFreeEconomyRooms) {
        guests.sort(Comparator.comparing(Guest::getPrice).reversed());

        List<Room> premiumRooms = premiumRoomsManager.bookRooms(guests, quantityFreePremiumRooms, quantityFreeEconomyRooms);
        List<Room> economyRooms = economyRoomManager.bookRooms(guests, quantityFreeEconomyRooms, premiumRooms.size());

        return Reservation.builder()
                .priceForEconomyRooms(economyRooms.stream().map(Room::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add))
                .priceForPremiumRooms(premiumRooms.stream().map(Room::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add))
                .economy(economyRooms)
                .premium(premiumRooms)
                .build();
    }
}
