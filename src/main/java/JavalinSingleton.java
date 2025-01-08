

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;


/**
 * Background: A json string representing a song will be sent in this POST request with the following fields: 
 *      songName, artistName
 */
public class JavalinSingleton {

    public static Javalin getInstance(){
        // implement Javalin configuration beginning by calling .create() method
        Javalin app = Javalin.create();

        // create an instance of ObjectMapper class obj -- later to input Jackson dependency (assist Java in reading incoming request in JSON String format)
        ObjectMapper om = new ObjectMapper();
        
        /**
         * problem1: retrieve the song object from the request body...
         *      1. return the song object as JSON in the response body.
         * 
         * Note: Please refer to the "RequestBody.MD" file for more assistance.
         */
        app.post("/echo", ctx -> {
            
            //implement logic here
            
            // retrieve JSON String from request body using Context obj's .body() method
            String jsonStr = ctx.body();

            // call .result() response method from Context obj to set result stream to specified String in JSON format
            ctx.result(jsonStr);

        });

        /**
         * problem2: retrieve the song object from the request body...
         *      1. update the artist in the song object to "Beatles"
         *      2. return the updated song object as JSON in the response body
         * 
         * Note: Please refer to the "RequestBody.MD" file for more assistance.
         */
        app.post("/changeartisttobeatles", ctx -> {

            //implement logic here

            // retrieve JSON String from request body using Context obj's .body() method
            String jsonStr = ctx.body();

            // call .result() response method from Context obj to set result stream to specified String in JSON format
            // ctx.result(jsonStr);

            /* Recall: Java is an OOP language -- deals w/ objects (and classes *blueprints of obj*) quite often ...
             * ... use 'jackson' dependency -- .readValue() --- to convert JSON String into Java objs (readable for Java program) */
            
             // initialize Java objs from JSON String to a newly created 'Song' obj
            Song song = om.readValue(jsonStr, Song.class);  
            // 1st param = JSON String to convert, 2nd param = class of datatype that we are trying to convert JSON String into

            // invoke .setArtistName() method from Song class
            song.setArtistName("Beatles");

            // invoke .json(obj) response method of Javalin's Context obj -- 
            // calls result(JSONString) & sets content type to JSON
            // aka return updated 'song' obj as JSON String format
            ctx.json(song);
               
        });


        return app;
    }
    
}
