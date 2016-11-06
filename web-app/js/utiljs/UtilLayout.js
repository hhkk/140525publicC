/**
 * Created with IntelliJ IDEA.
 * User: hkon
 * Date: 6/8/13
 * Time: 4:53 AM
 * To change this template use File | Settings | File Templates.
 */

// from http://www.hardcode.nl/subcategory_1/article_526-singleton-examples-in-javascript.htm
var singletonUtilLayout_layout_showAll_state = ( function() {

    /*private variables and methods (not accessible directly through the  mySingleton namespace): */

    //alert ("startdef")
    var privateVar_isShown_layout_pane_west = true;
    var privateVar_isShown_layout_pane_east = true;
    var privateVar_west_isLarge = false;
    var privateVar_east_isShown = true;

    function alertPrivate(){
        alert(privateVar);
    }
    function privateFunctionAlert(s){
        alert("ssss:" + s);
    }
    // alert ("startdef2")
    /* public variables and methods (can access private vars and methods ) */
    return {
        publicMethod_showEastAndWest:function(){
            //alert ("startdef21")
            //privateFunctionAlert("someone called publicMethod_showEastAndWest");
            try {
                innerLayout.show('east')
                innerLayout.show('west')
            }
            catch (err) {
                //      commandText  +"] successfail [" + "N/A (error) " + "]");
                //alert (" ******************************************* error ");
                //handleErr("LAYOUTcALLER", err);
            } finally {
            }
            privateVar_isShown_layout_pane_west = true;
            privateVar_isShown_layout_pane_east = true;
            //alert ("startdef22")
        },

        publicMethod_showMoreEastAndWest_orToggleWestIfAlreadyAllShown:function(){
            //alert ( "in publicMethod_showMoreEastAndWest_orToggleWestIfAlreadyAllShown");
            //privateFunctionAlert("someone called publicMethod_showMoreEastAndWest_orToggleWestIfAlreadyAllShown");
            var justShownWest = false;
            if (privateVar_east_isShown)
            {
                innerLayout.hide('east')
                privateVar_east_isShown = false;
            }
            else
            {
                if (!privateVar_isShown_layout_pane_west)
                {
                    //alert("hi mom");
                    justShownWest = true;
                    privateVar_west_isLarge = false;
                    innerLayout.show('west')
                    privateVar_isShown_layout_pane_west = true;
                }
                if (privateVar_isShown_layout_pane_west && !justShownWest)
                {
                    if (!privateVar_west_isLarge)
                    {
                        innerLayout.sizePane('west', '40%')
                        innerLayout.hide('east')
                        privateVar_west_isLarge = true;
                    }
                    else
                    {
                        innerLayout.sizePane('west', '20%')
                        privateVar_west_isLarge = false;
                    }
                }

            }
            if (!privateVar_isShown_layout_pane_east && !justShownWest)
            {
                //alert("hi mom2");
                innerLayout.show('east')
                privateVar_isShown_layout_pane_east = true;
            }

        },
        publicMethod_EastAndWestShowNone:function(){
            //privateFunctionAlert("someone called publicMethod_EastAndWestShowNone");
            innerLayout.hide('east')
            innerLayout.hide('west')
            privateVar_isShown_layout_pane_west = false;
            privateVar_isShown_layout_pane_east = false;
        },
        publicVar:'this is publicly accessible'
    }
})()
//alert ("startdef3")
singletonUtilLayout_layout_showAll_state.publicMethod_showEastAndWest();
//alert ("startdef4")


function UtilLayout_layout_showAll ()
{
    singletonUtilLayout_layout_showAll_state.publicMethod_showMoreEastAndWest_orToggleWestIfAlreadyAllShown();
//innerLayout.hide('north')
    innerLayout.show('south')
}

function UtilLayout_layout_hideAll ()
{
    singletonUtilLayout_layout_showAll_state.publicMethod_EastAndWestShowNone();
//    innerLayout.hide('east')
//    innerLayout.hide('west')
//innerLayout.hide('north')
    innerLayout.hide('south')
}


function layout_changePanelSize(layout, panelid, size)
{
    alert ("in layout_changePanelSize")
    layout.sizePane(panelid, size);
    //alert ("done layout_changePanelSize")
}

function layout_changePanelSize_slowly(layout, panelid, size_init, sizefinal)
{
    alert ("in layout_changePanelSize")
    for (i = size_init; i <=sizefinal; i++)
    {
        layout.sizePane(panelid, i);

    }
    //alert ("done layout_changePanelSize")
}



function northOnresize ()
    {
        //oooo("start north__onresize htNorthOri htMceTargetGiveNorth ");
        //return;
        var htNorthOri = $('#id-ui-layout-north-inner').height();
        var htMceTargetGiveNorth = htNorthOri - 28;

        //var t = $('#testme2ta').val() + 'px';
        var t = htMceTargetGiveNorth + 'px';
        //alert ("setting to t:") // +t
        $(".mceIframeContainer").attr ('style', 'height: '+t);
        //var t = '300px'
        //alert ("setting to:" + t)
        $('#txtUpper_ifr').css('height', t);
        //oooo ("done north__onresize htNorthOri:" +  htNorthOri + ", htMceTargetGiveNorth:" + htMceTargetGiveNorth);
    }


