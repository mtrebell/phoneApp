package com.example.fix.myapplication;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by fix on 20/10/2014.
 */
public class UserGrid {
    String baseUrl;

    public UserGrid(String address,String org,String app){
        baseUrl = address+'/'+org+'/'+app;
    }

   // Account Functions:
    public String login(String user,String password){
        String query = "/tokens?grant_type=password&username="+user+"&password="+password;
        request(query,"GET");
        return access_token;
    }

    public boolean logout(String user){
        String query = "/users/"+user+"/revoketokens";
        request(query,"PUT");
        return true;
    }

    public boolean newToken(){
        return true;
    }

    public boolean chgPassword(String user,String oldPass,String newPass){
        String query = "/users/"+user+"/password";
        request(query, "PUT");
        return true;
        //-d '{"newpassword":"foo9876a","oldpassword":"bar1234b"}'
    }

    public boolean addAccount(String username,String pass){
        return true;
    }

    public boolean deleteAccount(String username,String pass)
    {
        String query = "/users/"+username;
        request(query, "DELETE");
        return true;
    }

    //Friend Functions:
    public List<String> getFriends(String user){
        List<String> friends = new ArrayList<String>();
        String query = "users/Arthur/friends";
        request(query,"GET");
        return friends;
    }

    //Verify: Can do this search?
    public List<String> findFriend(String user,String friend){
        List<String> friends = new ArrayList<String>();
        String query = "users/Arthur/friends?ql= select * where name contains \'" + friend +'\'';
        request(query,"GET");
        return friends;
    }

    public boolean addFriend(String user,String friend){
        String query = "users/" + user + "/friends/"+friend;
        request(query,"POST");
        return true;
    }

    public boolean removeFriend(String user, String friend){
        String query = "users/" + user + "/friends/"+friend;
        request(query,"DELETE");
        return true;
    }

    public List<String> searchUser(String user){
        List<String> users = new ArrayList<String>();
        String query = "/pins?ql=select * where username contains"+user;
        request(query,"GET");
        return users;
    }

    //Pin Functions:

    public List<Pin> getDislike(String user,String restraunt){
        List dislike =new ArrayList<Pin>();
        String query = "/user/"+user + "/dislikes/" + restraunt;
        request(query,"GET");
        return dislike;
    }

    public List<Pin> getPins(String user,String restraunt){
        List<Pin> pins = new ArrayList<Pin>();
        String query = "/user/"+user + "liked/" + restraunt;
        request(query,"GET");
        query = "/user/"+user + "favorite/" + restraunt;
        request(query,"GET");
        query = "/user/"+user + "wishlist/" + restraunt;
        request(query,"GET");
        query = "/user/"+user + "reccomend/" + restraunt;
        request(query,"GET");
        return pins;
    }

    public boolean addPin(String user,String restraunt,List<String> pinType){
        for(String type:pinType) {
            String query = "/user/" +user +"/"+ type + "/" + restraunt;
            request(query, "GET");
        }
        return true;
    }

    public boolean removePin(String user,String restraunt,List<String> pinType){
        for(String type:pinType) {
            String query = "/user/" +user +"/"+ type + "/" + restraunt;
            request(query, "DELETE");
        }
        return true;
    }

    public boolean pinInfo(String user,int pinID){
        String query = "/pins/"+pinID;
        request(query,"GET");
        return true;
    }

    //Search Functions:

    //NOT DONE
    public boolean filterResults(List<String> resultList,List<String> filter){

        return true;
    }

    //Called if GPS is disabled
    //Finish GEOCODING LOCATION
    public boolean restrauntSearch(String name,String location,int km){
        String query = "/restraunt?ql=select uuid,name,location,address where ";
        if(name!=null){
            query+="name contains \'"+ name + "\' ";
        }
        if(location!=null){
            if(km!=0) {
                double lat=0;
                double lon=0;
                //GEOCODE STUFF HERE
                query += "location within " + km * 1000 + " of " + lat + ',' + lon;
            }
            else
                query+="address contains \'"+ location + '\'';
        }
        query=query.substring(0,query.length());
        request(query,"GET");
        return true;
    }

    //Called if GPS is enabled
    public boolean restrauntSearch(String name,int lat,int lon,int km){
        String query = "/restraunt?ql=select uuid,name,location,address where ";
        if(name!=null){
            query+="name contains \'"+ name + "\' ";
        }
        if(km!=0) {
                query += "location within " + km * 1000 + " of " + lat + ',' + lon;
            }
            else
                query+="location within 100 of \'" + lat + ',' + lon;

        query=query.substring(0,query.length());
        request(query,"GET");
        return true;
    }

    public boolean restrauntInfo(int restrauntID){
        String query = "/restraunt/"+restrauntID;
        request(query,"GET");
        return true;
    }

    private String request(String query,String method){
        URL obj;
        try {
            obj = new URL(baseUrl + query);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod(method);
            return con.getResponseMessage();
        }catch(Exception e){
            return null;
        }
    }
}


