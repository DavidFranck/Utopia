package com.david.pattern.adaptor;


import static com.david.pattern.adaptor.AudioType.*;

/**
 * 适配器模式将两个不相关的类 整合到一起 适配接口
 * 桥接模式是将两个有联系的类链接起来
 */
public class AudioPlayer implements MediaPlayer {

    @Override
    public void play(AudioType audioType, String fileName) {
        if (audioType == mp3) {
            System.out.println("Playing mp3 file. Name: " + fileName);
        } else if (audioType == AudioType.mp4 || audioType == AudioType.vlc) {
            new MediaAdapter(audioType).play(audioType, fileName);
        } else {
            System.out.println("Invalid media. " +
                    audioType + " format not supported");
        }
    }
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play(mp3, "beyond the horizon.mp3");
        audioPlayer.play(mp4, "alone.mp4");
        audioPlayer.play(vlc, "far far away.vlc");
        audioPlayer.play(avi, "mind me.avi");
    }
}
