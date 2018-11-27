package mateuszmacholl.truckapp.deserializer

import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.configurationprocessor.json.JSONException

class BingApiDistanceDeserializerTest {
    private val bingApiDistanceDeserializer = BingApiDistanceDeserializer()
    @Language("JSON")
    private val jsonToDeserialize: String = """{
        "resourceSets": [
        {
          "resources": [
          {
          "travelDistance": 461.309
          }
          ]
        }
      ]
    }"""
    @Language("JSON")
    private val incorrectJsonToDeserialize: String = """{
        "x": [
        {
          "resources": [
          {
          "travelDistance": 461.309
          }
          ]
        }
      ]
    }"""

    @Test
    fun deserialize() {
        val distance = bingApiDistanceDeserializer.deserialize(jsonToDeserialize)
        assertEquals(461.309, distance)
    }

    @Test
    fun deserialize_incorrectJson_throwJSONException() {
        //then
        Assertions.assertThrows<JSONException>(JSONException::class.java) {
            //when
            bingApiDistanceDeserializer.deserialize(incorrectJsonToDeserialize)
        }
    }
}