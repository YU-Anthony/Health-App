package DataWrapper;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

import java.time.Instant;

@Measurement(name = "cusion")
public class BasicRow {
    @Column(tag = true)
    String sit_status;

    @Column(tag = true)
    String sedentary;

    @Column(timestamp = true)
    public Instant time;

    @Column
    public long health_score;

    @Column
    public boolean unbalanced_pres;

    @Column
    public boolean concentrated_pres;


}
