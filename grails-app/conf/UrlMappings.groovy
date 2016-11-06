class UrlMappings {

    static mappings = {


        //"/"(controller:"todo", action:"index") // http://146.115.68.118:8084/ustodo   this connects
        //"/"(view:"/i11ComplexUsToDo")
        //"/"(redirect: [controller:"todo", action:"index"])
        "/$controller/$action?/$id?"{
            constraints {
                // apply constraints here
            }
        }
//        "/"(uri: "ustodo.com/todo")
        "/"(controller:"todo", action:"index") // http://146.115.68.118:8084/ustodo/product   this connects
        "/todo2"(controller:"todo2", action:"index") // http://146.115.68.118:8084/ustodo/product   this connects
        //"/"(action: "redirect", controller: "todo") // from http://stackoverflow.com/questions/10029971/grails-url-mapping-url-rewriting

//        "/"(controller:"todo", action:"i2") // http://146.115.68.118:8084/ustodo   this connects
        //"/"(controller:"todo", action:"i2") // http://146.115.68.118:8084/ustodo   this connects
        //"/"(view:"/index")
        "/product"(controller:"todo", action:"index") // http://146.115.68.118:8084/ustodo/product   this connects
        "/login/$action?"(controller: "login")
        "/logout/$action?"(controller: "logout")
        "500"(view:'/error')

        "/login/auth" (controller:'openId', action:'auth')

        "/login/openIdCreateAccount" {
            controller = 'openId'
            action = 'createAccount'
        }

        "500"(view:'/error')

        // openid from http://grails-plugins.github.com/grails-spring-security-openid/docs/manual/guide/3.%20Tutorials.html
        //
        //        "/login/auth" (
        //            controller : 'openId',
        //            action : 'auth')
        //
        //
        //        "/login/openIdCreateAccount" {
        //            controller = 'openId'
        //            action = 'createAccount'
        //        }
        //
        //        "/$controller/$action?/$id?"{
        //            constraints {
        //                // apply constraints here
        //            }
        //        }
        //        "/"(view:"/index")
        //        "500"(view:'/error')
        //
        //




    }
}


