import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class GetTests extends BaseTest {

    @Test
    void successfulGetSingleUserTest() {
        given()

                .when()
                .log().uri()
                .get("/users/2")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", is(2))
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"));
    }

    @Test
    void singleUser404Test() {
        given()

                .when()
                .log().uri()
                .get("/users/23")

                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }

    @Test
    void checkSingleResourceTest() {

        given()
                .log().uri()
                .get("/unknown/2")

                .then()
                .log().body()
                .statusCode(200)
                .body("data.year", is(2001))
                .body("data.pantone_value", is("17-2031"));
    }
}
