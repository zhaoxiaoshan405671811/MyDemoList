package utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import bean.AsyncBean;
import bean.FailedBean;


public class VolleyUtils {

    private static SharedPreferences sSp;
    private static SharedPreferences.Editor sEdit;

    public static  void  Send(final Context context, String url, final HashMap<String,String> params) {
        sSp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        sEdit = sSp.edit();
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
           Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                try {
                    JSONObject result = new JSONObject(response);
                    int code = result.optInt("code");
                    if (code==200){
                        Gson gson = new Gson();
                        AsyncBean bean = gson.fromJson(response, AsyncBean.class);
                        AsyncBean.DatasBean datas = bean.getDatas();
                        String key = datas.getKey();
                        sEdit.putString("key",key);
                        sEdit.commit();
                        Toast.makeText(context, "注册成功"+key, Toast.LENGTH_SHORT).show();

                    }else if(code==400) {
                        Gson gson = new Gson();
                        FailedBean failedBean = gson.fromJson(response, FailedBean.class);
                        String error = failedBean.getDatas().getError();
                        Toast.makeText(context, "注册失败"+error, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("hhhh",response.toString());
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
             Toast.makeText(context, "注册失败", Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    public static  void  logins(final Context context, String url, final HashMap<String,String> params) {
        sSp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        sEdit = sSp.edit();
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                try {
                    JSONObject result = new JSONObject(response);
                    int code = result.optInt("code");
                    if (code==200){
                        Gson gson = new Gson();
                        AsyncBean bean = gson.fromJson(response, AsyncBean.class);
                        AsyncBean.DatasBean datas = bean.getDatas();
                        String key = datas.getKey();
                        sEdit.putString("key",key);
                        sEdit.commit();
                        Toast.makeText(context, "注册成功"+key, Toast.LENGTH_SHORT).show();

                    }else if(code==400) {
                        Gson gson = new Gson();
                        FailedBean failedBean = gson.fromJson(response, FailedBean.class);
                        String error = failedBean.getDatas().getError();
                        Toast.makeText(context, "注册失败"+error, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("hhhh",response.toString());
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "注册失败", Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


    public static  String  get(Context context, String url, final HashMap<String,String> params) {
        final RequestQueue requestQueue = Volley.newRequestQueue(context);
        final String[] data = new String[1];
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
              data[0] =response;
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return params;
            }
        };
        requestQueue.add(stringRequest);
        return data.toString();
    }

}
