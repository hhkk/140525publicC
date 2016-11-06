import com.ustodo.UserRole
import com.ustodo.utilg.O
import com.ustodo.SecRole
import com.ustodo.SecUser
//CONFIGGROOVY
class BootStrap {

    //def springSecurityService

    def init = { servletContext ->
        // encode the password only if you're using an older version of the
        // spring-security-core plugin and      you don't have encoding logic in
        // your "SecUser" domain class:
        // String password = springSecurityService.encodePassword('password')
        String password = 'password'

        O.o "hbk in bootstrap init"
        def roleAdmin = new SecRole(authority: 'ROLE_ADMIN').save()
        def roleUser = new SecRole(authority: 'ROLE_USER').save()


        UserRole.create new SecUser(username: 'user', password: password, enabled: true).save(), roleUser
        UserRole.create new SecUser(username: 'ckckck', password: 'iii', enabled: true).save(), roleUser
        UserRole.create new SecUser(username: 'bkon', password: 'roundhill', enabled: true).save(), roleUser
        UserRole.create new SecUser(username: 'mkon', password: 'mkon', enabled: true).save(), roleUser
        UserRole.create new SecUser(username: 'zk', password: 'kz', enabled: true).save(), roleUser
        UserRole.create new SecUser(username: 'lb', password: 'lb', enabled: true).save(), roleUser
        UserRole.create new SecUser(username: 'yy', password: 'yy', enabled: true).save(), roleUser
        UserRole.create new SecUser(username: 'guest', password: 'guest1', enabled: true).save(), roleUser
        UserRole.create new SecUser(username: 'unittest', password: 'unittest', enabled: true).save(), roleUser

        def admin = new SecUser(username: 'admin', password: password, enabled: true).save()

        UserRole.create admin, roleUser
        UserRole.create admin, roleAdmin, true
    }
}
