

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show Player</title>
    </head>
    <body>
        <div class="nav">
        	<span class="menuButton"><g:link class="list" action="list" controller="team">Home</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New Player</g:link></span>
        </div>
        <div class="body">
            <h1>Show Player</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${player.id}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Name:</td>
                            
                            <td valign="top" class="value">${player.name}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Rating:</td>
                            
                            <td valign="top" class="value">${player.rating}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name">Rank:</td>
                            
                            <td valign="top" class="value">${player.rank}</td>
                        </tr>

                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${player?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
            <h1>Teams Player Belongs To</h1>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                   	        <th>Team</th>
                   	        <th>Rank</th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${teamList}" status="i" var="team">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        	<td><g:link action="show" id="${team.id}">${team.toString()?.encodeAsHTML()}</g:link></td>
                            <td>${team.rank?.encodeAsHTML()}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <h1>Rating Explanation</h1>
            <div class="list">
                <table>
                    <tbody>
                    <g:each in="${ratingExplain}" var="explain">
                        <tr>
                        	<td>${explain.encodeAsHTML()}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
