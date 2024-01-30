package org.json;
import org.junit.Test;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.json.*;

public class XMLTest2{
    
    
    /**
     * This method tests the efficacy of the milestone 2 method 2
     * As you can see the initial xml object is taken and a replacement is
     * named. The method is sucessfully able to replace the subobject (the entire
     * adress body including nick, name, street, and zipcode) with the new
     * object (jsonReplacement).
     * 
     * The return object from the function call is not null and the resulting
     * object matches the expected JSON Object output
     */
    @Test
    public void testReplacement() {
        try {
            String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<contact>\n" +
                    "  <address>\n" +
                    "    <street>\n" +
                    "  <nick>Crista </nick>\n" +
                    "  <name>Crista Lopes</name>\n" +
                    "  </street>\n" +
                    "    <zipcode>92614</zipcode>\n" +
                    "  </address>\n" +
                    "</contact>";

            String jsonReplacement =  
            "  <place>\n" +
            "    <street>Muholland Drive</street>\n" +
            "    <zipcode>92626</zipcode>\n" +
            "  </place>\n";

            String expected = "{\"contact\":{\"place\":{\"zipcode\":92626,\"street\":\"Muholland Drive\"}}}";
            

            Reader reader = new StringReader(xmlString);
            JSONPointer pointer = new JSONPointer("/contact/address");
            JSONObject replacement = XML.toJSONObject(jsonReplacement);
            

            JSONObject result = XML.toJSONObject(reader, pointer,replacement);
            System.out.println(result);
            assertNotNull("Resulting JSONObject should not be null", result);
            assertEquals(expected, result.toString());
        } catch (Error e) {
            fail("JSONException should definitively not be thrown: " + e.getMessage());
        }
    }
    /**
     * Further test efficacy of milestone 2 method 2 implementation by checking a different
     * XMLString for a subObject. The structure of this one is different and therefore allows
     * for a different means by which to verify the working order of this method.
     *  it checks to see if the subObject key has been placed within the xmlString and 
     * then it further checks if these values are able to be accessed.
     */
    @Test
    public void testReplacement2() {
        try {
            String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<contact>\n" +
                    "  <address>\n" +
                    "    <street>Ave of Nowhere</street>\n" +
                    "    <zipcode>92614</zipcode>\n" +
                    "  </address>\n" +
                    "  <nick>Crista </nick>\n" +
                    "  <name>Crista Lopes</name>\n" +
                    "</contact>";

            String jsonReplacement = "  <place>\n" +
                    "    <street>Muholland Drive</street>\n" +
                    "    <zipcode>92626</zipcode>\n" +
                    "  </place>\n";

            
            // reader for XML String
            Reader reader = new StringReader(xmlString);
            // pointer to locate the subObject that needs to be extracted
            JSONPointer pointer = new JSONPointer("/contact/address");
            // turning XML String of replacement into a JSON object
            JSONObject replacement = XML.toJSONObject(jsonReplacement);

            //  getting the answer
            JSONObject result = XML.toJSONObject(reader, pointer, replacement);


            JSONObject address = result.getJSONObject("contact").getJSONObject("place");
            assert result.getJSONObject("contact").has("place");
            assert address.getString("street").equals("Muholland Drive");
            assert address.getInt("zipcode") == (92626);
        } catch (Error e) {
            fail("JSONException should definitively not be thrown: " + e.getMessage());
        }
    }
    /**
     * What happens if we try to use a pointer to a subObject that doesn't exist?
     * We would expect that nothing happens and the same object is returned
     */
    @Test
    public void testReplacementNull() {
        try {
            String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<contact>\n" +
                    "  <address>\n" +
                    "    <street>Ave of Nowhere</street>\n" +
                    "    <zipcode>92614</zipcode>\n" +
                    "  </address>\n" +
                    "  <nick>Crista </nick>\n" +
                    "  <name>Crista Lopes</name>\n" +
                    "</contact>";

            String jsonReplacement = "  <place>\n" +
                    "    <street>Muholland Drive</street>\n" +
                    "    <zipcode>92626</zipcode>\n" +
                    "  </place>\n";

            // reader for XML String
            Reader reader = new StringReader(xmlString);
            // pointer that looks for subObject that doesn't exist
            JSONPointer pointer = new JSONPointer("/contact/dog");
            // turning XML String of replacement into a JSON object
            JSONObject replacement = XML.toJSONObject(jsonReplacement);
            // expected answer
            String expected = XML.toJSONObject(xmlString).toString();

            // getting the answer
            JSONObject actual = XML.toJSONObject(reader, pointer, replacement);

            assertEquals(expected, actual.toString());
        } catch (Error e) {
            fail("JSONException should definitively not be thrown: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        
    
        try {
        
            String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
            "<contact>\n"+
            "  <address>\n" +
            "    <street>Ave of Nowhere</street>\n" +
            "    <zipcode>92614</zipcode>\n" +
            "  </address>\n" +
            "  <nick>Crista </nick>\n" +
            "  <name>Crista Lopes</name>\n" +
            "</contact>";

            String jsonReplacement = "  <place>\n" +
                    "    <street>Muholland Drive</street>\n" +
                    "    <zipcode>92626</zipcode>\n" +
                    "  </place>\n";
            // String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            //         "<contact>\n" +
            //         "  <address>\n" +
            //         "    <street>\n" +
            //         "  <nick>Crista </nick>\n" +
            //         "  <name>Crista Lopes</name>\n" +
            //         "  </street>\n" +
            //         "    <zipcode>92614</zipcode>\n" +
            //         "  </address>\n" +

            //         "</contact>";

            
                
            Reader reader = new StringReader(xmlString);
            JSONPointer pointer = new JSONPointer("/contact/address");
            JSONObject replacement = XML.toJSONObject(jsonReplacement);

            JSONObject result = XML.toJSONObject(reader, pointer,replacement);

            

            
        
           
            System.out.println(result);
            String [] x = JSONObject.getNames(result);
            
            for(String z:x){
                System.out.println(z);
            }
            assertNotNull("Resulting JSONObject should not be null", result);
           // assertEquals(replacement, result.optString("place"));
        } catch (JSONException e) {
            fail("JSONException should definitively not be thrown: " + e.getMessage());
        }
    
    }
}

// if(parsed2(x,jsonObject,tagName,config,currentNestingDepth+1,path_tracker,replacement,openTagFound,LAST_PATH,NewTagName)){
// // if (openTagFound.get()) {

// // context.put(NewTagName, replacement);
// // return false;
// // }
// // if(tagName == LAST_PATH){
// // return true;
// // }
// if(tagName.equals(NewTagName)){

// }if(config.getForceList().contains(tagName)){
// // Force the value to be an array
// if(jsonObject.length()==0){context.put(tagName,new JSONArray());}else if(jsonObject.length()==1&&jsonObject.opt(config.getcDataTagName())!=null){

// context.append(tagName,jsonObject.opt(config.getcDataTagName()));}else{

// if(tagName.equals(NewTagName)){context.append(tagName,replacement);}else{context.append(tagName,jsonObject);}

// }}else{if(jsonObject.length()==0){context.accumulate(tagName,"");

// }else if(jsonObject.length()==1&&jsonObject.opt(config.getcDataTagName())!=null){System.out.println("pingu");if(tagName.equals(NewTagName)){context.accumulate(tagName,replacement.opt(config.getcDataTagName()));}else{context.accumulate(tagName,jsonObject.opt(config.getcDataTagName()));}

// // where subObjects are processed
// }else{
// // accumulates first subObject

// if(tagName==NewTagName){context.accumulate(tagName,replacement);}else{context.accumulate(tagName,jsonObject);}

// }}

// return false;}
