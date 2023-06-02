package com.example.meyepro.adapters.LiveCameraViewAdapter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.meyepro.R;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;

public class LiveCameraPlayer  implements MediaPlayer.EventListener, SurfaceHolder.Callback {
    private LibVLC libVLC;
    private SurfaceView surfaceView;
    private String mediaUrl;
    private MediaPlayer mediaPlayer;
  //  TextView textView ;
    public LiveCameraPlayer(LibVLC libVLC, SurfaceView surfaceView, String mediaUrl) {
        this.libVLC = libVLC;
        this.surfaceView = surfaceView;
        this.mediaUrl = mediaUrl;
        this.mediaPlayer = new MediaPlayer(libVLC);
      //  this.textView=textView;
    }

    public void start() {
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);

        Media media = new Media(libVLC, Uri.parse(mediaUrl));
        mediaPlayer.setMedia(media);
        mediaPlayer.setEventListener(this);
        mediaPlayer.setAspectRatio(null);
        mediaPlayer.setScale(0);

        media.release();
    }

    public void stop() {
        mediaPlayer.stop();
        mediaPlayer.getVLCVout().detachViews();
        mediaPlayer.release();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //SurfaceView surfaceView = findViewById(R.id.camera_surface_view);


//        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) textView.getLayoutParams();
//        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT); // Center the text view in the parent layout
//        textView.setLayoutParams(layoutParams);

        mediaPlayer.getVLCVout().setVideoSurface(holder.getSurface(), holder);
        mediaPlayer.getVLCVout().attachViews();
        mediaPlayer.play();
//        Canvas canvas = holder.lockCanvas();
//        Paint paint = new Paint();
//        paint.setColor(Color.RED);
//        paint.setTextSize(48f);
//        canvas.drawText("Lap 5", 100f, 100f, paint);
//        holder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Do nothing
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mediaPlayer.getVLCVout().detachViews();
    }

    @Override
    public void onEvent(MediaPlayer.Event event) {
        switch (event.type) {
            case MediaPlayer.Event.EndReached:
            case MediaPlayer.Event.EncounteredError:
                mediaPlayer.stop();
                mediaPlayer.getVLCVout().detachViews();
                break;
            default:
                break;
        }
    }
}
