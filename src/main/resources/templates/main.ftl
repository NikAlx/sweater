<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<div>
    <@l.logout />
</div>
<div>
    <form method="post" action="/main">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="text" name="text" placeholder="Input message"/>
        <input type="text" name="tag" placeholder="Tag">
        <button type="submit">Add message</button>
    </form>
</div>
<div>All messages</div>
<div>
    <form method="get" action="/main">
        <input type="text" name="tagFilter" placeholder="tag filter" value="${tagFilter!}">
        <button type="submit">Search</button>
    </form>
</div>
<div>
    <form method="post" action="/delete">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="text" name="deletedId" placeholder="id for delete">
        <button type="submit">delete</button>
    </form>
</div>
<#list messages as message>
    <div>
        <b>${message.id}</b>
        <span>${message.text}</span>
        <i>${message.tag}</i>
        <strong>${message.authorName}</strong>
    </div>
<#else>
No messages
</#list>
</@c.page>