package com.example.johan.person.response

class DetailInfo(fl: String?, vl: String?){ //primary constructor
	//create default getter and setter
	var field: String?
	//replace getter and setter
	var value: String?
	init {
		this.field = fl
		this.value = vl
	}
	//second constructor
	constructor (fl: String) : this(fl, null)
	//fourth constructor
	constructor () : this(null, null)
}
