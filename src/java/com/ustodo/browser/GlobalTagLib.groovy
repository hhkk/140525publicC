package com.ustodo.browser

/**
 * Created with IntelliJ IDEA.
 * SecUser: hkon
 * Date: 4/24/12
 * Time: 3:57 AM
 * To change this template use File | Settings | File Templates.
 */
class GlobalTagLib {

    static namespace = "wthr"
    def userAgentIdentService
    def isMsie = { attrs, body ->
        if (userAgentIdentService.isMsie()) {
            out << body()
        }
    }

    def isFirefox = { attrs, body ->
        if (userAgentIdentService.isFirefox()) {
            out << body()
        }
    }

    def isChrome = { attrs, body ->
        if (userAgentIdentService.isChrome()) {
            out << body()
        }
    }


    def isBlackberry = { attrs, body ->
        if (userAgentIdentService.isBlackberry()) {
            out << body()
        }
    }
}
