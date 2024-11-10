import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class DeleteTests extends BaseTest {
    @Test
    void checkDeleteUserTest() {

        given()
                .log().uri()
                .delete("/users/2")
                .then()
                .log().all()
                .statusCode(204);
    }
}
