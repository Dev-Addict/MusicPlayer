package ir.ariact.musicplayer.model;

public class Vars {
    private static final String INTENT_MUSIC_URI = "music uri";
    private static final String INTENT_MUSIC_POSITION = "music position";
    private static final String INTENT_FRAGMENT_TYPE = "fragment type";
    private static final String INTENT_ART_NAME = "art name";
    private static final String ARTIST_FRAGMENT_TYPE = "artist type";
    private static final String ALBUM_FRAGMENT_TYPE = "album type";
    private static final String MUSIC_SERVICE_CHANNEL = "music service channel";
    private static PlayMode musicPlayerPlayMode = PlayMode.ORDERED;
    private static SongFilter songFilter = SongFilter.NONE;
    private static String filterValue = "";
    private static SongState songState = null;
    private static long songId = 0;

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

    public static String getMusicServiceChannel() {
        return MUSIC_SERVICE_CHANNEL;
    }

    public static PlayMode getMusicPlayerPlayMode() {
        return musicPlayerPlayMode;
    }

    public static void setMusicPlayerPlayMode(PlayMode musicPlayerPlayMode) {
        Vars.musicPlayerPlayMode = musicPlayerPlayMode;
    }

    public static SongFilter getSongFilter() {
        return songFilter;
    }

    public static void setSongFilter(SongFilter songFilter) {
        Vars.songFilter = songFilter;
    }

    public static String getFilterValue() {
        return filterValue;
    }

    public static void setFilterValue(String filterValue) {
        Vars.filterValue = filterValue;
    }

    public static SongState getSongState() {
        return songState;
    }

    public static void setSongState(SongState songState) {
        Vars.songState = songState;
    }

    public static long getSongId() {
        return songId;
    }

    public static void setSongId(long songId) {
        Vars.songId = songId;
    }
}
