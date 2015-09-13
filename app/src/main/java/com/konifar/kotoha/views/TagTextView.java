package com.konifar.kotoha.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.konifar.kotoha.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TagTextView extends FrameLayout {

    @Bind(R.id.txt_tag)
    TextView txtTag;

    public TagTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TagTextView(Context context, String name) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.ui_tag_text, this, true);
        ButterKnife.bind(this, view);

        txtTag.setText(name);
    }

}
