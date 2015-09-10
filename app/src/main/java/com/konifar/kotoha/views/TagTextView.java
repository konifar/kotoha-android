package com.konifar.kotoha.views;

import android.content.Context;
import android.widget.TextView;

import com.konifar.kotoha.R;

public class TagTextView extends TextView {

    public TagTextView(Context context, String name) {
        super(context);
        setBackgroundResource(R.drawable.bg_tag_grey);
        setText(name);
        setTextColor(getResources().getColor(R.color.grey600));
    }

}
