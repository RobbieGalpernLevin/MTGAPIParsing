import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MTGNetworking {

    private String baseUrl;

    public MTGNetworking()
    {
        baseUrl = "https://api.magicthegathering.io/v1";
    }

    public String makeAPICallForCardName(String name, String set)
    {
        String endPoint = "/cards";
        while(set.indexOf(" ") != -1)
        {
            set = set.substring(0, set.indexOf(" ")) + "%20" + set.substring(set.indexOf(" ") + 1);
        }
        while(name.indexOf(" ") != -1)
        {
            name = name.substring(0, name.indexOf(" ")) + "%20" + name.substring(name.indexOf(" ") + 1);
        }
        String url = baseUrl + endPoint + "?name=" + name + "&sets=" + set;

        try {
            URI myUri = URI.create(url); // creates a URI object from the url string
            HttpRequest request = HttpRequest.newBuilder().uri(myUri).build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void parseCards(String json, String set)
    {
        JSONObject jsonObj = new JSONObject(json);
        JSONArray cardsArr = jsonObj.getJSONArray("cards");
        boolean cardInSet = false;
        for (int i = 0; i < cardsArr.length(); i++)
        {

            JSONObject card = cardsArr.getJSONObject(i);
            String setName = card.getString("setName");
            String name = card.getString("name");
            String cardType = card.getString("type");
            String manaCost = card.getString("manaCost");
            double cmc = card.getDouble("cmc");
            if(setName.equals(set))
            {
                System.out.println("Card Name: " + name + ", Set Name = " + setName + ", Card Type = " + cardType + ", Mana Cost = " + manaCost + ", CMC = " + cmc);
                cardInSet = true;
            }
        }
        if(!cardInSet)
        {
            System.out.println("That card does not exist in that set");
        }
    }
}