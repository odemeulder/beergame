package beergame;

import lombok.Data;

@Data
public class Transit {
  private int t1;
  private int t2;

  public Transit() {
    t1 = 4;
    t2 = 4;
  }

  public int handlePlay(int newShipment) {
    int shipAtDest = t1;
    t1 = t2;
    t2 = newShipment;
    return shipAtDest;
  }
}
