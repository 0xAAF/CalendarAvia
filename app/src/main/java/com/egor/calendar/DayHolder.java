package com.egor.calendar;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by magic on 7/20/17.
 */

class DayHolder extends RecyclerView.ViewHolder {

  private final TextView day;
  private final TextView coast;
  private final View     find;

  public DayHolder(View itemView) {
    super(itemView);
    day = (TextView) itemView.findViewById(R.id.day);
    coast = (TextView) itemView.findViewById(R.id.coast);
    find = itemView.findViewById(R.id.find);
  }

  void update(DayItem item) {
    int     visibleInCurrentMonth = item.currentMonth() ? View.VISIBLE : View.GONE;
    boolean selected              = item.getCoast() != null;
    itemView.setEnabled(item.currentMonth());
    itemView.setSelected(selected);

    day.setVisibility(visibleInCurrentMonth);
    day.setText(String.valueOf(item.getDay()));
    day.setEnabled(item.isWeekend());
    day.setSelected(selected);

    coast.setVisibility(selected ? View.VISIBLE : View.GONE);
    coast.setSelected(selected);
    if (selected) {
      coast.setText(String.valueOf(item.getCoast()));
    }
    find.setVisibility(selected ? View.GONE : visibleInCurrentMonth);
  }
}
