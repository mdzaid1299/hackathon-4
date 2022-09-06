package com.jap.ticketing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TicketImpl {
    public List<BusRoute> readFile(String filename) {
        List<BusRoute> busRoutes = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String data = bufferedReader.readLine();
            while ((data = bufferedReader.readLine()) != null) {
                String[] val = data.split(",");
                String schedule_no = val[0];
                String route_no = val[1];
                int ticket_from_stop_id = Integer.parseInt(val[2]);
                int ticket_from_stop_seq_no = Integer.parseInt(val[3]);
                int ticket_till_stop_id = Integer.parseInt(val[4]);
                int ticket_till_stop_seq_no = Integer.parseInt(val[5]);
                String ticket_date = val[6];
                String ticket_time = val[7];
                int total_ticket_amount = Integer.parseInt(val[8]);
                double travelled_KM = Double.parseDouble(val[9]);

                busRoutes.add(new BusRoute(schedule_no, route_no, ticket_from_stop_id, ticket_from_stop_seq_no, ticket_till_stop_id, ticket_till_stop_seq_no, ticket_date, ticket_time, total_ticket_amount, travelled_KM));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return busRoutes;
    }


    public List<BusRoute> sortDistance(List<BusRoute> tickitDist){
        //lambda expression
        Comparator<BusRoute> busRouteComparator = (o1,o2) -> (int) (o2.getTravelled_KM() - o1.getTravelled_KM());
        tickitDist.sort(busRouteComparator);
        return tickitDist;
    }


    public int calculateTotalCollections(List<BusRoute> price_of_ticket) {
        Iterator<BusRoute> iterator = price_of_ticket.iterator();
        int total_collection = 0;
        while (iterator.hasNext()) {
            total_collection = total_collection + iterator.next().getTotal_ticket_amount();
        }
        return total_collection;
    }

    public static void main(String[] args) {
        TicketImpl ticket = new TicketImpl();
        String filename = "sample.csv";

        List<BusRoute> routeList = ticket.readFile(filename);
        for (BusRoute value : routeList) {
            System.out.println(value);
        }
        System.out.println("===================================================");
        int output = ticket.calculateTotalCollections(routeList);
        System.out.println("output = " + output);
        System.out.println("===================================================");
        ticket.sortDistance(routeList);
        for (BusRoute value : routeList) {
            System.out.println(value);
        }
    }
}
