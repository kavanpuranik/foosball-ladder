
class TeamRankWeightedAveragePlayerRatingStrategy {
	def computePlayerRating(player){
        def rating = 0;
        
        def teams = Team.findAllByPlayerOneOrPlayerTwo(player, player)
        teams.each { team ->
            rating = rating + team.rank
        }
        
        if (teams.size == 0){
        	rating = Team.list().size
        } else {
            def teamCount = teams.size
            rating = (rating / teamCount) * ((teamCount + 1)/teamCount)
        }
        
        return rating;
    } 
	
	def explainRating(player) {
		def steps = []
		
		def rating = 0;
        
        def teams = Team.findAllByPlayerOneOrPlayerTwo(player, player)
        def teamCount = teams.size
        
        teams.each { team ->
            rating = rating + team.rank
        }
        
        if (teamCount) {
        	steps << "The average rank of the ${teamCount} teams is ${rating/teamCount}:"
        }
        
        teams.each { team ->
        	def diff = team.rank - (rating/teamCount)
        	steps << "$team.name has a rank of $team.rank (${diff > 0 ? '+' : ''}$diff from your team average)"
        }
        
        if (teams.size == 0){
        	rating = Team.list().size
        } else {
            def finalRating = (rating / teamCount) * ((teamCount + 1)/teamCount)
            steps << "The weighted average is ${finalRating} (${rating/teamCount} * ${teamCount+1} / $teamCount)"
        }
        
        return steps;
	}
}