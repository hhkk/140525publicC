
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<HTML>
<HEAD>
    <META http-equiv="Content-Type" content="text/html; charset=utf-8">

    <TITLE>Ajax Email Application</TITLE>

    <!--<link rel="stylesheet" type="text/css" href="1.css">-->
    %{--<LINK rel="stylesheet" type="text/css" href="http://layout.jquery-dev.net/lib/css/layout-default-latest.css">--}%
    <link rel="stylesheet" type="text/css" href="../css/layout-default-latest.css" />    %{--from http://layout.jquery-dev.net/demos/css/complex.css--}%



    <STYLE type="text/css">
    body, input, textarea {
        font-family:	Arial, Helvetica, sans-serif;
        font-size:		87.5%;
    }
    input, textarea {
        font-family:	Arial, Helvetica, sans-serif;
        font-size:		1em;
    }
    textarea {
        font-size:		1.1em;
    }
    .container {
        border-width:	0;
        overflow:		hidden;
        padding:		0;
        display:		none; /* hide panes until layout initializes */
    }
    .block {
        border:			1px dashed #BBB;
        padding:		5px 10px;
        font-weight:	bold;
    }
    .notBold{ font-weight: normal; }
    .border	{ border-width:	1px; }
    .lite	{ background: #EEE; color: #000; }
    .dark	{ background: #777; color: #FFF; }
    .grey	{ background: #CCC; color: #000; }
    .yellow	{ background: #FFA; color: #777; }
    .white	{ background: #FFF; color: #000; }

        /*** IMPORTANT - Layout container MUST have a 'height' or will be 'invisible' ***/
    .inner-layout-container { height: 100%; }
    #emailEdit.inner-layout-container { display: none; } /* initially hidden */


    #sortable { list-style-type: none; margin: 0; padding: 0; width: 60%; }
    #sortable li { margin: 0 3px 3px 3px; padding: 0.4em; padding-left: 1.5em; font-size: 1.4em; height: 18px;background-color:#999; }
    #sortable li span { position: absolute; margin-left: -1.3em; }

    #droppable { width: 150px; height: 150px; padding: 0.5em; float: left; margin: 10px; background-color:#999; }
    #draggable, #draggable-nonvalid { width: 100px; height: 100px; padding: 0.5em; float: left; margin: 10px 10px 10px 0; background-color:#969;}

    </STYLE>




    <SCRIPT type="text/javascript" src="http://layout.jquery-dev.net/lib/js/jquery-latest.js"></SCRIPT>
    <SCRIPT type="text/javascript" src="http://layout.jquery-dev.net/lib/js/jquery-ui-latest.js"></SCRIPT>
    <SCRIPT type="text/javascript" src="http://layout.jquery-dev.net/lib/js/jquery.layout-latest.js"></SCRIPT>
    <SCRIPT type="text/javascript" src="http://layout.jquery-dev.net/lib/js/debug.js"></SCRIPT>




    <SCRIPT type="text/javascript" language="javascript">

        function toggleInnerLayout ()
        {
            if (emailListingLayout && $("#emailListing").is(":visible"))
                showInnerLayout("emailEdit");
            else
                showInnerLayout("emailListing");
            return false; // cancel hyperlink
        };

        function showInnerLayout ( name )
        {
            var altName = name=="emailListing" ? "emailEdit" : "emailListing";
            $( "#"+ altName ).hide();	// hide OTHER layout container
            $( "#"+ name ).show();		// show THIS layout container
            // if layout is already initialized, then just resize it
            if (window[ name +"Layout" ])
                window[ name +"Layout" ].resizeAll();
            // otherwise init the layout the FIRST TIME
            else
                window[ name +"Layout" ] = $("#"+ name).layout( window[ name +"LayoutOptions" ] ); // use per-layout options
            //window[ name +"Layout" ] = $("#"+ name).layout( innerLayoutOptions ); // use common options
        };

        function resizeInnerLayout ()
        {
            if (emailListingLayout && $("#emailListing").is(":visible"))
                emailListingLayout.resizeAll();
            else if (emailEditLayout && $("#emailEdit").is(":visible"))
                emailEditLayout.resizeAll();
        };

        var
                outerLayout = null
                ,	outerLayoutOptions = {
                    center__paneSelector:	".outer-center"
                    ,	north__paneSelector:	".outer-north"
                    ,	spacing_open:			8	// ALL panes
                    ,	spacing_closed:			10	// ALL panes
                    ,	north__closable:		false
                    ,	north__resizable:		false
                    ,	north__spacing_open:	0
                    ,	center__onresize:		resizeInnerLayout
                }

                ,	emailListingLayout = null
                ,	emailListingLayoutOptions = {
                    spacing_open:			8	// ALL panes
                    ,	spacing_closed:			10	// ALL panes
                    ,	west__size:				180
                    ,	west__minSize:			180
                    ,	west__maxSize:			500
                    ,	east__size:				200
                    ,	east__minSize:			150
                    ,	east__maxSize:			300
                    ,	north__closable:		false
                    ,	north__resizable:		false
                    ,	north__spacing_open:	0
                    ,	west__togglerLength_open:	0
                    ,	east__togglerLength_open:	0
                    ,	east__togglerLength_closed:	0
                    ,	east__initClosed:		true
                    ,	center__onresize:		"emailListingInnerLayout.resizeAll"
                }

                ,	emailListingInnerLayout = null
                ,	emailListingInnerLayoutOptions = {
                    center__paneSelector:	".inner-center"
                    ,	south__paneSelector:	".inner-south"
                    ,	spacing_open:			8 // ALL panes
                    ,	spacing_closed:			10
                    ,	south__size:			"50%"
                    ,	south__minSize:			80
                    ,	south__maxSize:			"80%"
                }


                ,	emailEditLayout = null
                ,	emailEditLayoutOptions = {
                    spacing_open:			0
                    ,	triggerEventsOnLoad:	true
                    ,	center__onresize: function (pane, $Pane, state) {
                        // Layout will set the TEXTAREA height, but
                        // must manually calculate and set the width...
                        var
                                    $TextArea	= $Pane.children('.ui-layout-content')
                                ,	outerWidth	= state.innerWidth // handle padding on the pane
                                ,	padding		= 2 * parseInt( $TextArea.css('paddingLeft') )
                                ,	border		= 2 * parseInt( $TextArea.css('borderLeftWidth') )
                                ;
                        $TextArea.width( outerWidth - padding - border );
                    }
                }

                ;




        $(document).ready(function()
        {

            outerLayout = $('body').layout( outerLayoutOptions );

            // ALWAYS start with the email listing, so load that now
            emailListingLayout = $('#emailListing').layout( emailListingLayoutOptions );
            emailListingInnerLayout = $('#emailListing .ui-layout-center').layout( emailListingInnerLayoutOptions );

        });




    </SCRIPT>






</HEAD>







<BODY>

<DIV class="outer-north container">
    <DIV id="banner"  class="block lite">
        Banner &nbsp; &nbsp;
        <BUTTON onClick="toggleInnerLayout()" style="margin-left: 40%; vertical-align: middle;"> toggleInnerLayout() </BUTTON>
    </DIV>
</DIV>




<DIV class="outer-center container" >




    <DIV id="emailListing" class="inner-layout-container">

        <DIV class="ui-layout-north container">
            <DIV id="toolbar" class="block dark"> Email Listing Toolbar </DIV>
        </DIV>

        <DIV class="ui-layout-center container" >
            <DIV class="inner-center container">
                <!--<DIV id="toolbar" class="block dark"> Toolbar Option #2 </DIV>-->
                <DIV id="headers" class="block lite"> Table Headers </DIV>
                <DIV id="listing" class="ui-layout-content"> Email List </DIV>
            </DIV>
            <DIV class="inner-south container">
                <DIV class="block lite">Info</DIV>
                <DIV class="ui-layout-content notBold">
                    Reading Pane
                    <P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P>
                    <P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P>
                    <P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P>
                    <P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P>
                </DIV>
            </DIV>
        </DIV>

        <DIV class="ui-layout-west container border">
            <DIV style="text-align: right; border-bottom: 1px solid #BBB; cursor: pointer;"
                 onClick="emailListingLayout.close('west')"> &lt;&lt; </DIV>
            <UL id="droppable" class="ui-layout-content">
                <LI style="padding: 5px 0 5px 20px;">Item 0</LI>
                <LI style="padding: 5px 0 5px 20px;">Item 1</LI>
                <LI style="padding: 5px 0 5px 20px;">Item 2</LI>
            </UL>
        </DIV>

        <DIV class="ui-layout-east container border">
            <DIV class="block lite"> Help??? </DIV>
            <DIV class="ui-layout-content">
                <P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P>
                <P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P>
                <P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P>
                <P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P><P>.</P>
            </DIV>
        </DIV>

    </DIV><!-- /#emailListing -->





    <DIV id="emailEdit" class="inner-layout-container">

        <DIV class="ui-layout-north container lite">
            <DIV id="edit_toolbar" class="block dark"> Email Edit Main Toolbar </DIV>
            <DIV id="edit_message" class="block yellow"> Message Box </DIV>
            <DIV id="edit_header" class="ui-layout-content">
                &nbsp; &nbsp; <BUTTON style="width: 10ex;">TO</BUTTON> <INPUT> <BR>
                &nbsp; &nbsp; <BUTTON style="width: 10ex;">CC</BUTTON> <INPUT> Show BCC <BR>
                &nbsp; &nbsp; <BUTTON style="width: 10ex;">Subject</BUTTON> <INPUT>
            </DIV>
        </DIV>

        <DIV class="ui-layout-center container lite" style="padding: 0 15px;">
            <DIV id="edit_toolbar" class="block grey"> Email Editing Toolbar </DIV>
            <TEXTAREA class="ui-layout-content border">
                TEXTAREA Editor

                type here...
            </TEXTAREA>
            <DIV class="block notBold"> <INPUT type="checkbox"> Request Return Confirm </DIV>
        </DIV>

        <DIV class="ui-layout-south block lite notBold">
            Attach File: <INPUT type="file"> <BUTTON>Attach</BUTTON>
        </DIV>

    </DIV><!-- /#emailEdit -->





</DIV><!-- /.outer-center -->

</BODY>
</HTML>
