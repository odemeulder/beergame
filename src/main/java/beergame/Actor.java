package beergame;

import java.util.List;
import java.util.ArrayList;


import lombok.Getter;
import lombok.Setter;

public class Actor {

  @Getter
  @Setter
  private int inventory;
  @Getter
  @Setter
  private int order;
  @Getter
  @Setter
  private int backlog;

  private int outStandingPurchaseOrders;
  private List<Integer> orderHistory;
  private PlayerType type;

  public Actor(PlayerType type) {
    inventory = 4;
    order = 4;
    backlog = 0;
    this.outStandingPurchaseOrders = 12;
    orderHistory = new ArrayList<Integer>(List.of(4,4));
    this.type = type;
  }

  public int handlePlay(int delivery) {
    inventory = inventory + delivery;
    backlog = backlog + order;
    int shipment = backlog < inventory ? backlog : inventory;
    inventory = inventory - shipment;
    backlog = backlog - shipment;
    return shipment;
  }

  public int Play(SupplyChainState state) {
    
    // Calculated estimated demand based on current order and previous 2
    // just average of 3 values
    orderHistory.add(this.order);
    int historyDepth = 2;
    int estimatedDemand = orderHistory
      .stream()
      .skip(orderHistory.size()-historyDepth)
      .mapToInt(i->i)
      .sum() / historyDepth;
    estimatedDemand = 4;

    // ATTEMPT TO DO SOMETHING WITH COST  
    // int x = 0;
    // int startingInventory = inventory;
    // int startingBacklog = backlog;
    // inventory = startingInventory + outStandingPurchaseOrders + x - demand;
    // backlog = startingBacklog + inventory - demand;
    // double cost = inventory * INVENTORY_COST + backlog * BACKLOG_COST;
    // cost = (startingInventory + outStandingPurchaseOrders + x) * INVENTORY_COST + (startingBacklog + startingInventory + outStandingPurchaseOrders + x - demand) * BACKLOG_COST;

    // ALTERNATIVE 1: keep track of how much is already ordered before placing order
    // tends to lead to high inventory (backlog averse)
    outStandingPurchaseOrders -= state.getDelivered(this.type);
    int inventory = this.inventory;
    int backlog = this.backlog;
    int order = estimatedDemand * 4 + backlog - outStandingPurchaseOrders - inventory;
    if (order < 0) return 0;
    outStandingPurchaseOrders += order;
    return order;

    // ALTERNATIVE 2: naieve, just place order for estimated demand
    // very low cost
    // but in the long run, you get huge backlogs that get worse and worse
    // return estimatedDemand;
  }


}
