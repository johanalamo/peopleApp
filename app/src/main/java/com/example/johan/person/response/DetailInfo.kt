package com.example.johan.person.response

class DetailInfo(fl: String?, vl: String?) {
    var field: String?
    var value: String?

    init {
        this.field = fl
        this.value = vl
    }

    constructor (fl: String) : this(fl, null)

    constructor () : this(null, null)
}
