package controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import model.domain.Contract;
import model.domain.ContractController;
import model.domain.ContractController.Status;
import model.domain.Item;
import model.domain.LendingSystem;
import model.domain.Member;
import model.domain.Time;
import view.EnglishView;

/**
 * class controlls every thing will be run.
 */
public class MainMenu {
  EnglishView ui;
  // SwedishView ui;
  Controller controller;
  LendingSystem ls;
  Time time;
  Member member;
  Item item;
  ContractController cc;

  /**
   * Main Menu.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP")
  public MainMenu(EnglishView view, LendingSystem ls, Time t, Controller controller) {
    this.controller = controller;
    this.cc = new ContractController();
    this.ls = ls;
    this.time = t;
    this.ui = view;

  }

  private void showMemberMenu(EnglishView.MemberMenuEvent event) {
    switch (event) {
      case UpdateMember:
        member = controller.editMember(member, ui.editMember());
        showMemberMenu(ui.memberMenuList(member));
        break;
      case DeleteMember:
        ls.deleteMember(member.getId());
        break;
      case Cancel:
        member = null;
        break;
      case AddItem:
        controller.addNewItem(member.getId());
        break;
      case SelectItem:
        if (ls.getItems(member.getId()).size() > 0) {
          item = ui.itemSelection(ls.getItems(member.getId()));
          showItemMenu(ui.itemMenuList(item));
          showMemberMenu(ui.memberMenuList(member));
          break;
        }

        break;
      case ListItems:
        ui.itemsOutput(ls.getItems(member.getId()));
        showMemberMenu(ui.memberMenuList(member));
        break;
      default:
        break;
    }
  }

  private void showItemMenu(EnglishView.ItemMenuEvent event) {
    switch (event) {
      case LendItem:
        int startDay = ui.getStartDay(time.getDay());
        int endDay = ui.getEndDay(startDay);
        Member borrower;
        do {
          borrower = ui.memberSelection(ls.getMembers());
        } while (borrower.getId().equalsIgnoreCase(member.getId()));

        Status status = cc.lendingEligiblity(startDay, endDay,
            borrower, item, item.getOnGoingContracts());

        switch (status) {
          case NotAvailable:
            ui.outOfRangeTime();
            showItemMenu(ui.itemMenuList(item));
            break;

          case NotAffordable:
            ui.noEnoughMoney();
            showItemMenu(ui.itemMenuList(item));
            break;

          case Approve:
            ls.addContract(member, borrower, item, startDay, endDay);
            ui.approvedContract();
            showMenu();
            break;

          case expensiveAndNotAvailble:
            ui.noEnoughMoney();
            ui.outOfRangeTime();
            showItemMenu(ui.itemMenuList(item));
            break;

          default:
            break;
        }
        break;
      case ListContracts:
        Contract contract = ls.getContract(member.getId(), item.getId());
        ui.printContracts(ls.getOldContracts(member.getId(), item.getId()));
        if (contract != null) {
          ui.printContract(contract);
        }
        ui.printContracts(ls.getOnGoingContracts(member.getId(), item.getId()));
        showItemMenu(ui.itemMenuList(item));
        break;
      case DeleteItem:
        ls.deleteItem(member.getId(), item.getId());
        break;
      case UpdateItem:
        item = controller.editItem(member.getId(), item, ui.editItem());
        showItemMenu(ui.itemMenuList(item));
        break;
      default:
        break;
    }
  }

  /**
   * show Menu.
   */
  public void showMenu() {
    boolean run = true;

    while (run) {
      switch (ui.showMainMenu(time.getDay())) {
        case CREATE_MEMBER:
          controller.addNewMember();
          break;
        case LIST_MEMBERS_Simple:
          ui.printMembersSimple(ls.getMembers());
          break;
        case LIST_MEMBERS_Verbose:
          ui.printMembersVerbose(ls.getMembers());
          break;
        case SELECT_MEMBER:
          member = ui.memberSelection(ls.getMembers());
          showMemberMenu(ui.memberMenuList(member));
          break;
        case ADVANCE_DAY:
          time.moveOneDayForward();
          ls.controlContract(time.getDay());
          break;
        case QUIT:
          run = false;
          break;
        default:
          break;
      }

    }

  }

}
