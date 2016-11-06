	package com.ustodo.todo

    import com.ustodo.utilg.O
    import com.ustodo.utilg.mail.UtilEmail



    class SendCmdLogEmailThread {
        static class SendCmdLogEmailThread_ implements Runnable {

            def username
            def params_q
            public SendCmdLogEmailThread (String username, String params_q) {
                this.username = username
                O.o "got in structer" + params_q
                this.params_q = params_q
            }


            public void run() { // SEND EMAIL ON A THREAD
                O.o("130204 Hello from a thread sending email !");
                try {

                    Vector v = new Vector()
                    v.add("henry.kon@gmail.com");
                    UtilEmail.sendMail(v, "utd", "seeutd", "dummy");
                    UtilEmail.sendMail(v, "utd deleteme", "params.q [" + params_q + "]", username);
                    O.o "in thread done sent email ustodo user command [" + username + "]" + "params.q [" + params_q + "]";
                } catch ( Exception e )
                {
                    O.or("error in thread : sending non ckckck email notice - MAYBE THE NETWORK IS UNAVAILABLE", e)
                }
                return
            }



        }





// 3. EMAIL READ EMAIL, SEARCH EMAIL SEARCH EMAIL_READ_EMAIL NOT FILE ==============================================
//					if (MODE_EMAIL_ADVANCED) // do DB part
//					{
//						//O.o "@@@@@@@@@@@@@@ READING EMAIL rtnQ [$rtnQ]"
//
//						UtilMailSearchRequestWithDispositionOptions emailSearchRequest =
//							new UtilMailSearchRequestWithDispositionOptions (
//								"[Gmail]/All Mail",    // mailbox/folder
//								//"atemp",  // mailbox/folder
//								//"*", // search str
//								//"subjhk", // searchstr
//								"ustodo", // searchstr
//								1, // DATE CONSTRAINT
//								Calendar.MONTH,
//								//Calendar.YEAR,
//								//Calendar.DATE, // DATE CONSTRAINT
//								//1,
//								//Calendar.MONTH,
//								//1000, // emit - NUM RECORDS TO EMIT
//								//1000,  // db - NUM RECORDS TO WRITE TO DB
//								100, // emit - NUM RECORDS TO EMIT
//								100,  // db - NUM RECORDS TO WRITE TO DB
//								//UtilFileLineRaw.SrchMode.KEYAGE.toString(),
//								UtilMailSearch.SrchMode.KEYAGE.toString(),
//								com.ustodo.utilg.Cfg.dbname,
//								true,
//								Cfg.collnameEmailsSuckedIn,
//								"ustodo.com@gmail.com"
//								);
//						FileLine[] arrfl = UtilMailSearch.searchEmailOptionalSave (emailSearchRequest); // if null will get created there
//						arrfl.each {alFileLines << it}
//						//O.o "@@@@@@@@@@@@@@ 3 READING EMAIL rtnQ [$rtnQ]"
//						alReadLinesDbOrFileOrEmail = alReadLinesDbOrFileOrEmail.reverse()
//						O.o "@@@@@@@@@@@@@@ 4 DONE READING EMAIL got this many:" + arrfl.length
//					}
// DONE EMAIL READ =========================

    }
