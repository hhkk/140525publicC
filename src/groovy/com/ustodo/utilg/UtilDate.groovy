package com.ustodo.utilg;

import groovy.time.TimeCategory

import java.util.regex.Matcher
import java.util.regex.Pattern

public class UtilDate {

	public static String renderAgeAsLetterFromNowToFileDateStr (String d)
	{

		//O.o("date processing [" + d + "] len ["+ "]");
		try
		{
			// NOW
			def gc = new GregorianCalendar();
			long yyyy23 = gc.get(Calendar.YEAR);
			long mm23 = gc.get(Calendar.MONTH)+1;
			long dd23 = gc.get(Calendar.DAY_OF_MONTH);
			long hh23 = gc.get(Calendar.HOUR_OF_DAY);
			long mn23 = gc.get(Calendar.MINUTE);
			long ss23 = gc.get(Calendar.SECOND);

			// THEN
			// 012345678901234567890
			// 2007-12-27 11:40:01
			long yyyy = Long.parseLong (d[0..3])
			long mm = Long.parseLong (d[5..6])
			long dd = Long.parseLong (d[8..9])
			long hh = Long.parseLong (d[11..12])
			long mn = Long.parseLong (d[14..15])
			long ss = Long.parseLong (d[17..18])

			long ago =
					(yyyy23-yyyy) * 365 * 24 * 3600 +
					(mm23-mm)     * 30.5* 24 * 3600 +
					(dd23-dd)     *       24 * 3600 +
					(hh23-hh)     *            3600 +
					(mn23-mn)                *   60 +
					(ss23-ss);

			long _yyyy = 365 * 24 * 3600;
			long _mm =   30.5* 24 * 3600;
			long _ww =     7 * 24 * 3600;
			long _dd =         24 * 3600;
			long _hh =              3600;
			long _mn =                60;
			long _ss =                 1;

            if (ago < _ss)
                return "1<font size=-3>sec</font>"
            else if (ago < (60*_ss))
                return "< 1<font size=-3>min</font>"
            else if (ago < (10 * 60*_ss))
                return "< 10<font size=-3>min</font>"
            else if (ago < (10 * 60*_ss))
                return "< 30<font size=-3>min</font>"
			else if (ago < _hh)
			{
				int ageInMins = Math.round(ago/_mn);
				if (ageInMins < 50)
					return "<1h"
				else
					return "1h"
			}

			else if (ago < _dd)
			{
				int ageInHours = Math.round(ago/_hh);
				return ageInHours+"h"
			}
			else if (ago < _ww)
			{
				int ageInDays = Math.round(ago/_dd);
				return ageInDays+"d"
			}
			else if (ago < _mm)
			{
				int ageInWeeks = Math.round(ago/_ww);
				return ageInWeeks+"w"
			}
			else if (ago < _yyyy)
			{
				int ageInMo = Math.round(ago/(int) (30.5*24*3600));
				return ageInMo+"m";
			}
			else
			{
				int ageInMo = Math.round(ago/((int)_yyyy));
				return ageInMo+"y"
			}

			return ""


			//				if (ago > _yyyy)
			//				return "y"
			//			else if (ago > _mm)
			//				return "m"
			//			else if (ago > _ww)
			//				return "w"
			//			else if (ago > _dd)
			//				return "d"
			//			else if (ago > _hh)
			//				return "h"
			//			else if (ago > _mn)
			//				return "m"
			//			else if (ago > _ss)
			//				return "s"
			//			else // subsecond
			//				return"s"
		} catch ( Exception e)
		{
			//System.err.println ("error converting date ["+d+"] " + e.getMessage());
			//e.printStackTrace();
			return "1+y";
		}


	} // render age as letter

	public static String getDateForFile()
	{
		def gc = new GregorianCalendar();
		long yyyy = gc.get(Calendar.YEAR);
		long mm = gc.get(Calendar.MONTH)+1;
		long dd = gc.get(Calendar.DAY_OF_MONTH);
		long hh = gc.get(Calendar.HOUR_OF_DAY);
		long mn = gc.get(Calendar.MINUTE);
		long ss = gc.get(Calendar.SECOND);

		return "" + yyyy + "-" + pz(mm) + "-" + pz(dd) + " " + pz(hh) + ":" +pz(mn) + ":" + pz(ss);

	}

	public static String getDateLikeFileFormatFromUtilDate()
	{
		getDateLikeFileFormatFromUtilDate (new java.util.Date())
	}

