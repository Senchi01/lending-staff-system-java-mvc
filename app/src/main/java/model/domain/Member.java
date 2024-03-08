package model.domain;

import java.util.ArrayList;

/**
 * Class member.
 */
public class Member {
  private String name;
  private String email;
  private String phoneNumber;
  private String id;
  private int credit;
  private int creationDay;

  private ArrayList<Item> myItems = new ArrayList<>();

  /**
   * empty cons.
   */
  public Member(Member m) {
    this.name = m.getName();
    this.email = m.getEmail();
    this.phoneNumber = m.getPhoneNumber();
    this.id = m.getId();
    this.creationDay = m.getCreationDay();
    this.credit = m.getCredit();
    this.myItems = m.getItems();
  }

  /**
   * constructor.
   */
  public Member(String name, String email, String phoneNumber, String id, int creationDay) {
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.id = id;
    this.creationDay = creationDay;
    this.credit = 100;
  }

  /**
   * return the creationDay.
   */
  public int getCreationDay() {
    return creationDay;
  }


  /**
   * the names.
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
   * the email.
   */
  public String getEmail() {
    return email;
  }

  /**
   * the email to set.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * the phoneNumber.
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * the phoneNumber to set.
   * 
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * the id.
   */
  public String getId() {
    return id;
  }

  /**
   * the cradit.
   */
  public int getCredit() {
    return credit;
  }

  /**
   * the cradit to set.
   */
  public void addCredits(int cradit) {
    this.credit += cradit;
  }

  public void deduceCredit(int c) {
    this.credit -= c;
  }

  public void addItem(Item item) {
    myItems.add(item);
  }

  /**
   * return Item List.
   */
  public ArrayList<Item> getItems() {
    ArrayList<Item> itemList = new ArrayList<>();
    myItems.forEach(item -> itemList.add(item));
    return itemList;
  }

  void deleteItem(String id) {
    myItems.removeIf(i -> i.getId().equalsIgnoreCase(id));
  }

  /**
   * returns an item.
   */
  public Item getItem(String itemId) {
    return myItems.stream()
        .filter(item -> item.getId().equals(itemId))
        .findFirst()
        .orElse(new Item(null, null, null, 0, 0, ""));
  }

}
