package com.egor.calendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

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
    adapter.setMonth(0);
    final TextView dateTextView = (TextView) findViewById(R.id.date);
    dateTextView.setText(adapter.getDate());

    adapter.setOnDayClickListener(new CalendarAdapter.OnDayClickListener() {

      @Override
      public void onClick(@Nullable DayItem item) {
        adapter.setMonth(adapter.getMonth() + 1);
        dateTextView.setText(adapter.getDate());
      }
    });
  }
}
