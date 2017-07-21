package com.egor.calendar;

/**
 * Created by magic on 7/20/17.
 */

class DayItem {
  private final boolean currentMonth;
  private final boolean weekend;
  private final int     day;
  private       Integer cost;
  private final boolean minimal;

  public DayItem(
      int day,
      boolean currentMonth,
      boolean weekend,
      Integer cost,
      boolean minimal) {

    this.day = day;
    this.currentMonth = currentMonth;
    this.weekend = weekend;
    this.cost = cost;
    this.minimal = minimal;
  }

  public boolean isCurrentMonth() {
    return currentMonth;
  }

  public boolean isWeekend() {
    return weekend;
  }

  public int getDay() {
    return day;
  }

  public Integer getCost() {
    return cost;
  }

  public boolean isMinimal() {
    return minimal;
  }
}
