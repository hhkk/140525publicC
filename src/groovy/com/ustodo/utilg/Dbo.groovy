package com.ustodo.utilg

import com.mongodb.BasicDBObject;

class Dbo extends BasicDBObject {

	public Dbo ()
	{
		super();
	}
	
	public Dbo (Object key, Object val)
	{
		super(key, val);
	}
	
}
