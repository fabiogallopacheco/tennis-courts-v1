package com.tenniscourts.schedules;

import com.tenniscourts.config.BaseRestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Api(value = "ScheduleController")
@RestController
@AllArgsConstructor
public class ScheduleController extends BaseRestController {

    @Autowired
    private HttpServletRequest context;

    private final ScheduleService scheduleService;

    @ApiOperation(value = "Add Schedule")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Schedule Added"),
    })
    @RequestMapping(value = "/addScheduleTennisCourt", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<Void> addScheduleTennisCourt(@RequestBody CreateScheduleRequestDTO createScheduleRequestDTO) {
        return ResponseEntity.created(locationByEntity(scheduleService.addSchedule(createScheduleRequestDTO).getId())).build();
    }

    @ApiOperation(value = "Find Tennis Court with Schedules by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return a List of ScheduleDTO founds"),
    })
    @RequestMapping(value = "/findSchedulesByDates", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<ScheduleDTO>> findSchedulesByDates(@RequestParam(name = "startDate")
                                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                          LocalDate startDate,
                                                                  @RequestParam(name = "endDate")
                                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                          LocalDate endDate) {
        return ResponseEntity.ok(scheduleService.findSchedulesByDates(LocalDateTime.of(startDate, LocalTime.of(0, 0)), LocalDateTime.of(endDate, LocalTime.of(23, 59))));
    }

    @ApiOperation(value = "Find Schedules by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return a ScheduleDTO found"),
    })
    @RequestMapping(value = "/findByScheduleId/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ScheduleDTO> findByScheduleId(Long scheduleId) {
        return ResponseEntity.ok(scheduleService.findSchedule(scheduleId));
    }
}
