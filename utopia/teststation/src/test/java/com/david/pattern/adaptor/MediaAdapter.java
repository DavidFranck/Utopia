package com.david.pattern.adaptor;

public class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedMediaPlayer;

    public MediaAdapter(AudioType audioType) {
        if (audioType == AudioType.mp4) {
            setAdvancedMediaPlayer(new Mp4Player());
        } else if (audioType == AudioType.vlc) {
            setAdvancedMediaPlayer(new VlcPlayer());
        }
    }

    @Override
    public void play(AudioType audioType, String fileName) {
        if (audioType == AudioType.mp4) {
            advancedMediaPlayer.playMp4(fileName);
        } else if (audioType == AudioType.vlc) {
            advancedMediaPlayer.playVlc(fileName);
        }
    }

    public AdvancedMediaPlayer getAdvancedMediaPlayer() {
        return advancedMediaPlayer;
    }

    public void setAdvancedMediaPlayer(AdvancedMediaPlayer advancedMediaPlayer) {
        this.advancedMediaPlayer = advancedMediaPlayer;
    }
}
