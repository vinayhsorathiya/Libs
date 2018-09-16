package com.reload.libs.library;

/*
 * Created by Administer on 16 September 2018.
 *
 * Copyright (C) 2018 Reload Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.RelativeSizeSpan;
import android.text.style.SubscriptSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.EasyEditSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class AdvTextView extends android.support.text.emoji.widget.EmojiTextView {
    private Context mContext;
    private String mEmailColorCode;
    private String mUrlColorCode;
    private String mPhoneNumberColorCode;
    private String mMentionColorCode;
    private String mHashTagColorCode;
    private boolean detectMentions = false;
    private boolean detectHashTags = false;

    public AdvTextView(Context context) {
        super(context);
        this.mContext = context;
        init(context, null);
    }

    public AdvTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.mEmailColorCode = "#3344ff";
        this.mUrlColorCode = "#3344ff";
        this.mPhoneNumberColorCode = "#3344ff";
        this.mMentionColorCode = "#3344ff";
        this.mHashTagColorCode = "#3344ff";
        init(context, attrs);
    }

    public AdvTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @SuppressLint("NewApi")
    public AdvTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.AdvTextView, 0, 0);
        String mAdvText;
        try {
            mAdvText = attributes.getString(R.styleable.AdvTextView_setAdvText);
        } finally {
            attributes.recycle();
        }

    }

    public String getEmojiByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }

    public void setText(String text) {
        super.setText(text);

        final SpannableString ss = new SpannableString(text);

        ColorStateList blackColor = new ColorStateList(new int[][]{new int[]{}}, new int[]{Color.BLACK});

        for (int i = 0; i < text.length(); ++i) {
            char charAt = text.charAt(i);
            if (SpecialCharacter.getInstance().getCharacter().containsKey(charAt)) {
                ss.setSpan(new TextAppearanceSpan(mContext, R.style.Style_1), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }else {
                ss.setSpan(new TextAppearanceSpan(mContext, R.style.Style_2), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        String[] words = text.split(" ");
        for (String word : words) {
            Log.d("test", word);
            word = word.replace("\n", "");

            if (android.util.Patterns.EMAIL_ADDRESS.matcher(word).matches()) {
                final int startIndex = text.indexOf(word);
                final int endIndex = startIndex + word.length();

                ClickableSpan emailClickSpan = new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        setClickRippleData(ss, startIndex, endIndex);
                    }
                };

                ForegroundColorSpan emailColorSpan = new ForegroundColorSpan(Color.parseColor(mEmailColorCode));
                ss.setSpan(emailClickSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                ss.setSpan(new URLSpanNoUnderline(word), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                ss.setSpan(emailColorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        super.setText(ss);
        super.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void setClickRippleData(final SpannableString ss, final int startIndex, final int endIndex) {
        ss.setSpan(new BackgroundColorSpan(Color.parseColor("#310b0b86")), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        super.setText(ss);
        super.postDelayed(new Runnable() {
            @Override
            public void run() {
                ss.setSpan(new BackgroundColorSpan(Color.TRANSPARENT), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                AdvTextView.super.setText(ss);

            }
        }, 400);
    }

    public void setEmailColorCode(String emailColorCode) {
        this.mEmailColorCode = emailColorCode;
    }

    public void setUrlColorCode(String urlColorCode) {
        this.mUrlColorCode = urlColorCode;
    }

    public void setPhoneNumberColorCode(String phoneNumberColorCode) {
        this.mPhoneNumberColorCode = phoneNumberColorCode;
    }

    public void setHashTagColorCode(String hashTagColorCode) {
        this.mHashTagColorCode = hashTagColorCode;
    }

    public void setMentionColorCode(String mentionColorCode) {
        this.mMentionColorCode = mentionColorCode;
    }

    public void setDetectMentions(boolean value) {
        this.detectMentions = value;
    }

    public void setDetectHashTags(boolean value) {
        this.detectHashTags = value;
    }
}
