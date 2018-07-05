package spring.ticketing.web.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.ticketing.model.Ticket;
import spring.ticketing.model.TicketChannel;
import spring.ticketing.model.TicketDraft;
import spring.ticketing.model.data.ClientData;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketUpdateCommand implements TicketDraft {

  @NotNull
  private Integer id;

  @NotNull
  private ClientData client;

  @NotNull
  private TicketChannel channel;

  @NotEmpty
  private String contactName;

  @NotEmpty
  private String subject;

  private String description;

  private TicketUpdateCommand(Ticket other) {
    this.id = other.getId();
    this.client = ClientData.from(other.getClient());
    this.channel = other.getChannel();
    this.contactName = other.getContactName();
    this.subject = other.getSubject();
    this.description = other.getDescription();
  }

  public static TicketUpdateCommand from(Ticket other) {
    return new TicketUpdateCommand(other);
  }
}
