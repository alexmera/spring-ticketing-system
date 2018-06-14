package spring.ticketing.model;

import java.time.LocalDateTime;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface Ticket {

  @Nonnull
  Integer getId();

  @Nonnull
  Client getClient();

  @Nonnull
  AppUser getOperator();

  @Nullable
  AppUser getCoordinator();

  @Nonnull
  TicketChannel getChannel();

  @Nonnull
  String getContactName();

  @Nonnull
  String getSubject();

  @Nullable
  String getDescription();

  @Nonnull
  TicketStatus getStatus();

  @Nullable
  Resolution getResolution();

  @Nullable
  String getSolution();

  @Nonnull
  Boolean isEscalated();

  @Nonnull
  LocalDateTime getCreationDate();

  @Nonnull
  LocalDateTime getModificationDate();

  @Nullable
  LocalDateTime getClosingDate();
}
