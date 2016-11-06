package com.ustodo.todo

import com.ustodo.todo.TodoController
import com.ustodo.utilg.Profiler
import grails.converters.JSON
import com.ustodo.utilg.O
import grails.test.mixin.TestFor
import grails.test.mixin.web.ControllerUnitTestMixin
import grails.test.mixin.TestMixin

//http://grails.org/doc/latest/guide/testing.html#9.1 Unit Testing
 /**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */

//@TestMixin(ControllerUnitTestMixin)
@TestFor(TodoController)
//@TestMixin([GrailsUnitTestMixin, ControllerUnitTestMixin, DomainClassUnitTestMixin])

class Test121118_JSONTests_GroovyobjToJSONobjTest extends GroovyTestCase {

//    @TestFor(SimpleController)
    void testDummy()
    {
        assert(true)
    }

    void test1_whyNoIdInJSONObject() {
        try
        {
            Profiler.start("unittest1", true);


            if (true)
            {
                params.sort = "name";
                params.max = 20;

                controller.index ();

                //O.oNoFilter("ffdffd:" + response.getText());
                O.oNoFilter("0model:" + model);
                org.codehaus.groovy.grails.plugins.testing.GrailsMockHttpServletRequest x =
                    (org.codehaus.groovy.grails.plugins.testing.GrailsMockHttpServletRequest) model;
                O.oNoFilter("0.5 x.getJSON():" + x.getJSON());

                O.oNoFilter("1model.keySet():" + model.keySet());
                O.oNoFilter("2model.request:" + model.request);
                O.oNoFilter("2.5model.request:" + model.getAt("count"));
                O.oNoFilter("3model.request.keyset:" + model.request.keyset);
                O.oNoFilter("3.5model.request.keyset:" + model.request.keyset);
                O.oNoFilter("3.6model.request.getServletPath:" + model.request.getServletPath());
                O.oNoFilter("3.7model.request.getDispatcherType:" + model.request.getDispatcherType());
                assertTrue ("3.8model.q == '*'", (model.q == '*'));
                assertTrue ("4model.keySet().q == '*'", (model.keySet().q == '*'));
                assertTrue ("5model.keySet().q", model.keySet().q);
            }
            else
            {
                request.method = "POST"
                request.makeAjaxRequest()
                controller.getPage()
            }

            // see http://grails.org/doc/latest/guide/testing.html#9.1 Unit Testing
            //            request.method = "POST"
            //            request.makeAjaxRequest()
            //            controller.getPage()

            //O.oNoFilter("ffdffd:" + response.getContentAsString());

            // org.codehaus.groovy.grails.plugins.testing.GrailsMockHttpServletResponse
            O.oNoFilter("retuned response.getClass():" + response.getClass());

            //            try {
            //                String dd = new JSON(map.alFileLines[0]).toString();
            //                O.o ("JSON1:"+dd);
            //            }
            //            catch ( Exception e)
            //            {
            //                O.or "error in unit test", e
            //            }
            //
            //            try {
            //                String dd2 = new JSON(map.alFileLines).toString();
            //                O.o ("JSON2:"+dd2);
            //            }
            //            catch ( Exception e)
            //            {
            //                O.or "error2 in unit test", e
            //            }
            //
            //            try {
            //                render(contentType: 'text/json', text: map.alFileLines as JSON)
            //            }
            //            catch ( Exception e)
            //            {
            //                O.or "error3 in unit test", e
            //            }
            //
            //
            //            try {
            //                render(contentType: 'text/json', text: map.alFileLines[0] as JSON)
            //            }
            //            catch ( Exception e)
            //            {
            //                O.or "error4 in unit test", e
            //            }
            //
            //
            //
            //            //def sd = render map.alFileLines as JSON
            //            //O.o ("sd:"+sd);
        } catch ( Exception e)
        {
            O.or "error5 in unit test", e
            throw e;
        }
        finally {
            O.o "finally"
        }
    }


}
