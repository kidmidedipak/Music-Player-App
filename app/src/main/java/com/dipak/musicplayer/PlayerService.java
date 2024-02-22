package com.dipak.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PlayerService extends Service {

    //member
//    private static final int NOTIFICATION_ID = 123;
//    private final IBinder serviBinder=new ServiceBinder();
//    //player
//    ExoPlayer player;
//    PlayerNotificationManager notificationManager;
//
//    //class binder for clients
//    public class ServiceBinder extends Binder{
//        public  PlayerService getPlayerService(){
//            return PlayerService.this;
//        }
//    }
//
    @Override
    public IBinder onBind(Intent intent) {

       return null;
    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        //assign variable
//        player=new ExoPlayer.Builder(getApplicationContext()).build();
//
//        //audio focus attributes
//        AudioAttributes audioAttributes=new AudioAttributes.Builder()
//                .setUsage(C.USAGE_MEDIA)
//                .setContentType(C.CONTENT_TYPE_MUSIC)
//                .build();
//
//        player.setAudioAttributes(audioAttributes,true); //set audio attributes to the player
//
//        //notification manger
//        final String channelId=getResources().getString(R.string.app_name)+" Music Channel";
//
//        notificationManager=new PlayerNotificationManager.Builder(this,NOTIFICATION_ID,channelId)
//                .setNotificationListener(notificationListener)
//                .setMediaDescriptionAdapter(descriptionAdapter)
//                .setChannelImportance(IMPORTANCE_HIGH)
//                .setSmallIconResourceId(R.drawable.small_notification_icon)
//                .setChannelDescriptionResourceId(R.string.app_name)
//                .setNextActionIconResourceId(R.drawable.ic_skip_next)
//                .setPreviousActionIconResourceId(R.drawable.ic_skip_previous)
//                .setPauseActionIconResourceId(R.drawable.ic_pause)
//                .setPlayActionIconResourceId(R.drawable.ic_play)
//                .setChannelNameResourceId(R.string.app_name)
//                .build();
//
//        //set player to notification manager
//        notificationManager.setPlayer(player);
//        notificationManager.setPriority(NotificationCompat.PRIORITY_MAX);
//        notificationManager.setUseRewindAction(false);
//        notificationManager.setUseFastForwardAction(false);
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        //release the player
//        if(player.isPlaying())
//            player.stop();
//        notificationManager.setPlayer(null);
//        player.release();
//        player=null;
//        stopForeground(true);
//        stopSelf();
////
//
//        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        notificationManager.cancel(NOTIFICATION_ID);
//
//
//
//    }
//
//
//    //notification listener
//    PlayerNotificationManager.NotificationListener notificationListener=new PlayerNotificationManager.NotificationListener() {
//        @Override
//        public void onNotificationCancelled(int notificationId, boolean dismissedByUser) {
//            PlayerNotificationManager.NotificationListener.super.onNotificationCancelled(notificationId, dismissedByUser);
//            stopForeground(true);
//            if(player.isPlaying())
//            {
//                player.pause();
//            }
//        }
//
//
//        @Override
//        public void onNotificationPosted(int notificationId, Notification notification, boolean ongoing) {
//            PlayerNotificationManager.NotificationListener.super.onNotificationPosted(notificationId, notification, ongoing);
//            startForeground(notificationId,notification);
//        }
//    };
//
//
//
//    //notification description adapter
//    PlayerNotificationManager.MediaDescriptionAdapter descriptionAdapter=new PlayerNotificationManager.MediaDescriptionAdapter() {
//        @Override
//        public CharSequence getCurrentContentTitle(Player player) {
//            return Objects.requireNonNull(player.getCurrentMediaItem()).mediaMetadata.title;
//        }
//
//        @Nullable
//        @Override
//        public PendingIntent createCurrentContentIntent(Player player) {
//            //intent to open the app when clicked
//            Intent openAppIntent=new Intent(getApplicationContext(),MainActivity.class);
//
//            return PendingIntent.getActivity(getApplicationContext(),0,openAppIntent,PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
//        }
//
//        @Nullable
//        @Override
//        public CharSequence getCurrentContentText(Player player) {
//            return null;
//        }
//
//        @Nullable
//        @Override
//        public Bitmap getCurrentLargeIcon(Player player, PlayerNotificationManager.BitmapCallback callback) {
//            //try creating an image view on the fly then get its drawable
//            ImageView view=new ImageView(getApplicationContext());
//            view.setImageURI(Objects.requireNonNull(player.getCurrentMediaItem()).mediaMetadata.artworkUri);
//
//            //get view drawable
//            BitmapDrawable bitmapDrawable= (BitmapDrawable) view.getDrawable();
//            if(bitmapDrawable==null)
//            {
//                bitmapDrawable= (BitmapDrawable) ContextCompat.getDrawable(getApplicationContext(),R.drawable.app_logo);
//
//            }
//
//            assert bitmapDrawable!=null;
//
//            return bitmapDrawable.getBitmap();
//        }
//    };


}