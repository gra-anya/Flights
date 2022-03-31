import java.time.ZoneId;

public enum Airport {
    VVO(ZoneId.of("UTC+10")),
    TLV(ZoneId.of("Asia/Jerusalem"));

    private ZoneId zoneId;

    private Airport(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    public ZoneId getZoneId() {
        return zoneId;
    }
}
