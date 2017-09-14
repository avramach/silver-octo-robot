package cisco.reactx.blogs.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import cisco.reactx.blogs.api.BlogException;
import cisco.reactx.blogs.api.Comment;
import cisco.reactx.blogs.api.Comments;
import cisco.reactx.blogs.api.DataNotFoundException;
import cisco.reactx.blogs.api.DuplicateDataException;
import cisco.reactx.blogs.api.InvalidDataException;
import cisco.reactx.blogs.service.CommentsService;

@Path("/blogs/{blogid}/comments")
public class CommentsController {

	private static Comments commentService = CommentsService.getInstance();

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(@PathParam("blogid") int blogid) {
		try {
			List<Comment> comments = commentService.readAllByBlogId(blogid);
			return Response.ok().entity(comments).build();
		} catch (DataNotFoundException dnfe) {
			return Response.status(Response.Status.NO_CONTENT).build();
		} catch (BlogException be) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@JwtTokenExpected
	public Response create(@PathParam("blogid") int blogid, Comment comment) {
		try {
			comment.setBlogId(blogid);
			commentService.create(comment);
			return Response.status(Response.Status.CREATED).build();
		} catch (InvalidDataException ide) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} catch (DuplicateDataException dde) {
			return Response.status(Response.Status.CONFLICT).build();
		} catch (BlogException be) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Path("/{commentid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@JwtTokenExpected
	public Response update(@PathParam("blogid") int blogid, @PathParam("commentid") int commentid, Comment comment) {
		try {
			commentService.update(comment);
			return Response.ok().build();
		} catch (DataNotFoundException dnfe) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} catch (BlogException be) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path("/{commentid}")
	@JwtTokenExpected
	public Response delete(@PathParam("blogid") int blogid, @PathParam("commentid") int commentid) {
		try {
			commentService.delete(commentid);
			return Response.ok().build();
		} catch (DataNotFoundException dnfe) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} catch (BlogException be) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	}
