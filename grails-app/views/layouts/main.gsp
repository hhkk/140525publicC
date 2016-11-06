<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="UsToDo"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    %{--<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">--}%
    %{--<link rel="shortcut icon" href="${resource(dir: 'images', file: 'tab_green.png')}" type="image/x-icon">--}%
    %{--<script type="text/javascript">--}%
        %{--alert ("in main.gsp head")--}%
    %{--</script>--}%
    %{--<link rel="shortcut icon" href="${resource(dir: 'images', file: 'orangeicon.png')}" type="image/x-icon">--}%
    %{--<link rel="shortcut icon" href="../images/xxgrails_logo.png')}" type="image/x-icon">--}%
    <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="tdd1ext/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
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
    <g:javascript src="utiljs/UtilIndex.js" />
    <g:javascript src="utiljs/UtilJavascript.js" />


    <g:layoutHead/>
    <r:layoutResources />
</head>

<body onload="onloadhk_fromMaingspss(false)">

<script type="text/javascript">
    //alert ("in main.gsp")
</script>

%{--<g:if test="${uinewTF == true}">--}%
    %{--<body onload="onloadhk_fromMaingspss(false, 1)">--}%
%{--</g:if>--}%

%{--<g:if test="${uinewTF == false}">--}%
    %{--<body onload="onloadhk_fromMaingspss(true, 1)">--}%
%{--</g:if>--}%

<g:layoutBody/>
<div class="footer" role="contentinfo"></div>
<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
<g:javascript library="application"/>
<r:layoutResources />

</body>
</html>

%{--old--}%
%{--<!DOCTYPE html>--}%


%{--<html>--}%
%{--<head>--}%
%{--<title><g:layoutTitle default="UsToDo" /></title>--}%

%{--<g:javascript library="jquery" plugin="jquery"/>--}%

%{--<%--<link rel="stylesheet"--}%
%{--href="<g:createLinkTo dir='css'--}%
%{--file='hubbub.css'/>" />--}%
%{----%>--}%
%{--<g:layoutHead />--}%
%{--</head>--}%
%{--<body onload="${pageProperty(name:'body.onload')}">--}%
%{--<r:layoutResources />--}%
%{--<%--<div class="menu"><!--my common menu goes here-->--}%
%{--</menu>--}%
%{----%>--}%
%{--<%--<div id="hd"><a href="<g:createLinkTo dir="/"/>"> <img--}%
%{--id="logo" src="${createLinkTo(--}%
%{--dir: 'images',--}%
%{--file: 'headerlogo.png')}"--}%
%{--alt="hubbub logo" /> </a></div>--}%
%{----%>--}%
%{--<div id="bd">--}%
%{--<!-- start body -->--}%
%{--<g:layoutBody />--}%
%{--</div>--}%
%{--</body>--}%
%{--</html>--}%
