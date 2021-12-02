package com.hashmonopolist.andromix2.networking.apis;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hashmonopolist.andromix2.data.AddToQueueResults;
import com.hashmonopolist.andromix2.data.queue.QueueResults;
import com.hashmonopolist.andromix2.data.searchresults.SearchResults;
import com.hashmonopolist.andromix2.data.tracklist.TrackList;
import com.hashmonopolist.andromix2.data.tracklist.Tracks;

import java.util.HashMap;
import java.util.Map;

public class DeemixAPI {
    private final RequestQueue requestQueue;
    String cookie;
    private String server;
    private String ARL;
    public DeemixAPI(Context context, String server, String ARL) {
        Cache cache = new DiskBasedCache(context.getCacheDir());
        Network network = new BasicNetwork(new HurlStack());
        setARL(ARL);
        setServer(server);
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();
    }

    public void loginARL(LoginARLResponse loginARLResponse) {
        if (ARL == null) return;
        String url = this.server + "/api/login-arl?arl=" + this.ARL;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, null, System.out::println) {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                cookie = response.headers.get("Set-Cookie");
                loginARLResponse.onSuccess(response);
                return super.parseNetworkResponse(response);
            }
        };
        requestQueue.add(stringRequest);
    }

    public void addToQueue(String id, String type, AddToQueueResponse addToQueueResponse) {
        String url = this.server + "/api/addToQueue?url=https://www.deezer.com/" + type + "/" + id;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            AddToQueueResults addToQueueResults = new GsonBuilder().create().fromJson(response, AddToQueueResults.class);
            if (!addToQueueResults.getResult()) {
                addToQueueResponse.onFailure(addToQueueResults);
            } else {
                addToQueueResponse.onSuccess(response);
            }

        }, System.out::println) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Cookie", cookie);
                return headers;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void getAlbumsFromArtistID(String id, AlbumsFromArtistIDResponse albumsFromArtistIDResponse) {
        String url = this.server + "/api/getTracklist?type=artist&id=" + id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            Gson gson = new GsonBuilder().create();
            albumsFromArtistIDResponse.onSuccess(gson.fromJson(response, TrackList.class));
        }, System.out::println);
        requestQueue.add(stringRequest);
    }

    public void getTracksFromAlbumID(String id, TracksFromAlbumIDResponse tracksFromAlbumIDResponse) {
        String url = this.server + "/api/getTracklist?type=album&id=" + id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            Gson gson = new GsonBuilder().create();
            tracksFromAlbumIDResponse.onSuccess(gson.fromJson(response, Tracks.class));
        }, System.out::println);
        requestQueue.add(stringRequest);
    }

    public void mainSearch(String term, SearchResponse searchResponse) {
        String url = this.server + "/api/mainSearch?term=" + term;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            Gson gson = new GsonBuilder().create();
            searchResponse.onSuccess(gson.fromJson(response, SearchResults.class));
        }, System.out::println);
        requestQueue.add(stringRequest);
    }

    public void getQueue(QueueResponse queueResponse) {
        String url = this.server + "/api/getQueue";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            Gson gson = new GsonBuilder().create();
            queueResponse.onSuccess(gson.fromJson(response, QueueResults.class));
        }, System.out::println);
        requestQueue.add(stringRequest);
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getARL() {
        return ARL;
    }

    public void setARL(String ARL) {
        this.ARL = ARL;
    }

    public interface SearchResponse {
        void onSuccess(SearchResults searchResults);
    }

    public interface LoginARLResponse {
        void onSuccess(NetworkResponse networkResponse);
    }

    public interface TracksFromAlbumIDResponse {
        void onSuccess(Tracks tracks);
    }

    public interface AddToQueueResponse {
        void onSuccess(String networkResponse);

        void onFailure(AddToQueueResults addToQueueResults);
    }

    public interface AlbumsFromArtistIDResponse {
        void onSuccess(TrackList albums);
    }

    public interface QueueResponse {
        void onSuccess(QueueResults queueResults);
    }
}
