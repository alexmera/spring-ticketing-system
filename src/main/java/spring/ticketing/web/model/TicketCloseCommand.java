package spring.ticketing.web.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.ticketing.model.Resolution;
import spring.ticketing.model.Ticket;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketCloseCommand {

  @NotNull
  private Integer id;

  @NotNull
  private Resolution resolution;

  @NotEmpty
  private String resolutionInfo;
  
  private TicketCloseCommand(Ticket other) {
    this.id = other.getId();
    this.resolution = other.getResolution();
    this.resolutionInfo = other.getResolutionInfo();
  }

  public static TicketCloseCommand from(Ticket other) {
    return new TicketCloseCommand(other);
  }
}
