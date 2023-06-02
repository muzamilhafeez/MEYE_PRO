package com.example.meyepro.fragments.Admin.view.Teacher.Teacher.video;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.meyepro.R;
import com.example.meyepro.api.Api;
import com.example.meyepro.databinding.FragmentAdminViewTabTeacherScheduleBinding;
import com.example.meyepro.databinding.FragmentAdminViewTabTecherRecodingsVideoViewBinding;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.util.VLCVideoLayout;

import com.example.meyepro.models.recordings_details_by_teachername;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import java.util.ArrayList;


public class AdminViewTabTecherRecodingsVideoViewFragment extends Fragment {
    FragmentAdminViewTabTecherRecodingsVideoViewBinding binding;
//    private LibVLC libVlc;
//    private MediaPlayer mediaPlayer;
//    private VLCVideoLayout videoLayout;
String videoUrl;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentAdminViewTabTecherRecodingsVideoViewBinding.inflate(getLayoutInflater());
//code start
//        VideoView videoView = findViewById(R.id.video_view11);
//        if(videoView==null){
//
//        }

        Bundle b = getArguments();
        ArrayList<recordings_details_by_teachername> data = (ArrayList<recordings_details_by_teachername >) b.getSerializable("id");
       recordings_details_by_teachername showrecording= data.get(0);
        videoUrl = Api.BASE_URL+"video?path="+showrecording.getFileName();
//        binding.txtViewCameraDetail.setText(data.get(0).getNO()+"");
//        String videoUrl = Api.BASE_URL+"video?path="+data.get(0).getFileName();
        binding.textViewTeacherRecordingsDetailsTeacherName.setText("Teacher Name "+showrecording.getTeacherName());
        binding.textViewTeacherRecordingsDetailsCourseName.setText("Course Name "+showrecording.getCourseName());
        binding.textViewTeacherRecordingsDetailsDate.setText("Date"+showrecording.getDate());
        binding.textViewTeacherRecordingsDetailsSection.setText("Section "+showrecording.getDiscipline());
        binding.textViewTeacherRecordingsDetailsDateStart.setText("Start Time " +showrecording.getStartTime());
        binding.textViewTeacherRecordingsDetailsDateEnd.setText("End Time "+showrecording.getEndTime());
        binding.textViewTeacherRecordingsDetailsVenue.setText("Venue  "+showrecording.getVenue());
//        binding.textViewTeacherRecordingsDetailsFilenameRecording.setText(""+showrecording.getFileName().split(",")[2]);
//        String videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";





//       binding.videoViewTeacher.setVideoURI(Uri.parse(videoUrl));
//        binding.videoViewTeacher.setMediaController(new android.widget.MediaController(getContext()));
//        binding.videoViewTeacher.requestFocus();
//        binding.videoViewTeacher.start();


        //vlc
//        libVlc = new LibVLC(getContext());
//        MediaController mediaController = new MediaController(getContext());
//        mediaController.setAnchorView(binding.videoLayout);
////        mediaController.setMediaPlayer(libVlc)
//        mediaPlayer = new MediaPlayer(libVlc);
//      //  videoLayout = findViewById(R.id.videoLayout);
//            videoLayout=binding.videoLayout;
//        mediaPlayer.attachViews(videoLayout, null, false, false);
//
//        Media media = new Media(libVlc, Uri.parse(videoUrl));
//        media.setHWDecoderEnabled(true, false);
//        media.addOption(":network-caching=600");
//
//        mediaPlayer.setMedia(media);
//        media.release();
//        mediaPlayer.play();
//        //end code
        return binding.getRoot();
    }
    private SimpleExoPlayer player;
    private void initializePlayer() {
        // Create a SimpleExoPlayer instance
        player = new SimpleExoPlayer.Builder(getContext()).build();

        // Bind the player to the view
        PlayerView playerView = binding.videoPlayerView;
        playerView.setPlayer(player);

        // Create a MediaSource representing the media to be played
        MediaSource mediaSource = new ProgressiveMediaSource.Factory(
                new DefaultDataSourceFactory(getContext(), "exoplayer-example")
        ).createMediaSource(Uri.parse(videoUrl));

        // Prepare the player with the source
        player.setMediaSource(mediaSource);
        player.prepare();
    }
    @Override
    public void onStart() {
        super.onStart();
        initializePlayer();
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