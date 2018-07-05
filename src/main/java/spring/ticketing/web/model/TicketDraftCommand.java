package spring.ticketing.web.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.ticketing.model.TicketChannel;
import spring.ticketing.model.TicketDraft;
import spring.ticketing.model.data.ClientData;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDraftCommand implements TicketDraft {

  @NotNull
  private ClientData client;

  @NotNull
  private TicketChannel channel;

  @NotEmpty
  private String contactName;

  @NotEmpty
  private String subject;

  private TicketDraftCommand(TicketDraft other) {
    this.client = ClientData.from(other.getClient());
    this.channel = other.getChannel();
    this.contactName = other.getContactName();
    this.subject = other.getSubject();
  }

  public static TicketDraftCommand from(TicketDraft other) {
    return new TicketDraftCommand(other);
  }
}
