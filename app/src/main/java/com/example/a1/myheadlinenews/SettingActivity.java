package com.example.a1.myheadlinenews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import utils.Night_styleutils;

public class SettingActivity extends AppCompatActivity {
    @BindView(R.id.night_mode_text)
    TextView mNightModeText;
    @BindView(R.id.checkbox_night_mode)
    CheckBox mCheckboxNightMode;
    @BindView(R.id.night_mode)
    LinearLayout mNightMode;
    @BindView(R.id.list_mode_text)
    TextView mListModeText;
    @BindView(R.id.checkbox_list_mode)
    CheckBox mCheckboxListMode;
    @BindView(R.id.setting_list_mode)
    LinearLayout mSettingListMode;
    @BindView(R.id.font_size_text)
    TextView mFontSizeText;
    @BindView(R.id.font_size)
    TextView mFontSize;
    @BindView(R.id.font_size_arrow)
    ImageView mFontSizeArrow;
    @BindView(R.id.setting_font_size)
    LinearLayout mSettingFontSize;
    @BindView(R.id.list_comment_text)
    TextView mListCommentText;
    @BindView(R.id.list_comment_mode)
    TextView mListCommentMode;
    @BindView(R.id.list_comment_arrow)
    ImageView mListCommentArrow;
    @BindView(R.id.setting_list_comment)
    LinearLayout mSettingListComment;
    @BindView(R.id.refresh_list_text)
    TextView mRefreshListText;
    @BindView(R.id.refresh_list_mode)
    TextView mRefreshListMode;
    @BindView(R.id.refresh_list_arrow)
    ImageView mRefreshListArrow;
    @BindView(R.id.setting_refresh_list)
    LinearLayout mSettingRefreshList;
    @BindView(R.id.load_image_text)
    TextView mLoadImageText;
    @BindView(R.id.load_image_mode)
    TextView mLoadImageMode;
    @BindView(R.id.load_image_arrow)
    ImageView mLoadImageArrow;
    @BindView(R.id.setting_load_image)
    LinearLayout mSettingLoadImage;
    @BindView(R.id.clear_text)
    TextView mClearText;
    @BindView(R.id.cache_size)
    TextView mCacheSize;
    @BindView(R.id.clear_arrow)
    ImageView mClearArrow;
    @BindView(R.id.clear)
    LinearLayout mClear;
    @BindView(R.id.notify_text)
    TextView mNotifyText;
    @BindView(R.id.checkbox_notify)
    CheckBox mCheckboxNotify;
    @BindView(R.id.notify)
    LinearLayout mNotify;
    @BindView(R.id.interactive_text)
    TextView mInteractiveText;
    @BindView(R.id.checkbox_interactive)
    CheckBox mCheckboxInteractive;
    @BindView(R.id.interactive)
    LinearLayout mInteractive;
    @BindView(R.id.use_swip_text)
    TextView mUseSwipText;
    @BindView(R.id.checkbox_use_swipe)
    CheckBox mCheckboxUseSwipe;
    @BindView(R.id.use_swipe)
    LinearLayout mUseSwipe;
    @BindView(R.id.checkbox_close_shake_hand)
    CheckBox mCheckboxCloseShakeHand;
    @BindView(R.id.close_shake_hand)
    LinearLayout mCloseShakeHand;
    @BindView(R.id.share_when_favor_text)
    TextView mShareWhenFavorText;
    @BindView(R.id.checkbox_share_when_favor)
    CheckBox mCheckboxShareWhenFavor;
    @BindView(R.id.share_when_favor)
    LinearLayout mShareWhenFavor;
    @BindView(R.id.share_when_diggbury_text)
    TextView mShareWhenDiggburyText;
    @BindView(R.id.checkbox_share_when_diggbury)
    CheckBox mCheckboxShareWhenDiggbury;
    @BindView(R.id.share_when_diggbury)
    LinearLayout mShareWhenDiggbury;
    @BindView(R.id.update_text)
    TextView mUpdateText;
    @BindView(R.id.version_new)
    ImageView mVersionNew;
    @BindView(R.id.current_version)
    TextView mCurrentVersion;
    @BindView(R.id.update_arrow)
    ImageView mUpdateArrow;
    @BindView(R.id.update)
    LinearLayout mUpdate;
    @BindView(R.id.recommendation_text)
    TextView mRecommendationText;
    @BindView(R.id.recommendation_arrow)
    ImageView mRecommendationArrow;
    @BindView(R.id.recommendation)
    LinearLayout mRecommendation;
    @BindView(R.id.switch_domain_text)
    TextView mSwitchDomainText;
    @BindView(R.id.checkbox_switch_domain)
    CheckBox mCheckboxSwitchDomain;
    @BindView(R.id.switch_domain)
    LinearLayout mSwitchDomain;
    @BindView(R.id.use_help_text)
    TextView mUseHelpText;
    @BindView(R.id.use_help_arrow)
    ImageView mUseHelpArrow;
    @BindView(R.id.use_help)
    LinearLayout mUseHelp;
    @BindView(R.id.copyright)
    TextView mCopyright;
    @BindView(R.id.release_info)
    TextView mReleaseInfo;
    private int theme = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.night_mode_text, R.id.checkbox_night_mode, R.id.night_mode, R.id.list_mode_text, R.id.checkbox_list_mode, R.id.setting_list_mode, R.id.font_size_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.night_mode_text:
                break;
            case R.id.checkbox_night_mode:
                break;
            case R.id.night_mode:
                break;
            case R.id.list_mode_text:
                break;
            case R.id.checkbox_list_mode:
                break;
            case R.id.setting_list_mode:
                break;
            case R.id.font_size_text:
                break;
        }
    }
}
