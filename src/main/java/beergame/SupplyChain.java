package beergame;

import lombok.Data;

@Data
public class SupplyChain {

  private Actor retailer;
  private Actor distributor;
  private Actor wholesaler;
  private FactoryActor factory;
  private Transit production;
  private Transit fact2Whol;
  private Transit whol2Dist;
  private Transit dist2Ret;

  public SupplyChain() {
    retailer = new Actor(PlayerType.RETAILER);
    distributor = new Actor(PlayerType.DISTRIBUTOR);
    wholesaler = new Actor(PlayerType.WHOLESALER);
    factory = new FactoryActor();
    production = new Transit();
    fact2Whol = new Transit();
    whol2Dist = new Transit();
    dist2Ret = new Transit();
  }

  public SupplyChainState getSupplyChainState() {
    return SupplyChainState.builder()
    .retInventory(retailer.getInventory()) 
    .disInventory(distributor.getInventory())
    .whoInventory(wholesaler.getInventory())
    .facInventory(factory.getInventory())
    .retOrder(retailer.getOrder())
    .disOrder(distributor.getOrder())
    .whoOrder(wholesaler.getOrder())
    .facOrder(factory.getOrder())
    .facProd1(production.getT1())
    .facProd2(production.getT2())
    .fac2whoTrans1(fact2Whol.getT1())
    .fac2whoTrans2(fact2Whol.getT2())
    .who2disTrans1(whol2Dist.getT1())
    .who2disTrans2(whol2Dist.getT2())
    .dis2retTrans1(dist2Ret.getT1())
    .dis2retTrans2(dist2Ret.getT2())
    .retBacklog(retailer.getBacklog())
    .disBacklog(distributor.getBacklog())
    .whoBacklog(wholesaler.getBacklog())
    .facBacklog(factory.getBacklog())
    .build();
  }

  public void placeNewOrders(int demand, int[] orders) throws Exception {

    if (orders.length < 4) {
      throw new Exception("orders needs to have 4 ints");
    }

    retailer.setOrder(demand);
    distributor.setOrder(orders[0]);
    wholesaler.setOrder(orders[1]);
    factory.setOrder(orders[2]);
    factory.setProductionOrder(orders[3]);
  }

}