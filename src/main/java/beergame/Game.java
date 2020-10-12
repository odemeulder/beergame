package beergame;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
  
  private SupplyChain supplyChain;
  private List<SupplyChainState> history;

  private int numCycles;

  public Game() {
    supplyChain = new SupplyChain();
    numCycles = 90;
    history = new ArrayList<>();
    history.add(supplyChain.getSupplyChainState());
  }

  public void Play() {

    Random r = new Random();
    int[] demand = r.ints(numCycles + 2, 2, 7).toArray();

    try {

      // Play game
      for (int i = 1; i <= numCycles; i++ ) {
        int[] orders = GameMaster.AskForOrders(supplyChain);
        SupplyChainState state = Cycle.Play(supplyChain, demand[i], orders);
        history.add(state);
      }

      // Display results
      int i = 0;
      double totalCost = 0;
      for(SupplyChainState scs: history) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Week %d: ", i++));
        sb.append(String.format( "Demand %d", demand[i]));
        sb.append(scs);
        System.out.println(sb);
        totalCost += scs.cost();
      }
      NumberFormat numFormat = NumberFormat.getCurrencyInstance();
      System.out.println(numFormat.format(totalCost));

    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }
}
