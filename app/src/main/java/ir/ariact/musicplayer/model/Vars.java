package ir.ariact.musicplayer.model;

public class Vars {
    private static final String INTENT_MUSIC_URI = "music uri";
    private static final String INTENT_MUSIC_POSITION = "music position";
    private static final String INTENT_FRAGMENT_TYPE = "fragment type";
    private static final String INTENT_ART_NAME = "art name";
    private static final String ARTIST_FRAGMENT_TYPE = "artist type";
    private static final String ALBUM_FRAGMENT_TYPE = "album type";

    public static String getIntentMusicUri() {
        return INTENT_MUSIC_URI;
    }

    public static String getIntentMusicPosition() {
        return INTENT_MUSIC_POSITION;
    }

    public static String getIntentFragmentType() {
        return INTENT_FRAGMENT_TYPE;
    }

    public static String getIntentArtName() {
        return INTENT_ART_NAME;
    }

    public static String getArtistFragmentType() {
        return ARTIST_FRAGMENT_TYPE;
    }

    public static String getAlbumFragmentType() {
        return ALBUM_FRAGMENT_TYPE;
    }
}
