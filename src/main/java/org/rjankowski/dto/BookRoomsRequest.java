package org.rjankowski.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class BookRoomsRequest {
    private List<GuestRequest> guests;
    private int quantityFreePremiumRooms;
    private int quantityFreeEconomyRooms;
}
