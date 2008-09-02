class PlayerRatingStrategyController {
	static def playerRatingStrategy = new TeamRankWeightedAveragePlayerRatingStrategy()
	static def strategies = [ new TeamRankWeightedAveragePlayerRatingStrategy(), new PlayerNameLengthPlayerRatingStrategy() ]
	
    def list = {
		[ strategies:strategies.findAll { it.class != playerRatingStrategy.class }, currentStrategy:playerRatingStrategy ]
    }
	
	def select = {
		render(params)
		def strategy = strategies.find { it.class.name == params.name }
		
		if (!strategy) {
			flash.message = "Select a strategy"
	        redirect(action:list)
		} else {
			playerRatingStrategy = strategy
			flash.message = "Strategy ${strategy.class.name} selected."
			redirect(action:list, controller:'team')
		}
	}
}