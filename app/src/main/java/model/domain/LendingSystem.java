package model.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * manager class.
 */
public class LendingSystem {
  private ArrayList<Member> members;
  
  private Random random = new Random();

  /**
   * LendSystem constructor.
   */
  public LendingSystem() {
    members = new ArrayList<>();
    
  }

  /**
   * method that returns a copy of member list.
   */
  public ArrayList<Member> getMembers() {
    ArrayList<Member> memberList = new ArrayList<>();
    for (Member m : members) {
      memberList.add(m);
    }
    return memberList;
  }

  /**
   * to pass to member.
   * return member.
   */
  private Member getMember(String key) {
    for (int i = 0; i < members.size(); i++) {
      if (members.get(i).getId().equalsIgnoreCase(key)
          || members.get(i).getEmail().equalsIgnoreCase(key)) {
        return members.get(i);
      }
    }
    return null;
  }

  /**
   * modifyMember.
   */
  public Member modifyMember(String id, String name, String email, String phone) {
    Member member = getMember(id);
    if (member != null) {
      member.setName(name);
      member.setEmail(email);
      member.setPhoneNumber(phone);
    }
    return member;

  }

  /**
   * checks if the member's phoneNr or email is exists.
   */
  public boolean exists(String key) {
    for (int i = 0; i < members.size(); i++) {
      if (members.get(i).getEmail().equalsIgnoreCase(key)
          || members.get(i).getPhoneNumber().equalsIgnoreCase(key)) {
        return true;
      }
    }
    return false;
  }

  /**
   * check if item name duplicated.
   */
  public boolean itemNameCheck(String name) {
    for (Member m : members) {
      for (Item i : m.getItems()) {
        if (i.getName().equalsIgnoreCase(name)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * add new member.
   */
  public void addNewMember(String name, String email, String phonenumber, int creationDay) {
    members.add(new Member(name, email, phonenumber, generateId(), creationDay));

  }

  /**
   * to delete a member.
   */
  public void deleteMember(String key) {
    Iterator<Member> mems = members.iterator();
    while (mems.hasNext()) {
      Member member = mems.next();
      if (member.getId().equalsIgnoreCase(key)) {
        mems.remove();
      }
    }

  }

  /**
   * to delete an item.
   */
  public void deleteItem(String memId, String itemId) {
    Item item = getItem(memId, itemId);
    if (item != null) {
      getMember(memId).deleteItem(itemId);
    }
  }

  /**
   * To edit an item.
   */

  public Item editItem(String memId, String itemId, String name,
      String description, int cost, String category) {
    Item item = getItem(memId, itemId);
    if (item != null) {
      item.setCategory(category);
      item.setCost(cost);
      item.setName(name);
      item.setDescription(description);
      return item;
    }
    return null;

  }

  /**
   * returns an item.
   */
  public Item getItem(String key, String itemId) {

    return getMember(key).getItem(itemId);
  }

  public ArrayList<Item> getItems(String id) {
    return getMember(id).getItems();
  }

  /**
   * adds an item.
   */
  public void addNewItem(String category, String name, String description,
      int price, int creationDay, String memberId) {
    Member member = getMember(memberId);
    Item newItem = new Item(name, category, description, price, creationDay, generateId());
    member.addCredits(100);
    member.addItem(newItem);

  }

  /**
   * add contract and boolean to cheack if the contract was add.
   */
  public void addContract(Member owner, Member lender, Item item, int start, int end) {
    Contract newContract = new Contract(start, end, new Item(item),
        new Member(owner), new Member(lender));
    getMember(owner.getId()).getItem(item.getId()).addContract(newContract);

  }

  /**
   * Control Contract.
   */

  public void controlContract(int day) {
    ArrayList<Contract> contractList;
    for (Member mem : members) {
      for (Item i : mem.getItems()) {
        contractList = i.getOnGoingContracts();
        if (contractList.size() != 0) {
          for (Contract c : contractList) {
            if (c.getStartDay() == day) {
              i.uppdateOnGoingContract(c);
              getMember(c.getOwner().getId()).addCredits(c.getCredit());
              getMember(c.getBorrower().getId()).deduceCredit(c.getCredit());

              break;
            }
          }
        }
        if (i.getContract() != null) {

          if (i.getContract().getEndDay() == (day - 1)) {
            i.updatedCurrentContract(i.getContract());
            break;
          }
        }
      }
    }
  }

  public ArrayList<Contract> getOnGoingContracts(String memberId, String itemId) {
    return getItem(memberId, itemId).getOnGoingContracts();
  }

  public ArrayList<Contract> getOldContracts(String memberId, String itemId) {
    return getItem(memberId, itemId).getOldContracts();
  }

  public Contract getContract(String memberId, String itemId) {
    return getItem(memberId, itemId).getContract();
  }

  /*
   * generats random id.
   */
  private String generateId() {
    // chose a Character random from this String.
    String alphaNumericString = "0123456789abcdefghijklmnopqrstuvxyz";
    StringBuilder sb;

    sb = new StringBuilder(6);
    for (int i = 0; i < 6; i++) {
      // generate a random number between -.
      // 0 to AlphaNumericString variable length.
      do {
        int index = random.nextInt(alphaNumericString.length());
        // add Character one by one in end of sb.
        sb.append(alphaNumericString.charAt(index));
        // create StringBuffer size of 6 characters.
      } while (idCheck(sb.toString()));
    }

    return sb.toString();

  }

  private boolean idCheck(String id) {
    boolean exist = false;
    if (getMember(id) != null) {
      exist = true;

    }
    for (Member m : members) {
      for (Item i : m.getItems()) {
        if (i.getId().equalsIgnoreCase(id)) {
          exist = true;
        }
      }
    }

    return exist;
  }

}
