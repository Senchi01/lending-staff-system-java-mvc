package model.domain;

/**
 * Time control class.
 */
public class Time {

  private int day;

  public void moveOneDayForward() {
    this.day += 1;
  }

  public int getDay() {
    return day;
  }

}
