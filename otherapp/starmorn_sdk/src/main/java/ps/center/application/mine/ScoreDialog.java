package ps.center.application.mine;

import android.content.Context;
import androidx.recyclerview.widget.GridLayoutManager;
import java.util.ArrayList;
import java.util.List;
import ps.center.application.BuildConfig;
import ps.center.R;
import ps.center.application.config.ApplicationConfig;
import ps.center.business.http.base.BusHttp;
import ps.center.centerinterface.constant.CenterConstant;
import ps.center.databinding.BusinessDialogScoreBinding;
import ps.center.utils.Save;
import ps.center.utils.Super;
import ps.center.utils.ToastUtils;
import ps.center.views.dialog.BaseDialogVB2;

/* loaded from: classes.jar:ps/center/application/mine/ScoreDialog.class */
public class ScoreDialog extends BaseDialogVB2<BusinessDialogScoreBinding> {
    private ScoreAdapter scoreAdapter;
    private int starListWidth;

    public ScoreDialog(Context context) {
        super(context);
        this.starListWidth = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ps.center.views.dialog.BaseDialogVB2
    public BusinessDialogScoreBinding getLayout() {
        return BusinessDialogScoreBinding.inflate(getLayoutInflater());
    }

    @Override // ps.center.views.dialog.BaseDialogVB2
    protected void initData() {
        List<ScoreStar> list = new ArrayList<>();
        list.add(new ScoreStar());
        list.add(new ScoreStar());
        list.add(new ScoreStar());
        list.add(new ScoreStar());
        list.add(new ScoreStar());
        this.scoreAdapter = new ScoreAdapter(getContext(), list);
        ((BusinessDialogScoreBinding) this.binding).starList.setLayoutManager(new GridLayoutManager(getContext(), 5));
        ((BusinessDialogScoreBinding) this.binding).starList.setAdapter(this.scoreAdapter);
        this.scoreAdapter.notifyDataSetChanged();
        ((BusinessDialogScoreBinding) this.binding).starView.post(() -> {
            this.starListWidth = ((BusinessDialogScoreBinding) this.binding).starView.getWidth();
        });
        ((BusinessDialogScoreBinding) this.binding).rootLayout.setBackgroundResource(ApplicationConfig.getSettingConfig().scoreDialogBackImage);
    }

    @Override // ps.center.views.dialog.BaseDialogVB2
    public void setListener() {
        ((BusinessDialogScoreBinding) this.binding).closeBtn.setOnClickListener(v -> {
            dismiss();
        });
        ((BusinessDialogScoreBinding) this.binding).starView.setOnClickListener(v2 -> {
        });
        ((BusinessDialogScoreBinding) this.binding).starView.setOnTouchListener((v3, event) -> {
            switch (event.getAction()) {
                case 0:
                case 2:
                    if (this.starListWidth != 0) {
                        int starWidth = (int) (this.starListWidth / 5.0f);
                        float x = event.getX();
                        if (x > 0.0f && x < starWidth * 1.0f) {
                            setScore(1);
                            break;
                        } else if (x > starWidth * 1.0f && x < starWidth * 2.0f) {
                            setScore(2);
                            break;
                        } else if (x > starWidth * 2.0f && x < starWidth * 3.0f) {
                            setScore(3);
                            break;
                        } else if (x > starWidth * 3.0f && x < starWidth * 4.0f) {
                            setScore(4);
                            break;
                        } else if (x > starWidth * 4.0f && x < starWidth * 5.0f) {
                            setScore(5);
                            break;
                        }
                    }
                    break;
                case 1:
                    ((BusinessDialogScoreBinding) this.binding).submit.setVisibility(0);
                    if (this.scoreAdapter.getStarNum() < 4) {
                        ((BusinessDialogScoreBinding) this.binding).input.setVisibility(0);
                        ((BusinessDialogScoreBinding) this.binding).submit.setText("问题反馈");
                        break;
                    } else {
                        ((BusinessDialogScoreBinding) this.binding).input.setVisibility(8);
                        ((BusinessDialogScoreBinding) this.binding).submit.setText("好评鼓励");
                        break;
                    }
            }
            return false;
        });
        ((BusinessDialogScoreBinding) this.binding).submit.setOnClickListener(v4 -> {
            String content = ((BusinessDialogScoreBinding) this.binding).input.getText().toString().trim();
            if (this.scoreAdapter.getStarNum() == 0) {
                ToastUtils.show(Super.getContext(), "请给我们评个星吧～ ");
                return;
            }
            if (content.equals(BuildConfig.VERSION_NAME)) {
                if (this.scoreAdapter.getStarNum() == 0) {
                    ToastUtils.show(Super.getContext(), "请给我们评个星吧～ ");
                    return;
                } else {
                    ToastUtils.show(Super.getContext(), "感谢您的评价！");
                    dismiss();
                    return;
                }
            }
            BusHttp.bot().dingBot(((((((((content + "\n") + "\n") + "用户ID：" + Save.instance.getLong(CenterConstant.UID, -1L)) + "\n") + "用户昵称：" + CenterConstant.getUser().username) + "\n") + "会员状态：" + CenterConstant.getUser().isVip) + "\n") + "反馈类型：好评弹窗（" + this.scoreAdapter.getStarNum() + "星）", null);
            ToastUtils.show(Super.getContext(), "提交成功, 我们会尽快处理您的问题。");
            dismiss();
        });
    }

    private void setScore(int score) {
        this.scoreAdapter.setStarNum(score);
        if (score > 3) {
            ((BusinessDialogScoreBinding) this.binding).statusImage.setImageResource(R.mipmap.business_score_status_2);
        } else {
            ((BusinessDialogScoreBinding) this.binding).statusImage.setImageResource(R.mipmap.business_score_status_3);
        }
    }
}