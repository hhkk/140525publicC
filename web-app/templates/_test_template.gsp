%{--<div>----------------> ${theItem}</div>--}%

<span class="himom">
    <br>
    %{--redx red x--}%
    <input type="image" name="delCmdHist" value="delCmdHist" src="images/skin/deleteRecent.png" height="8px" width="5px"
           title="Delete from Recent (history)"
           onmouseover="recentCmdDelOne('${it}')"
    >
    <span class="onMouseDelRecent" onmouseover="document.getElementById('txtUpper').value = '${it}'"
          onclick="document.hkformname.submit()"
    %{--onclick="document.forms["hkformname"].submit();"--}%
    %{--onclick="document.getElementById('txtUpper').focus()"--}%
    %{--ondblclick="clearCmdHist()"--}%
    >

        %{-- OLD WORKS onComplete: "postCheckBox();")--}%
        %{-- NEWER works onComplete: "postCheckBox('${listdboOUTForIndexHtml[i].hkoid}');")--}%
        %{--<font class="fontClassToGetText" i= size=-2 color= ${((i + 1) % 2) == 1 ? "#000000" : "#666666"}>--}%
        <font class="fontClassToGetText" i= size=-2>
            ${com.ustodo.utilg.UtilHtml.formatForHtmlDisplay(it.toString())}
        </font>
    </span>
</span>
