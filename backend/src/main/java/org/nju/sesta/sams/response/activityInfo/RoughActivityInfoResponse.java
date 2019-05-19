package org.nju.sesta.sams.response.activityInfo;
import lombok.Data;
import org.nju.sesta.sams.entity.Activity;
import java.util.Calendar;

@Data
public class RoughActivityInfoResponse {
    private long activityId;
    private String activtyName;
    private Calendar initTime;
    public  RoughActivityInfoResponse (Activity activity){
       this.setInitTime(activity.getInitDate());
       this.setActivityId(activity.getId());
       this.setActivtyName(activity.getName());

    }

}
