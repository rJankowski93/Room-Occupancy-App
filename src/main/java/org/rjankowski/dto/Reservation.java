package org.rjankowski.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class Reservation {
    public final static BigDecimal MINIMAL_PRICE_PREMIUM_ROOM = new BigDecimal(100);

    private final List<Room> premium;
    private final List<Room> economy;
    private final BigDecimal priceForPremiumRooms;
    private final BigDecimal priceForEconomyRooms;
}
