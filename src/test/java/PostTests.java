import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class PostTests extends BaseTest {

    @Test
    void successfulCheckCreateUserTest() {

        String data = "{\"name\": \"Shmadko\", \"job\": \"Praporshchick\"}";

        given()
                .body(data)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("/users")

                .then()
                .log().all()
                .statusCode(201)
                .body("name", is("Shmadko"))
                .body("job", is("Praporshchick"));
    }

    @Test
    void successfulRegistrationTest() {
        String data = "{\"email\": \"eve.holt@reqres.in\",\"password\": \"pistol\"}";

        given()
                .body(data)
                .contentType(JSON)

                .when()
                .log().uri()
                .post("/register")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(4))
                .body("token", is(notNullValue()));
    }

    @Test
    void unsuccessfulRegistrationMissingPasswordTest() {
        String data = "{\"email\": \"eve.holt@reqres.in\",\"password\": \"\"}";

        given()
                .body(data)
                .contentType(JSON)

                .when()
                .log().uri()
                .post("/register")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void unsuccessfulRegistrationMissingEmailTest() {
        String data = "{\"email\": \"\",\"password\": \"pistol\"}";

        given()
                .body(data)
                .contentType(JSON)

                .when()
                .log().uri()
                .post("/register")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }


}
