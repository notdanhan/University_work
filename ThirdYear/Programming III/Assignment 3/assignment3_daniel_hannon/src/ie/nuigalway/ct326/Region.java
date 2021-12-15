package ie.nuigalway.ct326;

/**
 * Region enum
 * @Author Daniel Hannon(19484286)
 */
public enum Region {
    STATE ("State"),
    BORDER ("Border"),
    MIDLAND ("Midland"),
    WEST ("West"),
    DUBLIN ("Dublin"),
    MIDEAST ("Mid-East"),
    MIDWEST ("Mid-West"),
    SOUTHEAST ("South-East"),
    SOUTHWEST ("South-West");
    private String regionName;

    /**
     *
     * @param regionName String of the region name
     */
    Region(String regionName) {
        this.regionName = regionName;
    }

    /**
     *
     * @return String of region name
     */
    public String getRegionName() {
        return this.regionName;
    }

}
