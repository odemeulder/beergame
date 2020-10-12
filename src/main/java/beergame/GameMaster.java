package beergame;

import java.util.List;
import java.util.ArrayList;

public class GameMaster {

  public static int[] AskForOrders(SupplyChain state) {
    return getActors(state)
    .stream()
    .mapToInt(actor -> actor.Play(state.getSupplyChainState()))
    .toArray();  
  }

  private static List<Actor> getActors(SupplyChain state) {
    return new ArrayList<>(
      List.of(
        state.getRetailer(), 
        state.getDistributor(), 
        state.getWholesaler(), 
        state.getFactory())
    );
  }
}
