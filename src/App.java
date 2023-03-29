import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {     

    //fazer uma conexao http e buscar  os top 250 Séries
    String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json";
    URI endereco = URI.create(url);
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
    HttpResponse<String> response = client.send(request,BodyHandlers.ofString());
    String body = response.body();
    
    //extrair só os dados que interessam (titulo, poster, classificação)
    JsonParser parser = new JsonParser();
    List<Map<String, String>> listaDeFilmes = parser.parse(body);

    //exibir e manipular os dados
    for (Map<String,String> filme : listaDeFilmes) {
        System.out.println("\u001b[34mNome da série:\u001b[m \u001b[1m \u001b[35m"+ filme.get("title")+"\u001b[m ");
        System.out.println("\u001b[34mPoster:\u001b[m \u001b[4m \u001b[36m"+filme.get("image")+"\u001b[m ");
        System.out.print("\u001b[34mNota:\u001b[m \u001b[1m \u001b[42m"+filme.get("imDbRating")+"\u001b[m ");
        double nota = Double.parseDouble(filme.get("imDbRating"));
        int numeroEstrelas = (int)nota;
        for (int n = 1; n <= numeroEstrelas; n++){
            System.out.print("⭐");
        }
        System.out.println("\n");
    }
    }   
}
