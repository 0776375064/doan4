package SuLy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asus
 */
public class SuLyDate {
    public static List days(String dateStart, String dateEnd) {
        LocalDate start = LocalDate.parse(dateStart);
        LocalDate end = LocalDate.parse(dateEnd);
        List<String> totalDates = new ArrayList<>();
        while (!start.isAfter(end)) {
            totalDates.add(start.toString().substring(8, 10).replaceAll("\\s", ""));
            start = start.plusDays(1);
        }
        return totalDates;
    }
     public static List dates(String dateStart, String dateEnd) {
        LocalDate start = LocalDate.parse(dateStart);
        LocalDate end = LocalDate.parse(dateEnd);
        List<String> totalDates = new ArrayList<>();
        while (!start.isAfter(end)) {
            totalDates.add(start.toString().replaceAll("\\s", ""));
            start = start.plusDays(1);
        }
        return totalDates;
    }
}
