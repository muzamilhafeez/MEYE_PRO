package com.example.meyepro.fragments.Admin.view.Teacher.Teacher.video;

import android.widget.MediaController;


import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;

public class VLCMediaPlayerControl implements MediaController.MediaPlayerControl {

    private MediaPlayer mediaPlayer;

    public VLCMediaPlayerControl(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    @Override
    public void start() {
        mediaPlayer.play();
    }

    @Override
    public void pause() {
        mediaPlayer.pause();
    }

    @Override
    public int getDuration() {
        return (int) mediaPlayer.getLength();
    }

    @Override
    public int getCurrentPosition() {
        return (int) mediaPlayer.getTime();
    }

    @Override
    public void seekTo(int pos) {
        mediaPlayer.setTime(pos);
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
//        return mediaPlayer.getBuffering();
        return 0;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return mediaPlayer.getAudioTrack();
    }
}
