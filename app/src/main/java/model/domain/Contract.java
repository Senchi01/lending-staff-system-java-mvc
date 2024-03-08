package model.domain;


/**
 * contract.
 */
public class Contract {
  private int startDay;
  private int endDay;
  private int credit;
  private Member owner;
  private Member borrower;

  private Item item;


  /**
   * constuctor.
   */
  public Contract(int startDay, int endDay, Item item, Member owner, Member borrower) {
    this.startDay = startDay;
    this.endDay = endDay;
    this.owner = new Member(owner);
    this.borrower = new Member(borrower);
    this.item = new Item(item);
    this.credit = item.getCost() * totalDays();

  }

  /**
   * Copy Constructor.
   */
  public Contract(Contract c) {
    this.startDay = c.startDay;
    this.endDay = c.endDay;
    this.owner = c.owner;
    this.borrower = c.borrower;
    this.item = c.item;
    this.credit = c.credit;
  }

  public int getCredit() {
    return credit;
  }

  public Item getItem() {
    return new Item(item);
  }

  public Member getBorrower() {
    return new Member(borrower);
  }

  public Member getOwner() {
    return new Member(owner);
  }

  /**
   * the startDate.
   */
  public int getStartDay() {
    return startDay;
  }


  /**
   * the endDate.
   */
  public int getEndDay() {
    return endDay;
  }


  /*
   * TOTAL DAYS.
   */
  public int totalDays() {
    return (endDay + 1) - startDay;

  }
}
