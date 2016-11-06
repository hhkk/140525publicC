package com.ustodo.utilg

class Rte extends RuntimeException {

	public Rte ()
	{
		super();
	}
	
	public Rte(String s)
	{
		throw new RuntimeException(s)
	}
}
