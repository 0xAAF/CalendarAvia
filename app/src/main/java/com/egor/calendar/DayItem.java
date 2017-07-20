package com.egor.calendar;

/**
 * Created by magic on 7/20/17.
 */

class DayItem {
  private final boolean currentMonth;
  private final boolean weekend;
  private final int     day;
  private Integer     coast;

  public DayItem(
      int day,
      boolean currentMonth,
      boolean weekend,
      Integer coast) {

    this.day = day;
    this.currentMonth = currentMonth;
    this.weekend = weekend;
    this.coast = coast;
  }

  private boolean isCurrentMonth() {
    return currentMonth;
  }

  public boolean isWeekend() {
    return weekend;
  }

  public int getDay() {
    return day;
  }

  public boolean currentMonth() {
    return currentMonth;
  }

  public Integer getCoast() {
    return coast;
  }
}
