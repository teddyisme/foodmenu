package com.lixinsheng.foodmenu.ui.activity;

import android.content.Intent;

import com.lixinsheng.foodmenu.R;
import com.lixinsheng.foodmenu.test.BuildConfig;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

/**
 * Created by lixinsheng on 16/2/26.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTest {
        @Test
        public void testMainActivity() {
            MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
            mainActivity.findViewById(R.id.edit_text_search).performClick();

//            Intent expectedIntent = new Intent(mainActivity, SecondActivity.class);
//            ShadowActivity shadowActivity = Shadows.shadowOf(mainActivity);
//            Intent actualIntent = shadowActivity.getNextStartedActivity();



//            Assert.assertEquals(expectedIntent, actualIntent);
        }
}
