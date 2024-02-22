package com.dipak.musicplayer;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Context context;
    List<Song> songs;
    ExoPlayer player;
    ConstraintLayout playerView;
    private static final int VIEW_TYPE_SONG = 0;
    private static final int VIEW_TYPE_AD = 1;
    public SongAdapter(Context context, List<Song> songs, ExoPlayer player, ConstraintLayout playerView) {
        this.context = context;
        this.songs = songs;
        this.player = player;
        this.playerView=playerView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//        inflate song and view holder
//        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.song_row_item,parent,false);
//
//        return new SongViewHolder(view);

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case VIEW_TYPE_SONG:
                // Inflate layout for songs
                View songView = inflater.inflate(R.layout.song_row_item, parent, false);
                return new SongViewHolder(songView);
            case VIEW_TYPE_AD:
                // Inflate layout for ads
                View adView = inflater.inflate(R.layout.item_ad, parent, false);
                return new AdViewHolder(adView);
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case VIEW_TYPE_SONG:
        //current song and view holder
        if(position<songs.size()) {
            Song song = songs.get(position);
            SongViewHolder viewHolder = (SongViewHolder) holder;

            //set value to view
            viewHolder.titleHolder.setText(song.getTitle());
            viewHolder.durationHolder.setText(getDuration(song.getDuration()));
            viewHolder.sizeHolder.setText(getSize(song.getSize()));

            //artwork
            Uri artworkUri = song.getArtworkUri();
            if (artworkUri != null) {
                viewHolder.artworkHolder.setImageURI(artworkUri);
                if (viewHolder.artworkHolder.getDrawable() == null) {
                    viewHolder.artworkHolder.setImageResource(R.drawable.splash_logo);
                }
            }

            //play song on item click
            viewHolder.itemView.setOnClickListener(view -> {

                //start the player service
//            context.startService(new Intent(context.getApplicationContext(), PlayerService.class));

                //is song played from search view?, if yes hide the soft keyboard by clearing the focus, see the method in main activity
                SearchView searchView = ((MainActivity) context).searchView;
                if (searchView != null)
                    searchView.clearFocus();

                //show the player view
                playerView.setVisibility(View.VISIBLE);

                //playing the song
                if (!player.isPlaying()) {
                    player.setMediaItems(getMediaItems(), position, 0);

                } else {
                    player.pause();
                    player.seekTo(position, 0);

                }

                //prepare and play
                player.prepare();
                player.play();


                //check if the record audio permission is granted, hence request it
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                    //request the record audio perm.
                    ((MainActivity) context).recordAudioPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO);
                }
            });
        }

                break;
            case VIEW_TYPE_AD:
                // Load ad into AdView for AdViewHolder
                AdViewHolder adViewHolder = (AdViewHolder) holder;
                AdRequest adRequest = new AdRequest.Builder().build();
                adViewHolder.adView.loadAd(adRequest);
                break;
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (position + 1) % 6 == 0 ? VIEW_TYPE_AD : VIEW_TYPE_SONG;
    }

    private List<MediaItem> getMediaItems() {
        //define a list of media items
        List<MediaItem> mediaItems=new ArrayList<>();
        for (Song song:songs)
        {
            MediaItem mediaItem=new MediaItem.Builder()
                    .setUri((song.getUri()))
                    .setMediaMetadata(getMetadata(song))
                    .build();
            //add the media item to media items list
            mediaItems.add(mediaItem);
        }
        return mediaItems;
    }

    private MediaMetadata getMetadata(Song song) {
        return new MediaMetadata.Builder()
                .setTitle(song.getTitle())
                .setArtworkUri(song.getArtworkUri())
                .build();
    }

    public static class SongViewHolder extends RecyclerView.ViewHolder{

        ImageView artworkHolder;
        TextView titleHolder, durationHolder, sizeHolder;
        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            artworkHolder=itemView.findViewById(R.id.artworkView);
            titleHolder=itemView.findViewById(R.id.titleView);
            durationHolder=itemView.findViewById(R.id.durationView);
            sizeHolder=itemView.findViewById(R.id.sizeView);
        }
    }

    @Override
    public int getItemCount() {
        return songs.size() + (songs.size() / 5);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filterSongs(List<Song> filteredList)
    {
        songs=filteredList;
        notifyDataSetChanged();
    }

    @SuppressLint("DefaultLocale")
    private String getDuration(int totalDuration)
    {
        String totalDurationText;

        int hrs=totalDuration/(1000*60*60);
        int min=(totalDuration%(1000*60*60))/(1000*60);
        int secs=(((totalDuration%(1000*60*60))%(1000*60*60))%(1000*600))/1000;

        if(hrs<1)
        {
            totalDurationText=String.format("%02d:%02d", min, secs);
        }else{
            totalDurationText=String.format("%1d:%02d:%02d",hrs,min,secs);
        }

        return totalDurationText;
    }

    //size
    private String getSize(long bytes){

        String hrSize;

        double k=bytes/1024.0;
        double m= ((bytes/1024.0)/1024.0);
        double g = (((bytes/1024.0)/1024.0)/1024.0);
        double t= ((((bytes/1024.0)/1024.0)/1024.0)/1024.0);

        //the format
        DecimalFormat dec=new DecimalFormat("0.00");
        if(t>1){
            hrSize=dec.format(t).concat(" TB");
        }else if(g>1){
            hrSize=dec.format(g).concat(" GB");
        }else if(m>1){
            hrSize=dec.format(m).concat(" MB");
        }else if(k>1){
            hrSize=dec.format(k).concat(" KB");
        }else{
            hrSize=dec.format(g).concat(" Bytes");
        }
        return hrSize;
    }

    public static class AdViewHolder extends RecyclerView.ViewHolder {
        AdView adView;

        public AdViewHolder(@NonNull View itemView) {
            super(itemView);
            adView = itemView.findViewById(R.id.adView);
        }
    }
}
