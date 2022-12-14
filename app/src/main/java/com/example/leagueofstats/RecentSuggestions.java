package com.example.leagueofstats;

import android.content.SearchRecentSuggestionsProvider;

public class RecentSuggestions extends SearchRecentSuggestionsProvider {

    public static final String AUTHORITY = "com.example.leagueofstats.RecentSuggestions";
    public static final int MODE = DATABASE_MODE_QUERIES;

    public RecentSuggestions() {
        setupSuggestions(AUTHORITY,MODE);
    }

}