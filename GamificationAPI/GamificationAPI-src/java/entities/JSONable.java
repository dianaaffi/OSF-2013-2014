/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author joseph
 */
public interface JSONable {
    public JSONObject toJSON ();
    public void fromJSON(JSONObject obj);
}
