<%--
  Created by IntelliJ IDEA.
  User: hkon
  Date: 3/9/13
  Time: 4:39 AM
  To change this template use File | Settings | File Templates.

  uses jquery appendto to create button and textarea


--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>ff</title>

    %{--<g:javascript src="jquery/jquery-1.7.1.min.js" />--}%
    <g:javascript src="ajax/AjaxJsonFetch.js" />
    <g:javascript src="emit/emit0001.js" />
    <g:javascript src="emit/emitCheckbox.js" />
    <g:javascript src="emit/table/emitJsonTable2.js" />
    <g:javascript src="emit/IndexSuperclass.js" />
    <g:javascript src="jquery/jquery-1.7.1.min.js" />
    <g:javascript src="tinymce/jscripts/tiny_mce/tiny_mce.js" />
    <g:javascript src="utiljs/handleErr.js" />
    <g:javascript src="utiljs/jqueryAutocomplete.js" />
    <g:javascript src="utiljs/onloadhk_fromMaingspss.js" />
    <g:javascript src="utiljs/stacktrace.js" />
    <g:javascript src="utiljs/UtilClass.js" />
    <g:javascript src="utiljs/UtilDOM.js" />
    <g:javascript src="utiljs/UtilString.js" />
    <g:javascript src="utiljs/UtilTinyMCE.js" />
    <g:javascript src="utiljs/UtilClass.js" />
    <g:javascript src="utiljs/UtilIndex.js" />
    <g:javascript src="utiljs/UtilJavascript.js" />



    <script type="text/javascript">

        function testme(n)
        {
            //alert ("in testme n:" + n)
            //  alert ("ddd:"+$('#btn_1').dddddd)
            //alert ("in testme getKeys(n):" + getKeysAndValues   ($('#btn_1')))
            //console.log ("in testme getKeys(n):" + getKeysAndValues   (n))
            //alert
            //alert ("in testme caller this.id:" + this.id)
            //var s = tinyMCE.activeEditor.getContent();
            tinyMCE.execCommand('mceFocus', true, 'txtUpper'+n);
            tinyMCE.activeEditor.setContent('aa'+ tinyMCE.activeEditor.getContent())
            tinymce.activeEditor.setContent('bb'+ tinymce.activeEditor.getContent())
            window.parent.tinyMCE.activeEditor.setContent('cc'+ tinymce.activeEditor.getContent())
            window.parent.tinymce.activeEditor.setContent('dd'+ tinymce.activeEditor.getContent())

            //tinyMCE.activeEditor.focus();

            //tinyMCE.get('txtUpper').focus();

            //window.parent.tinyMCE.get('txtUpper').focus()

            //this.focus();

            tinyMCE.execCommand('mceFocus', true, 'txtUpper'+n);
            //alert ("12")
            //$("#txtUpper").focus()
            //alert ("24")
        }


        $(document).ready(
//                  function() {
//                      alert ("sdsdds")
//                    }
//        //
        function()
        {
            // http://stackoverflow.com/questions/8936652/dynamically-create-buttons-with-jquery
            for(i = 1; i <= 2; i++)
            {
                //alert("hi1")
                $('<button/>', {
                    text: i, //set text 1 to 10
                    id: i,
                    id2: 'btn_'+i,
                    dddddd: i,
                    click: function () { var idx = this.id; testme(this.id); }
                }).appendTo("#page-background");
            }
//            click: function () { testme($(this)); }

            //alert("hi2")
            $('<textarea/>', {
                text: 'hi MCE.dynamic', //set text 1 to 10
                id: 'txtUpper1',
                class: 'fld1upperclass1',
                click: function () { testme('hi'); },
                onkeyup:"alert ('in onkeyup')"
            }).appendTo("#page-background");

            //alert("hi2")
            $('<textarea/>', {
                text: 'hi MCE.dynamic', //set text 1 to 10
                id: 'txtUpper2',
                class: 'fld1upperclass2',
                click: function () { testme('hi'); },
                onkeyup:"alert ('in onkeyup')"
            }).appendTo("#page-background");


            //alert ("pre mce fld1upperclass 1")
            initmcehbk("i5.desc.fld1upperclass", // 1
                    "fld1upperclass1",
                    "",
                    "simple",
                    '16px',  // 5
                    "50",
                    "txtUpper1"
            );
            //alert ("pre mce fld1upperclass 1")
            initmcehbk("i5.desc.fld1upperclass", // 1
                    "fld1upperclass2",
                    "",
                    "simple",
                    '16px',  // 5
                    "50",
                    "txtUpper2"
            );
            //alert ("pre mce fld1upperclass 2")
            //initmcehbk("i5.desc.fld1upperclassORI", "fld1upperclassORI", "",
             //       "advanced", '16px', "50", "specific_textareas");
            //alert ("pre mce fld1upperclass 3")


            //tinyMCE.execCommand('mceFocus', false, 'txtUpper');
            var rich = (typeof tinyMCE != "undefined") && tinyMCE.activeEditor != null && !tinyMCE.activeEditor.isHidden();

            //alert ("rich1:" + rich);


//            var s = tinyMCE.activeEditor.getContent();
//            tinyMCE.activeEditor.setContent('aa'+ s)
//
//            tinyMCE.activeEditor.focus();


        }
);




        //                <textarea id="txtUpper" name="q" class="fld1upperclass" style="color: blue; margin-left: 0px; font-size: 16px; vertical-align: top; display: none;" type="text" size="90%" cols="90" rows="dontMatterWillBeMce" onkeypress="" onkeyup="copyupdown(1);handleEnter(this, event);jQuery.ajax({type:'POST',data:'autocomp=' + this.value, url:'/ustodo/todo/ajax_autocompleteSearchUpper',success:function(data,textStatus){jQuery('#leftnav').html(data);},error:function(XMLHttpRequest,textStatus,errorThrown){jQuery('#not_listoutputxxxxxxxxxxx').html(XMLHttpRequest.responseText);},complete:function(XMLHttpRequest,textStatus){postautocomplete('txtUpper');}});" title="
// aria-hidden="true">jquery instr</textarea>

    </script>
</head>
<body>
sddddsdsdsd

<div id="page-background">
</div>

%{--onkeyup="copyupdown(1);handleEnter(this, event);${--}%
    %{--remoteFunction(action: 'ajax_autocompleteSearchUpper',--}%
            %{--update: [success: 'leftnav', failure: 'not_listoutputxxxxxxxxxxx'],--}%
            %{--params: '\'autocomp=\' + this.value',--}%
            %{--onComplete: 'postautocomplete(\'txtUpper\');')}"--}%


%{--<g:textArea--}%
        %{--id="txtUpper2"--}%
        %{--name="q"--}%
        %{--class="fld1upperclassORI"--}%
        %{--style="xxheight:4em;color:blue;margin-left:0px;font-size:16px; vertical-align: top"--}%
        %{--type="text"--}%
        %{--size="90%"--}%
        %{--cols="90"--}%
        %{--rows="dontMatterWillBeMce"--}%
        %{--onkeypress=""--}%

        %{--value="hi ,MCE.static"--}%
        %{--title="hk tooltip title"--}%
%{--/>--}%




</body>
</html>