    public static String getDateLikeFileFormatFromUtilDate(java.util.Date dt)
    {
        def gc = new GregorianCalendar();
        gc.setTime(dt);
        long yyyy = gc.get(Calendar.YEAR);
        long mm = gc.get(Calendar.MONTH)+1;
        long dd = gc.get(Calendar.DAY_OF_MONTH);
        long hh = gc.get(Calendar.HOUR_OF_DAY);
        long mn = gc.get(Calendar.MINUTE);
        long ss = gc.get(Calendar.SECOND);

        return "" + yyyy + "-" + pz(mm) + "-" + pz(dd) + " " + pz(hh) + ":" +pz(mn) + ":" + pz(ss);

    }



    public static String getDateLikeFileFormatFromUtilDateNoTime(java.util.Date dt)
	{
		def gc = new GregorianCalendar();
		gc.setTime(dt);
		long yyyy = gc.get(Calendar.YEAR);
		long mm = gc.get(Calendar.MONTH)+1;
		long dd = gc.get(Calendar.DAY_OF_MONTH);

		return "" + yyyy + "-" + pz(mm) + "-" + pz(dd);

	}

	public static String getDateForFileNoSpacesForFileSystem()
	{
		def gc = new GregorianCalendar();
		long yyyy = gc.get(Calendar.YEAR);
		long mm = gc.get(Calendar.MONTH)+1;
		long dd = gc.get(Calendar.DAY_OF_MONTH);
		long hh = gc.get(Calendar.HOUR_OF_DAY);
		long mn = gc.get(Calendar.MINUTE);
		long ss = gc.get(Calendar.SECOND);

		return "" + yyyy + "-" + pz(mm) + "-" + pz(dd) + "_" + pz(hh) + "_" +pz(mn) + "_" + pz(ss);

	}

	public static String getDateForCollectionName()
	{
		def gc = new GregorianCalendar();
		long yyyy = gc.get(Calendar.YEAR);
		long mm = gc.get(Calendar.MONTH)+1;
		long dd = gc.get(Calendar.DAY_OF_MONTH);
		long hh = gc.get(Calendar.HOUR_OF_DAY);
		long mn = gc.get(Calendar.MINUTE);
		long ss = gc.get(Calendar.SECOND);

		String s = "" + yyyy.toString().substring(2,4) + pz(mm) + pz(dd) + "_" + pz(hh) + pz(mn) + pz(ss);
		return s;

	}

	// padZeroToTwoWide
	public static String pz(long l) {
		String s = "" + l
		if (s.length() ==2 )
			return s
		else
			return "0" + s;

	}


	private static Map parseTimeAgoLike1m(String maxAgeLike1M)
	{
		def hm = new HashMap();
		Matcher matcher = Pattern.compile("([0-9].*)([a-zA-Z].*)").matcher(maxAgeLike1M);
		boolean matchFound = matcher.find();
		//O.o("processing " + maxAgeLike1M)
		if (!matchFound || matcher.groupCount() != 2)
			throw new Rte("invalid time/age string [${maxAgeLike1M}]");
		else
		{
			hm.put("num", matcher.group(1)); // eg '1'
			hm.put("unit", matcher.group(2)); // eg 'm'
		}
		hm;
	}

	private static Date buildMinDateFromMaxAge(String maxAgeLike1M)
	{
		Date dtnow = new java.util.Date();
		Map hm = parseTimeAgoLike1m(maxAgeLike1M);
		// from http://groovy.codehaus.org/api/groovy/time/package-summary.html
		Date dtthen = null;
		use (TimeCategory) {
			int num = Integer.parseInt(hm.get( "num"));
			def unit = hm.get( "unit");

			//O.o "num:", num
			//O.o "unit:", unit
			//int i = 1;
			if (unit.equals("m"))
			{
				//println num.months.ago
				dtthen = num.months.ago
			}
			else if (unit.equals("d"))
			{
				//println num.days.ago
				dtthen = num.days.ago
			}
			else
				throw new Rte("invalid unit string [${}]");

			//println num.unit.ago
			//println i.days.ago

			//println new Date() - 3.months
		}
		dtthen;

	}

	public static String buildMinDateStrFromMaxAge(String maxAgeLike1M)
	{
		Date d = buildMinDateFromMaxAge(maxAgeLike1M);
		return getDateLikeFileFormatFromUtilDate(d)

	}

	public static void main (String[] args)
	{
		try
		{
			def ago = "13m";
			def s = buildMinDateFromMaxAge(ago)
			O.o("ago [${ago}] yields s:" + s)
			//O.o("getDateLikeFileFormatFromUtilDate(dtmin):", getDateLikeFileFormatFromUtilDate(dtmin))
		}
		catch (Throwable t )
		{
			O.or("t", t);
			throw t;
			assert(false);
		} finally {
			O.o "done"
			assert (true)
		}


	} // main
}

