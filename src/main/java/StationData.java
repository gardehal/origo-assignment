public class StationData
{
    private String station_id;
    private String name;
    private String address;
    private double lat;
    private double lon;
    private int capacity;
    private int is_installed;
    private int is_renting;
    private int is_returning;
    private int last_reported;
    private int num_bikes_available;
    private int num_docks_available;

    public StationData()
    {}

    public StationData(String station_id, String name, String address, double lat, double lon, int capacity,
                       int is_installed, int is_renting, int is_returning, int last_reported,
                       int num_bikes_available, int num_docks_available)
    {
        this.station_id = station_id;
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.lon = lon;
        this.capacity = capacity;
        this.is_installed = is_installed;
        this.is_renting = is_renting;
        this.is_returning = is_returning;
        this.last_reported = last_reported;
        this.num_bikes_available = num_bikes_available;
        this.num_docks_available = num_docks_available;
    }

    @Override
    public String toString()
    {
        return "StationData{" + "station_id='" + station_id + '\'' + ", name='" + name + '\'' + ", address='" + address + '\'' + ", lat=" + lat + ", lon=" + lon + ", capacity=" + capacity + ", is_installed=" + is_installed + ", is_renting=" + is_renting + ", is_returning=" + is_returning + ", last_reported=" + last_reported + ", num_bikes_available=" + num_bikes_available + ", num_docks_available=" + num_docks_available + '}';
    }

    // Getters and setters

    public String getStation_id()
    {
        return station_id;
    }

    public void setStation_id(String station_id)
    {
        this.station_id = station_id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public double getLat()
    {
        return lat;
    }

    public void setLat(double lat)
    {
        this.lat = lat;
    }

    public double getLon()
    {
        return lon;
    }

    public void setLon(double lon)
    {
        this.lon = lon;
    }

    public int getCapacity()
    {
        return capacity;
    }

    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
    }

    public int getIs_installed()
    {
        return is_installed;
    }

    public void setIs_installed(int is_installed)
    {
        this.is_installed = is_installed;
    }

    public int getIs_renting()
    {
        return is_renting;
    }

    public void setIs_renting(int is_renting)
    {
        this.is_renting = is_renting;
    }

    public int getIs_returning()
    {
        return is_returning;
    }

    public void setIs_returning(int is_returning)
    {
        this.is_returning = is_returning;
    }

    public int getLast_reported()
    {
        return last_reported;
    }

    public void setLast_reported(int last_reported)
    {
        this.last_reported = last_reported;
    }

    public int getNum_bikes_available()
    {
        return num_bikes_available;
    }

    public void setNum_bikes_available(int num_bikes_available)
    {
        this.num_bikes_available = num_bikes_available;
    }

    public int getNum_docks_available()
    {
        return num_docks_available;
    }

    public void setNum_docks_available(int num_docks_available)
    {
        this.num_docks_available = num_docks_available;
    }
}
