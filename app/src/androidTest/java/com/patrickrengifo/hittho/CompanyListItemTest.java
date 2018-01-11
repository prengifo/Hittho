package com.patrickrengifo.hittho;

import android.support.test.runner.AndroidJUnit4;

import com.facebook.litho.testing.espresso.ComponentHostMatchers;
import com.facebook.litho.testing.espresso.LithoActivityTestRule;
import com.patrickrengifo.hittho.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.CoreMatchers.containsString;

/**
 * Created by Patrick Rengifo on 1/10/18.
 */

@RunWith(AndroidJUnit4.class)
public class CompanyListItemTest {

    @Rule
    public LithoActivityTestRule<MainActivity> mActivity =
            new LithoActivityTestRule<>(MainActivity.class);

    @Test
    public void companyNameIsDisplayed() {
        onView(ComponentHostMatchers.componentHostWithText(containsString("Hitta")))
                .check(matches((isDisplayed())));
    }

    @Test
    public void addressIsDisplayed() {
        onView(ComponentHostMatchers.componentHostWithText(containsString("Drottninggatan 95A, Stockholm")))
                .check(matches((isDisplayed())));
    }
}
