package org.softwire.training.resources;

import io.dropwizard.auth.Auth;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softwire.training.db.WallDAO;
import org.softwire.training.models.UserPrincipal;
import org.softwire.training.models.SocialEvent;
import org.softwire.training.views.WallView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/wall")
public class WallResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(WallResource.class);

    private final WallDAO wallDAO;

    public WallResource(WallDAO wallDAO) {
        this.wallDAO = wallDAO;
    }

    @GET
    @Path("{subjectName}")
    @Produces(MediaType.TEXT_HTML)
    public WallView get(
            @Auth UserPrincipal userPrincipal,
            @PathParam("subjectName")  @NotEmpty String subject) {
        LOGGER.info("Get wall. User: {} Subject: {}", userPrincipal, subject);

        List<SocialEvent> socialEvents = wallDAO.readWall(subject);
        return new WallView(socialEvents, subject, userPrincipal.getUser());
    }

    @POST
    @Path("{subjectName}/write")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response post(
            @Auth UserPrincipal userPrincipal,
            @PathParam("subjectName") @NotEmpty String subject,
            @FormParam("message") @NotEmpty String message) {

        LOGGER.info("Post to Wall. User: {} Subject: {} Message: {}",
                userPrincipal, subject, message);

        SocialEvent socialEvent = new SocialEvent(userPrincipal.getUser(), message);
        wallDAO.writeOnWall(subject, socialEvent);
        return Response.seeOther(URI.create("/wall/" + subject)).build();
    }
}
