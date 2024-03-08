package view;

import java.util.ArrayList;
import model.domain.Contract;
import model.domain.Item;
import model.domain.Member;

/**
 * viewinterface.
 */
public interface ViewInterface {

  /**
   * start menu options.
   */
  public enum StartMenu {
    Error,
    CREATE_MEMBER,
    LIST_MEMBERS_Simple,
    LIST_MEMBERS_Verbose,
    SELECT_MEMBER,
    ADVANCE_DAY,
    QUIT
  }

  /**
   * member menu options.
   */
  public enum MemberMenuEvent {
    Cancel,
    UpdateMember,
    DeleteMember,
    Error,
    ListItems,
    SelectItem,
    AddItem
  }

 
  /**
   * item menu options.
   */
  public enum ItemMenuEvent {
    Cancel,
    UpdateItem,
    DeleteItem,
    ListContracts,
    LendItem,
    Error
  }

  /**
   * Show Main.
   */
  public StartMenu showMainMenu(int day);

  public ItemMenuEvent itemMenuList(Item item);

  public Item itemSelecetion(ArrayList<Item> items);

  public int editItem();

  public void itemsOutput(ArrayList<Item> items);

  public Item itemSelection(ArrayList<Item> items);

  public String addItemCategory();

  public String createItemName();

  public String addItemDescription();

  public int addItemPrice();

  public void itemNameDuplicatedError();

  /**
   * retrun numbers.
   */
  public int editMember();

  public String addPhoneNumber();

  public String addName();

  public String addEmail();

  public Member memberSelection(ArrayList<Member> members);

  public void emailError();

  public void phoneError();

  public void printContracts(ArrayList<Contract> contracts);

  public void printContract(Contract contract);

  public int getStartDay(int currentDay);

  public int getEndDay(int startDay);

  public void outOfRangeTime();

  public void noEnoughMoney();

  public void approvedContract();

  public MemberMenuEvent memberMenuList(Member member);

  public void printMembersSimple(ArrayList<Member> memberList);

  public void printMembersVerbose(ArrayList<Member> memberList);
}