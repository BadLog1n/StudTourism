package com.oneseed.studtourism.ui.search

import org.json.JSONArray

class SearchApi {
    fun returnJson(jsonArray: JSONArray): ArrayList<TourismData> {
        val list = ArrayList<TourismData>()
        for (i in 1 until jsonArray.length()) {
            try {
                val jsonObject = jsonArray.getJSONObject(i).getJSONObject("details")
                val name = jsonObject.getString("name")
                val fedDistrict = jsonObject.getString("district")
                val region = jsonObject.getString("region")
                val city = jsonObject.getString("city")
                val photo = jsonObject.getString("photo")
                val cost = (50..1000).random().toString()
                val organization = "none"
                val type = "none"
                val food = "none"
                val sort = "none"
                list.add(TourismData(name, fedDistrict, region, city, photo, cost, organization, type, food, sort))
            }
            catch (e: Exception) {
                continue
            }

        }
        return list
    }


}