package org.rjankowski;

import org.junit.Assert;
import org.junit.Test;
import org.rjankowski.dto.Guest;
import org.rjankowski.dto.Reservation;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class RoomOccupancyManagerTest {

    RoomOccupancyManager roomOccupancyManager = new RoomOccupancyManager(new PremiumRoomsManager(), new EconomyRoomManager());
    List<Double> prices = DoubleStream.of(23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209).boxed().collect(Collectors.toList());
    List<Guest> guests = prices.stream().map(price -> new Guest(BigDecimal.valueOf(price))).collect(Collectors.toList());

    @Test
    public void testCase1() {
        int quantityFreePremiumRooms = 3;
        int quantityFreeEconomyRooms = 3;

        Reservation reservation = roomOccupancyManager.bookRooms(guests, quantityFreePremiumRooms, quantityFreeEconomyRooms);

        Assert.assertEquals(BigDecimal.valueOf(738.0), reservation.getPriceForPremiumRooms());
        Assert.assertEquals(BigDecimal.valueOf(167.99), reservation.getPriceForEconomyRooms());
        Assert.assertEquals(3, reservation.getPremium().size());
        Assert.assertEquals(3, reservation.getEconomy().size());
    }

    @Test
    public void testCase2() {
        int quantityFreePremiumRooms = 7;
        int quantityFreeEconomyRooms = 5;

        Reservation reservation = roomOccupancyManager.bookRooms(guests, quantityFreePremiumRooms, quantityFreeEconomyRooms);

        Assert.assertEquals(BigDecimal.valueOf(1054.0), reservation.getPriceForPremiumRooms());
        Assert.assertEquals(BigDecimal.valueOf(189.99), reservation.getPriceForEconomyRooms());
        Assert.assertEquals(6, reservation.getPremium().size());
        Assert.assertEquals(4, reservation.getEconomy().size());
    }

    @Test
    public void testCase3() {
        int quantityFreePremiumRooms = 2;
        int quantityFreeEconomyRooms = 7;

        Reservation reservation = roomOccupancyManager.bookRooms(guests, quantityFreePremiumRooms, quantityFreeEconomyRooms);

        Assert.assertEquals(BigDecimal.valueOf(583.0), reservation.getPriceForPremiumRooms());
        Assert.assertEquals(BigDecimal.valueOf(189.99), reservation.getPriceForEconomyRooms());
        Assert.assertEquals(2, reservation.getPremium().size());
        Assert.assertEquals(4, reservation.getEconomy().size());
    }

    @Test
    public void testCase4() {
        int quantityFreePremiumRooms = 7;
        int quantityFreeEconomyRooms = 1;

        Reservation reservation = roomOccupancyManager.bookRooms(guests, quantityFreePremiumRooms, quantityFreeEconomyRooms);

        Assert.assertEquals(BigDecimal.valueOf(1153.99), reservation.getPriceForPremiumRooms());
        Assert.assertEquals(BigDecimal.valueOf(45.00), reservation.getPriceForEconomyRooms());
        Assert.assertEquals(7, reservation.getPremium().size());
        Assert.assertEquals(1, reservation.getEconomy().size());
    }
}
