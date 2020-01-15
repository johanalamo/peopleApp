package com.example.johan.person.adapter

import android.content.Context
import com.example.johan.person.R
import com.example.johan.person.response.DetailInfo
import com.example.johan.person.response.Person
import com.example.johan.person.ucFirst

class DetailsAdapter {
    companion object {
        fun getExtraData(p: Person?, context: Context): ArrayList<DetailInfo> {
            var extra: ArrayList<DetailInfo> = ArrayList()

            extra.add(
                DetailInfo(
                    context.getString(R.string.strCompleteName).toUpperCase() + ":",
                    ucFirst(p?.name?.title) + ". " + ucFirst(p?.name?.first) + " " + ucFirst(p?.name?.last)
                )
            )
            extra.add(
                DetailInfo(
                    context.getString(R.string.strDocument).toUpperCase() + ":",
                    p?.id?.name + ": " + p?.id?.value
                )
            )
            extra.add(
                DetailInfo(
                    context.getString(R.string.strNationality).toUpperCase() + ":",
                    p?.nat
                )
            )
            extra.add(
                DetailInfo(
                    context.getString(R.string.strEmail).toUpperCase() + ":",
                    p?.email
                )
            )
            extra.add(
                DetailInfo(
                    context.getString(R.string.strGender).toUpperCase() + ":",
                    p?.gender
                )
            )
            extra.add(
                DetailInfo(
                    context.getString(R.string.strAge).toUpperCase() + ":",
                    p?.dob?.age.toString()
                )
            )
            extra.add(
                DetailInfo(
                    context.getString(R.string.strBornDate).toUpperCase() + ":",
                    p?.dob?.date
                )
            )
            extra.add(
                DetailInfo(
                    context.getString(R.string.strPhone).toUpperCase() + ":",
                    p?.phone
                )
            )
            extra.add(DetailInfo(context.getString(R.string.strCell).toUpperCase() + ":", p?.cell))
            extra.add(
                DetailInfo(
                    context.getString(R.string.strAddress).toUpperCase() + ":",
                    p?.location?.street?.number + " - " + p?.location?.street?.name + "\n" + p?.location?.city + "\n" + p?.location?.state
                )
            )
            extra.add(
                DetailInfo(
                    context.getString(R.string.strPostalCode).toUpperCase() + ":",
                    p?.location?.postcode
                )
            )

            return extra
        }

    }
}