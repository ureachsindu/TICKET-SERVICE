package com.dxc.tks.api.service;

import com.dxc.tks.api.dto.TicketDTO;
import com.dxc.tks.api.exception.IdNotFoundException;
import com.dxc.tks.api.exception.UserNotFoundException;
import com.dxc.tks.api.model.Ticket;
import org.springframework.context.NoSuchMessageException;

import java.util.List;

public interface TicketService {


    public List<TicketDTO> getTickets();

    public Ticket saveTicket(TicketDTO ticketDTO)
            throws NoSuchMessageException, IdNotFoundException, UserNotFoundException;

    public TicketDTO getTicketById(int id) throws NoSuchMessageException, IdNotFoundException;

    public Ticket updateTicketById(TicketDTO ticketDTO,int id) throws NoSuchMessageException,UserNotFoundException, IdNotFoundException;

    public Boolean deleteTicketById(int id) throws NoSuchMessageException, IdNotFoundException;

}
