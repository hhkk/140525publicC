/**
 * Created with IntelliJ IDEA.
 * User: hkon
 * Date: 3/10/13
 * Time: 6:03 AM
 * To change this template use File | Settings | File Templates.
 */


function copyupdown(mode1down2up3upnohtml)
{

    //alert ("in copyupdown:" + mode1down2up3upnohtml)



    // onmouseover="getElementByIdHK('txtUpper').value = ''"
    if (mode1down2up3upnohtml == 1)
    {
        //alert ("in copydown1")
        try {
            //var filter = 'input:[class=fld2lowerclass][class=fld2lowerclass]'
            //tinyMCE.get('my_editor').setContent('jjjjjjjjj');

            tinyMCE.activeEditor.setContent(getElementByIdHK('txtUpper').value)
        }  catch (err) {
            handleErr(err);
        }

    }
    // called by mce.init type code
    else if (mode1down2up3upnohtml  == 2 ) // strip html tags
    {
        var s = tinyMCE.activeEditor.getContent({format : 'text'}) // works
//            var s = tinyMCE.activeEditor.getContent().replace(/<[^>]+>/g,"") // works
        // if (s.endsWith("&nbsp;"))
        if (endswithhk_includingHtmlConversion(s, "&nbsp;"))
        {
            console.log ("s ends in nbsp see [" + s + "] len [" + s.length + "]")
            //s = (s.substring(0, s.length))

        }
        //console.log ("setting fld1upper to:" + s +", len [" + s.length + "]")
        getElementByIdHK('txtUpper').value = s
        //alert ("html tags were stripped in this copy - be sure you like the results or try the other copy up arrow")
    }
    else if (mode1down2up3upnohtml  == 3 )
    {
        alert ("in copydown2 mode 3")
        getElementByIdHK('txtUpper').value = tinyMCE.activeEditor.getContent()
    }
    else
        alert ("error invalid copy mode:" + mode1down2up3upnohtml);

}


