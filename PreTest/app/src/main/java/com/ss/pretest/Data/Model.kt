package com.ss.pretest.Data


import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Reader


data class login_data(
    var issuccess: Boolean,
    var message: String,
    var payload: Payload
) {

    class Deserializer : ResponseDeserializable<login_data> {
        override fun deserialize(content: String): login_data = Gson().fromJson(content, login_data::class.java)!!
    }

    class ListDeserializer : ResponseDeserializable<List<login_data>> {
        override fun deserialize(reader: Reader): List<login_data> {
            val type = object : TypeToken<List<login_data>>() {}.type
            return Gson().fromJson(reader, type)
        }
    }
}

data class Payload(
    var token: String,
    var user_info: UserInfo
)

data class UserInfo(
    var EOID: String,
    var FID: String,
    var IOU: Any,
    var IOULIMIT: Any,
    var accstatus: Int,
    var action: Any,
    var approveby: String,
    var approvedate: Any,
    var businessaddress: String,
    var businessname: String,
    var businesstype: String,
    var city: String,
    var companyregdoc: String,
    var companyregno: String,
    var createdby: Any,
    var createddate: String,
    var email: String,
    var id: Int,
    var idx: String,
    var isdirectdebitset: Boolean,
    var landline: String,
    var login_datetime: String,
    var mobile: String,
    var need_approval: Boolean,
    var ownername: String,
    var postcode: String,
    var promotioncode: Any,
    var registrtiondate: String,
    var vatdoc: String,
    var vatno: String
)
