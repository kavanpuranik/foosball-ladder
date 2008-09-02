<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>So, Who Won?</title>
    </head>
    <body>
        <div class="nav">
        	<span class="menuButton"><g:link class="list" action="list" controller="team">Home</g:link></span>
        </div>
        <div class="body">
            <h1>So, Who Won?</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
                <div class="dialog">
                    <table cellspacing="0" cellpadding="3">
                    	<tbody>
			              <tr>
			                <td colspan="10" style="vertical-align: middle; text-align:center;">Closest Match</td>
			              </tr>
			              
<g:each in="${teamPairings}" status="i" var="teamPairing">
			              <tr>
			                <td rowspan="4" style="vertical-align: middle">
			                <g:form action="gameResult" method="post">
			                	<input type="hidden" name="winningTeam.player1" value="${teamPairing[0].playerOne.id}"/>
			                	<input type="hidden" name="winningTeam.player2" value="${teamPairing[0].playerTwo.id}"/>
			                	<input type="hidden" name="losingTeam.player1" value="${teamPairing[1].playerOne.id}"/>
			                	<input type="hidden" name="losingTeam.player2" value="${teamPairing[1].playerTwo.id}"/>
			                	<div class="buttons">
								<span class="button"><input class="save" type="submit" value="Won" onclick="return confirm('${teamPairing[0].name} beat ${teamPairing[1].name}?\n\nAre you sure?');"/></span>
								</div>
			                </g:form>
			                </td>
			                <td colspan="5"></td>
			                <td rowspan="4" style="vertical-align: middle">
			                <g:form action="gameResult" method="post">
			                	<input type="hidden" name="winningTeam.player1" value="${teamPairing[1].playerOne.id}"/>
			                	<input type="hidden" name="winningTeam.player2" value="${teamPairing[1].playerTwo.id}"/>
			                	<input type="hidden" name="losingTeam.player1" value="${teamPairing[0].playerOne.id}"/>
			                	<input type="hidden" name="losingTeam.player2" value="${teamPairing[0].playerTwo.id}"/>
								<div class="buttons">
								<span class="button"><input class="save" type="submit" value="Won" onclick="return confirm('${teamPairing[1].name} beat ${teamPairing[0].name}?\n\nAre you sure?');"/></span>
								</div>
			                </g:form>			                
			                </td>
			              </tr>

			              <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			                <td colspan="2" class="teamHeader"><g:teamPairing team="${teamPairing[0]}" /></td>
			                <td>vs.</td>
			                <td colspan="2" class="teamHeader"><g:teamPairing team="${teamPairing[1]}" /></td>
			              </tr>
			              
			              <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			                <td><g:teamPairPlayer player="${teamPairing[0].playerOne}" /></td>
			                <td><g:teamPairPlayer player="${teamPairing[0].playerTwo}" /></td>
			                <td></td>
			                <td><g:teamPairPlayer player="${teamPairing[1].playerOne}" /></td>
			                <td><g:teamPairPlayer player="${teamPairing[1].playerTwo}" /></td> 
			              </tr>
			              
			              <tr>
			                <td colspan="5" style="border-bottom: 1px solid black;"></td>
			              </tr>
</g:each>			 
			              <tr>
			                <td colspan="10" style="vertical-align: middle; text-align:center;">Unfairest Match</td>
			              </tr>
             
			            </tbody>
		            </table>
                </div>
        </div>
    </body>
</html>
