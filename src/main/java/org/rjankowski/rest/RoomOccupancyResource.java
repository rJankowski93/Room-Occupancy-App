package org.rjankowski.rest;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.rjankowski.dto.BookRoomsRequest;
import org.rjankowski.dto.Guest;
import org.rjankowski.dto.Reservation;
import org.rjankowski.services.RoomOccupancyManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class RoomOccupancyResource {

    private final RoomOccupancyManager roomOccupancyManager;

    @PostMapping("/book-rooms")
    @ApiOperation(value = "Book rooms")
    public ResponseEntity<Reservation> bookRooms(@RequestBody BookRoomsRequest request) {
        Reservation reservation = roomOccupancyManager
                .bookRooms(request.getGuests().stream().map(guestRequest -> new Guest(guestRequest.getPrice())).collect(Collectors.toList()),
                        request.getQuantityFreePremiumRooms(),
                        request.getQuantityFreeEconomyRooms());

        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }
}
