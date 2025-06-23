package com.litemob.screentogetherpink.ui.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.litemob.screentogetherpink.R;
import com.litemob.screentogetherpink.databinding.ActivityPlayBinding;
import com.litemob.screentogetherpink.ui.dialog.RDialog;
import com.tencent.live2.V2TXLivePlayer;
import com.tencent.live2.V2TXLivePlayerObserver;
import com.tencent.live2.impl.V2TXLivePlayerImpl;

import ps.center.views.activity.IntentGet;
import ps.center.views.dialog.BaseDialog;


import static com.litemob.screentogetherpink.BaseConstant.handler;

public class PlayActivity extends MyBaseActivity<ActivityPlayBinding> {
    private String url;
    private V2TXLivePlayer mLivePlayer;
    private boolean isOpen = true;

    @Override
    protected ActivityPlayBinding getLayout() {
        return ActivityPlayBinding.inflate(this.getLayoutInflater());
    }

    @Override
    public void finish() {
        if (mLivePlayer != null) {
            mLivePlayer.stopPlay();
        }
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        super.finish();
    }

    @Override
    protected void initData(IntentGet intentGet) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        url = intentGet.getIntentValue("url", "");
        Log.e("666",url);
        mLivePlayer = new V2TXLivePlayerImpl(PlayActivity.this);
        mLivePlayer.setRenderView(binding.videoView);
        mLivePlayer.startLivePlay(url);

        new RDialog(PlayActivity.this, "温馨提示", "需对方设备先开始共享后，才能获取到您的连接信号。", "我知道了", new BaseDialog.Call() {
            @Override
            public void call(Object o) {

            }
        }).show();

        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        binding.ivOff.setOnClickListener(v -> {
            finish();
        });
        binding.ivVoice.setOnClickListener(v -> {
            isOpen = !isOpen;
            if (isOpen) {
                binding.ivVoice.setImageResource(R.mipmap.img_play_voice_on);
                int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                int targetVolume = maxVolume / 2;
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, targetVolume, 0);
            } else {
                // 静音媒体音量
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
                binding.ivVoice.setImageResource(R.mipmap.img_play_voice_off);

            }
        });


        mLivePlayer.setObserver(new V2TXLivePlayerObserver() {
            @Override
            public void onError(V2TXLivePlayer player, int code, String msg, Bundle extraInfo) {
                super.onError(player, code, msg, extraInfo);
                Log.e("deletelog", "onError  " + msg + "   extraInfo" + extraInfo);
            }

            @Override
            public void onWarning(V2TXLivePlayer player, int code, String msg, Bundle extraInfo) {
                super.onWarning(player, code, msg, extraInfo);
                Log.e("deletelog", "onWarning  " + msg + "   extraInfo" + extraInfo);

            }

            @Override
            public void onConnected(V2TXLivePlayer player, Bundle extraInfo) {
                super.onConnected(player, extraInfo);
                Log.e("deletelog", "onConnected  " + "extraInfo" + extraInfo);

            }

            @Override
            public void onVideoPlaying(V2TXLivePlayer player, boolean firstPlay, Bundle extraInfo) {
                super.onVideoPlaying(player, firstPlay, extraInfo);
                Log.e("deletelog", "onConnected  " + "extraInfo" + extraInfo);

            }

            @Override
            public void onAudioPlaying(V2TXLivePlayer player, boolean firstPlay, Bundle extraInfo) {
                super.onAudioPlaying(player, firstPlay, extraInfo);
                Log.e("deletelog", "onAudioPlaying  " + "extraInfo" + extraInfo);

            }
        });
    }
}
