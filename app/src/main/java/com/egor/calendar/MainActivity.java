package com.egor.calendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by magic on 7/20/17.
 */

public class MainActivity extends AppCompatActivity {

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    RecyclerView view = (RecyclerView) findViewById(R.id.list);
    view.setLayoutManager(new GridLayoutManager(this, 7));
    final CalendarAdapter adapter = new CalendarAdapter();
    view.setAdapter(adapter);
    final TextView dateTextView = (TextView) findViewById(R.id.date);
    dateTextView.setText(adapter.getDate());

    adapter.setOnDayClickListener(new CalendarAdapter.OnDayClickListener() {

      @Override
      public void onClick(@Nullable DayItem item) {
        adapter.setMonth(adapter.getMonth() + 1);
        dateTextView.setText(adapter.getDate());
      }
    });
    Map<Integer, Integer> map = new HashMap<>();
    map.put(14, 2000);
    map.put(26, 4000);
    map.put(20, 2200);
    map.put(23, 3400);
    map.put(5, 2000);
    map.put(9, 6000);

    adapter.setItems(map);
    adapter.setMonth(0);
  }
}
