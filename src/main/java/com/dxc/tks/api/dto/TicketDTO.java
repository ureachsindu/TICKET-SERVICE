package com.dxc.tks.api.dto;
import com.dxc.tks.api.model.Ticket;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TicketDTO {
    private int ticketid;
    private String description;
    private String createdBy;
    private String ownedBy;
    private String status;
    @NotNull(message = "${tks.title.missing}")
    private String title;
    @NotNull(message = "${tks.price.missing}")
    private String price;
    public TicketDTO(Ticket ticket){
        this.ticketid = ticket.getTicketid();
        this.status = ticket.getStatus();
        this.description = ticket.getDescription();
        this.ownedBy = ticket.getOwnedBy();
        this.createdBy = ticket.getCreatedBy();
        this.price = ticket.getPrice();
        this.title = ticket.getTitle();

    }

}
