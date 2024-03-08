package controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import model.domain.Item;
import model.domain.LendingSystem;
import model.domain.Member;
import model.domain.Time;
import view.EnglishView;

/**
 * class that controll adding and modifying items and members.
 */
public class Controller {

  private LendingSystem ls;
  private Time time;
  private EnglishView ui;
  //private SwedishView ui;


  /**
   * Constructor.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP")
  public Controller(EnglishView view, LendingSystem ls, Time t) {
    this.ui = view;
    this.time = t;
    this.ls = ls;
  }

  /**
   * Create new member.
   */
  void addNewMember() {
    String name = ui.addName();
    String email = ui.addEmail();
    while (ls.exists(email)) {
      ui.emailError();
      email = ui.addEmail();
    }
    String phoneNr = ui.addPhoneNumber();
    while (ls.exists(phoneNr)) {
      ui.phoneError();
      phoneNr = ui.addPhoneNumber();
    }
    ls.addNewMember(name, email, phoneNr, time.getDay());

  }

  /**
   * edit Member.
   */
  public Member editMember(Member mem, int selected) {
    String name = mem.getName();
    String email = mem.getEmail();
    String phoneNr = mem.getPhoneNumber();

    if (selected == 0) {
      name = ui.addName();
    } else if (selected == 1) {
      while (ls.exists(email)) {
        email = ui.addEmail();
      }
    } else if (selected == 2) {
      while (ls.exists(phoneNr)) {
        phoneNr = ui.addPhoneNumber();
      }
    }

    return ls.modifyMember(mem.getId(), name, email, phoneNr);
  }

  /**
   * create new item.
   */
  public void addNewItem(String memId) {
    
    String name = ui.createItemName();
    while (ls.itemNameCheck(name)) {
      ui.itemNameDuplicatedError();
      name = ui.createItemName();
    }
    String description = ui.addItemDescription();
    String category = ui.addItemCategory();
    int price = ui.addItemPrice();
    int creationDay = time.getDay();
    ls.addNewItem(category, name, description, price, creationDay, memId);
  }

  /**
   * edit Item.
   */
  public Item editItem(String memId, Item item, int selected) {
    String name = item.getName();
    String description = item.getDescription();
    String category = item.getCategory();
    int price = item.getCost();

    if (selected == 0) {
      while (ls.itemNameCheck(name)) {
        ui.itemNameDuplicatedError();
        name = ui.createItemName();
      }    
    } else if (selected == 1) {
      category = ui.addItemCategory();
    } else if (selected == 2) {
      description = ui.addItemDescription();
    } else if (selected == 3) {
      price = ui.addItemPrice();
    }

    return ls.editItem(memId, item.getId(), name, description, price, category);
  }

}
