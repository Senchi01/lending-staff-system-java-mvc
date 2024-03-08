package controller;

import model.domain.LendingSystem;
import model.domain.Time;
import view.EnglishView;

/**
 * run class.
 */
public class Run {
  /**
   * Run the program.
   */
  public static void main(String[] args) {
    Time time = new Time();
    EnglishView ui = new EnglishView();
    
    //SwedishView ui = new SwedishView();
    LendingSystem ls = new LendingSystem();
    Controller controller = new Controller(ui, ls, time);
    MainMenu mm = new MainMenu(ui, ls, time, controller);

    mm.showMenu();
  }
}
