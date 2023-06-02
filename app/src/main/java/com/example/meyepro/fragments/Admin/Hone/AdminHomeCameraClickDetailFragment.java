package com.example.meyepro.fragments.Admin.Hone;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.example.meyepro.R;
import com.example.meyepro.databinding.FragmentAdminHomeCameraClickDetailBinding;
import com.example.meyepro.models.CAMERA;
import com.example.meyepro.models.Venue;

import org.videolan.libvlc.LibVLC;

import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.util.VLCVideoLayout;

import java.util.ArrayList;


public class AdminHomeCameraClickDetailFragment extends Fragment {
FragmentAdminHomeCameraClickDetailBinding Binding;
    private static final String url = "http://192.168.43.35:8080/video";

    private LibVLC libVlc;
    private MediaPlayer mediaPlayer;
    private VLCVideoLayout videoLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Binding= FragmentAdminHomeCameraClickDetailBinding.inflate(getLayoutInflater());
        //start code
        videoLayout=Binding.videoLayoutLive;
        LibVLC libVLC = new LibVLC(getContext());
        mediaPlayer = new MediaPlayer(libVLC);
        Media media = new Media(libVLC, Uri.parse(url));
        mediaPlayer.setMedia(media);
        mediaPlayer.play();
        mediaPlayer.attachViews(videoLayout, null, true, false);
        onStart();

//        Binding.textViewTeacherRecordingsDetailsCourseName.setText(data.get(0).getPortNumber()+"");
        //end
        return Binding.getRoot();

    }

    @Override
    public void onStart()
    {
        super.onStart();



    }

    @Override
    public void onStop()
    {
        super.onStop();

        mediaPlayer.stop();
        mediaPlayer.detachViews();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        mediaPlayer.release();
        if(libVlc!=null){
            libVlc.release();
        }

    }
}