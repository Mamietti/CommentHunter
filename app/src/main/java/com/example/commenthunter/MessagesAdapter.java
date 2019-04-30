package com.example.commenthunter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MessagesAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private List<String> messageList = new ArrayList<>();
    private LayoutInflater inflater;

    public MessagesAdapter(Context context, ArrayList<String> messages) {
        super(context, 0, messages);
        mContext = context;
        messageList = messages;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = convertView;
        if(listItem == null)
            listItem = inflater.inflate(R.layout.row_item_messages, parent,false);

        String currentMessage = messageList.get(position);

        TextView comment = (TextView)listItem.findViewById(R.id.commentText);
        comment.setText(currentMessage);

        return listItem;
    }
}
