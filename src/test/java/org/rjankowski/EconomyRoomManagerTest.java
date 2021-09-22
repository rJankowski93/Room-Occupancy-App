package org.rjankowski;

import org.junit.Assert;
import org.junit.Test;
import org.rjankowski.dto.Guest;
import org.rjankowski.dto.Room;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class EconomyRoomManagerTest {

    EconomyRoomManager economyRoomManager = new EconomyRoomManager();

    @Test
    public void bookRooms_zeroGuests_zeroFreeRooms_zeroReservedRooms() {

        List<Guest> guests = Collections.emptyList();
        int quantityFreeEconomyRooms = 0;

        List<Room> reservedRooms = economyRoomManager.bookRooms(guests, quantityFreeEconomyRooms, 0);

        Assert.assertEquals(0, reservedRooms.size());
    }

    @Test
    public void bookRooms_oneGuest_oneFreeRooms_oneReservedRoom() {
        List<Double> prices = DoubleStream.of(23).boxed().collect(Collectors.toList());
        List<Guest> guests = prices.stream().map(price -> new Guest(BigDecimal.valueOf(price))).collect(Collectors.toList());
        int quantityFreeEconomyRooms = 1;

        List<Room> reservedRooms = economyRoomManager.bookRooms(guests, quantityFreeEconomyRooms, 0);

        Assert.assertEquals(1, reservedRooms.size());
        Assert.assertEquals(BigDecimal.valueOf(23.0), reservedRooms.stream().map(Room::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    @Test
    public void bookRooms_fiveGuests_twoFreeRooms_twoReservedRooms() {
        List<Double> prices = DoubleStream.of(23, 56, 48, 21, 11).boxed().collect(Collectors.toList());
        List<Guest> guests = prices.stream().map(price -> new Guest(BigDecimal.valueOf(price))).collect(Collectors.toList());
        int quantityFreeEconomyRooms = 2;

        List<Room> reservedRooms = economyRoomManager.bookRooms(guests, quantityFreeEconomyRooms, 0);

        Assert.assertEquals(2, reservedRooms.size());
        Assert.assertEquals(BigDecimal.valueOf(79.0), reservedRooms.stream().map(Room::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    @Test
    public void bookRooms_tenGuests_zeroFreeRooms_zeroReservedRooms() {
        List<Double> prices = DoubleStream.of(23, 56, 48, 21, 11, 58, 74, 12, 16, 19).boxed().collect(Collectors.toList());
        List<Guest> guests = prices.stream().map(price -> new Guest(BigDecimal.valueOf(price))).collect(Collectors.toList());
        int quantityFreeEconomyRooms = 0;

        List<Room> reservedRooms = economyRoomManager.bookRooms(guests, quantityFreeEconomyRooms, 0);

        Assert.assertEquals(0, reservedRooms.size());
    }
}
