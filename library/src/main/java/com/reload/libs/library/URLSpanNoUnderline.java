package com.reload.libs.library;

/*
 * Created by Administer on 15 September 2018.
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

import android.text.TextPaint;
import android.text.style.URLSpan;

public class URLSpanNoUnderline extends URLSpan {

    URLSpanNoUnderline(String p_Url) {
        super(p_Url);
    }

    public void updateDrawState(TextPaint p_DrawState) {
        super.updateDrawState(p_DrawState);
        p_DrawState.setUnderlineText(false);
    }

    /*@Override
    public void onClick(View widget) {
        super.onClick(widget);
        widget.setBackground(widget.getContext().getResources().getDrawable(R.drawable.xml_custom_effects));
        Toast.makeText(widget.getContext(), "Okkk", Toast.LENGTH_SHORT).show();
    }*/
}
