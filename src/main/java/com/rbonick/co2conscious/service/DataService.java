package com.rbonick.co2conscious.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;

/**
 * Service to retrieve CO2 data from scientific web services.
 *
 * Created by rbonick on 12/7/15.
 */
@Service
public class DataService {

    /**
     * Returns all data for a particular website
     * @return String of all data collected in the form "Month/Year: Level"
     */
    public String getData() {
        StringBuilder content = new StringBuilder();

        try {
            // Create a URL for the desired page
            URL url = new URL("ftp://aftp.cmdl.noaa.gov/products/trends/co2/co2_mm_mlo.txt");

            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = in.readLine()) != null) {
                if(!line.substring(0, 1).equals("#")){
                    parseLine(line, content);
                }
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Error parsing data\n" + Arrays.toString(e.getStackTrace()));
        }

        return content.toString();
    }

    private void parseLine(String line, StringBuilder content) {
        // Append Year, Month, and Level
        String[] lineContents = line.split("\\s+");
        for(String str : lineContents){
            System.out.print(str + ",");
        }
        System.out.print("\n");
        content.append(lineContents[1]).append("/");
        content.append(lineContents[0]).append(": ");
        content.append(lineContents[5]);

        content.append("\n");
    }
}
