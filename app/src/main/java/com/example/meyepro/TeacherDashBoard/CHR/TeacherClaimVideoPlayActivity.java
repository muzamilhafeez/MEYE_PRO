package com.example.meyepro.TeacherDashBoard.CHR;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.example.meyepro.api.Api;
import com.example.meyepro.databinding.ActivityTeacherClaimVideoPlayBinding;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import retrofit2.http.Url;

public class TeacherClaimVideoPlayActivity extends AppCompatActivity {
    ActivityTeacherClaimVideoPlayBinding Binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding= ActivityTeacherClaimVideoPlayBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());
        String Url = Api.BASE_URL+"video?path="+getIntent().getStringExtra("VideoFile");
        onStart(Url);

    }

    private SimpleExoPlayer player;
    private void initializePlayer(String videoUrl) {
        // Create a SimpleExoPlayer instance
        player = new SimpleExoPlayer.Builder(getApplicationContext()).build();

        // Bind the player to the view
        PlayerView playerView = Binding.videoPlayerView;
        playerView.setPlayer(player);

        // Create a MediaSource representing the media to be played
        MediaSource mediaSource = new ProgressiveMediaSource.Factory(
                new DefaultDataSourceFactory(getApplicationContext(), "exoplayer-example")
        ).createMediaSource(Uri.parse(videoUrl));

        // Prepare the player with the source
        player.setMediaSource(mediaSource);
        player.prepare();
    }

    public void onStart(String videoURL) {
        super.onStart();
        initializePlayer(videoURL);
    }
    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }
    private void releasePlayer() {
        player.release();
    }
//    @Override
//    public void onStop()
//    {
//        super.onStop();
//
//        mediaPlayer.stop();
//        mediaPlayer.detachViews();
//    }
//
//    @Override
//   public void onDestroy()
//    {
//        super.onDestroy();
//
//        mediaPlayer.release();
//        libVlc.release();
//    }

}