package com.ustodo.browser

import org.springframework.web.context.request.RequestContextHolder as RCH

/**
 * Created with IntelliJ IDEA.
 * SecUser: hkon
 * Date: 4/24/12
 * Time: 3:53 AM
 * To change this template use File | Settings | File Templates.
 */

class WebTierService {

    boolean transactional = false

    static scope = "prototype"

    def getRequest()
    {
        return RCH.currentRequestAttributes().currentRequest
    }

    def getSession()
    {
        return RCH.currentRequestAttributes().session
    }

}
