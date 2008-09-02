
class TeamPairPlayerTagLib {
	def teamPairPlayer = { attrs ->
		out << """${attrs.player.name}
<span class="ranking">(${attrs.player.rank ?: '?'})</span>
<span class="rating">${attrs.player.rating}</span>"""
	}
}
