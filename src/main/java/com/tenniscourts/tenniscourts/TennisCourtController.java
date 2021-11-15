package com.tenniscourts.tenniscourts;

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

@Api(value = "TennisCourtController")
@RestController
@AllArgsConstructor
public class TennisCourtController extends BaseRestController {

    private final TennisCourtService tennisCourtService;

    @ApiOperation(value = "Add Tennis Court")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Tennis Court Added"),
    })
    @RequestMapping(value = "/addTennisCourt", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<TennisCourtDTO> addTennisCourt(@RequestBody TennisCourtDTO tennisCourtDTO) {
        return ResponseEntity.created(locationByEntity(tennisCourtService.addTennisCourt(tennisCourtDTO).getId())).build();
    }

    @ApiOperation(value = "Find Tennis Court by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return TennisCourtDTO found"),
    })
    @RequestMapping(value = "/findTennisCourtById/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<TennisCourtDTO> findTennisCourtById(Long tennisCourtId) {
        return ResponseEntity.ok(tennisCourtService.findTennisCourtById(tennisCourtId));
    }

    @ApiOperation(value = "Find Tennis Court with Schedules by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return TennisCourtDTO found"),
    })
    @RequestMapping(value = "/findTennisCourtWithSchedulesById/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<TennisCourtDTO> findTennisCourtWithSchedulesById(Long tennisCourtId) {
        return ResponseEntity.ok(tennisCourtService.findTennisCourtWithSchedulesById(tennisCourtId));
    }
}
