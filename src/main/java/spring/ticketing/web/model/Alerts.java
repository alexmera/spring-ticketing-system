package spring.ticketing.web.model;

import com.google.common.collect.ForwardingList;
import java.util.ArrayList;
import java.util.List;
import lombok.Value;
import spring.ticketing.web.model.Alerts.Alert;
import spring.ticketing.web.model.Alerts.Alert.Level;

public class Alerts extends ForwardingList<Alert> {

  private final List<Alert> delegateList;

  public Alerts() {
    delegateList = new ArrayList<>();
  }

  @Override
  protected List<Alert> delegate() {
    return delegateList;
  }

  public void succes(String message) {
    add(new Alert(Level.SUCCESS, message));
  }

  public void danger(String message) {
    add(new Alert(Level.DANGER, message));
  }

  public void warning(String message) {
    add(new Alert(Level.WARNING, message));
  }

  public void info(String message) {
    add(new Alert(Level.INFO, message));
  }

  @Value
  static class Alert {

    private Level level;
    private String message;

    public String getClassName() {
      switch (level) {
        case SUCCESS:
          return "alert-success";
        case DANGER:
          return "alert-danger";
        case WARNING:
          return "alert-warning";
        case INFO:
          return "alert-info";
        default:
          return "alert-light";
      }
    }

    public enum Level {
      SUCCESS, DANGER, WARNING, INFO
    }
  }
}
