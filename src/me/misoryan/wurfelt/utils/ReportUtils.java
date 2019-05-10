package me.misoryan.wurfelt.utils;

import me.misoryan.wurfelt.Wurfelt;
import me.misoryan.wurfelt.commands.ReportCommand.ReportCommand;

import java.util.Iterator;
import java.util.Set;

/*
 REPORT-ID, NAME|REASON|REPORTER|STATE|OPERATOR
 */
public class ReportUtils {

    public static void setReportState(Long n, String state) {
        String data = ReportCommand.ReportData.get(n);
        String[] info = data.split("|");
        info[3] = state;
        ReportCommand.ReportData.put(n, info[0] + "|" + info[1] + "|" + info[2] + "|" + info[3] + "|" + info[4]);
    }

    public static String checkPlayerReportExists(String player) {
        Iterator i = ReportCommand.ReportData.entrySet().iterator();
        while (i.hasNext()) {
            Object obj = i.next();
            String key = obj.toString();
            String data = ReportCommand.ReportData.get(key);
            if (data.split("|")[0].toLowerCase().equals(player.toLowerCase())) {
                if (data.split("|")[0].equals(player)) {
                    return key;
                }
                else {
                    return key + "|" + data.split("|")[0];
                }
            }
        }
        return null;
    }

    public static void createNewReport(String player, String reason,String reporter) {
        Iterator i = ReportCommand.ReportData.entrySet().iterator();
        Long reportid = 0L;
        if (ReportCommand.ReportData.size() == 0) {
            reportid = 0L;
            ReportCommand.ReportData.put(reportid,player + "|" + reason + "|" + reporter + "|null|null");
            return;
        }
        while (i.hasNext()) {
            Object obj = i.next();
            String key = obj.toString();
            reportid = Long.valueOf(key);
        }
        ReportCommand.ReportData.put(reportid,player + "|" + reason + "|" + reporter + "|null|null");
        return;
    }
}

