package me.subhrajyoti.myday;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_bar)
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        MyDayFragment myDayFragment = new MyDayFragment();

        bottomNavigationView.setOnNavigationItemSelectedListener(
                menuItem -> {
                    switch (menuItem.getItemId()) {
                        case R.id.bottombaritem_myday :
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragment_holder, myDayFragment)
                                    .commit();
                    }
                    return true;
                }
        );


    }


}
