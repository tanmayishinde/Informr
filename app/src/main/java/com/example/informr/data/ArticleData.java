package com.example.informr.data;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.informr.controller.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ArticleData {

    ArrayList<Article> articles = new ArrayList<>();
    public void getNewsList(final ArticleListAsyncResponse callBack) {

        String url = "https://newsapi.org/v1/articles?source=techcrunch&apiKey=c0c01dbc5f3a446cad41cb991b1fdb93";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray articleArray = response.getJSONArray("articles");

                    for (int i = 0; i < articleArray.length(); i++) {
                        //create article object
                        JSONObject articleObject = articleArray.getJSONObject(i);
                        Article article = new Article();

                        article.setAuthor(articleObject.getString("author"));
                        article.setTitle(articleObject.getString("title"));
                        article.setDescription(articleObject.getString("description"));
                        article.setImageUrl(articleObject.getString("urlToImage"));
                        article.setPublishedDate(articleObject.getString("publishedAt"));
                        article.setNewsUrl(articleObject.getString("url"));

                        articles.add(article);
                    }
                    if (null != callBack) callBack.processFinish(articles);


                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }
}
