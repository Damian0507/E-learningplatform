package com.example.e_learningplatform.HomeProfessorAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_learningplatform.HomeAdapters.ChatAdapter;
import com.example.e_learningplatform.HomeClasses.Chat;
import com.example.e_learningplatform.HomeProfessorClasses.ProfessorChat;
import com.example.e_learningplatform.R;

import java.util.ArrayList;


public class ChatProfessorAdapter extends RecyclerView.Adapter<ChatProfessorAdapter.MyViewHolder> {

    Context context;
    ArrayList<ProfessorChat> chatArrayList;

    public ChatProfessorAdapter(Context context, ArrayList<ProfessorChat> chatArrayList) {
        this.context = context;
        this.chatArrayList = chatArrayList;
    }





    @NonNull
    @Override
    public ChatProfessorAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.chat_item,parent,false);


        return new ChatProfessorAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatProfessorAdapter.MyViewHolder holder, int position) {

        ProfessorChat chat = chatArrayList.get(position);
        holder.nume_utilizator_textView.setText(chat.getNume_utilizator());
        holder.last_message_textView.setText(chat.getLast_message());
        holder.profile_imageView.setImageResource(chat.getProfile_pic());


    }

    @Override
    public int getItemCount() {
        return chatArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{



        TextView nume_utilizator_textView,last_message_textView;
        ImageView profile_imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            nume_utilizator_textView = itemView.findViewById(R.id.nume_utilizator_textView);
            last_message_textView = itemView.findViewById(R.id.last_message_textView);
            profile_imageView = itemView.findViewById(R.id.profile_pic);
        }
    }
}
