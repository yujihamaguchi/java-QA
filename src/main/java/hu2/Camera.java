package hu2;

import java.awt.*;
import java.util.function.*;
import java.util.stream.*;

@SuppressWarnings("unchecked")
public class Camera {

  private Function<Color, Color> filter;

  public Camera() {
    setFilters();
  }

  public Color capture(final Color inputColor) {
    final Color processedColor = filter.apply(inputColor);
    return processedColor;
  }

  public void setFilters(final Function<Color, Color>... filters) {
    filter = Stream.of(filters)
                   .reduce((filter, next) -> filter.compose(next))
                   .orElseGet(Function::identity);
  }
}
