package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Main
{

    public static void main(String[] args)
    {
	    // write your code here
        System.out.println("Hello");

        printBikeInformation();

    }

    public static void printBikeInformation()
    {
        String stationInformation = "https://gbfs.urbansharing.com/oslobysykkel.no/station_information.json";

        parse(stationInformation);
    }

    public static int parse(String url)
    {
        URL validatedURL;
        BufferedReader br;

        try
        {
            validatedURL = new URL(url);
        }
        catch(MalformedURLException urlException)
        {
            System.out.println("There was a problem validating the URL. Exiting program.");
            System.out.println(urlException);
            return 1;
        }

        try
        {
            URLConnection conn = validatedURL.openConnection();
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String inputLine;

            while ((inputLine = br.readLine()) != null)
            {
                System.out.println(inputLine);
            }

            br.close();
        }
        catch(IOException ioException)
        {
            System.out.println("There was a problem connecting to the URL. Exiting program.");
            System.out.println(ioException);
            return 2;
        }

        return 0;
    }
}
