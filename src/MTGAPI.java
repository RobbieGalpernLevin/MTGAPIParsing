public class MTGAPI
{
    private MTGNetworking networker;

    public MTGAPI() {
        this.networker = new MTGNetworking();
    }

    public void getCardName(String name, String set)
    {
        String setBeforeChange = set;
        String response = networker.makeAPICallForCardName(name, set);
        networker.parseCards(response, setBeforeChange);
    }
}
