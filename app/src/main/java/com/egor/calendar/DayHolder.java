package com.egor.calendar;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by magic on 7/20/17.
 */

class DayHolder extends RecyclerView.ViewHolder {

  private final TextView day;
  private final TextView cost;
  private final View     find;

  public DayHolder(View itemView) {
    super(itemView);
    day = (TextView) itemView.findViewById(R.id.day);
    cost = (TextView) itemView.findViewById(R.id.cost);
    find = itemView.findViewById(R.id.find);
  }

  void update(DayItem item) {
    int     visibleInCurrentMonth = item.isCurrentMonth() ? View.VISIBLE : View.GONE;
    boolean hasCost               = item.getCost() != null;
    boolean green                 = item.isMinimal();
    itemView.setEnabled(item.isCurrentMonth());
    itemView.setSelected(green);

    day.setVisibility(visibleInCurrentMonth);
    day.setText(String.valueOf(item.getDay()));
    day.setEnabled(item.isWeekend());
    day.setSelected(green);

    cost.setVisibility(hasCost ? visibleInCurrentMonth : View.GONE);
    cost.setSelected(green);
    cost.setEnabled(false);
    if (hasCost) {
      cost.setText(String.valueOf(item.getCost()));
    }
    find.setVisibility(hasCost ? View.GONE : visibleInCurrentMonth);
  }
}
