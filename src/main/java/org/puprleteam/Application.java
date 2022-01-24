package org.puprleteam;





import org.puprleteam.interfaces.TrackingService;
import org.puprleteam.pojos.Tracking;
import org.puprleteam.service.TrackingServiceImpl;

import java.io.IOException;




public class Application {
    public static void main(String[] args) {
        Tracking tracking = new Tracking();
        tracking.setTask_name("Java_learning");
        tracking.setUser_id(234234L);
        tracking.setStatus(1);

        TrackingService trackingService = new TrackingServiceImpl();
        trackingService.create(tracking);

        System.out.println(trackingService.read(tracking.getId()));
    }
}
