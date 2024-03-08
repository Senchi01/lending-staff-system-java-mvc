package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.domain.Contract;
import model.domain.Item;
import model.domain.Member;

/**
 * ui swedish version.
 */
public class SwedishView implements ViewInterface {
  Scanner scanner = new Scanner(System.in, "UTF-8");

  @Override
  public StartMenu showMainMenu(int day) {
    System.out.println("\ndag: " + day);
    System.out.println("Huvudmeny:");
    System.out.println("a. Skapa en ny medlem");
    System.out.println("b. Lista alla medlemmar (enkelt)");
    System.out.println("c. Lista alla medlemmar (verbose)");
    System.out.println("d. Välj en medlem");
    System.out.println("e. Avancera en dag");
    System.out.println("f. Lämna");

    String choice = scanner.nextLine();
    scanner.nextLine();

    switch (choice) {
      case "a":
        return StartMenu.CREATE_MEMBER;
      case "b":
        return StartMenu.LIST_MEMBERS_Simple;
      case "c":
        return StartMenu.LIST_MEMBERS_Verbose;
      case "d":
        return StartMenu.SELECT_MEMBER;
      case "e":
        return StartMenu.ADVANCE_DAY;
      case "f":
        return StartMenu.QUIT;
      default:
        System.out.println("Ogiltigt val. Var god försök igen.");
        showMainMenu(day);
        return StartMenu.Error;
    }

  }

  @Override
  public ItemMenuEvent itemMenuList(Item item) {
    System.out.println("\n" + "artikeln som valts är: " + item.getName());
    System.out.println("\nartikelsmeny:");
    System.out.println("a. Tillbaka");
    System.out.println("b. Låna objekt");
    System.out.println("c. Uppdatera objekt");
    System.out.println("d. Ta-bort objekt");
    System.out.println("e. Lista alla kontrakter");
    String choice = scanner.nextLine();
    scanner.nextLine();

    switch (choice) {
      case "a":
        return ItemMenuEvent.Cancel;
      case "b":
        return ItemMenuEvent.LendItem;
      case "c":
        return ItemMenuEvent.UpdateItem;
      case "d":
        return ItemMenuEvent.DeleteItem;
      case "e":
        return ItemMenuEvent.ListContracts;
      default:
        System.out.println("Ogiltigt val. Var god försök igen.");

        return ItemMenuEvent.Error;
    }
  }

  @Override
  public Item itemSelecetion(ArrayList<Item> items) {
    int input;
    System.out.println("\nVänligen välj ett objekt:");

    itemsOutput(items);

    do {
      input = scanner.nextInt();
    } while (input > items.size());
    return items.get(input);

  }

  @Override
  public int editItem() {
    System.out.println("\nVälj vad du vill uppdatera: ");
    System.out.println("0. namn\n1. Beskrivning\n2. Kategori\n3. Pris");
    int input;
    while (true) {
      input = scanner.nextInt();
      if (input < 0 || input > 3) {
        System.out.println("\nOgiltigt val. "
            + "Vänligen välj ett nummer mellan 0 och 3");
      } else {
        return input;
      }
    }
  }

  @Override
  public void itemsOutput(ArrayList<Item> items) {
    int ind = 0;

    System.out.println("atriklar");
    for (Item i : items) {
      System.out.println("atrikl " + ind + ". namn:" + i.getName() + " category: "
          + i.getCategory() + " beskrivning: " + i.getDescription() + " pris:" + i.getCost());
      ind++;
    }
    System.out.println();
  }

  @Override
  public Item itemSelection(ArrayList<Item> items) {
    int input;
    System.out.println("\nVänligen välj ett objekt:");

    itemsOutput(items);

    do {
      input = scanner.nextInt();
    } while (input > items.size());
    return items.get(input);
  }

  @Override
  public String addItemCategory() {
    System.out.println("\nVänligen ange en artikelkategori: ");
    String input;
    while (true) {
      input = scanner.nextLine();
      if (input.length() >= 2) {
        return input;
      } else {
        System.out.println("Ogiltig inmatning, ange en kategori"
            + " med minst två tecken.");
      }
    }
  }

  @Override
  public String createItemName() {
    String input;
    System.out.print("\nVänligen ange ett objekts namn: ");
    while (true) {
      input = scanner.nextLine();
      if (input.length() >= 2) {
        return input;
      } else {
        System.out.println("Ogiltig inmatning, ange ett namn med minst två tecken.");
      }
    }
  }

  @Override
  public String addItemDescription() {
    System.out.print("\nVänligen ange en artikelbeskrivning:");
    String input;
    while (true) {
      input = scanner.nextLine();
      if (input.length() >= 2) {
        return input;
      } else {
        System.out.println("Ogiltig inmatning, "
            + "vänligen ange en beskrivning med minst två tecken.");

      }
    }
  }

  @Override
  public int addItemPrice() {
    System.out.println("\nAnge daglig artikelkostnad: ");
    int input;
    while (true) {
      input = scanner.nextInt();
      if (input >= 0) {
        return input;
      } else {
        System.out.println("Ogiltig inmatning, ange en positiv kostnad.");
      }
    }
  }

  @Override
  public int editMember() {
    System.out.println("\nVälj vad du vill uppdatera: ");

    System.out.println("0. namn.\n1. mejl\n2. mobilnummer.");
    int input = -1;
    while (true) {
      input = scanner.nextInt();
      if (input < 0 || input > 2) {
        System.out.println("Ogiltig inmatning, "
            + "Ange ett tal mellan 0 och 2");
      } else {
        return input;
      }
    }
  }

