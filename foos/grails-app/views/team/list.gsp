

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>FOOSBALL ladder</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="create" action="playGame">Play a Game</g:link></span>
            <span class="menuButton"><g:link class="list" action="list" controller="playerRatingStrategy">Change Rating System</g:link></span>
        </div>
        <div class="body">
            <h1>Foosball Ladder</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div>Select player for a rating breakdown.<br/>&nbsp;</div>
            <div class="list" style="float:left;">
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
            <div style="float:left;">&nbsp;</div>
            <div class="list" style="float:clear;">
                <table>
                    <thead>
                        <tr>
                   	        <th>Player</th>
                   	        <th>Rank</th>
                   	        <th>Rating</th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${playerList}" status="i" var="player">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                        	<td><g:link controller="player" action="show" id="${player.id}">${player.name?.encodeAsHTML()}</g:link></td>
                        
                            <td>${player.rank?.encodeAsHTML()}</td>
                        
                        	<td>${player.rating?.encodeAsHTML()}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
