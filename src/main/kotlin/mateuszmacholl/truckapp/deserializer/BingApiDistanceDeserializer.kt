package mateuszmacholl.truckapp.deserializer

import org.springframework.boot.configurationprocessor.json.JSONArray
import org.springframework.boot.configurationprocessor.json.JSONException
import org.springframework.boot.configurationprocessor.json.JSONObject
import org.springframework.stereotype.Component

@Component
class BingApiDistanceDeserializer {
    fun deserialize(json: String): Double {
        try {
            val resourceSets = JSONObject(json).get("resourceSets") as JSONArray
            val resourceSets0 = resourceSets.get(0) as JSONObject
            val resources = resourceSets0.get("resources") as JSONArray
            val resources0 = resources.get(0) as JSONObject
            return resources0.get("travelDistance") as Double
        } catch (ex: JSONException){
            throw JSONException("${ex.message}. Given json is incorrect")
        }
    }
}