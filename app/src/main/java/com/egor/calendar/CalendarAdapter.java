package com.egor.calendar;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by magic on 7/20/17.
 */

public class CalendarAdapter extends RecyclerView.Adapter<DayHolder> {

  private DayItem[] days = new DayItem[42];
  private int                   month;
  private Map<Integer, Integer> costMap;
  private OnDayClickListener    listener;
  private int                   costMin;

  public CalendarAdapter() {
    costMap = new HashMap<>();
  }

  @Override
  public DayHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new DayHolder(LayoutInflater
                             .from(parent.getContext())
                             .inflate(R.layout.item_day, parent, false));
  }

  @Override
  public void onBindViewHolder(final DayHolder holder, final int position) {

    if (listener != null) {
      holder.itemView.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
          listener.onClick(days[holder.getAdapterPosition()]);
        }
      });
    }
    holder.update(days[position]);
  }

  @Override
  public int getItemCount() {
    return days.length;
  }

  public void setItems(Map<Integer, Integer> coastMap) {
    this.costMap.clear();
    this.costMap.putAll(coastMap);
    this.costMin = Collections.min(coastMap.values());
    notifyDataSetChanged();
  }

  public void setMonth(int month) {
    this.month = month;
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.MONTH, month);
    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);

    int dayStart  = calendar.get(Calendar.DAY_OF_WEEK);
    int daysCount = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    int prevMonth = month - 1;

    calendar.set(Calendar.MONTH, prevMonth);
    int prevDaysCount = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    int current       = prevDaysCount - wrapDay(dayStart);
    for (int i = 0; i < days.length; i++) {
      Integer cost = costMap.get(current);
      days[i] = new DayItem(
          ++current, //день
          prevDaysCount == 0 && daysCount != 0, // текущий месяц ?
          ((i + 1) % 7) == 0 || ((i + 2) % 7) == 0, //выходной ?
          prevDaysCount == 0 ? cost : null, // цена в этот день, может быть null
          cost != null && cost.equals(costMin) // минимальная цена за месяц ?
      );

      if (current == prevDaysCount) {
        prevDaysCount = 0;
        current = 0;
      } else if (prevDaysCount == 0 && current == daysCount) {
        daysCount = 0;
        current = 0;
      }
    }
    notifyDataSetChanged();
  }

  int getMonth() {
    return month;
  }

  public String getDate() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.MONTH, month);
    return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) +
           " " +
           calendar.get(Calendar.YEAR);
  }

  private int wrapDay(int day) {
    switch (day) {
      case Calendar.MONDAY:
        return 7;
      case Calendar.TUESDAY:
        return 1;
      case Calendar.WEDNESDAY:
        return 2;
      case Calendar.THURSDAY:
        return 3;
      case Calendar.FRIDAY:
        return 4;
      case Calendar.SATURDAY:
        return 5;
      case Calendar.SUNDAY:
        return 6;
    }
    return 7;
  }

  public void setOnDayClickListener(OnDayClickListener listener) {
    this.listener = listener;
  }

  public interface OnDayClickListener {
    void onClick(DayItem item);
  }
}
