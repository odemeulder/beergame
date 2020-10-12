package beergame;

import lombok.Getter;
import lombok.Setter;

public class FactoryActor extends Actor {
  
  @Getter
  @Setter
  private int productionOrder;

  public FactoryActor() {
    super(PlayerType.FACTORY);
    productionOrder = 4;
  }

}
