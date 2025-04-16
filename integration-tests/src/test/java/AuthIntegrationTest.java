import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AuthIntegrationTest {
    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://localhost:4004";
    }

//    the happy path
    @Test
    public void shouldReturnOKWithValidToken(){
//        Arrange - setup the test needs eg data
//        Act - the code we right to trigger the test
//        Assert - test whether OK will come out

        String loginPayload = """
                    {
                        "email": "testuser@test.com",
                        "password": "password123"
                    }
                """;


        Response response =  given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .extract().response();

        System.out.println("The Generated Token is: " + response.jsonPath().getString("token"));
    }

    //    the unhappy path
    @Test
    public void shouldReturnUnauthorizedOnInValidLogin(){
//        Arrange - setup the test needs eg data
//        Act - the code we right to trigger the test
//        Assert - test whether OK will come out

        String loginPayload = """
                    {
                        "email": "invalid@test.com",
                        "password": "wrongpassword"
                    }
                """;


        given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(401);
    }
}
