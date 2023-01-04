package eu.telecomnancy.model;

public enum MediaType {
    IMG, VIDEO, AUDIO;

    public static MediaType fromString(String type) {
        switch (type) {
            case "IMG":
                return IMG;
            case "VIDEO":
                return VIDEO;
            case "AUDIO":
                return AUDIO;
            default:
                return null;
        }
    }

    public String toString() {
        switch (this) {
            case IMG:
                return "IMG";
            case VIDEO:
                return "VIDEO";
            case AUDIO:
                return "AUDIO";
            default:
                return null;
        }
    }
}
