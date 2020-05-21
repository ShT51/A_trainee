package theory03.task14;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * There is a class Request(data: String) for representing some XML requests in a payment system.
 * The class has a getter method getData() and a constructor with one argument (data). See below.
 * <p>
 * Typically, the value of data looks like shown below (in a human-readable format, in processing it's a single line string).
 *
 * <request>
 * <transaction>
 * <type>payment</type>
 * <sum>100000</sum>
 * <order_id>e94dc619-6172-4ffe-aae8-63112c551570</order>
 * <desc>We'd like to buy an elephant</desc>
 * </transaction>
 * <digest>CZVMYTgc3iiOdJjFP+6dhQ==</digest>
 * </request>
 * <p>
 * You should to write the chain of responsibility pattern in functional style for stage-by-stage request creating.
 * For this:
 * 1) write a functional interface RequestHandler with a single abstract method handle and a default method;
 * the first is needed for using lambda expressions and should accept a Request and
 * returns new Request with changed data, the second is for combining several handlers into the one.
 * 2) create a commonRequestHandler combined from three existing handlers:
 * wrapInTransactionTag, createDigest and wrapInRequestTag.
 * *
 */
public class Solution {
    private static final String ORIGIN_REQUEST = "<type>payment</type><sum>100000</sum><order_id>e94dc619-6172-4ffe-aae8-63112c551570</order><desc>We'd like to buy an elephant</desc>";
    private static final String RESULT_REQUEST = "<request><transaction><type>payment</type><sum>100000</sum><order_id>e94dc619-6172-4ffe-aae8-"
            + "63112c551570</order><desc>We'd like to buy an elephant</desc></transaction>"
            + "<digest>CZVMYTgc3iiOdJjFP+6dhQ==</digest></request>";

    /**
     * It represents a handler and has two methods: one for handling requests and other for combining handlers
     *
     * Archetype:
     * @FunctionalInterface
     * public interface Function<T, R> {
     *     R apply(T t);
     * .....
     *         default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
     *         Objects.requireNonNull(after);
     *         return (T t) -> after.apply(apply(t));
     *     }
     * .....
     * }
     */
    @FunctionalInterface
    interface RequestHandler {

        // !!! write a method handle that accept request and returns new request here
        // it allows to use lambda expressions for creating handlers below
        Request handle(Request req);

        // !!! write a default method for combining this and other handler single one
        // the order of execution may be any but you need to consider it when composing handlers
        // the method may has any name
        default RequestHandler andThen(RequestHandler nextHandler) {
            return (Request req) -> nextHandler.handle(handle(req));
        }
    }

    /**
     * Accepts a request and returns new request with data wrapped in the tag <transaction>...</transaction>
     */
    final static RequestHandler wrapInTransactionTag =
            (req) -> new Request(String.format("<transaction>%s</transaction>", req.getData()));

    /**
     * Accepts a request and returns a new request with calculated digest inside the tag <digest>...</digest>
     */
    final static RequestHandler createDigest =
            (req) -> {
                String digest = "";
                try {
                    final MessageDigest md5 = MessageDigest.getInstance("MD5");
                    final byte[] digestBytes = md5.digest(req.getData().getBytes(StandardCharsets.UTF_8));
                    digest = new String(Base64.getEncoder().encode(digestBytes));
                } catch (Exception ignored) {
                }
                return new Request(req.getData() + String.format("<digest>%s</digest>", digest));
            };

    /**
     * Accepts a request and returns a new request with data wrapped in the tag <request>...</request>
     */
    final static RequestHandler wrapInRequestTag =
            (req) -> new Request(String.format("<request>%s</request>", req.getData()));

    /**
     * It should represents a chain of responsibility combined from another handlers.
     * The format: commonRequestHandler = handler1.setSuccessor(handler2.setSuccessor(...))
     * The combining method setSuccessor may has another name
     */
    final static RequestHandler commonRequestHandler =
            wrapInTransactionTag.andThen(createDigest.andThen(wrapInRequestTag));

    // !!! write the combining of existing handlers here

    /**
     * Immutable class for representing requests.
     * If you need to change the request data then create new request.
     */
    static class Request {
        private final String data;

        public Request(String requestData) {
            this.data = requestData;
        }

        public String getData() {
            return data;
        }
    }

    public static void main(String[] args) {
        new Solution().run();
    }

    public void run() {
        Request request = new Request(ORIGIN_REQUEST);
        assert(RESULT_REQUEST.equals(commonRequestHandler.handle(request).getData()));
    }
}