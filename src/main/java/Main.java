import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Main
{

    public static void main(String[] args)
    {
        printBikeInformation();
    }

    public static int printBikeInformation()
    {
        //Parse static URLs
        String stationStatusUrl = "https://gbfs.urbansharing.com/oslobysykkel.no/station_status.json";
        String stationInformationUrl = "https://gbfs.urbansharing.com/oslobysykkel.no/station_information.json";

        ArrayList stationStatus = parse(stationStatusUrl);
        ArrayList stationInformation = parse(stationInformationUrl);

        // Check if we have any data to show
        if(stationStatus.size() < 1 || stationInformation.size() < 1)
        {
            System.out.println("Partial or no data available. Exiting program.");
            return 1;
        }

        ArrayList<StationData> printData = mergeArrays(stationStatus, stationInformation);

        System.out.println("Total number of stations: " + printData.size() + ", data generated POSIX: " + printData.get(0).getLast_reported());

        System.out.println("\tID |\tName |\tAdress |\tLat |\tLon |\tCapacity |\tIs Installed |\tIs Renting |\tIs Returning |\tLast Reported |\tBikes Available |\tDocs Available");

        for (StationData sd : printData)
        {
            // Using the StationDatas toString method.
//            System.out.println(sd);

            // Something more readable
            formatPrint(sd);
        }

        return 0;
    }

    public static int formatPrint(StationData sd)
    {
//        System.out.println("\tID |\tName |\tAdress |\tLat |\tLon |\tCapacity |\tIs Installed |\tIs Renting |\tIs Returning |\tLast Reported |\tBikes Available |\tDocs Available");
        System.out.println("\t" + sd.getStation_id()
                + " |\t" + sd.getName()
                + " |\t" + sd.getAddress()
                + " |\t" + sd.getLat()
                + " |\t" + sd.getLon()
                + " |\t" + sd.getCapacity()
                + " |\t" + sd.getIs_installed()
                + " |\t" + sd.getIs_renting()
                + " |\t" + sd.getIs_returning()
                + " |\t" + sd.getLast_reported()
                + " |\t" + sd.getNum_bikes_available()
                + " |\t" + sd.getNum_docks_available());

        return 0;
    }

    // Simple bubble sort to get the arrays in order of ID
    public static ArrayList<StationData> bubbleSort(ArrayList<StationData> arrayList)
    {
        int n = arrayList.size();
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (parseInt(arrayList.get(j).getStation_id()) > parseInt(arrayList.get(j+1).getStation_id()))
                {
                    // swap arr[j+1] and arr[i]
                    StationData temp = arrayList.get(j);
                    arrayList.set(j, arrayList.get(j + 1));
                    arrayList.set(j + 1, temp);
                }

         return arrayList;
    }

    // Method for merging the 2 arrays of status and information
    public static ArrayList<StationData> mergeArrays(ArrayList<StationData> stationStatus, ArrayList<StationData> stationInformation)
    {
        ArrayList<StationData> result = new ArrayList<StationData>();
        int iteratorOffset = 0;

        stationStatus = bubbleSort(stationStatus);
        stationInformation = bubbleSort(stationInformation);

        // Nested loop in case there are stations on one of the arrays and not the other. The offset is there to limit the loops needed in case a station is missing in one array
        for(int i = 0; i < stationStatus.size(); i++)
        {
            forJ: for(int j = i; j < stationStatus.size(); j++)
            {
                if(stationStatus.get(i).getStation_id().equals(stationInformation.get(j + iteratorOffset).getStation_id()))
                {
                    StationData currentStatus = stationStatus.get(i);
                    StationData currentInfo = stationInformation.get(j);

                    String station_id = currentInfo.getStation_id();
                    String name = currentInfo.getName();
                    String address = currentInfo.getAddress();
                    double lat = currentInfo.getLat();
                    double lon = currentInfo.getLon();
                    int capacity = currentInfo.getCapacity();
                    int is_installed = currentStatus.getIs_installed();
                    int is_renting = currentStatus.getIs_renting();
                    int is_returning = currentStatus.getIs_returning();
                    int last_reported = currentStatus.getLast_reported();
                    int num_bikes_available = currentStatus.getNum_bikes_available();
                    int num_docks_available = currentStatus.getNum_docks_available();

                    StationData sd = new StationData(station_id, name, address, lat, lon, capacity,
                            is_installed, is_renting, is_returning, last_reported,
                            num_bikes_available, num_docks_available);

                    result.add(sd);
//
//                    iteratorOffset += j;
                    break forJ;
                }
            }
        }

//        System.out.println("status size " + stationStatus.size());
//        System.out.println("info size " + stationInformation.size());
//        System.out.println("res size " + result.size());

        return result;
    }

    public static ArrayList parse(String url)
    {
        URL validatedURL;
        BufferedReader br;
        String data = "";

        // Validate URL
        try
        {
            validatedURL = new URL(url);
        }
        catch(MalformedURLException urlException)
        {
            System.out.println("There was a problem validating the URL. Exiting program.");
            System.out.println(urlException);
            return null;
        }

        // Read from URL
        try
        {
            URLConnection conn = validatedURL.openConnection();
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String inputLine;

            while((inputLine = br.readLine()) != null)
            {
                data += inputLine;
            }

            br.close();
        }
        catch(IOException ioException)
        {
            System.out.println("There was a problem connecting to the URL. Exiting program.");
            System.out.println(ioException);
            return null;
        }

        // Parse string into JSON and navigate to data/stations
        JsonObject jsonObject = (new JsonParser()).parse(data).getAsJsonObject();
        JsonObject jsonParsedData = jsonObject.get("data").getAsJsonObject();
        JsonArray jsonParsedStations = jsonParsedData.get("stations").getAsJsonArray();

        // Convert to StationData object in ArrayList
        ArrayList<StationData> parsedData = new ArrayList<StationData>();
        Gson gson = new Gson();

        for (JsonElement station : jsonParsedStations)
        {
//            System.out.println(station);

            parsedData.add(gson.fromJson(station, StationData.class));
        }

        return parsedData;
    }
}
