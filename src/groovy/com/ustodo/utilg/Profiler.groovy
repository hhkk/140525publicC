package com.ustodo.utilg;

import java.util.Calendar;

import java.sql.Date;

import com.ustodo.utilg.O;


public class Profiler {

	private static HashMap hmStartEnd = new HashMap()
	private static HashMap hmCheck = new HashMap()
	private static HashMap hmDesc = new HashMap()

	static synchronized String start(String desc)
	{
		start(desc, true)
	}
	static synchronized String start(String desc, boolean output)
	{
		long l = UtilPerf.now();
		Long t = Thread.currentThread().getId()
		hmStartEnd.put(t, l);
		hmCheck.put(t, l);
		String rtn = "PERF.start [" + desc + "]";
		hmDesc.put(t, desc)
		if (output)
		{
			//println "\r\n"
			O.o rtn;
		}
		return rtn;
	}

	static synchronized String check(String desc)
	{
		check(desc, true)
	}
	static synchronized String check(String desc, boolean output)
	{
        //O.o "in Profiler.check desc [${desc}]  output [${output}] "
		if (null == hmCheck.get(Thread.currentThread().getId()))
			Profiler.start("backstop profile start");

		long l = hmCheck.get(Thread.currentThread().getId());
		long lFromStart = hmStartEnd.get(Thread.currentThread().getId());
		if (l == null)
		{
			start("autoStart:" + desc, output)
			l = hmCheck.get(Thread.currentThread().getId());
			lFromStart = hmStartEnd.get(Thread.currentThread().getId());
		}
		hmCheck.put(Thread.currentThread().getId(), UtilPerf.now());
		String rtn = "PERF: tot [" +  UtilPerf.msSince(lFromStart) + "] incr [" +  UtilPerf.msSince(l) + "] desc [" + desc + "]"
		if (output)
			O.o rtn;
		return rtn;
	}

	static synchronized String end(String desc)
	{
		end (desc, true)
	}
	static synchronized String end(String desc, boolean output)
	{
		hmDesc.put(Thread.currentThread().getId(), desc)
		return end (output)

	}

	static synchronized String end(boolean output)
	{
		Long t = Thread.currentThread().getId();

		//O.oc("hmDesc.get(t) ", hmDesc.get(t) )
		String rtn = "PERF.end [" + hmDesc.get(t) +
			" partial in ms:" + UtilPerf.msSince(hmCheck.get(Thread.currentThread().getId())) +
			" total   in ms:" + UtilPerf.msSince(hmStartEnd.get(Thread.currentThread().getId())) +"]"
		if (output)
			O.o rtn;
		assert (hmStartEnd.get(t) != null)
		assert (hmCheck.get(t) != null)
		assert (hmDesc.get(t) != null)
		hmStartEnd.remove(t);
		hmCheck.remove(t);
		hmDesc.remove(t);
		return rtn;
	}
}