/*
 *#######################
 * INNER LAYOUT SETTINGS
 *#######################
 *
 * These settings are set in 'list format' - no nested data-structures
 * Default settings are specified with just their name, like: fxName:"slide"
 * Pane-specific settings are prefixed with the pane name + 2-underscores: north__fxName:"none"
 */
// hbk130623

//$(window).resize(function() {
$("#id-ui-layout-north-inner").resize(function() {
    //$('#log').append('<div>Handler for .resize() called.</div>');
    alert ("resize happened");
});
//alert ("done adding resize");

// http://stackoverflow.com/questions/3293771/how-to-get-rid-of-margins-in-jquery-layout


var layoutSettings_Inner = {
    applyDefaultStyles:				true // basic styling for testing & demo purposes
    ,	minSize:						20 // TESTING ONLY
    ,	spacing_closed:					14
    ,	north__spacing_closed:			8
    ,	south__spacing_closed:			8
    ,	north__togglerLength_closed:	-1 // = 100% - so cannot 'slide open'
    ,	south__togglerLength_closed:	-1
    //,	fxName:							"slide" // do not confuse with "slidable" option!
    ,	fxName:							"none" // do not confuse with "slidable" option!
    //,	fxSpeed_open:					000
    //,	fxSpeed_close:					000
    //,	fxSettings_open:				{ easing: "easeInQuint" }
    //,	fxSettings_close:				{ easing: "easeOutQuint" }
    //,	north__fxName:					"none"
    //,	south__fxName:					"drop"
    //,	south__fxSpeed_open:			0
    //,	south__fxSpeed_close:			0
    //,	initClosed:						true
    ,	center__minWidth:				200
    ,	center__minHeight:				200

    // inner north  1/4
    ,	north: {
        size:			230 // north default size HBK

        //        ,	resizable: 				true
        //        ,	slidable:				true

        ,   maskContents:  true
        ,   maskObjects:   true

    }

    // inner west 2/4
    ,   west:
    {
        initClosed:				false
        ,	togglerTip_open:		"Close East Pane"
        ,	togglerTip_closed:		"Open East Pane"
        ,	resizerTip_open:		"Resize East Pane"
        ,	slideTrigger_open:		"mouseover"
        // ,spacing_open: 0
    }

    // inner center 3/4
    ,   center: {
        //size:			230 // north default size HBK

        //        ,	resizable: 				true
        //        ,	slidable:				true

        maskContents:  true
        ,maskObjects:   true

    }

    // inner south 4/4
    ,   south:
     {
         initClosed:				true
    }

//    ,   north__maskContents:  true
//    ,   north__maskObjects:   true
    ,   north__onresize: northOnresize    // zindex stuff from here - not sure what is does - maybe useful with resize issue http://layout.jquery-dev.net/changelog.cfm
    //    ,   zIndex:                      null        // the PANE zIndex - resizers and masks will be +1
    ////  DO NOT CHANGE the zIndex values below unless you clearly understand their relationships
    //    ,   zIndexes: {                              // set _default_ z-index values here...
    //        pane_normal:             0           // normal z-index for panes
    //        ,   content_mask:            1           // applied to overlays used to mask content INSIDE panes during resizing
    //        ,   resizer_normal:          2           // normal z-index for resizer-bars
    //        ,   pane_sliding:            100         // applied to *BOTH* the pane and its resizer when a pane is 'slid open'
    //        ,   pane_animate:            1000        // applied to the pane when being animated - not applied to the resizer
    //        ,   resizer_drag:            10000       // applied to the CLONED resizer-bar when being 'dragged'
    //}
    };

//layoutSettings_Inner.options.north.onresize = function () { alert ("in here hk"); }


/*
 *#######################
 * OUTER LAYOUT SETTINGS
 *#######################
 *
 * This configuration illustrates how extensively the layout can be customized
 * ALL SETTINGS ARE OPTIONAL - and there are more available than shown below
 *
 * These settings are set in 'sub-key format' - ALL data must be in a nested data-structures
 * All default settings (applied to all panes) go inside the defaults:{} key
 * Pane-specific settings go inside their keys: north:{}, south:{}, center:{}, etc
 */
