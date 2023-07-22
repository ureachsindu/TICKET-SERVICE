package com.dxc.tks.api.resource;

import com.dxc.tks.api.dto.BaseResponse;
import com.dxc.tks.api.dto.TicketDTO;
import com.dxc.tks.api.enums.SecurityError;
import com.dxc.tks.api.exception.IdNotFoundException;
import com.dxc.tks.api.exception.UserNotFoundException;
import com.dxc.tks.api.model.Ticket;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.dxc.tks.api.service.TicketService;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private MessageSource source;
   @Autowired
    private TicketService ticketService;

    /**
     * This method is created to add Ticket
     *
     * @param ticketDTO hold the information of the Ticket
     * @return ticket
     * @throws UserNotFoundException
     * //@throws ApplicationCustomException application specific exception
     */
    @PostMapping("/addTicket")
    @Operation(summary = "Api to add ticket")
    public ResponseEntity<?> saveTicket(@Valid @RequestBody TicketDTO ticketDTO)
            throws UserNotFoundException{
        Ticket ticketResponse = null;
        try {
            ticketResponse = ticketService.saveTicket(ticketDTO);
            if (ticketResponse != null) {
               return new ResponseEntity<Object>(
                        new BaseResponse(HttpStatus.CREATED.value(),
                                source.getMessage("tks.success.message", null, null), ticketResponse),
                        HttpStatus.CREATED);
            } else {
                return new ResponseEntity<Object>(new BaseResponse(HttpStatus.BAD_REQUEST.value(),
                        SecurityError.OPERATION_FAILED.getDescription(), null), HttpStatus.BAD_REQUEST);
            }
        }
        catch (UserNotFoundException e) {
            return new ResponseEntity<Object>(
                    new BaseResponse(HttpStatus.NOT_FOUND.value(), SecurityError.OPERATION_FAILED.getDescription(),
                            source.getMessage("tks.user.not.found.message", null, null)),
                    HttpStatus.NOT_FOUND);
        } catch (NoSuchMessageException e) {
            return new ResponseEntity<Object>(new BaseResponse(HttpStatus.NOT_FOUND.value(),
                    SecurityError.OPERATION_FAILED.getDescription(), null), HttpStatus.NOT_FOUND);

        }
    }
    /**
     * This method is created to get list of tickets
     *
     * @return response entity object
     */
    @GetMapping
    @Operation(summary = "Api to get list of tickets")
    public ResponseEntity<?> getTickets() {
        final List<TicketDTO> ticketResponse = ticketService.getTickets();
        if (ticketResponse != null) {
            return new ResponseEntity<Object>(new BaseResponse(HttpStatus.OK.value(),
                    source.getMessage("tks.success.message", null, null), ticketResponse), HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(new BaseResponse(HttpStatus.NOT_FOUND.value(),
                    SecurityError.OPERATION_FAILED.getDescription(), null), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This method is created to get the ticket information based on the ticket id
     *
     * @param id ticket id of the ticket
     * @return response entity object
     * @throws IdNotFoundException
     */
    @GetMapping("/{id}")
    @Operation(summary = "Api to get the ticket information based on the ticket id")
    public ResponseEntity<?> getTicketById(@PathVariable int id) throws IdNotFoundException{
        TicketDTO ticketDTO = null;
        try {
            ticketDTO = ticketService.getTicketById(id);
            if (ticketDTO != null) {
               return new ResponseEntity<Object>(new BaseResponse(HttpStatus.OK.value(),
                        source.getMessage("tks.success.message", null, null), ticketDTO), HttpStatus.OK);
            } else {
                return new ResponseEntity<Object>(new BaseResponse(HttpStatus.NOT_FOUND.value(),
                        SecurityError.OPERATION_FAILED.getDescription(), null), HttpStatus.NOT_FOUND);
            }
        }
        catch(IdNotFoundException e) {
                return new ResponseEntity<Object>(
                        new BaseResponse(HttpStatus.NOT_FOUND.value(), SecurityError.OPERATION_FAILED.getDescription(),
                                source.getMessage("tks.ticket.not.found.message", null, null)),
                        HttpStatus.NOT_FOUND);

        }
        }

    /**
     * This method is created to update Ticket
     *
     * @param ticketDTO holds the information of the ticketDTO
     * @return ticketDTO
     * @throws NoSuchMessageException
     */
    @PutMapping("/update/{id}")
    @Operation(summary = "Api to update ticket ")
    public ResponseEntity<?> updateTicketById(@RequestBody TicketDTO ticketDTO, @PathVariable int id)
            throws NoSuchMessageException, IdNotFoundException {
        Ticket ticketDTOResponse = null;
        try {
            ticketDTOResponse = ticketService.updateTicketById(ticketDTO,id);
            if (ticketDTOResponse != null) {
               return new ResponseEntity<Object>(
                        new BaseResponse(HttpStatus.CREATED.value(),
                                source.getMessage("tks.update.message", null, null), ticketDTOResponse),
                        HttpStatus.CREATED);
            } else {
                return new ResponseEntity<Object>(new BaseResponse(HttpStatus.BAD_REQUEST.value(),
                        SecurityError.OPERATION_FAILED.getDescription(), null), HttpStatus.BAD_REQUEST);
            }
        }catch (UserNotFoundException e) {
            return new ResponseEntity<Object>(
                    new BaseResponse(HttpStatus.NOT_FOUND.value(), SecurityError.OPERATION_FAILED.getDescription(),
                            source.getMessage("tks.user.not.found.message", null, null)),
                    HttpStatus.NOT_FOUND);
        }
        catch (IdNotFoundException e) {
            return new ResponseEntity<Object>(
                    new BaseResponse(HttpStatus.NOT_FOUND.value(), SecurityError.OPERATION_FAILED.getDescription(),
                            source.getMessage("tks.ticket.not.found.message", null, null)),
                    HttpStatus.NOT_FOUND);
        }
    }


    /**
     * This method is created to delete the ticket information based on the ticket id
     *
     * @param id ticket id of the ticket
     * @return response entity object
     * @throws IdNotFoundException if id is not found
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Api to get the ticket information based on the ticket id")
    public ResponseEntity<?> deleteTicketById(@PathVariable int id) throws IdNotFoundException {

        try {
            var isRemoved = ticketService.deleteTicketById(id);
            if (isRemoved) {
                 return new ResponseEntity<Object>(new BaseResponse(HttpStatus.OK.value(),
                     source.getMessage("tks.delete.message", null, null), id), HttpStatus.OK);
            } else {
                return new ResponseEntity<Object>(new BaseResponse(HttpStatus.NOT_FOUND.value(),
                        SecurityError.OPERATION_FAILED.getDescription(), null), HttpStatus.NOT_FOUND);
            }
        } catch (IdNotFoundException e) {
            return new ResponseEntity<Object>(
                    new BaseResponse(HttpStatus.NOT_FOUND.value(), SecurityError.OPERATION_FAILED.getDescription(),
                            source.getMessage("tks.ticket.not.found.message", null, null)),
                    HttpStatus.NOT_FOUND);
            }
        }
    }


