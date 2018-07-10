https://projects.spring.io/spring-security/


https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/


Spring Security 5.0.0的主要亮点在于它只需要最小化的JDK 8、反应式安全特性、OAuth 2.0（OIDC）和现代密码存储。

在Spring Security 5.0.0之前，密码是明文保存，十分不安全。因为这一次发布的是大版本，所以我们决定使用更安全的密码存储方式。 我们有幸与密码存储专家John Steven合作，他是OWASP密码存储手册的主要作者。Spring Security现在默认提供的是最新的密码存储方式。当然，我们也可以使用其他方式来存储密码。

现在让我们来看看增强的方法级别的安全（示例来自Spring Security网站）。
可以使用@EnableGlobalMethodSecurity来启用基于注解的安全，只要这个类也使用了@Configuration。例如：

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class MethodSecurityConfig {
// ...
}
然后就可以实现方法级别的安全：

public interface BankService {

@Secured("IS_AUTHENTICATED_ANONYMOUSLY")
public Account readAccount(Long id);

@Secured("IS_AUTHENTICATED_ANONYMOUSLY")
public Account[] findAccounts();

@Secured("ROLE_TELLER")
public Account post(Account account, double amount);
}
有时候可能需要比GlobalMethodSecurity更灵活的方式，那么可以通过扩展GlobalMethodSecurityConfiguration来实现自定义安全策略，并在这个类上面加上@EnableGlobalMethodSecurity：

@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {
		// ... create and return custom MethodSecurityExpressionHandler ...
		return expressionHandler;
	}
}
Reactor项目的反应式流有一个特性，它支持将等待中的线程分配给新的任务作业，这就给实现ThreadLocal类型映射带来了新的挑战。Reactor为此提供了Context API，它是一种反应式的ThreadLocal map。

在5.0.0版本里，可以通过ReactiveSecurityContextHolder.getContext()方法访问反应式Context，并在方法调用过程中使用token（以及其他应用程序相关数据）。













