package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.domain.Contract;
import model.domain.Item;
import model.domain.Member;

/**
 * ui english version.
 */
public class EnglishView implements ViewInterface {
  Scanner scanner = new Scanner(System.in, "UTF-8");

  @Override
  public StartMenu showMainMenu(int day) {
    System.out.println("\nday: " + day);
    System.out.println("Main Menu:");
    System.out.println("1. Create a new member");
    System.out.println("2. List all members (simple)");
    System.out.println("3. List all members (verbose)");
    System.out.println("4. Select a member");
    System.out.println("5. Advance one day");
    System.out.println("6. Quit");

    int choice = scanner.nextInt();
    scanner.nextLine();

    switch (choice) {
      case 1:
        return StartMenu.CREATE_MEMBER;
      case 2:
        return StartMenu.LIST_MEMBERS_Simple;
      case 3:
        return StartMenu.LIST_MEMBERS_Verbose;
      case 4:
        return StartMenu.SELECT_MEMBER;
      case 5:
        return StartMenu.ADVANCE_DAY;
      case 6:
        return StartMenu.QUIT;
      default:
        System.out.println("Invalid choice. Please try again.");
        showMainMenu(day);
        return StartMenu.Error;
    }

  }

  @Override
  public ItemMenuEvent itemMenuList(Item item) {
    System.out.println("\n" + "item been chosen is: " + item.getName());
    System.out.println("\nItem menu:");
    System.out.println("0. Cancel");
    System.out.println("1. Lend item");
    System.out.println("2. Update item");
    System.out.println("3. Delete item");
    System.out.println("4. list all contracts");
    int choice = scanner.nextInt();
    scanner.nextLine();

    switch (choice) {
      case 0:
        return ItemMenuEvent.Cancel;
      case 1:
        return ItemMenuEvent.LendItem;
      case 2:
        return ItemMenuEvent.UpdateItem;
      case 3:
        return ItemMenuEvent.DeleteItem;
      case 4:
        return ItemMenuEvent.ListContracts;
      default:
        System.out.println("Invalid choice. Please try again.");

        return ItemMenuEvent.Error;
    }
  }

  @Override
  public Item itemSelecetion(ArrayList<Item> items) {
    int input;
    System.out.println("\nPlease chose an item:");

    itemsOutput(items);

    do {
      input = scanner.nextInt();
    } while (input > items.size());
    return items.get(input);

  }

  @Override
  public int editItem() {
    System.out.println("\nPlease choose what to update: ");
    System.out.println("0. name\n1. description\n2. category\n3. price");
    int input;
    while (true) {
      input = scanner.nextInt();
      if (input < 0 || input > 3) {
        System.out.println("\nInvalid input. "
            + "Please enter a number between 0 and 3");
      } else {
        return input;
      }
    }
  }

  @Override
  public void itemsOutput(ArrayList<Item> items) {
    int ind = 0;

    System.out.println();
    for (Item i : items) {
      System.out.println("item " + ind + ". name:" + i.getName() + " category: " + i.getCategory()
          + " description: " + i.getDescription() + " cost:" + i.getCost());
      ind++;
    }
    System.out.println();
  }

  @Override
  public Item itemSelection(ArrayList<Item> items) {
    int input;
    System.out.println("\nPlease chose an item:");

    itemsOutput(items);

    do {
      input = scanner.nextInt();
    } while (input > items.size());
    return items.get(input);
  }

  @Override
  public String addItemCategory() {
    System.out.println("\nPlease enter an item category: ");
    String input;
    while (true) {
      input = scanner.nextLine();
      if (input.length() >= 2) {
        return input;
      } else {
        System.out.println("Invalid input, please enter a category"
            + " with at least two characters.");
      }
    }
  }

  @Override
  public String createItemName() {
    String input;
    System.out.print("\nPlease enter an item name: ");
    while (true) {
      input = scanner.nextLine();
      if (input.length() >= 2) {
        return input;
      } else {
        System.out.println("Invalid input, please enter a name with at least two characters.");
      }
    }
  }

  @Override
  public String addItemDescription() {
    System.out.print("\nPlease enter an item description: ");
    String input;
    while (true) {
      input = scanner.nextLine();
      if (input.length() >= 2) {
        return input;
      } else {
        System.out.println("Invalid input, "
            + "please enter a description with at least two characters.");

      }
    }
  }

  @Override
  public int addItemPrice() {
    System.out.println("\nPlease enter daily item cost: ");
    int input;
    while (true) {
      input = scanner.nextInt();
      if (input >= 0) {
        return input;
      } else {
        System.out.println("Invalid input, please enter a positive cost.");
      }
    }
  }

  @Override
  public int editMember() {
    System.out.println("\nPlease choose what to update: ");

    System.out.println("0. name.\n1. email\n2. phoneNr.");
    int input = -1;
    while (true) {
      input = scanner.nextInt();
      if (input < 0 || input > 2) {
        System.out.println("Invalid input. "
            + "Please enter a number between 0 and 2");
      } else {
        return input;
      }
    }
  }

