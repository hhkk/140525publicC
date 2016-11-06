package com.ustodo



class OpenID {

	String url

	static belongsTo = [user: SecUser]

	static constraints = {
		url unique: true
	}
}
