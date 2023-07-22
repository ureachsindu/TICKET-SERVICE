package com.dxc.tks.api.service;

import com.dxc.tks.api.dao.TicketRepository;
import com.dxc.tks.api.dto.TicketDTO;
import com.dxc.tks.api.exception.IdNotFoundException;
import com.dxc.tks.api.exception.UserNotFoundException;
import com.dxc.tks.api.model.Ticket;
import com.dxc.tks.api.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private MessageSource messageSource;

    @Override
    public Ticket saveTicket(TicketDTO ticketDTO)
            throws NoSuchMessageException, IdNotFoundException, UserNotFoundException {
           Ticket ticket = new Ticket();

            ticket.setDescription(ticketDTO.getDescription());
            ticket.setStatus(ticketDTO.getStatus());
            ticket.setPrice(ticketDTO.getPrice());
            ticket.setTitle(ticketDTO.getTitle());
            ticket.setOwnedBy(ticketDTO.getOwnedBy());
            ticket.setCreatedBy(ticketDTO.getCreatedBy());

            return ticketRepository.save(ticket);
    }

    public List<TicketDTO> getTickets() {
        List<TicketDTO> ticketsDtoList = new ArrayList<>();
        List<Ticket> tickets = ticketRepository.findAll();

        if (tickets != null && tickets.size() != 0) {
            for (Ticket ticket : tickets) {
                ticketsDtoList.add(new TicketDTO(ticket));
            }
        }
        return ticketsDtoList;
    }

    @Override
    public TicketDTO getTicketById(int id) throws NoSuchMessageException, IdNotFoundException {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isEmpty()) {
            ticket = ticketRepository.findById(id);
        }
        if (ticketRepository.findById(id).isEmpty()) {
            throw new IdNotFoundException(id);
        } else {
            return new TicketDTO(ticket.get());
        }
    }

    public Ticket updateTicketById(TicketDTO ticketDTO,int id)throws NoSuchMessageException,UserNotFoundException, IdNotFoundException{

        Ticket ticket = ticketRepository.findById(id).get();
        if (id !=  0) {
            Optional<Ticket> ticketExist = ticketRepository.findById(id);
            if (ticketExist.isEmpty()) {
                //throw new IdNotFoundException(
                    //    messageSource.getMessage("tks.ticket.not.found.message", null, null));
            } else {
                ticket = ticketRepository.findById(id).get();
            }
        }
        ticket.setDescription(ticketDTO.getDescription());
        ticket.setCreatedBy(ticketDTO.getCreatedBy());
        ticket.setOwnedBy(ticketDTO.getOwnedBy());
        ticket.setTitle(ticketDTO.getTitle());
        ticket.setPrice(ticketDTO.getPrice());
        ticket.setStatus(ticketDTO.getStatus());

       // Ticket updateTicket = ticketRepository.save(ticket);

        //if (updateTicket != null)
         //   return new TicketDTO(updateTicket);
        return ticketRepository.save(ticket);
    }

    public Boolean deleteTicketById(int id)throws NoSuchMessageException, IdNotFoundException{
        if(ticketRepository.existsById(id) == true){
             ticketRepository.deleteById(id);
             return true;
        }else{
            throw new IdNotFoundException(id);
        }
    }

}