  @Override
  public String addPhoneNumber() {
    System.out.print("\nPlease enter a phone number: ");
    String input;

    while (true) {
      input = scanner.nextLine();
      if (input.matches("\\d+")/* && input.length() > 9 && input.length() < 11 */) {
        return input;
      } else {
        System.out.println("Invalid input, please enter a phone number with length of 10 digits.");
      }
    }
  }

  @Override
  public String addName() {
    String input;
    System.out.print("\nPlease enter a name: ");
    while (true) {
      input = scanner.nextLine();
      if (input.length() >= 2) {
        return input;
      } else {
        System.out.println("Invalid input, please enter a name with at least two characters.");
      }
    }
  }

  @Override
  public String addEmail() {
    String input;
    System.out.print("\nPlease enter an email: ");
    input = scanner.nextLine();
    while (!input.contains("@") || !input.contains(".")) {
      System.out.println("invalid email! Please enter a viald one");
      input = scanner.nextLine();
    }
    return input;
  }

  @Override
  public void emailError() {
    System.out.println("Error: duplicate email, try agian");
  }

  @Override
  public void phoneError() {
    System.out.println("Error: duplicate phone nr., try agian");
  }

  @Override
  public Member memberSelection(ArrayList<Member> members) {
    int input = 0;

    System.out.println("\nPlease choose a member:");

    for (int i = 0; i < members.size(); i++) {
      System.out.println(i + ". " + members.get(i).getName());
    }

    input = scanner.nextInt();
    while (input > members.size() || input < 0) {
      input = scanner.nextInt();
      System.out.println("please choose a member from the list.");
    }

    return members.get(input);
  }

  @Override
  public void printContracts(ArrayList<Contract> contracts) {
    for (Contract c : contracts) {
      System.out.println("\nLender:" + c.getBorrower().getName() + "\nStart day:" + c.getStartDay()
          + "\nEnd day " + c.getEndDay() + "\ntotal_Price: " + c.getCredit());
    }
  }

  @Override
  public void printContract(Contract c) {
    System.out.println("lender: " + c.getBorrower().getName() + ", the price in this contract: " + c.getCredit()
        + "lent day" + c.getStartDay() + "contract end day " + c.getEndDay());
  }

  @Override
  public int getStartDay(int currentDay) {
    int input = 0;
    System.out.println("Please enter the first lending day:");
    input = scanner.nextInt();
    while (input < currentDay) {
      System.out.println("Invalid input. Please enter a valid number.");
      input = scanner.nextInt();
    }
    return input;
  }

  @Override
  public int getEndDay(int startDay) {
    int input = 0;
    System.out.println("Please enter the last lending day:");
    input = scanner.nextInt();
    while (input < startDay) {
      System.out.println("Invalid input. Please enter a valid number.");
      input = scanner.nextInt();

    }
    return input;
  }

  @Override
  public void outOfRangeTime() {
    System.out.println("selected time is conflicted!");
  }

  @Override
  public void noEnoughMoney() {
    System.out.println("you dont have enough money to lend this item for so long"
        + "\n please reduce the time!");
  }

  @Override
  public void approvedContract() {
    System.out.println("Contract successfully created!");
  }

  @Override
  public void itemNameDuplicatedError() {
    System.out.println("The selected item name is already exists for this member.");
  }

  @Override
  public MemberMenuEvent memberMenuList(Member member) {
    System.out.println("\n" + "member chosen is: " + member.getName());
    System.out.println("\nMember menu:");
    System.out.println("0. Cancel");
    System.out.println("1. Update member information");
    System.out.println("2. Delete member");
    System.out.println("3. List all items");
    System.out.println("4. Select item");
    System.out.println("5. Add item");
    int choice = scanner.nextInt();
    scanner.nextLine();

    switch (choice) {
      case 1:
        return MemberMenuEvent.UpdateMember;
      case 2:
        return MemberMenuEvent.DeleteMember;
      case 3:
        return MemberMenuEvent.ListItems;
      case 4:
        return MemberMenuEvent.SelectItem;
      case 5:
        return MemberMenuEvent.AddItem;
      case 0:
        return MemberMenuEvent.Cancel;
      default:
        System.out.println("Invalid choice. Please try again.");
        return MemberMenuEvent.Error;
    }
  }

  @Override
  public void printMembersSimple(ArrayList<Member> memberList) {
    for (Member m : memberList) {
      System.out.println("\nName: " + m.getName() + ", email: "
          + m.getEmail() + ", Credits: " + m.getCredit() + ", id: " + m.getId()
          + "\nowned items: " + m.getItems().size());
    }
  }

  @Override
  public void printMembersVerbose(ArrayList<Member> memberList) {
    for (Member m : memberList) {
      System.out.println("Name: " + m.getName() + ", email: "
          + m.getEmail() + ", id: " + m.getId());
      System.out.println("  Items: ");
      for (Item i : m.getItems()) {
        if (i.getContract() == null) {
          System.out.println("\tItem: " + i.getName() + ": "
              + i.getCategory() + ", description: " + i.getDescription()
              + ", Lent to No One Currenty\n ");
        } else {
          System.out.println("\tItem: " + i.getName() + ": " + i.getCategory() + ", " + "Lent to "
              + i.getContract().getBorrower().getName() + "\n");
        }

      }
    }
  }

}
