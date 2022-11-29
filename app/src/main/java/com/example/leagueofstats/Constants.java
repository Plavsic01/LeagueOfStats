package com.example.leagueofstats;

public class Constants {

    public static final String API_KEY = "RGAPI-455b8388-a653-4a2d-8c68-e82c472934a0";


    public static class Summoner{
        public static final String SUMMONER_URL = "https://eun1.api.riotgames.com/lol/summoner/v4/summoners/by-name/";
        public static final String SUMMONER_ICON_URL = "http://ddragon.leagueoflegends.com/cdn/12.22.1/img/profileicon/";
    }

    public static class Champion{
        public static final String CHAMPION_ICON_URL = "http://ddragon.leagueoflegends.com/cdn/12.22.1/img/champion/";
        public static final String CHAMPION_ITEM_URL = "http://ddragon.leagueoflegends.com/cdn/12.22.1/img/item/";
    }


    public static class Ranked{
        public static final String RANKED_URL = "https://eun1.api.riotgames.com/lol/league/v4/entries/by-summoner/";
    }

    public static class Match{
        public static final String ALL_MATCHES_URL = "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/";
        public static final String MATCH_URL = "https://europe.api.riotgames.com/lol/match/v5/matches/";
        public static final String SUMMONER_SPELL_URL = "http://ddragon.leagueoflegends.com/cdn/12.21.1/img/spell/";
    }

}
