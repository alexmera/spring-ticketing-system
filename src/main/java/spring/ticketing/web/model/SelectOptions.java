package spring.ticketing.web.model;

import com.google.common.collect.ForwardingList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Value;
import spring.ticketing.web.model.SelectOptions.SelectOption;

public class SelectOptions extends ForwardingList<SelectOption> {

  private final List<SelectOption> options = new ArrayList<>();

  public SelectOptions(final Class<? extends Enum<?>> enumClass) {
    String prefix = enumClass.getCanonicalName();
    Arrays.asList(enumClass.getEnumConstants())
        .forEach(e -> options.add(new SelectOption(e.name(), prefix + "." + e.name())));
  }

  public SelectOption findByValue(String value) {
    return options.stream()
        .filter(selectOption -> selectOption.value.equals(value))
        .findFirst()
        .get();
  }

  public String captionCode(String value) {
    return findByValue(value).captionCode;
  }

  @Override
  protected List<SelectOption> delegate() {
    return options;
  }

  @Value
  static class SelectOption {

    private String value;
    private String captionCode;
  }

}
