package com.example.commenthunter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MessagesAdapter extends ArrayAdapter<Message> {

    private Context mContext;
    private List<Message> messageList = new ArrayList<>();

    public MessagesAdapter(Context context, ArrayList<Message> messages) {
        super(context, 0, messages);
        mContext = context;
        messageList = messages;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.row_item_messages, parent,false);

        Message currentMessage = messageList.get(position);

        TextView name = listItem.findViewById(R.id.commentTextView);
        name.setText(currentMessage.message);

        return listItem;
    }
}
