package com.litemob.screentogetherpink.ui.activity;

import android.os.Bundle;

import com.gyf.immersionbar.ImmersionBar;
import com.litemob.screentogetherpink.BaseConstant;

import androidx.viewbinding.ViewBinding;
import ps.center.application.mine.ScoreDialog;
import ps.center.views.DataChanger.DataChangeCallBack;
import ps.center.views.DataChanger.DataChangeManager;
import ps.center.views.activity.BaseActivityVB;

public abstract class MyBaseActivity<T extends ViewBinding> extends BaseActivityVB<T> implements DataChangeCallBack {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      DataChangeManager.get().registerChangCallBack(this);
      ImmersionBar.with(this)
              .statusBarDarkFont(true)   //true字是深色，false字是白色
              .init();
   }

   public boolean isTop = true;

   @Override
   protected void onResume() {
      super.onResume();
      isTop = true;
   }

   @Override
   protected void onStop() {
      super.onStop();
      isTop = false;
   }

   @Override
   public void change(int i, Object o) {
      if(i == BaseConstant.SHOW_RATING && isTop){
         new ScoreDialog(MyBaseActivity.this).show();
      }
   }

   @Override
   protected void onDestroy() {
      super.onDestroy();
      DataChangeManager.get().unregisterChangCallBack(this);
   }
}
