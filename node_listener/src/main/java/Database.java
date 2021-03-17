import DataWrapper.ToDataBase;
import com.influxdb.client.*;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;

import java.time.Instant;
import java.util.List;

public class Database {
    private static char[] token = "VSOOTKwkRgVrMJFpxSCHGtW8j52yErlaop-yTr9MW_sJgQ2a91v7_bLSysGye-UMund8mDifm--IIOyDTGAMGA==".toCharArray();
    private static String org = "innovation Lab";
    private static String bucket = "ServerDB";
    private static InfluxDBClient db = InfluxDBClientFactory.create("http://localhost:8086", token, org, bucket);;
//    private static InfluxDBClient db = InfluxDBClientFactory.create();

    public static void write(ToDataBase tdb) {
        try (WriteApi writeApi = db.getWriteApi()) {
            System.out.println(tdb.healthScore);
            Point point = Point.measurement("cusion")
                    .addField("health_score", tdb.healthScore)
                    .addField("concentrated_pres", tdb.isAPBalanced)
                    .addField("unbalanced_pres", tdb.isLRBalanced)
                    .addTag("sit_status", String.valueOf(tdb.isSitting))
                    .addTag("sedentary", String.valueOf(tdb.sedentary))
                    .time(Instant.now().getEpochSecond(), WritePrecision.S);

            writeApi.writePoint(point);
        }
    }

    public static void closeDB(){
        db.close();
    }
}
