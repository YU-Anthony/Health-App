import DataWrapper.BasicRow;
import DataWrapper.SittingTime;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.QueryApi;

import java.util.List;

public class AppQuerior {

    // The inlfuxdn is maintained locally at present.

    private static char[] token = "VSOOTKwkRgVrMJFpxSCHGtW8j52yErlaop-yTr9MW_sJgQ2a91v7_bLSysGye-UMund8mDifm--IIOyDTGAMGA==".toCharArray();
    private static String org = "innovation Lab";
    private static String bucket = "ServerDB";
    private static InfluxDBClient db = InfluxDBClientFactory.create("http://localhost:8086", token, org, bucket);;
//    private static InfluxDBClient db = InfluxDBClientFactory.create();

    // Select the last 2 (each table) of basic info from now to yestoday. Wrapped in BasicRow Class.
    public static void basicInfo() {
        String flux = "  from(bucket:\"ServerDB\")  \n" +
                "  |> range(start: -1d)\n" +
                "  |> filter(fn: (r) => r[\"_measurement\"] == \"cusion\")\n" +
                "  |> tail(n:2, offset: 0)\n" +
                "  |> pivot(rowKey: [\"_time\"], columnKey: [\"_field\"], valueColumn: \"_value\")\n" +
                "  |> yield()";

        QueryApi queryApi = db.getQueryApi();

        List<BasicRow> basicRows = queryApi.query(flux, BasicRow.class);
        for (BasicRow basicRow : basicRows) {
            System.out.println(basicRow.time + " -->> " + basicRow.health_score + " " + basicRow.concentrated_pres + " " + basicRow.unbalanced_pres);
        }
    }

    // Select the last record in which the sit_status is true, calculate the sitting time from now, return in String, check SittingTime Class.
    public static void sittingTime() {
        String flux = "from(bucket:\"ServerDB\")  \n" +
                "  |> range(start: -1d)\n" +
                "  |> filter(fn: (r) => r[\"_measurement\"] == \"cusion\")\n" +
                "  |> filter(fn: (r) => r[\"_field\"] == \"health_score\")\n" +
                "  |> filter(fn: (r) => r[\"sit_status\"] == \"true\")\n" +
                "  |> first()\n" +
                "  |> map(fn: (r) => ({ r with _sitTime: string(v: duration(v: uint(v:now()) - uint(v:r._time))) }))\n" +
                "  |> yield()";
        QueryApi queryApi = db.getQueryApi();

        List<SittingTime> sittingTimes= queryApi.query(flux, SittingTime.class);
        for (SittingTime sittingTime : sittingTimes) {
            System.out.println(sittingTime.time + " -->> " + sittingTime.sitTime);
        }
    }

    public static void main(String[] args) {
        basicInfo();
        sittingTime();
        db.close();
    }

}
