package stepdefinitions;

import groovy.json.JsonParser;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.util.JSONPObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.parallel.Resources;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


public class Products {
    public int StatusCode;
    public RequestSpecification httpRequest;
    public Response response;
    public int ResponseCode;
    public ResponseBody body;
    public  JSONObject requestParams;
    private String s;



    @Given("I hit the url of products to get the api endpoint")
    public void i_hit_url_of_products_to_get_the_api_endpoint(){

        RestAssured.baseURI = "https://fakestoreapi.com/";
    }

    @When("I pass the url in the request")
    public void i_pass_the_url_in_the_request() {
        httpRequest = given();
        response = httpRequest.get("products");
    }
    @Then("I receive the response code as {int}")
    public void i_receive_the_response_code_as(Integer int1) {
        ResponseCode =  response.getStatusCode();
        assertEquals(ResponseCode, 200);
    }

    @Then("I Verify That the rate of the first product is {}")
    public void i_verify_that_the_rate_of_the_first_product_is (String rate){
        body = response.getBody();
        //convert response body to string
        String responseBody = body.asString();
        //JSON representation from Response body
        JsonPath jsnpath = response.jsonPath();
        String s = jsnpath.getJsonObject("rating[0].rate").toString();
        assertEquals(rate, s);

    }


    @Given("I hit the url of post product api endpoint")
    public void iHitTheUrlOfPostProductApiEndpoint() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
        httpRequest = given();
        requestParams = new JSONObject();


    }

    @And("I pass the request body of product title{}")
    public void iPassTheRequestBodyOfProductTitleProductTitle(String title) {
        requestParams.put("title", title);
        requestParams.put("price",13.5);
        requestParams.put("description","lorem ipsum set");
        requestParams.put("image","https://i.pravatar.cc");
        requestParams.put("cateogry","electronic");
        httpRequest.body(requestParams.toJSONString());
        Response response =httpRequest.post("products");
        ResponseBody body = response.getBody();
        JsonPath jsnpath = response.jsonPath();
        s = jsnpath.getJsonObject("id").toString();
        System.out.println(response.getStatusLine());
        System.out.println(body.asString());
    }
    @And("I pass the request body of price{}")
    public void iPassTheRequestBodyOfPrice(String price) {

        requestParams.put("price",price);
        httpRequest.body(requestParams.toJSONString());
        Response response =httpRequest.post("products");
        ResponseBody body = response.getBody();
        JsonPath jsnpath = response.jsonPath();
        s = jsnpath.getJsonObject("id").toString();
        System.out.println(response.getStatusLine());
        System.out.println(body.asString());
    }

    @Then("I receive the response body with id as {}")
    public void iReceiveTheResponseBodyWithIdAsId(String id) {
        assertEquals(id, s);

    }


    @Given("I hit the url of put product api endpoint")
    public void iHitTheUrlOfPutProductApiEndpoint() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
        requestParams = new JSONObject();


    }

    @When("I pass the url of products in tthe request with {}")
    public void iPassTheUrlOfProductsInTtheRequestWithProductnumber(String productnumber){
        httpRequest = given();
        requestParams.put("title","Product Testing");
        requestParams.put("price","13.5");
        requestParams.put("description","lorem ipsum set");
        requestParams.put("image","https://i.pravatar.cc");
        requestParams.put("category","electronic");
        httpRequest.body(requestParams.toJSONString());
        response = httpRequest.put("products/" + productnumber);
        ResponseBody body =response.getBody();
        JsonPath jsnpath = response.jsonPath();
        s = jsnpath.getJsonObject("id").toString();
        System.out.println(response.getStatusLine());
        System.out.println(body.asString());

    }

    @Given("I hit the url of delete product api endpoint")
    public void iHitTheUrlOfDeleteProductApiEndpoint() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
        requestParams = new JSONObject();
    }

    @When("I pass the url of delete products in the request with {}")
    public void iPassTheUrlOfDeleteProductsInTheRequestWithProductnumber(String productnumber) {
        httpRequest = given();
        requestParams.put("title","Product Testing");
        requestParams.put("price","13.5");
        requestParams.put("description","lorem ipsum set");
        requestParams.put("image","https://i.pravatar.cc");
        requestParams.put("category","electronic");
        httpRequest.body(requestParams.toJSONString());
        response = httpRequest.delete("products/" + productnumber);
        ResponseBody body =response.getBody();
        JsonPath jsnpath = response.jsonPath();
        s = jsnpath.getJsonObject("id").toString();
        System.out.println(response.getStatusLine());
        System.out.println(body.asString());
    }
}
