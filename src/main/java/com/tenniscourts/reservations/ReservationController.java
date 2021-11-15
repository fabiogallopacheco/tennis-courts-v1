package com.tenniscourts.reservations;

import com.tenniscourts.config.BaseRestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "ReservationController")
@RestController
@AllArgsConstructor
public class ReservationController extends BaseRestController {

    private final ReservationService reservationService;

    @ApiOperation(value = "Add a book Reservation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Book Reservation Added"),
    })
    @RequestMapping(value = "/bookReservation", method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity<Void> bookReservation(@RequestBody CreateReservationRequestDTO createReservationRequestDTO) {
        return ResponseEntity.created(locationByEntity(reservationService.bookReservation(createReservationRequestDTO).getId())).build();
    }


    @ApiOperation(value = "Find Reservation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return a ReservationDTO found"),
    })
    @RequestMapping(value = "/findReservation/{id}", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<ReservationDTO> findReservation(Long reservationId) {
        return ResponseEntity.ok(reservationService.findReservation(reservationId));
    }


    @ApiOperation(value = "Cancel Reservation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return a ReservationDTO cancelled"),
    })
    @RequestMapping(value = "/cancelReservation/{id}", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<ReservationDTO> cancelReservation(Long reservationId) {
        return ResponseEntity.ok(reservationService.cancelReservation(reservationId));
    }

    @ApiOperation(value = "Reschedule a Reservation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return a ReservationDTO rescheduled"),
    })
    @RequestMapping(value = "/rescheduleReservation/{reservationId}/{scheduleId}", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<ReservationDTO> rescheduleReservation(Long reservationId, Long scheduleId) {
        return ResponseEntity.ok(reservationService.rescheduleReservation(reservationId, scheduleId));
    }
}
