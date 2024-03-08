package model.domain;

import java.util.ArrayList;

/**
 * Item Class.
 */
public class Item {

  private String id;
  private int cost;
  private String name;
  private String category;
  private String description;
  private int creationDay;

  private Contract contract;

  private ArrayList<Contract> onGoingContracts = new ArrayList<>(); 
  private ArrayList<Contract> oldContracts = new ArrayList<>();

  /**
   * constructor.
   */
  public Item(String name, String category, String description,
      int cost, int creationDay, String id) {
    this.category = category;
    this.name = name;
    this.description = description;
    this.cost = cost;
    this.creationDay = creationDay;
    this.id = id;
  }

  /**
   * Copy cons.
   */
  public Item(Item i) {
    this.category = i.getCategory();
    this.name = i.getName();
    this.description = i.getDescription();
    this.cost = i.getCost();
    this.creationDay = i.getCreationDay();
    this.id = i.getId();
    this.onGoingContracts = i.getOnGoingContracts();
    this.contract = i.getContract();
    this.oldContracts = i.getOldContracts();

  }

  /**
   * retrun current contract.
   * 
   */
  public Contract getContract() {
    return this.contract;
  }

  /**
   * make a copy of the old contracts. 
   */
  public ArrayList<Contract> getOldContracts() {
    ArrayList<Contract> contractList = new ArrayList<>();
    for (Contract c : oldContracts) {
      contractList.add(c);
    }

    return contractList;
  }

  /**
   * make a copy of the on going contracts.
   */
  public ArrayList<Contract> getOnGoingContracts() {
    ArrayList<Contract> contractList = new ArrayList<>();
    for (Contract c : onGoingContracts) {
      contractList.add(c);
    }
    
    return contractList;
  }

  
  

  /**
   * return the creationDay.
   */
  public int getCreationDay() {
    return creationDay;
  }


  /**
   * the cost.
   */
  public int getCost() {
    return cost;
  }

  /**
   * the cost to set.
   */
  public void setCost(int cost) {
    this.cost = cost;
  }

  /**
   * the name.
   */
  public String getName() {
    return name;
  }

  /**
   * the name to set.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * the category.
   */
  public String getCategory() {
    return category;
  }

  /**
   * the category to set.
   */
  public void setCategory(String category) {
    this.category = category;
  }

  /**
   * the description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * the description to set.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * add contract.
   */
  public void updatedCurrentContract(Contract c) {
    if (contract.toString().equals(c.toString())) {
      oldContracts.add(contract);
      contract = null;
    }
  }

  /**
   * uppdateOnGoingContract.
   */
  public void uppdateOnGoingContract(Contract c) {
    for (Contract con : onGoingContracts) {
      if (con.toString().equals(c.toString())) {
        contract = con;
        onGoingContracts.remove(con);
        
        break;
      }
    }
  }

  /**
   * add contract.
   */
  public void addContract(Contract c) {
    onGoingContracts.add(c);

  }



  public String getId() {
    return this.id;
  }

  void setId(String id) {
    this.id = id;
  }
  

}
