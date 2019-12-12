package com.example.johan.person.response

import com.google.gson.annotations.SerializedName

typealias  MapPerson = Map<String?, Person>
//data class PhoneBookListResponse = Array<Product>

class RemoteDataResponse {
  @SerializedName("results")  val results: Array<Person>? = null
  @SerializedName("info")     val metaData: MetaDataRemoteData? = MetaDataRemoteData()
}

class MetaDataRemoteData {
  @SerializedName("results")  val results: Int? = 0
  @SerializedName("seed")     val seed: String? = null
  @SerializedName("version")  val version: String? = null
}

class Person {
  @SerializedName("name")     val name: PersonName? = PersonName()
  @SerializedName("picture")  val picture: PersonPicture? = PersonPicture()
  @SerializedName("email")    val email: String? = ""
  @SerializedName("login")    val login: PersonLogin? = PersonLogin()
  @SerializedName("location") val location: Location? = Location()
  @SerializedName("cell")     val cell: String? = ""
  @SerializedName("phone")    val phone: String? = ""
  @SerializedName("nat")      val nat: String? = ""
  @SerializedName("dob")      val dob: DOB? = DOB()  //data of born
  @SerializedName("gender")   val gender: String? = ""
  @SerializedName("id")       val id: IdentityDocument? = IdentityDocument()
}

class PersonName {
    @SerializedName("title") var title: String? = ""
    @SerializedName("first") val first: String? = ""
    @SerializedName("last")  val last: String? = ""
}

class DOB {
    @SerializedName("date") var date: String? = ""
    @SerializedName("age") val age: Int? = 0
}

 class IdentityDocument {
     @SerializedName("name") var name: String? = ""
     @SerializedName("value") val value: String? = ""
 }
class Location {
    @SerializedName("street")   var street: Street? = Street()
    @SerializedName("city")     val city: String? = ""
    @SerializedName("state")    val state: String? = ""
    @SerializedName("postcode") val postcode: String? = ""
}

class Street {
  @SerializedName("number") var number: String? = ""
  @SerializedName("name")   var name: String? = ""

}

class PersonPicture {
    @SerializedName("large")     var large: String? = ""
    @SerializedName("medium")    val medium: String? = ""
    @SerializedName("thumbnail") val thumbnail: String? = ""
}

class PersonLogin {
    @SerializedName("username") var username: String? = ""
    @SerializedName("uuid")     val uuid: String? = ""
}
