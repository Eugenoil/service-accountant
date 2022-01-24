package org.puprleteam.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.*;
import org.puprleteam.interfaces.TrackingService;
import org.puprleteam.pojos.Tracking;
import org.puprleteam.service.TrackingServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;

/**
 * @author Dunin Eugene
 */
@Slf4j
@WebServlet(urlPatterns = "/trackings/*")
public class RestTaskServlet extends HttpServlet {
    private static final String TRACKING_CREATED_SUCCESS_JSON = "{ \"tracking_id\" : \"%s\" }";
    private TrackingService service;
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void init() throws ServletException {
        final Object trackingService = getServletContext().getAttribute("trackingService");

        this.service = (TrackingServiceImpl) trackingService;

    }

    /**
     *
     * @param req (/trackings/{id})
     * @param resp
     * @throws IOException
     *
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");
        Long id = Long.parseLong(idParam, 15);

        Tracking tracking = service.read(id);

        resp.setStatus(200);
        resp.setHeader("Content-Type", "application/json");
        resp.setContentType("application/json; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.getOutputStream().println(objectMapper.writeValueAsString(tracking));
    }

    /**
     *
     * @param req (/trackings/[?task_name=...&user_id=...&status=...])
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();


        String taskName = req.getParameter("task_name");
        String userId = req.getParameter("user_id");
        Long userIdParam = Long.parseLong(userId, 15);
        String status = req.getParameter("status");

        Tracking tracking = new Tracking();
        tracking.setTask_name(taskName);
        tracking.setUser_id(userIdParam);
        tracking.setStatus(Integer.valueOf(status));
        tracking.setStart_time(startTime);
        tracking.setEnd_time(endTime);

        TrackingService trackingService = new TrackingServiceImpl();
        Serializable id = trackingService.create(tracking);

        resp.setStatus(201);
        resp.setHeader("Content-Type", "application/json");
        resp.setContentType("application/json; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(String.format(TRACKING_CREATED_SUCCESS_JSON, id.toString()));
        resp.getOutputStream().println(objectMapper.writeValueAsString(tracking));
    }


    /**
     *
     * @param req (trackings/{id})
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");
        Long id = Long.parseLong(idParam, 15);

        boolean isDeleted = service.delete(id);

        if (isDeleted) {
            resp.setStatus(200);
        } else {
            resp.setStatus(204);
        }
        resp.setHeader("Content-Type", "application/json");
        resp.setContentType("application/json; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
    }

    /**
     *
     * @param req (/trackings/[?id=...&status=...])
     * @param resp
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");
        String statusParam = req.getParameter("status");
        Integer status = Integer.parseInt(statusParam);
        Long id = Long.parseLong(idParam);
        if (service.read(id) == null) {
            resp.setStatus(422);
            resp.getOutputStream().println("You cannot update Todo with id " + id + " because it doesn't exists!");
        }
        Tracking tracking = service.read(id);
        Calendar startTime = tracking.getStart_time();
        Calendar endTime = Calendar.getInstance();
        long totalSeconds = endTime.getTimeInMillis() - startTime.getTimeInMillis();
        Duration duration = Duration.millis(totalSeconds);
        Period period = duration.toPeriod();
        long HH = period.getHours();
        long MM = period.getMinutes();
        long SS = period.getSeconds();

        String timeInHHMMSS = String.format("%02d:%02d:%02d", HH, MM, SS);

        tracking.setStatus(status);
        tracking.setDuration(timeInHHMMSS);
        tracking.setEnd_time(endTime);


        service.update(tracking);
        resp.setStatus(200);
        resp.setHeader("Content-Type", "application/json");
        resp.setContentType("application/json; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.getOutputStream().println(objectMapper.writeValueAsString(tracking));
    }


}
