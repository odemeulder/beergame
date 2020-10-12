package beergame;

import java.text.NumberFormat;

import lombok.Builder;

@Builder
public class SupplyChainState {
  private int retInventory;
  private int disInventory;
  private int whoInventory;
  private int facInventory;
  private int retBacklog;
  private int disBacklog;
  private int whoBacklog;
  private int facBacklog;
  private int facProd1;
  private int facProd2;
  private int fac2whoTrans1;
  private int fac2whoTrans2;
  private int who2disTrans1;
  private int who2disTrans2;
  private int dis2retTrans1;
  private int dis2retTrans2;
  private int retOrder;
  private int disOrder;
  private int whoOrder;
  private int facOrder;

  private final double INVENTORY_COST = 0.5;
  private final double BACKLOG_COST = 1;

  @Override
  public String toString() {
    NumberFormat numFormat = NumberFormat.getCurrencyInstance();
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("|  R: [%d] {%d}<-- %d <-- %d |  ", retInventory, retBacklog, dis2retTrans1, dis2retTrans2));
    sb.append(String.format("D: [%d] {%d}<-- %d <-- %d |  ", disInventory, disBacklog, who2disTrans1, who2disTrans2));
    sb.append(String.format("W: [%d] {%d}<-- %d <-- %d |  ", whoInventory, whoBacklog, fac2whoTrans1, fac2whoTrans2));
    sb.append(String.format("F: [%d] {%d}<-- %d <-- %d | ", facInventory, facBacklog, facProd1, facProd2));
    sb.append(numFormat.format(cost()));
    return sb.toString();
  }

  public int getDelivered(PlayerType type) {
    switch (type) {
      case RETAILER:
        return dis2retTrans1;
      case DISTRIBUTOR:
        return who2disTrans1;
      case FACTORY:
        return facProd1;
      case WHOLESALER:
        return fac2whoTrans1;
      default:
        return 0;
    }
  }


  public double cost() {
    int inv = retInventory + disInventory + whoInventory + facInventory;
    int bl = retBacklog + disBacklog + whoBacklog + facBacklog;
    return inv * INVENTORY_COST + bl *BACKLOG_COST;
  }

}
