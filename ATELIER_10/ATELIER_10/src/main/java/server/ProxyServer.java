package server;

import domaine.Query;
import domaine.QueryFactory;
import domaine.QueryFactoryImpl;
import domaine.QueryMethod;

import java.util.Scanner;

public class ProxyServer {

    private final QueryFactory queryFactory;

    public ProxyServer(QueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public void startServer(){
        try(Scanner scanner = new Scanner(System.in)){
            while (true){
                System.out.println("Entrez une URL a visiter");
                String url = scanner.nextLine().trim();

                if(url.isEmpty()){
                    System.out.println("Url vide , réessaie");
                    continue;
                }

                Query query = QueryFactory.getQuery();
                query.setHttpMethod(QueryMethod.GET);
                query.setUrl(url);
                QueryHandler handler = new QueryHandler(query);
                handler.sendQueryAndPrintResponse();
            }
        }

    }

}
