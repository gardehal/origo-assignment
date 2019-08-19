import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

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

//        TODO
        //merge arrays on ID
        //print and exit

        return 0;
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

//        TODO Stationdata
        ArrayList<String> parsedData = new ArrayList<String>();
        for (JsonElement station : jsonParsedStations)
        {
//            System.out.println(station);

            Gson gson = new Gson();
            parsedData.add(gson.toJson(station));
        }

        System.out.println(parsedData.get(0));

        return parsedData;
    }
}
