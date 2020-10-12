package beergame;

public class Cycle {
  
  public static SupplyChainState Play(SupplyChain supplyChain, int demand, int[] orders) throws Exception {

    if (orders.length < 4) {
      throw new Exception("orders needs to have 4 ints");
    }

    int factoryProduced = supplyChain.getProduction().handlePlay(supplyChain.getFactory().getProductionOrder());
    int factoryShipment = supplyChain.getFactory().handlePlay(factoryProduced);
    int wholesaleDelivery = supplyChain.getFact2Whol().handlePlay(factoryShipment);
    int wholesaleShipment = supplyChain.getWholesaler().handlePlay(wholesaleDelivery);
    int distributorDelivery = supplyChain.getWhol2Dist().handlePlay(wholesaleShipment);
    int distributorShipment = supplyChain.getDistributor().handlePlay(distributorDelivery);
    int retailerDelivery = supplyChain.getDist2Ret().handlePlay(distributorShipment);
    supplyChain.getRetailer().handlePlay(retailerDelivery);
    supplyChain.placeNewOrders(demand, orders);

    return supplyChain.getSupplyChainState();
  }

}
