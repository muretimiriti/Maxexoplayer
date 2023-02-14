package com.example.yetumusicapplication;

import android.app.Application;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;


public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;
    ExoPlayer exoPlayer;
    private StyledPlayerView mExoplayerView;
     public DefaultHttpDataSource.Factory;






    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        mView=itemView;

    }

    public void setvideo(final Application ctx,String title, final String url){

        TextView mtextview=mView.findViewById(R.id.Titletv);
        mExoplayerView=mView.findViewById(R.id.exoplayer_view);


        mtextview.setText(title);
        try {
            BandwidthMeter bandwidthMeter =new DefaultBandwidthMeter.Builder(ctx).build();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            exoPlayer=(ExoPlayer) ExoplayerFactory.newSimpleInstance(ctx);
            Uri video=Uri.parse(url);



            DefaultHttpDataSource dataSource=new DefaultHttpDataSource("video");
            ExtractorsFactory extractorsFactory=new DefaultExtractorsFactory();
            MediaSource mediaSource=new ProgressiveMediaSource (video,dataSourceFactory,extractorsFactory,null,null);
            mExoplayerView.setPlayer(exoPlayer);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(false);

        }catch (Exception e){
            Log.e("ViewHolder","exoplayer error"+e.toString());
        }


    }
}
