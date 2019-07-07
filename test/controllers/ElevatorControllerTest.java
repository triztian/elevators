package controllers;

import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.mvc.Http.Status.BAD_REQUEST;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;
import static play.test.Helpers.*;

public class ElevatorControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void testGetElevatorSuccess() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/elevator/0");

		Result result = route(app, request);
		assertEquals(OK, result.status());
		System.out.println(contentAsString(result));

	}
	
    @Test
    public void testGetElevatorInvalidID() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/elevator/99");

		Result result = route(app, request);
		assertEquals(BAD_REQUEST, result.status());

		System.out.println(contentAsString(result));

	}
	
	@Test
	public void testPostCallSuccess() {

        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .uri("/floor/10");

		Result result = route(app, request);

		System.out.println(contentAsString(result));

		assertEquals(OK, result.status());


	}

	@Test
	public void testPostCallInvalidFloor() {

        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .uri("/floor/99");

		Result result = route(app, request);
		assertEquals(BAD_REQUEST, result.status());

		System.out.println(contentAsString(result));

	}

	@Test
	public void testGetFloorsSuccess() {
	
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/floors");

		Result result = route(app, request);

		assertEquals(OK, result.status());

		System.out.println(contentAsString(result));

	}

}
