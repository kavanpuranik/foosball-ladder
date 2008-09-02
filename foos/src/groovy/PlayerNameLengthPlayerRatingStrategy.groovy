
class PlayerNameLengthPlayerRatingStrategy {
	def computePlayerRating(player){
        return player.name.length();
    } 
	
	def explainRating(player) {
		def steps = []
		
		steps << "Player's name is ${player.name.length()} letters long."
        
        return steps;
	}
}