  @Override
  public String addPhoneNumber() {
    System.out.print("\nVänligen ange ett telefonnummer:");
    String input;

    while (true) {
      input = scanner.nextLine();
      if (input.matches("\\d+") && input.length() > 9 && input.length() < 11) {
        return input;
      } else {
        System.out.println("Ogiltig inmatning, ange ett telefonnummer med 10 siffror.");
      }
    }
  }

  @Override
  public String addName() {
    String input;
    System.out.print("\nVänligen ange ett namn: ");
    while (true) {
      input = scanner.nextLine();
      if (input.length() >= 2) {
        return input;
      } else {
        System.out.println("Ogiltig inmatning, ange ett namn med minst två tecken.");
      }
    }
  }

  @Override
  public String addEmail() {
    String input;
    System.out.print("\nVänligen ange ett mejl: ");
    input = scanner.nextLine();
    while (!input.contains("@") || !input.contains(".")) {
      System.out.println("Ogiltig Mejl! Ange en giltig");
      input = scanner.nextLine();
    }
    return input;
  }

  @Override
  public void emailError() {
    System.out.println("Fel meddelande: duplicerad mejl, försök igen");
  }

  @Override
  public void phoneError() {
    System.out.println("Fel meddelande: duplicerad tel.nr., försök igen");
  }

  @Override
  public Member memberSelection(ArrayList<Member> members) {
    int input = 0;

    System.out.println("\nVälj en medlem:");

    for (int i = 0; i < members.size(); i++) {
      System.out.println(i + ". " + members.get(i).getName());
    }

    input = scanner.nextInt();
    while (input > members.size() || input < 0) {
      input = scanner.nextInt();
      System.out.println("välj en medlem från listan.");
    }

    return members.get(input);
  }

  @Override
  public void printContracts(ArrayList<Contract> contracts) {
    for (Contract c : contracts) {
      System.out.println("\ndenna artikel ska bli lånad till: " + c.getBorrower().getName()
          + ", och priset för denna artikel mellan dag " + c.getStartDay() + " och dag " + c.getEndDay()
          + " är " + c.getCredit());
    }
  }

  @Override
  public void printContract(Contract c) {
    System.out.println("\nlångivaren: " + c.getBorrower().getName() + ", priset i detta kontrakt: " + c.getCredit()
        + "lånades dag" + c.getStartDay() + " kontraktet slutar dag " + c.getEndDay());
  }

  @Override
  public int getStartDay(int currentDay) {
    int input = 0;
    System.out.println("Ange första lånedagen:");
    input = scanner.nextInt();
    while (input < currentDay) {
      System.out.println("Felaktig input. Var vänlig skriv in ett giltigt nummer.");
      input = scanner.nextInt();
    }
    return input;
  }

  @Override
  public int getEndDay(int startDay) {
    int input = 0;
    System.out.println("Ange sista lånedagen:");
    input = scanner.nextInt();
    while (input < startDay) {
      System.out.println("Felaktig input. Var vänlig skriv in ett giltigt nummer.");
      input = scanner.nextInt();

    }
    return input;
  }

  @Override
  public void outOfRangeTime() {
    System.out.println("den valda tiden är utanför intervallet!");
  }

  @Override
  public void noEnoughMoney() {
    System.out.println(
        "du har inte tillräckligt med pengar för att låna ut det här föremålet så länge"
            + "\n snälla minska tiden!");
  }

  @Override
  public void approvedContract() {
    System.out.println("Kontraktet har skapats!");
  }

  @Override
  public void itemNameDuplicatedError() {
    System.out.println("Den utvalda namnet är redan finns, var vänlig och välj ett annat.");
  }

  @Override
  public MemberMenuEvent memberMenuList(Member member) {
    System.out.println("\n" + "medlem som valdes är: " + member.getName());
    System.out.println("\nMedlemsmeny:");
    System.out.println("a. lämna");
    System.out.println("b. Uppdatera medlemsinformation");
    System.out.println("c. ta-bort medlem");
    System.out.println("d. Lista alla artiklar");
    System.out.println("e. Välj artikel");
    System.out.println("f. Adda artikel");
    String choice = scanner.nextLine();
    scanner.nextLine();

    switch (choice) {
      case "b":
        return MemberMenuEvent.UpdateMember;
      case "c":
        return MemberMenuEvent.DeleteMember;
      case "d":
        return MemberMenuEvent.ListItems;
      case "e":
        return MemberMenuEvent.SelectItem;
      case "f":
        return MemberMenuEvent.AddItem;
      case "a":
        return MemberMenuEvent.Cancel;
      default:
        System.out.println("Invalid choice. Please try again.");
        return MemberMenuEvent.Error;
    }
  }

  @Override
  public void printMembersSimple(ArrayList<Member> memberList) {
    for (Member m : memberList) {
      System.out.println("\nNamn: " + m.getName() + ", mejl: "
          + m.getEmail() + ", Krediter: " + m.getCredit() + ", id: " + m.getId()
          + "\nägda objekt: " + m.getItems().size());
    }
  }

  @Override
  public void printMembersVerbose(ArrayList<Member> memberList) {
    for (Member m : memberList) {
      System.out.println("Namn: " + m.getName() + ", mejl: "
          + m.getEmail() + ", id: " + m.getId());
      System.out.println("  objekt: ");
      for (Item i : m.getItems()) {
        if (i.getContract() == null) {
          System.out.println("\tobjekt: " + i.getName() + ": "
              + i.getCategory() + ", beskrivning: " + i.getDescription()
              + ", Utlånad till ingen valuta\n ");
        } else {
          System.out.println("\tItem: " + i.getName() + ": " + i.getCategory()
              + ", " + "utlånad till " + i.getContract().getBorrower().getName() + "\n");
        }
      }

    }
  }

}
