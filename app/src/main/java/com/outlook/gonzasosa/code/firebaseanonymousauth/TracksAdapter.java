package com.outlook.gonzasosa.code.firebaseanonymousauth;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.outlook.gonzasosa.code.firebaseanonymousauth.Models.My_Services;
import com.outlook.gonzasosa.code.firebaseanonymousauth.Models.Track;

import java.util.ArrayList;
import java.util.Locale;

public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.TracksViewHolder>{// implements MediaPlayer.OnPreparedListener{
    private ArrayList<Track> tracks;
    private Context context;
    public static String cancion;

    TracksAdapter(Context ctx, ArrayList<Track> t) {
        tracks = t;
        context = ctx;
    }

    @Override
    public TracksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.track_item, null, false);
        return new TracksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TracksViewHolder holder, int position) {
        final Track track = tracks.get(position);
        holder.setData(track.title, track.album, track.year);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(context, My_Services.class);
                getSong(track.title);
                context.startService(intent);
                //Toast.makeText(context,"Pasa algp aqui",Toast.LENGTH_SHORT).show();
                // getSongsList();
            }
        });
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

    /*@Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();

    }*/


 /* public void reproducir(String titulo) {
       MediaPlayer mediaplayer = new MediaPlayer();

        try {

            //mediaplayer.setDataSource("Memoria interna/Music/" + nombre + ".mp3"); //celular eduardo memoria interna
            //mediaplayer.setDataSource("Tarjeta SD/Musica/" + nombre + ".mp3");


            mediaplayer.setDataSource("/storage/8235-1CE9/Musica/" + titulo +".mp3"); // celular de martin

            mediaplayer.setOnPreparedListener(this);
            mediaplayer.prepare();

        } catch (IOException e) {
            //Log.d("Muestra" , e.getMessage());
        }

        //mediaplayer.prepareAsync();

    }*/


    class TracksViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvAlbum, tvYear;

        TracksViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvAlbum = (TextView) itemView.findViewById(R.id.tvAlbum);
            tvYear = (TextView) itemView.findViewById(R.id.tvYear);
        }


        void setData(String title, String album, int year) {
            tvTitle.setText(title);
            tvAlbum.setText(album);

            String s = String.format(Locale.US, "%s", year);
            tvYear.setText(s);
        }
    }

/*
    private void getSongsList() {
        ContentResolver musicResolver = context.getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        String[] myProjection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST
        };

        Cursor musicCursor = musicResolver.query(musicUri, myProjection, null, null, null);

        if (musicCursor != null && musicCursor.moveToFirst()) {
            int titleColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);

            do {
                String output = String.format(Locale.US,
                        "Artist: %s, Title: %s,  Album: %s", musicCursor.getString(titleColumn),
                        musicCursor.getString(artistColumn), musicCursor.getString(idColumn));

                Log.d(MainActivity.TAG, output);

            } while (musicCursor.moveToNext());
        }

        if (musicCursor != null) musicCursor.close();
    }
*/


    private void getSong(String song) {
        ContentResolver musicResolver = context.getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        String[] myProjection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST
        };

        String mySelectionClause = String.format(Locale.US, "%s = ?", MediaStore.Audio.Media.TITLE);
        String[] mySelectionArgs = new String[]{song};

        Cursor musicCursor = musicResolver.query(musicUri, myProjection, mySelectionClause, mySelectionArgs, null);

        if (musicCursor != null && musicCursor.moveToFirst()) {
            int titleColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            //  int idColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media._ID);
            //  int artistColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);

            do {
                // String output = String.format(Locale.US, "Artist: %s, Title: %s,  Album: %s", musicCursor.getString(titleColumn),
                // String output = String.format(Locale.US, "%s", musicCursor.getString(titleColumn),
                //       musicCursor.getString(artistColumn), musicCursor.getString(idColumn));


                String output = String.format(Locale.US, "%s", musicCursor.getString(titleColumn));
                Toast.makeText(context, output, Toast.LENGTH_LONG).show();
                cancion = output;


                //reproducir(output);


            } while (musicCursor.moveToNext());
        }

        if (musicCursor != null) musicCursor.close();
    }

/*
    public void reproducir(String nombre) {
        Uri datos = Uri.parse(Environment.getExternalStorageDirectory().getPath() + "/" + nombre + ".mp3");
        MediaPlayer mp = MediaPlayer.create(context, datos);
        mp.setOnPreparedListener(this);
    }




    public void reproducir(String nombre) {
        Uri datos = Uri.parse(Environment.getExternalStorageDirectory().getPath() + "/Musica/" + nombre + ".mp3");
        MediaPlayer mp = MediaPlayer.create(context, datos);
        mp.setOnPreparedListener(this);
    }
*/

}