var layoutSettings_Outer = {
    name: "outerLayout" // NO FUNCTIONAL USE, but could be used by custom code to 'identify' a layout
    // options.defaults apply to ALL PANES - but overridden by pane-specific settings
    ,	defaults: {
        size:					"auto"
        ,	minSize:				50
        ,	paneClass:				"pane" 		// default = 'ui-layout-pane'
        ,	resizerClass:			"resizer"	// default = 'ui-layout-resizer'
        ,	togglerClass:			"toggler"	// default = 'ui-layout-toggler'
        ,	buttonClass:			"button"	// default = 'ui-layout-button'
        ,	contentSelector:		".content"	// inner div to auto-size so only it scrolls, not the entire pane!
        ,	contentIgnoreSelector:	"span"		// 'paneSelector' for content to 'ignore' when measuring room for content
        ,	togglerLength_open:		35			// WIDTH of toggler on north/south edges - HEIGHT on east/west edges
        ,	togglerLength_closed:	35			// "100%" OR -1 = full height
        ,	hideTogglerOnSlide:		true		// hide the toggler when pane is 'slid open'
        ,	togglerTip_open:		"Close This Pane"
        ,	togglerTip_closed:		"Open This Pane"
        ,	resizerTip:				"Resize This Pane"
        //	effect defaults - overridden on some panes
        ,	fxName:					"slide"		// none, slide, drop, scale
        ,	fxSpeed_open:			000
        ,	fxSpeed_close:			000
        ,	fxSettings_open:		{ easing: "easeInQuint" }
        ,	fxSettings_close:		{ easing: "easeOutQuint" }
    }

//      outer north 1b/4
    ,	north: {
        spacing_open:			    1			// cosmetic spacing
        ,	togglerLength_open:		0			// HIDE the toggler button
        ,	togglerLength_closed:	-1			// "100%" OR -1 = full width of pane
        ,	resizable: 				false
        ,	slidable:				false
        //	override default effect
        ,	fxName:					"none"
    }

//      outer south 2b/4
    ,	south: {
        maxSize:				200
        ,	spacing_closed:			0			// HIDE resizer & toggler when 'closed'
        ,	slidable:				false		// REFERENCE - cannot slide if spacing_closed = 0
        ,	initClosed:				true
        //	CALLBACK TESTING...
//            ,	onhide_start:			function () { return confirm("START South pane hide \n\n onhide_start callback \n\n Allow pane to hide?"); }
//            ,	onhide_end:				function () { alert("END South pane hide \n\n onhide_end callback"); }
//            ,	onshow_start:			function () { return confirm("START South pane show \n\n onshow_start callback \n\n Allow pane to show?"); }
//            ,	onshow_end:				function () { alert("END South pane show \n\n onshow_end callback"); }
//            ,	onopen_start:			function () { return confirm("START South pane open \n\n onopen_start callback \n\n Allow pane to open?"); }
//            ,	onopen_end:				function () { alert("END South pane open \n\n onopen_end callback"); }
//            ,	onclose_start:			function () { return confirm("START South pane close \n\n onclose_start callback \n\n Allow pane to close?"); }
//            ,	onclose_end:			function () { alert("END South pane close \n\n onclose_end callback"); }
//            ,	onresize_start:			function () { return confirm("START South pane resize \n\n onresize_start callback \n\n Allow pane to be resized?)"); }
//            ,	onresize_end:			function () { alert("END South pane resize \n\n onresize_end callback \n\n NOTE: onresize_start event was skipped."); }
    }

//      outer west 3b/4
    ,	west: {
        size:					300
        ,	spacing_closed:			21			// wider space when closed
        ,	togglerLength_closed:	21			// make toggler 'square' - 21x21
        ,	togglerAlign_closed:	"top"		// align to top of resizer
        ,	togglerLength_open:		0			// NONE - using custom togglers INSIDE west-pane
        ,	togglerTip_open:		"Close West Pane"
        ,	togglerTip_closed:		"Open West Pane"
        ,	resizerTip_open:		"Resize West Pane"
        ,	slideTrigger_open:		"click" 	// default
        ,	initClosed:				true
        //	add 'bounce' option to default 'slide' effect
        ,	fxSettings_open:		{ easing: "easeOutBounce" }
    }

//      outer east 4b/4
    ,	east: {
        size:					300
        ,	spacing_closed:			21			// wider space when closed
        ,	togglerLength_closed:	21			// make toggler 'square' - 21x21
        ,	togglerAlign_closed:	"top"		// align to top of resizer
        ,	togglerLength_open:		0 			// NONE - using custom togglers INSIDE east-pane
        ,	togglerTip_open:		"Close East Pane"
        ,	togglerTip_closed:		"Open East Pane"
        ,	resizerTip_open:		"Resize East Pane"
        ,	slideTrigger_open:		"mouseover"
        ,	initClosed:				true
        //	override default effect, speed, and settings
        ,	fxName:					"drop"
        ,	fxSpeed:				"normal"
        ,	fxSettings:				{ easing: "" } // nullify default easing
    }
//      outer center
    ,	center: {
        paneSelector:			"#mainContent" 			// sample: use an ID to select pane instead of a class
        ,	minWidth:				200
        ,	minHeight:				200
    }
};
