

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Choose The Players</title>
        <resource:autoComplete skin="default" />  
    </head>
    <body>
        <div class="nav">
        	<span class="menuButton"><g:link class="list" action="list" controller="team">Home</g:link></span>
            <span class="menuButton"><g:link class="create" action="create" controller="player">New Player</g:link></span>
        </div>
        <div class="body">
            <h1>Choose The Players</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${team}">
            <div class="errors">
                <g:renderErrors bean="${team}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="chooseTeamMembers" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
	                        <tr class="prop">
	                            <td valign="top" class="name">
	                                <label for="winner">Players:</label>
	                            </td>
	                            <td valign="top" class="value">
	                                <g:select optionKey="id" optionValue="name" from="${Player.list()}" name="member1" value="${member1}" noSelection="${['null':'Select One...']}"></g:select>
	                            </td>
	                            <td valign="top" class="value">
	                                <g:select optionKey="id" optionValue="name" from="${Player.list()}" name="member2" value="${member2}" noSelection="${['null':'Select One...']}"></g:select>
	                            </td>
	                            <td valign="top" class="value">
	                                <g:select optionKey="id" optionValue="name" from="${Player.list()}" name="member3" value="${member3}" noSelection="${['null':'Select One...']}"></g:select>
	                            </td>
	                            <td valign="top" class="value">
	                                <g:select optionKey="id" optionValue="name" from="${Player.list()}" name="member4" value="${member4}" noSelection="${['null':'Select One...']}"></g:select>
	                            </td>
	                        </tr> 
	                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="edit" type="submit" value="Select" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
