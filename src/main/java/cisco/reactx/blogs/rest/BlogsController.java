package cisco.reactx.blogs.rest;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cisco.reactx.blogs.api.Blog;
import cisco.reactx.blogs.api.BlogException;
import cisco.reactx.blogs.api.Blogs;
import cisco.reactx.blogs.api.DataNotFoundException;
import cisco.reactx.blogs.api.DuplicateDataException;
import cisco.reactx.blogs.api.InvalidDataException;
import cisco.reactx.blogs.service.BlogsService;

@Path("/blogs")
public class BlogsController {
	private Blogs blogsService = BlogsService.getInstance();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(@QueryParam("offset") int offset, @QueryParam("count") int count,
			@QueryParam("category") String category) {
		try {
			List<Blog> blogs = blogsService.readAllBlogs(offset, count, category);
			return Response.ok().entity(blogs).build();
		} catch (DataNotFoundException dnfe) {
			return Response.status(Response.Status.NO_CONTENT).build();
		} catch (BlogException be) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@JwtTokenExpected
	public Response create(Blog blog, @Context UriInfo uriInfo) {
		try {
			blogsService.create(blog);
			String newId = String.valueOf(blog.getBlogId());
			URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
			return Response.created(uri).entity(blog).build();

		} catch (InvalidDataException ide) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} catch (DuplicateDataException dde) {
			return Response.status(Response.Status.CONFLICT).build();
		} catch (BlogException be) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/{blogId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response read(@PathParam("blogId") long blogId) {
		Blog blog = blogsService.read(blogId);
		return Response.ok().entity(blog).build();
	}

	@POST
	@Path("/{blogid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@JwtTokenExpected
	public Response update(@PathParam("blogid") int blogid, Blog blog) {
		try {
			blogsService.update(blog);
			return Response.ok().build();
		} catch (InvalidDataException ide) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} catch (BlogException be) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path("/{blogid}")
	@JwtTokenExpected
	public Response delete(@PathParam("blogid") int blogid) {
		try {
			blogsService.delete(blogid);
			return Response.ok().build();
		} catch (DataNotFoundException dnfe) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} catch (BlogException be) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	public class ArrayBean {
	    ArrayList<String> category = new ArrayList<String>();
	    ArrayList<String> count = new ArrayList<String>();
	}
	
	public class CategoryBean {
	    private String category ;
	    private String count ;
	    public CategoryBean(String categoryName, String categoryCount) {
	    	setCategory(categoryName);
	    	setCount(categoryCount); 
	    }
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public String getCount() {
			return count;
		}
		public void setCount(String count) {
			this.count = count;
		}
	}
	
	@GET
	@Path("/categories")
	@Produces(MediaType.APPLICATION_JSON)
	public Response readCategory(@QueryParam("category") String category) {
		List<Blog> blogs = blogsService.readByCategory(category);
		Set<String> categorySet = new HashSet<String>();
		List<String> categoryList = new ArrayList<String>();
	    List<CategoryBean> beanList = new ArrayList<CategoryBean>();

		if (blogs.size() > 0) {
			if (category == null) {
				for (int i = 0; i < blogs.size(); i++) {
					categoryList.add(blogs.get(i).getCategory());
					categorySet.add(blogs.get(i).getCategory());
				}
				for (String s : categorySet) {
					CategoryBean c = new CategoryBean(s,Integer.toString(Collections.frequency(categoryList, s)));
					beanList.add(c);
				}
				GsonBuilder gsonBuilder = new GsonBuilder();
				Gson gson = gsonBuilder.create();
				return Response.ok().entity(gson.toJson(beanList)).build();
			} else {
				return Response.ok().entity(blogs).build();
			}
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@GET
	@Path("/user/{userName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response readUser(@PathParam("userName") String userName) {
		List<Blog> blogs = blogsService.readByUserId(userName);

		if (blogs.size() > 0) {
			return Response.ok().entity(blogs).build();
		}
		return Response.status(Response.Status.NO_CONTENT).build();
	}

}
