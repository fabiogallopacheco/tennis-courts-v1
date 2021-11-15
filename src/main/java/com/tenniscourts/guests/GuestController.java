package com.tenniscourts.guests;

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

import java.util.List;

@Api(value = "GuestController")
@RestController
@AllArgsConstructor
public class GuestController extends BaseRestController {

    private final GuestService guestService;

    @ApiOperation(value = "Add a Guest")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Guest Added"),
    })
    @RequestMapping(value = "/addGuest", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Void> addGuest(@RequestBody GuestDTO guestDTO) {
        return ResponseEntity.created(locationByEntity(guestService.addGuest(guestDTO).getId())).build();
    }

    @ApiOperation(value = "Find Guest by Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return a GuestDTO found"),
    })
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<GuestDTO> findById(Long guestId) {
        return ResponseEntity.ok(guestService.findById(guestId));
    }

    @ApiOperation(value = "Find Guest by Name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return a GuestDTO found"),
    })
    @RequestMapping(value = "/findByName/{name}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<GuestDTO>> findByName(String name) {
        return ResponseEntity.ok(guestService.findByName(name));
    }

    @ApiOperation(value = "Update Guest")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return a GuestDTO updated"),
    })
    @RequestMapping(value = "/updateGuest", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<GuestDTO> updateGuest(@RequestBody GuestDTO guestDTO) {
        return ResponseEntity.ok(guestService.updateGuest(guestDTO));
    }

    @ApiOperation(value = "Delete a Guest")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Guest Deleted"),
    })
    @RequestMapping(value = "/deleteGuest/{id}", method = RequestMethod.GET)
    public ResponseEntity<Void> deleteGuest(Long guestId) {
        guestService.deleteGuest(guestId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "List all Guest")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return a list GuestDTO found"),
    })
    @RequestMapping(value = "/listAll", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<GuestDTO>> listAll() {
        return ResponseEntity.ok(guestService.listAll());
    }

}
