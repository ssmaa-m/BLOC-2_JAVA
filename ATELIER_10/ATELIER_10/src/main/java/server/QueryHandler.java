package server;

import domaine.Query;
import domaine.QueryImpl;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class QueryHandler {

    private Query query;

    public QueryHandler(Query query) {
        this.query = query;
    }

    public CompletableFuture<Void> sendQueryAndPrintResponse() {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                       .uri(URI.create(query.getUrl()))
                       .GET()
                       .build();

        return client.sendAsync(request , HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    System.out.println("Status :" + response.statusCode());
                    System.out.println("Body : " + response.body());
                });
    }
 }
