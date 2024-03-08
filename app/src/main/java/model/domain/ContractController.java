package model.domain;

import java.util.ArrayList;

/**
 * class that controlls contract states.
 */
public class ContractController {

  /**
   * enum that has the states.
   */
  public static enum Status {
    Approve,
    expensiveAndNotAvailble,
    NotAffordable,
    NotAvailable
  }

  /**
   * returns false if a contract exists in the list where the period starts and
   * ends within the
   * dates of the contract or the period encompasses the dates of the contract.
   * returns true if the list is empty or no conflicting contract is found.
   */
  private boolean availableTime(int startDay, int endDay, ArrayList<Contract> contracts) {
   

    if (contracts.size() != 0) {
      for (Contract c : contracts) {
        if ((startDay >= c.getStartDay() && startDay <= c.getEndDay())
            || (endDay >= c.getStartDay() && endDay <= c.getEndDay())
            || (startDay <= c.getStartDay() && endDay >= c.getEndDay())) {
          return false;
        }
      }   
    }
    return true;

  }

  /**
   * checks if a member can afford the price of an item.
   */
  private boolean affordable(int startDay, int endDay, Member member, Item item) {
    return member.getCredit() >= (((endDay - startDay) + 1 ) * item.getCost());
    
  }

  /**
   * retruns an enum to define if a contract is available or not.
   */
  public Status lendingEligiblity(int startDay, int endDay, Member member,
      Item item, ArrayList<Contract> contracts) {
    boolean availableTime = availableTime(startDay, endDay, contracts);
    System.out.println(availableTime);
    boolean affordable = affordable(startDay, endDay, member, item);
    if (!availableTime) {
      return Status.NotAvailable;
    } else if (!affordable) {
      return Status.NotAffordable;
    } else if (!affordable && !availableTime) {
      return Status.expensiveAndNotAvailble;
    }
    return Status.Approve;
  }

}
