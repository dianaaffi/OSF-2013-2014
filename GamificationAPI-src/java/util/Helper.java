/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import entities.JSONable;
import java.util.List;
import org.codehaus.jettison.json.JSONArray;

/**
 *
 * @author joseph
 */
public class Helper {
    public static JSONArray listToJSONArray (List list) {
        JSONArray array = new JSONArray();
        if(list != null && !list.isEmpty()) {
            for(Object j:list) {
                array.put(((JSONable)j).toJSON());
            }
        }
        return array;
    }
}
