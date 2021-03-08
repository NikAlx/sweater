<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
Add new user
<br>
${message!}
<@l.login "/registration" />
<a href="/login">Login existing user</a>
</@c.page>