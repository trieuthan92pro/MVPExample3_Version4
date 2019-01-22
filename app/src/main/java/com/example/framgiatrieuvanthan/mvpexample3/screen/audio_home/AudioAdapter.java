package com.example.framgiatrieuvanthan.mvpexample3.screen.audio_home;


import android.content.ClipData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.framgiatrieuvanthan.mvpexample3.R;
import com.example.framgiatrieuvanthan.mvpexample3.data.model.Audio;

import java.util.List;

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.ViewHolder> {

    private List<Audio> audioList;
    private Context context;
    private ItemClickListener mItemClickListener;

    public AudioAdapter(Context context, List<Audio> list, ItemClickListener listener){
        mItemClickListener = listener;
        audioList = list;
        this.context = context;
    }

    public void setAudioList(List<Audio> list){
        if(audioList == null){
            audioList = list;
        }
        else{
            audioList.clear();
            audioList.addAll(list);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.audio_item_layout,
                viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Audio audio = audioList.get(i);
        viewHolder.textViewTitle.setText(audio.getTitle());
        viewHolder.textViewArtist.setText(audio.getArtist());
        viewHolder.imageViewIcon.setImageBitmap(audio.getBitmapAudio());
    }

    @Override
    public int getItemCount() {
        if(audioList == null){ //if list audio is null, then
            return 0; // size of this playlist is 0
        } // otherwise, size of them is list.size()
        return audioList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        ImageView imageViewIcon;
        TextView textViewTitle;
        TextView textViewArtist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewIcon = itemView.findViewById(R.id.image_icon);
            textViewArtist = itemView.findViewById(R.id.text_artist);
            textViewTitle = itemView.findViewById(R.id.text_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mItemClickListener.onItemClick(position);
        }
    }

    public interface ItemClickListener{
        void onItemClick(int clickedPosition);
    }
}
