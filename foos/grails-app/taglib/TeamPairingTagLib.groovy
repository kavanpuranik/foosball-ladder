
class TeamPairingTagLib {
	def teamPairing = { attrs ->
		out << """ ${attrs.team.name} <span class="ranking">(${attrs.team.rank ?: '?'})</span> """
	}
	
//	def teamWinButton = { attrs ->
//		out << """
//		<form method="post" action="/foos/team/gameResult">
//			<input type="hidden" name="" value=""/>
//			<span class="button"><input class="save" type="submit" value="Won" /></span>
//		</form>
//		"""
//	}
}
