package com.example.qlbhcdio.ui.home;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.qlbhcdio.ui.account.AccountFragment;
import com.example.qlbhcdio.ui.history.HistoryFragment;
import com.example.qlbhcdio.ui.shop.ShopFragment;
import com.example.qlbhcdio.ui.settings.SettingFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPageAdapter extends FragmentStatePagerAdapter implements ShopFragment.SendItemToGirdAdapter {
    private SendItemToHomePage itemToHomePage;
    private List<Fragment> fragments = new ArrayList<>();

    public ViewPageAdapter(@NonNull FragmentManager fm, SendItemToHomePage itemToHomePage) {
        super(fm);
        this.itemToHomePage = itemToHomePage;
        fragments.add(new ShopFragment(this));
        fragments.add(new HistoryFragment());
        fragments.add(new AccountFragment());
        fragments.add(new SettingFragment());
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public void OnSend(String jsonProduct) {
        itemToHomePage.onSend(jsonProduct);
    }

    // gửi item tới Home
    public interface SendItemToHomePage {
        void onSend(String jsonProduct);
    }
}
