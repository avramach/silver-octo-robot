package cisco.reactx.blogs.api;

@SuppressWarnings("serial")
public class BlogException extends RuntimeException {

    public BlogException() {
    }

    public BlogException(String message) {
        super(message);
    }

    public BlogException(Throwable cause) {
        super(cause);
    }

    public BlogException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
