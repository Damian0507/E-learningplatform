package com.example.e_learningplatform.Home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_learningplatform.HomeAdapters.ChatAdapter;
import com.example.e_learningplatform.HomeAdapters.HomeAdapter;
import com.example.e_learningplatform.HomeClasses.Chat;
import com.example.e_learningplatform.HomeClasses.Materii;
import com.example.e_learningplatform.R;

import java.util.ArrayList;

public class ChatStudentFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Chat>chatArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_student, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dateInitialize_chat();

        recyclerView = view.findViewById(R.id.chat_recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ChatAdapter chatAdapter = new ChatAdapter(getContext(),chatArrayList);
        recyclerView.setAdapter(chatAdapter);
        chatAdapter.notifyDataSetChanged();
        recyclerView.setHasFixedSize(true);
    }
    private void dateInitialize_chat() {


        chatArrayList.add(new Chat("Duma Virgil","De ce nu merg caloriferele?",R.drawable.chat_profile_pic));
        chatArrayList.add(new Chat("Barna Cornel","Trecutul se repeta!",R.drawable.chat_profile_pic));
        chatArrayList.add(new Chat("Dragu Daniel","Sa invatati tabelele de adevar!",R.drawable.chat_profile_pic));
        chatArrayList.add(new Chat("Mnerie Corina","Sa predati proiectele va rog!",R.drawable.chat_profile_pic));
        chatArrayList.add(new Chat("Balas Marius","Azi este o zi buna!",R.drawable.chat_profile_pic));


    }
}