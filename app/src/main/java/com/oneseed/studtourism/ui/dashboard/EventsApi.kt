package com.oneseed.studtourism.ui.dashboard

import org.json.JSONArray

class EventsApi {
    fun returnJson(jsonArray: JSONArray): ArrayList<EventData> {
        val list = ArrayList<EventData>()
        for (i in 1 until jsonArray.length()) {
            try {
                val jsonObject = jsonArray.getJSONObject(i).getJSONObject("details")
                val name = jsonObject.getString("name")
                val eventImage = jsonObject.getJSONArray("photos").getString(0)
                val dateFrom = jsonObject.getJSONObject("dates").getString("from")
                val price = jsonObject.getString("price")
                val dateto = jsonObject.getJSONObject("dates").getString("to")
                list.add(EventData(name, eventImage, dateFrom, dateto, price, "", false))
            } catch (e: Exception) {
                continue
            }

        }
        return list
    }


}