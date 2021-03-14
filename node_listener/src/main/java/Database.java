import java.time.Instant;

import DataWrapper.fromBase;
import com.influxdb.client.*;
import com.influxdb.client.write.Point;
import com.influxdb.client.domain.WritePrecision;

public class Database {
    private static char[] token = "VSOOTKwkRgVrMJFpxSCHGtW8j52yErlaop-yTr9MW_sJgQ2a91v7_bLSysGye-UMund8mDifm--IIOyDTGAMGA==".toCharArray();
    private static String org = "innovation Lab";
    private static String bucket = "sample";
    private static InfluxDBClient db = InfluxDBClientFactory.create("http://localhost:8086", token, org, bucket);;

    public static void write(fromBase bs){
        try (WriteApi writeApi = db.getWriteApi()) {

//            Point point = Point.measurement("bs")
//                    .addField("health_score", bs.health_score)
//                    .addField("Concentrated_pres", bs.concentrated_pressure)
//                    .addField("unbalanced_pres", bs.unbalanced_pressure)
//                    .addTag("sit_status", String.valueOf(bs.sit_status))
//                    .addTag("sedentary", String.valueOf(bs.sedentary))
//                    .time(Instant.now().toEpochMilli(), WritePrecision.MS);

//            writeApi.writePoint(point);

        }
    }

}
