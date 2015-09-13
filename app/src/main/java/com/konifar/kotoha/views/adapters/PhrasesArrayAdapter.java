package com.konifar.kotoha.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.konifar.kotoha.R;
import com.konifar.kotoha.models.pojo.Phrase;
import com.konifar.kotoha.views.TagTextView;

import org.apmem.tools.layouts.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PhrasesArrayAdapter extends ArrayAdapter<Phrase> {

    public PhrasesArrayAdapter(Context context) {
        super(context, R.layout.item_phrase, new ArrayList<Phrase>());
    }

    @Override
    public View getView(int pos, View view, ViewGroup parent) {
        ViewHolder holder;

        if (view == null || view.getTag() == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_phrase, parent, false);
            holder = new ViewHolder(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        view.setTag(holder);

        bindData(holder, getItem(pos));

        return view;
    }

    private void bindData(ViewHolder holder, Phrase phrase) {
        holder.txtPhrase.setText(phrase.getText());

        holder.containerTags.removeAllViews();
        List<String> tags = phrase.getTagList();
        if (tags != null && !tags.isEmpty()) {
            for (String tagName : tags) {
                holder.containerTags.addView(new TagTextView(getContext(), tagName));
            }
        }
    }

    static class ViewHolder {
        @Bind(R.id.txt_phrase)
        TextView txtPhrase;
        @Bind(R.id.container_tags)
        FlowLayout containerTags;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
